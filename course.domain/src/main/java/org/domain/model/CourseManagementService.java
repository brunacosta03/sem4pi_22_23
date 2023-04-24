package org.domain.model;

import org.domain.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseManagementService{
    /**
     * CourseRepository
     */
    private final CourseRepository repo;

    public CourseManagementService(final CourseRepository repo) {
        this.repo = repo;
    }


}
