package com.codecool.turtleevent.service;

import com.codecool.turtleevent.model.Bringer;
import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.ToBring;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.ToBringDTO;
import com.codecool.turtleevent.model.dto.ToDoDTO;
import com.codecool.turtleevent.repository.ToBringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToBringService {

    private ToBringRepository toBringRepository;

    private EventService eventService;
    private BringerService bringerService;

    @Autowired
    public void setToBringRepository(ToBringRepository toBringRepository) {
        this.toBringRepository = toBringRepository;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setBringerService(BringerService bringerService) {
        this.bringerService = bringerService;
    }

    public ToBring findById(Long id){
        Optional<ToBring> toBring = toBringRepository.findById(id);
        return toBring.orElse(null);
    }

    public List<ToBringDTO> getAll() {
        List<ToBring> toBrings = toBringRepository.findAll();
        return convertToDTO(toBrings);
    }


    public List<ToBringDTO> getAllByEvent(IdDTO eventId) {
        Event event = eventService.findEventById(eventId.getId());
        if (event != null) {
            List<ToBring> toBringsByEvent = toBringRepository.findAllByEvent(event);
            return convertToDTO(toBringsByEvent);
        }
        return null;
    }

    /** Converts a list of ToBrings into a list of ToBringDTOs */
    private List<ToBringDTO> convertToDTO(List<ToBring> toBringsByEvent) {
        return toBringsByEvent.stream()
                .map(toBring -> new ToBringDTO(toBring.getId(),
                        toBring.getEvent().getId(),
                        toBring.getTitle(),
                        toBring.getComment(),
                        toBring.getSubAmount(),
                        toBring.getTotalAmount(),
                        toBring.getBringers().stream()
                                .map(Bringer::getId)
                                .collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }


    public RestResponseDTO add(ToBringDTO toBring) {
        try {
            Event event = eventService.findEventById(toBring.getEventId());
            if (event != null) {
                ToBring newToBring = new ToBring(event, toBring.getTitle(), toBring.getComment(), toBring.getTotalAmount(), LocalDateTime.now());
                toBringRepository.save(newToBring);
                return new RestResponseDTO(true, "'to bring' added successfully!");
            } else {
                throw new EventNotFoundException("This event does not exist!");
            }

        } catch (Exception e) {
            return new RestResponseDTO(false, "Failed to add!");
        }
    }


    @Transactional
    public RestResponseDTO update(ToBringDTO newToBring) {
        Optional<ToBring> toBring = toBringRepository.findById(newToBring.getId());
        if(toBring.isPresent()) {
            if(newToBring.getTitle() != null) toBring.get().setTitle(newToBring.getTitle());
            if(newToBring.getComment() != null) toBring.get().setComment(newToBring.getComment());
            // Do we get null or 0?
            if(newToBring.getTotalAmount() != 0) toBring.get().setTotalAmount(newToBring.getTotalAmount());
            return new RestResponseDTO(true, "'To bring' updated!");
            }
        return new RestResponseDTO(false, "'To bring' failed to update!");
    }

    @Transactional
    public void updateSubAmount(Long id, int amount){
        ToBring toBring = toBringRepository.findById(id).orElse(null);
        if (toBring != null) {
            toBring.setSubAmount(toBring.getSubAmount() + amount);
            System.out.println(toBring.getSubAmount() + amount);
        }

    }

    public RestResponseDTO delete(IdDTO id) {
        Optional<ToBring> toBring = toBringRepository.findById(id.getId());
        if(toBring.isPresent()) {
            toBringRepository.delete(toBring.get());
            return new RestResponseDTO(true, "'To bring' deleted successfully!");
        }
        return new RestResponseDTO(false, "Failed to delete 'To bring'!");
    }
}
