package exercise.controller;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import exercise.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO createContact(@RequestBody ContactCreateDTO contactCreateDTO) {
        // Преобразование ContactCreateDTO в сущность Contact
        Contact contact = new Contact();
        contact.setFirstName(contactCreateDTO.getFirstName());
        contact.setLastName(contactCreateDTO.getLastName());
        contact.setPhone(contactCreateDTO.getPhone());

        // Сохранение контакта
        Contact savedContact = contactRepository.save(contact);

        // Преобразование сохранённой сущности в ContactDTO
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(savedContact.getId());
        contactDTO.setFirstName(savedContact.getFirstName());
        contactDTO.setLastName(savedContact.getLastName());
        contactDTO.setPhone(savedContact.getPhone());
        contactDTO.setCreatedAt(savedContact.getCreatedAt());
        contactDTO.setUpdatedAt(savedContact.getUpdatedAt());

        return contactDTO;
    }
}