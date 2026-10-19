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
}
