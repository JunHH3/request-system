package dev.request.system.service;

import dev.request.system.entity.Request;
import dev.request.system.entity.RequestStatus;
import dev.request.system.repository.RequestRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RequestServiceTest {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    @Transactional
    void save없이도_상태변경이_DB에반영된다(){
        Request request = requestRepository.save(
                new Request("테스트 제목", "테스트 내용")
        );

        requestService.startProgress(request.getId());

        entityManager.flush();
        entityManager.clear();

        Request result = requestRepository.findById(request.getId())
                .orElseThrow();

        assertEquals(RequestStatus.IN_PROGRESS, result.getRequestStatus());
    }

    @Test
    void 예외가_발생하면_저장이_롤백된다(){
        long before = requestRepository.count();

        assertThrows(IllegalStateException.class, () ->
                transactionTemplate.executeWithoutResult(status -> {
                    requestRepository.save(new Request("롤백 테스트", "내용"));
                    throw new IllegalStateException("강제 실패");
                })
        );

        long after = requestRepository.count();

        assertEquals(before, after);
    }

    @Test
    void 진행전_완료처리하면_예외가_발생한다(){
        // 1. RECEIVED 상태의 요청 저장
        Request request = requestRepository.save(
                new Request("텍스트 제목", "텍스트 내용")
        );

        // 2~3. 완료 처리 시 IllegalStateException 발생 검증
        assertThrows(IllegalStateException.class, () ->
                requestService.completeRequest(request.getId()));

        // 4. DB에서 다시 조회
        Request result = requestRepository.findById(request.getId())
                .orElseThrow();

        // 5. 상태가 RECEIVED인지 검증
        assertEquals(RequestStatus.RECEIVED, result.getRequestStatus());
    }

    @Test
    void 요청이_정상적으로_완료된다() {

        // 1. RECEIVED 상태의 요청 저장
        Request request = new Request("제목", "내용");
        requestRepository.save(request);

        // 2. startProgress() 실행
        requestService.startProgress(request.getId());

        // 3. DB에서 조회하여 IN_PROGRESS 확인
        Request request1 = requestRepository.findById(request.getId())
                        .orElseThrow();
        assertEquals(RequestStatus.IN_PROGRESS, request1.getRequestStatus());

        // 4. completeRequest() 실행
        requestService.completeRequest(request.getId());

        // 5. DB에서 다시 조회하여 COMPLETED 확인
        // 5. 완료 처리 후 DB에서 다시 조회
        Request request2 = requestRepository.findById(request.getId())
                .orElseThrow();
        assertEquals(RequestStatus.COMPLETED, request2.getRequestStatus());
    }
}
