package com.codecool.turtleevent.service;

import com.codecool.turtleevent.model.*;
import com.codecool.turtleevent.model.dto.BringerDTO;
import com.codecool.turtleevent.model.dto.DoerDTO;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.repository.BringerRepository;
import com.codecool.turtleevent.repository.DoerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoerService {

    private DoerRepository doerRepository;
    private ToDoService toDoService;
    private UserService userService;

    @Autowired
    public void setDoerRepository(DoerRepository doerRepository) {
        this.doerRepository = doerRepository;
    }

    @Autowired
    public void setToDoService(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private List<DoerDTO> convertToDTO(List<Doer> doers) {
        List<DoerDTO> DoerDTOList = doers.stream()
                .map(doer -> new DoerDTO(doer.getId(),
                        doer.getToDo().getId(),
                        doer.getUser().getId(),
                        doer.getAttachment()))
                .collect(Collectors.toList());
        return DoerDTOList;
    }

    public List<DoerDTO> getAll() {
        List<Doer> doers = doerRepository.findAll();
        return convertToDTO(doers);
    }

    public List<DoerDTO> getAllByToDo(IdDTO toDoId) {
        ToDo toDo = toDoService.findById(toDoId.getId());
        if (toDo != null) {
            List<Doer> doers = doerRepository.findAllByToDo(toDo);
            return convertToDTO(doers);
        }
        return null;
    }

    public List<DoerDTO> getAllByUser(IdDTO userId) {
        User user = userService.findUserById(userId.getId());
        if (user != null) {
            List<Doer> doers = doerRepository.findAllByUser(user);
            return convertToDTO(doers);
        }
        return null;
    }

    public RestResponseDTO add(DoerDTO doer) {
        try {
            ToDo toDo = toDoService.findById(doer.getToDoId());
            User user = userService.findUserById(doer.getUserId());
            if (toDo != null && user != null) {
                Doer newDoer = new Doer(
                        toDo,
                        user,
                        doer.getAttachment(),
                        LocalDateTime.now());
                doerRepository.save(newDoer);
                return new RestResponseDTO(true, "Doer created successfully!");
            } else if (toDo == null) {
                throw new ToDoNotFoundException("This 'to do' does not exist!");
            } else {
                throw new UserNotFoundException("This user does not exist!");
            }
        } catch (ToDoNotFoundException | UserNotFoundException e) {
            return new RestResponseDTO(false, "Bringer creation failed! " + e);
        }
    }

    @Transactional
    public RestResponseDTO update(DoerDTO newDoer) {
        Optional<Doer> doer = doerRepository.findById(newDoer.getId());
        if (doer.isPresent()) {
            if (newDoer.getAttachment() != null) doer.get().setAttachment(newDoer.getAttachment());
            return new RestResponseDTO(true, "Doer updated successfully!");
        }
        return new RestResponseDTO(false, "Doer failed to update!");
    }

    public RestResponseDTO delete(IdDTO id) {
        Doer doer = doerRepository.findById(id.getId()).orElse(null);
        if (doer != null){
            doerRepository.delete(doer);
            return new RestResponseDTO(true, "Doer deleted.");
        }
        return new RestResponseDTO(false, "Doer can't be deleted.");
    }
}
