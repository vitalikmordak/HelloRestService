package com.example.hellorestservice.controller;

import com.example.hellorestservice.db.model.Contact;
import com.example.hellorestservice.db.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    IContactService contactService;

    @RequestMapping(value = "/hello/contacts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Contact> getContactsByRegex(@RequestParam("nameFilter") String nameFilter) {
        return contactService.findByRegex(nameFilter);
    }
}
