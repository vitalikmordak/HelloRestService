package com.example.hellorestservice.db.service;

import com.example.hellorestservice.db.model.Contact;
import com.example.hellorestservice.db.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ContactService implements IContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findByRegex(String regex) {
        List<Contact> contacts = contactRepository.findAll();
        Pattern pattern = Pattern.compile(regex);
        List<Contact> filteredContacts = new ArrayList<>();

        contacts.forEach(contact -> {
            if (!pattern.matcher(contact.getName()).matches())
                filteredContacts.add(contact);
        });
        return filteredContacts;
    }
}
