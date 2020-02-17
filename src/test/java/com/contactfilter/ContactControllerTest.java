package com.contactfilter;

import com.contactfilter.constants.EndpointPath;
import com.contactfilter.controller.ContactController;
import com.contactfilter.entity.Contact;
import com.contactfilter.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class ContactControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ContactController(contactService)).build();
    }
    @Test
    void shouldReturnsContactsAsIs() throws Exception {
        String nameFilter = "^A.*$";
        List<Contact> contactsUnderTest = List.of(new Contact(1, "Natalia1 Burtnyk"),
                new Contact(2, "Olesia2 Pastyshyn"),
                new Contact(3,"Olivia"),
                new Contact(4, "asdf3 fjuo lkoiea"),
                new Contact(5, "Andrey4 Popko"),
                new Contact(6, "buly bbllow"),
                new Contact(7, "Klodia5 Musley"),
                new Contact(8, "5678"));
        when(contactService.filterBy(anyString())).thenReturn(contactsUnderTest);

        mockMvc.perform(get(EndpointPath.CONTACTS).param("nameFilter", nameFilter).accept(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(contactsUnderTest.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name", containsInAnyOrder("Natalia1 Burtnyk",
                        "Olesia2 Pastyshyn",
                        "Olivia",
                        "asdf3 fjuo lkoiea",
                        "Andrey4 Popko",
                        "buly bbllow",
                        "Klodia5 Musley",
                        "5678")));
    }
}
