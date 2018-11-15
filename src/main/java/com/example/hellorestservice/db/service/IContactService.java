package com.example.hellorestservice.db.service;

import com.example.hellorestservice.db.model.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> findByRegex(String regex);

    List<Contact> findAll();
}
