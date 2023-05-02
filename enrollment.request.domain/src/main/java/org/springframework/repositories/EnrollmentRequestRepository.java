package org.springframework.repositories;

import eapli.framework.domain.repositories.DomainRepository;

import org.domain.model.Course;
import org.springframework.domain.EnrollmentRequest;
import org.usermanagement.domain.model.User;

import java.util.Optional;

public interface EnrollmentRequestRepository extends DomainRepository<Long, EnrollmentRequest> {
    EnrollmentRequest findByCourseAndStudent(Course course, User student);

    Optional<EnrollmentRequest> findById(Long Id);

    EnrollmentRequest save(EnrollmentRequest request);
}
