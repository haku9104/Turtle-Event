package com.codecool.turtleevent.service;

import com.codecool.turtleevent.model.*;
import com.codecool.turtleevent.model.dto.*;
import com.codecool.turtleevent.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private EventService eventService;
    private UserService userService;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<MessageDTO> getAll() {
        List<Message> messages = messageRepository.findAll();
        return convertToDTO(messages);
    }

    public List<MessageDTO> getAllByEvent(IdDTO eventId) {
        Event event = eventService.findEventById(eventId.getId());
        List<Message> messages = messageRepository.findAllByEvent(event);
        return convertToDTO(messages);
    }

    public List<MessageDTO> getAllByUser(IdDTO userId) {
        User user = userService.findUserById(userId.getId());
        List<Message> messages = messageRepository.findAllByAuthor(user);
        return convertToDTO(messages);
    }

    /** Converts a list of Messages into a list of MessageDTOs */
    private List<MessageDTO> convertToDTO(List<Message> messages) {
        return messages.stream()
                .map(message -> new MessageDTO(message.getId(),
                        message.getEvent().getId(),
                        message.getAuthor().getId(),
                        message.getText(),
                        message.getPosted()))
                .collect(Collectors.toList());
    }

    public RestResponseDTO addMessage(MessageDTO message) {
        try {
            Event event = eventService.findEventById(message.getEventId());
            User user = userService.findUserById(message.getAuthorId());
            if (event != null && user != null) {
                Message newMessage = new Message(
                        user,
                        event,
                        message.getText(),
                        LocalDateTime.now());
                messageRepository.save(newMessage);
                return new RestResponseDTO(true, "Message created successfully!");
            } else if (event == null) {
                throw new ToBringNotFoundException("This event does not exist!");
            } else {
                throw new UserNotFoundException("This user does not exist!");
            }
        } catch (ToBringNotFoundException | UserNotFoundException e) {
            return new RestResponseDTO(false, "Message creation failed! " + e);
        }

    }

    @Transactional
    public RestResponseDTO update(MessageDTO newMessage) {
        Optional<Message> message = messageRepository.findById(newMessage.getId());
        if (message.isPresent()) {
            if (newMessage.getText() != null) message.get().setText(newMessage.getText());
            return new RestResponseDTO(true, "Message updated successfully!");
        }
        return new RestResponseDTO(false, "Message failed to update!");
    }

    public RestResponseDTO delete(IdDTO id) {
        Message message = messageRepository.findById(id.getId()).orElse(null);
        if (message != null){
            messageRepository.delete(message);
            return new RestResponseDTO(true, "Message deleted.");
        }
        return new RestResponseDTO(false, "Message can't be deleted.");
    }
}
