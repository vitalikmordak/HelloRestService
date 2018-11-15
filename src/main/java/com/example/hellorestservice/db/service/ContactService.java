package com.example.hellorestservice.db.service;

import com.example.hellorestservice.db.model.Contact;
import com.example.hellorestservice.db.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@CacheConfig(cacheNames = "all")
public class ContactService implements IContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> findByRegex(String regex) {
        List<Contact> all = findAll();
        Pattern pattern = Pattern.compile(regex);
        List<Contact> filtered = new ArrayList<>();

        all.forEach(contact -> {
            if (!pattern.matcher(contact.getName()).matches())
                filtered.add(contact);
        });
        return filtered;
    }
    @Cacheable(value = "all", key = "0")
    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
}
