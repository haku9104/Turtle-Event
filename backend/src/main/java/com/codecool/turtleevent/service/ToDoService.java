package com.codecool.turtleevent.service;

import com.codecool.turtleevent.model.Doer;
import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.ToBring;
import com.codecool.turtleevent.model.ToDo;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.ToBringDTO;
import com.codecool.turtleevent.model.dto.ToDoDTO;
import com.codecool.turtleevent.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    private ToDoRepository toDoRepository;
    private EventService eventService;

    @Autowired
    public void setToDoRepository(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public ToDo findById(Long id){
        Optional<ToDo> toDo = toDoRepository.findById(id);
        return toDo.orElse(null);
    }

    public List<ToDoDTO> getAll() {
        List<ToDo> toDos = toDoRepository.findAll();
        return convertToDTO(toDos);
    }

    public List<ToDoDTO> getAllByEvent(IdDTO eventId) {
        Event event = eventService.findEventById(eventId.getId());
        if (event != null) {
            List<ToDo> toDosByEvent = toDoRepository.findAllByEvent(event);
            return convertToDTO(toDosByEvent);
        }
        return null;
    }

    /** Converts a list of ToDos into a list of ToDoDTOs */
    private List<ToDoDTO> convertToDTO(List<ToDo> toDos) {
        return toDos.stream()
                .map(toDo -> new ToDoDTO(toDo.getId(),
                        toDo.getEvent().getId(),
                        toDo.getTitle(),
                        toDo.getDoers().stream()
                                .map(Doer::getId)
                                .collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public RestResponseDTO add(ToDoDTO toDo) {
        try {
            Event event = eventService.findEventById(toDo.getEventId());
            if (event != null) {
                ToDo newToDo = new ToDo(
                        event,
                        toDo.getTitle(),
                        LocalDateTime.now());
                toDoRepository.save(newToDo);
                return new RestResponseDTO(true, "'to do' added successfully!");
            } else {
                throw new EventNotFoundException("This event does not exist!");
            }

        } catch (Exception e) {
            return new RestResponseDTO(false, "Failed to add!" + e);
        }
    }

    @Transactional
    public RestResponseDTO update(ToDoDTO newToDo) {
        Optional<ToDo> toDo = toDoRepository.findById(newToDo.getId());
        if(toDo.isPresent()) {
            if(newToDo.getTitle() != null) toDo.get().setTitle(newToDo.getTitle());
            return new RestResponseDTO(true, "'To do' updated!");
        }
        return new RestResponseDTO(false, "'To do' failed to update!");
    }

    public RestResponseDTO delete(IdDTO id) {
        Optional<ToDo> toDo = toDoRepository.findById(id.getId());
        if(toDo.isPresent()) {
            toDoRepository.delete(toDo.get());
            return new RestResponseDTO(true, "'To do' deleted successfully!");
        }
        return new RestResponseDTO(false, "Failed to delete 'To do'!");
    }
}
