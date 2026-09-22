package dev.request.system.controller;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

    @Test
    void 요청목록을_조회한다() throws Exception {
        when(requestService.findAllRequests()).thenReturn(List.of());
        mockMvc.perform(get("/requests"))
                .andExpect(view().name("request/list"))
                .andExpect(model().attributeExists("requests"));
    }

    @Test
    void 요청상세를_조회한다() throws Exception {
        Request request = new Request("테스트 제목", "테스트 내용");
        when(requestService.findByRequestId(1L)).thenReturn(request);
        mockMvc.perform(get("/requests/1"))
                .andExpect(view().name("request/detail"))
                .andExpect(model().attributeExists("request"));
    }
}
