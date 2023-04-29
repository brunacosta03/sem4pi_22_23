package org.springframework.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentRequestTest {


    @Test
    void create() {

        assertThrows(
                IllegalArgumentException.class,
            () -> EnrollmentRequest.create(null, null)
        );

    }
}