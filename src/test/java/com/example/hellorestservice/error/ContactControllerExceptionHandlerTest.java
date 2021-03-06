package com.example.hellorestservice.error;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void noHandlerFoundException() throws Exception {
        this.mockMvc.perform(get("/hello/contact"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void unknownException() throws Exception {
        this.mockMvc.perform(get("/hello/contacts"))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }
}