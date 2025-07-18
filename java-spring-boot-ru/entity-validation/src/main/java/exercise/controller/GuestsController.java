package exercise.controller;

import exercise.mapper.GuestMapper;
import exercise.model.Guest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.GuestRepository;
import exercise.dto.GuestDTO;
import exercise.dto.GuestCreateDTO;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/guests")
public class GuestsController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;

    @GetMapping(path = "")
    public List<GuestDTO> index() {
        var products = guestRepository.findAll();
        return products.stream()
                .map(p -> guestMapper.map(p))
                .toList();
    }

    @GetMapping(path = "/{id}")
    public GuestDTO show(@PathVariable long id) {

        var guest =  guestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Guest with id " + id + " not found"));
        var guestDto = guestMapper.map(guest);
        return guestDto;
    }

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestDTO create(@Valid @RequestBody GuestCreateDTO guestData) {
        Guest guest = new Guest();
        guest.setName(guestData.getName());
        guest.setEmail(guestData.getEmail());
        guest.setPhoneNumber(guestData.getPhoneNumber());
        guest.setClubCard(guestData.getClubCard());
        guest.setCardValidUntil(guestData.getCardValidUntil());

        // Сохранение гостя
        Guest savedGuest = guestRepository.save(guest);

        // Преобразование Guest в GuestDTO
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setId(savedGuest.getId());
        guestDTO.setName(savedGuest.getName());
        guestDTO.setEmail(savedGuest.getEmail());
        guestDTO.setPhoneNumber(savedGuest.getPhoneNumber());
        guestDTO.setClubCard(savedGuest.getClubCard());
        guestDTO.setCardValidUntil(savedGuest.getCardValidUntil());

        return guestDTO;
    }
    // END
}
