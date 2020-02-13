package com.contactfilter.service;

import com.contactfilter.entity.Contact;
import com.contactfilter.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService (ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public List<Contact> filterBy(String nameFilter) {
        List<Contact> allContacts = contactRepository.findAll();

      return allContacts.stream()
              .filter(contact -> !contact.getName().matches(nameFilter))
              .collect(Collectors.toList());
    }
}
