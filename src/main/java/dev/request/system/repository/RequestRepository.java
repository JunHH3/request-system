package dev.request.system.repository;

import dev.request.system.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
