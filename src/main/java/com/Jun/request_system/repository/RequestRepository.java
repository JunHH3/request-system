package com.Jun.request_system.repository;

import com.Jun.request_system.entity.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
