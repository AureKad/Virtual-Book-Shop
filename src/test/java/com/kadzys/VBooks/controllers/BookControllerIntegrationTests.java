package com.kadzys.VBooks.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadzys.VBooks.models.Book;
import com.kadzys.VBooks.models.Role;
import com.kadzys.VBooks.models.User;
import com.kadzys.VBooks.services.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.kadzys.VBooks.TestAuthenticationUtil.getAdmin;
import static com.kadzys.VBooks.TestDataUtil.createBookA;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private BookService bookService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    private WebApplicationContext wac;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService, WebApplicationContext wac) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.wac = wac;
    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnHttp201Created() throws Exception {
        Book book = createBookA();
        String bookJson = objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
                        .with(user("admin").password("1234").roles("ADMIN"))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

}
