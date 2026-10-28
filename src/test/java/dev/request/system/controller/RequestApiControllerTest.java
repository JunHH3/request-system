package dev.request.system.controller;

import dev.request.system.dto.RequestForm;
import dev.request.system.entity.Request;
import dev.request.system.exception.RequestNotFoundException;
import dev.request.system.service.RequestService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequestApiController.class)
public class RequestApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RequestService requestService;

    @Test
    void 제목이_비어있으면_400을_반환하고_저장하지_않는다() throws Exception {

        mockMvc.perform(post("/api/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "title": "",
                        "content": "컴퓨터 수리"
                        }
                        """))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(requestService);
    }

    @Test
    void 정상등록은_201을_반환한다() throws Exception {

        Request saved = new Request("모니터 수리", "화면 문제");
        ReflectionTestUtils.setField(saved, "id", 23L);

        when(requestService.createRequest("모니터 수리", "화면 문제"))
                .thenReturn(saved);

        mockMvc.perform(post("/api/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "title": "모니터 수리",
                        "content": "화면 문제"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(23))
                .andExpect(jsonPath("$.status").value("RECEIVED"));

        verify(requestService).createRequest("모니터 수리", "화면 문제");
    }

    @Test
    void 존재하지_않는_요청_수정은_404를_반환한다() throws Exception {

        doThrow(new RequestNotFoundException(9999L))
                .when(requestService)
                .updateRequest(eq(9999L), any(RequestForm.class));

        mockMvc.perform(patch("/api/requests/9999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "title": "노트북 수리",
                        "content": "전원 문제"
                        }
                        """))
                .andExpect(status().isNotFound());

        verify(requestService)
                .updateRequest(eq(9999L), any(RequestForm.class));
    }

}
