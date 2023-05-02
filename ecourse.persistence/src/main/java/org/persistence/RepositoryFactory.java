package org.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import org.domain.repositories.BoardRepository;
import org.domain.repositories.CourseRepository;
import org.springframework.repositories.EnrollmentRequestRepository;
import org.usermanagement.domain.repositories.UserRepository;

public interface RepositoryFactory {
    /**
     * Create a Transactional Context.
     * @return TransactionalContext
     */
    TransactionalContext newTransactionalContext();

    /**
     * For configure persistence context.
     * @return UserRepository
     */
     UserRepository users();

    /**
     * For configure persistence context.
     * @return CourseRepository
     */
    CourseRepository courses();

    /**
     * For configure persistence context.
     * @return BoardRepository
     */
    BoardRepository boards();

    /**
     * For configure persistence context.
     * @return EnrollmentRequestRepository
     */
    EnrollmentRequestRepository enrollmentRequests();

}
