package com.example.hellorestservice.db.service;

import com.example.hellorestservice.db.model.Contact;
import com.example.hellorestservice.db.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContactServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public IContactService employeeService() {
            return new ContactService();
        }
    }

    @Autowired
    private IContactService contactService;

    @MockBean
    private ContactRepository repository;

    private List<Contact> contactList;

    private Contact contactA, contactB;

    @Before
    public void setUp() throws Exception {
        contactA = new Contact();
        contactB = new Contact();
        contactA.setId(1);
        contactA.setName("Alex");
        contactB.setId(2);
        contactB.setName("Boris");

        contactList = new ArrayList<>();
        contactList.add(contactA);
        contactList.add(contactB);

        when(repository.findAll()).thenReturn(contactList);
        contactList.remove(contactA);
        when(repository.findOne(Example.of(contactB))).thenReturn(Optional.of(contactB));
    }

    @Test
    public void findByRegex() {
        assertEquals(contactB, contactService.findByRegex("^.A*$").get(0));
    }

    @Test
    public void findAll() {
        assertEquals(contactList, contactService.findAll());
    }
}