package com.contactfilter.controller;

import com.contactfilter.entity.Contact;
import com.contactfilter.service.ContactService;
import com.contactfilter.constants.EndpointPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(EndpointPath.CONTACTS)
    List<Contact> getContacts(@RequestParam String nameFilter) {
       return contactService.filterBy(nameFilter);
    }
}
