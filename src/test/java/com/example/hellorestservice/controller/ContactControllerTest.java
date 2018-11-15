package com.example.hellorestservice.controller;

import com.example.hellorestservice.db.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContactController contactController;

    @Test
    public void getContactsByRegex() throws Exception {
        this.mockMvc.perform(get("/hello/contacts").param("nameFilter", ""))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        List<Contact> expected = new ArrayList<>();
        expected.add(new Contact());
        when(contactController.getContactsByRegex("")).thenReturn(expected);
        assertEquals(expected, contactController.getContactsByRegex(anyString()));
    }

}