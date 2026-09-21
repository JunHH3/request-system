package dev.request.system.controller;

import dev.request.system.dto.RequestForm;
import dev.request.system.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockitoBean private RequestService requestService;

    @Test
    void 빈등록은_등록되지_않는다() throws Exception{
        mockMvc.perform(
                        post("/requests")
                                .param("title", "")
                                .param("content", "")
                )
                .andExpect(view().name("request/new"));
        verify(requestService, never()).createRequest(any(RequestForm.class));
    }
}
