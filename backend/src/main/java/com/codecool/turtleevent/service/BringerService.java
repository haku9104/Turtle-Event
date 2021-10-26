package com.codecool.turtleevent.service;

import com.codecool.turtleevent.model.Bringer;
import com.codecool.turtleevent.model.ToBring;
import com.codecool.turtleevent.model.User;
import com.codecool.turtleevent.model.dto.BringerDTO;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.ToBringDTO;
import com.codecool.turtleevent.repository.BringerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BringerService {

    private BringerRepository bringerRepository;
    private ToBringService toBringService;
    private UserService userService;

    @Autowired
    public void setBringerRepository(BringerRepository bringerRepository) {
        this.bringerRepository = bringerRepository;
    }

    @Autowired
    public void setToBringService(ToBringService toBringService) {
        this.toBringService = toBringService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<BringerDTO> getAll() {
        List<Bringer> bringers = bringerRepository.findAll();
        return convertToDTO(bringers);
    }

    public List<BringerDTO> getAllByToBring(IdDTO id) {
        ToBring toBring = toBringService.findById(id.getId());
        if (toBring != null) {
            List<Bringer> bringers = bringerRepository.findAllByToBring(toBring);
            return convertToDTO(bringers);
        }
        return null;
    }

    public List<BringerDTO> getAllByUser(IdDTO userId) {
        User user = userService.findUserById(userId.getId());
        if (user != null) {
            List<Bringer> bringers = bringerRepository.findAllByUser(user);
            return convertToDTO(bringers);
        }
        return null;
    }

    private List<BringerDTO> convertToDTO(List<Bringer> bringers) {
        List<BringerDTO> BringerDTOList = bringers.stream()
                .map(bringer -> new BringerDTO(bringer.getId(),
                        bringer.getToBring().getId(),
                        bringer.getUser().getId(),
                        bringer.getPrice(),
                        bringer.getAmount(),
                        bringer.getAttachment()))
                .collect(Collectors.toList());
        return BringerDTOList;
    }


    public RestResponseDTO add(BringerDTO bringer) {
        try {
            ToBring toBring = toBringService.findById(bringer.getToBringId());
            User user = userService.findUserById(bringer.getUserId());
            if (toBring != null && user != null) {
                Bringer newBringer = new Bringer(
                        toBring,
                        user,
                        bringer.getPrice(),
                        bringer.getAmount(),
                        bringer.getAttachment(),
                        LocalDateTime.now());
                bringerRepository.save(newBringer);
                toBringService.updateSubAmount(toBring.getId(), newBringer.getAmount());
                return new RestResponseDTO(true, "Bringer created successfully!");
            } else if (toBring == null) {
                throw new ToBringNotFoundException("This 'to bring' does not exist!");
            } else {
                throw new UserNotFoundException("This user does not exist!");
            }
        } catch (ToBringNotFoundException | UserNotFoundException e) {
            return new RestResponseDTO(false, "Bringer creation failed! " + e);
        }
    }

    @Transactional
    public RestResponseDTO update(BringerDTO newBringer) {
        Optional<Bringer> bringer = bringerRepository.findById(newBringer.getId());
        if (bringer.isPresent()) {
            if (newBringer.getAmount() != 0) bringer.get().setAmount(newBringer.getAmount());
            if (newBringer.getPrice() != 0) bringer.get().setPrice(newBringer.getPrice());
            if (newBringer.getAttachment() != null) bringer.get().setAttachment(newBringer.getAttachment());
            return new RestResponseDTO(true, "Bringer updated successfully!");
        }
        return new RestResponseDTO(false, "Bringer failed to update!");
    }

    @Transactional
    public RestResponseDTO increaseAmount(IdDTO bringerId) {
        Bringer bringer = bringerRepository.findById(bringerId.getId()).orElse(null);
        if (bringer != null){
            if (bringer.getToBring().getSubAmount() < bringer.getToBring().getTotalAmount()) {
                bringer.setAmount(bringer.getAmount() + 1);
                bringer.getToBring().setSubAmount(bringer.getToBring().getSubAmount() + 1);
                return new RestResponseDTO(true, "Increased amount by 1.");
            }
        }
        return new RestResponseDTO(false, "Can't increase amount.");
    }

    @Transactional
    public RestResponseDTO decreaseAmount(IdDTO bringerId) {
        Bringer bringer = bringerRepository.findById(bringerId.getId()).orElse(null);
        if (bringer != null){
            if (bringer.getToBring().getSubAmount() > 0)
            bringer.setAmount(bringer.getAmount() - 1);
            bringer.getToBring().setSubAmount(bringer.getToBring().getSubAmount() - 1);
            return new RestResponseDTO(true, "Decreased amount by 1.");
        }
        return new RestResponseDTO(false, "Can't decrease amount.");
    }

    @Transactional
    public RestResponseDTO changeAmount(IdDTO bringerId, int amount) {
        Bringer bringer = bringerRepository.findById(bringerId.getId()).orElse(null);
        if (bringer != null){
            if (bringer.getToBring().getSubAmount() - bringer.getAmount() + amount >= 0
                && bringer.getToBring().getSubAmount() - bringer.getAmount() + amount <= bringer.getToBring().getTotalAmount()) {
                bringer.setAmount(amount);
                bringer.getToBring().setSubAmount(bringer.getToBring().getSubAmount() - bringer.getAmount() + amount);
                return new RestResponseDTO(true, "Amount set.");
            }
        }
        return new RestResponseDTO(false, "Amount can't be set.");
    }


    public RestResponseDTO delete(IdDTO id) {
        Bringer bringer = bringerRepository.findById(id.getId()).orElse(null);
        if (bringer != null){
            bringerRepository.delete(bringer);
            return new RestResponseDTO(true, "Bringer deleted.");
        }
        return new RestResponseDTO(false, "Bringer can't be deleted.");
    }
}
