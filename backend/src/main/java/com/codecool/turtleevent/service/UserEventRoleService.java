package com.codecool.turtleevent.service;

import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.User;
import com.codecool.turtleevent.model.UserEventRole;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.UserEventRoleDTO;
import com.codecool.turtleevent.repository.UserEventRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEventRoleService {

    private UserEventRoleRepository userEventRoleRepository;

    private EventService eventService;
    private UserService userService;

    @Autowired
    public void setUserEventRoleRepository(UserEventRoleRepository userEventRoleRepository) {
        this.userEventRoleRepository = userEventRoleRepository;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<UserEventRoleDTO> getAllByEvent(IdDTO eventId) {
        Event event = eventService.findEventById(eventId.getId());
        List<UserEventRole> userEventRoles = userEventRoleRepository.findAllByEvent(event);
        return convertToDTO(userEventRoles);

    }

    public List<UserEventRoleDTO> getAllByUser(IdDTO userId) {
        User user = userService.findUserById(userId.getId());
        List<UserEventRole> userEventRoles = userEventRoleRepository.findAllByUser(user);
        return convertToDTO(userEventRoles);
    }

    public List<UserEventRoleDTO> getAll() {
        List<UserEventRole> userEventRoles = userEventRoleRepository.findAll();
        return convertToDTO(userEventRoles);
    }

    /** Converts a list of UserEventRole into a list of UserEventRoleDTO */
    private List<UserEventRoleDTO> convertToDTO(List<UserEventRole> userEventRoles){
        return userEventRoles.stream()
                .map(t -> new UserEventRoleDTO(
                        t.getId(),
                        t.getUser().getId(),
                        t.getEvent().getId(),
                        t.getRole()))
                .collect(Collectors.toList());
    }


    public RestResponseDTO addUserEventRole(UserEventRoleDTO userEventRole) {
        User user = userService.findUserById(userEventRole.getUserId());
        Event event = eventService.findEventById(userEventRole.getEventId());

        if(user != null && event != null) {
            userEventRoleRepository.save(new UserEventRole(user, event, userEventRole.getRole(), LocalDateTime.now()));
            return new RestResponseDTO(true, "User - event connection created!");
        }
        return new RestResponseDTO(false, "Failed to create User - event connection!");
    }

    public RestResponseDTO delete(Long id) {
        Optional<UserEventRole> userEventRole = userEventRoleRepository.findById(id);
        if(userEventRole.isPresent()) {
            userEventRoleRepository.delete(userEventRole.get());
            return new RestResponseDTO(true, "'User-Event-Role' deleted successfully!");
        }
        return new RestResponseDTO(false, "Failed to delete 'User-Event-Role'!");
    }

    public RestResponseDTO update(UserEventRoleDTO newUserEventRole) {
        Optional<UserEventRole> userEventRole = userEventRoleRepository.findById(newUserEventRole.getId());

        if (userEventRole.isPresent()) {
            try {
                userEventRole.get().setRole(newUserEventRole.getRole());
                return new RestResponseDTO(true, "The role update is successful");
            } catch (Exception e) {
                return new RestResponseDTO(false, "Not valid role type");
            }
        }
        return new RestResponseDTO(false, "Failed to update role type!");
    }
}
