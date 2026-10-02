package dev.request.system.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestTest {

    @Test
    void 요청처리를_시작한다(){
        Request request = new Request("테스트 제목", "테스트 내용");
        request.startProgress();
        assertEquals(RequestStatus.IN_PROGRESS, request.getRequestStatus());
    }

    @Test
    void 요청처리를_완료한다(){
        Request request = new Request("테스트 제목", "테스트 내용");
        request.startProgress();
        request.complete();
        assertEquals(RequestStatus.COMPLETED, request.getRequestStatus());
    }

    @Test
    void 접수상태에서는_완료할수없다(){
        Request request = new Request("테스트 제목", "테스트 내용");

        assertThrows(IllegalStateException.class, () -> request.complete());
    }

    @Test
    void 완료상태에서는_다시처리중으로_돌아갈수없다(){
        Request request = new Request("테스트 제목", "테스트 내용");
        request.startProgress();
        request.complete();
        assertThrows(IllegalStateException.class, () -> request.startProgress());
    }
}
