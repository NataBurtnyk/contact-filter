package com.contactfilter;

import com.contactfilter.entity.Contact;
import com.contactfilter.repository.ContactRepository;
import com.contactfilter.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp(){
        List<Contact> contactsUnderTest = List.of(new Contact(1, "Natalia1 Burtnyk"),
                new Contact(2, "Olesia2 Pastyshyn"),
                new Contact(3,"Olivia"),
                new Contact(4, "asdf3 fjuo lkoiea"),
                new Contact(5, "Andrey4 Popko"),
                new Contact(6, "buly bbllow"),
                new Contact(7, "Klodia5 Musley"),
                new Contact(8, "5678"));

        when(contactRepository.findAll()).thenReturn(contactsUnderTest);
    }

    @Test
    void shouldReturnsContactsThatDoNotStartWith_A() {
        String nameFilter = "^A.*$";
        List<Contact> filteredContacts = contactService.filterBy(nameFilter);
        filteredContacts.forEach(contact -> assertFalse(contact.getName().startsWith("A")));
    }

    @Test
    void shouldReturnsContactsThatDoNotStartWith_b(){
        String nameFilter = "^b.*$";
        List<Contact> filteredContacts = contactService.filterBy(nameFilter);
        filteredContacts.forEach(contact -> assertFalse(contact.getName().startsWith("b")));
    }

    @Test
    void shouldReturnsContactsThatDoNotContainTheLetters_a_e_i(){
        String nameFilter = "^.*[aei].*$";
        List<Contact> filteredContacts = contactService.filterBy(nameFilter);
        filteredContacts.forEach(contact -> assertFalse(contact.getName().matches(nameFilter)));
    }

    @Test
    void shouldReturnsContactsThatDoNotContainOnlyDigits(){
        String nameFilter = "\\d";
        List<Contact> filteredContacts = contactService.filterBy(nameFilter);
        filteredContacts.forEach(contact -> assertFalse(contact.getName().matches(nameFilter)));
    }

    @Test
    void shouldReturnsEmptyListIfThereNoDataInTheTable(){
        String nameFilter = "\\d";
        when(contactRepository.findAll()).thenReturn(Collections.emptyList());
        List<Contact> filteredContacts = contactService.filterBy(nameFilter);
        assertTrue(filteredContacts.isEmpty());
    }

    @Test
    void shouldReturnsAllContactsAsIsIfNameFilterIsNull(){
        String nameFilter = null;
        when(contactRepository.findAll()).thenReturn(List.of(new Contact (1,"Test Test"),
                new Contact(2, "Test2 test"),
                new Contact (3, "Test3 test")));
        List<Contact> filteredContacts = contactService.filterBy(nameFilter);
        assertEquals("Test Test", filteredContacts.get(0).getName());
        assertEquals("Test2 test", filteredContacts.get(1).getName());
        assertEquals("Test3 test", filteredContacts.get(2).getName());
    }

}
