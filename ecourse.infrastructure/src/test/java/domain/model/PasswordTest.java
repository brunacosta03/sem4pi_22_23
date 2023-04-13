package domain.model;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.junit.jupiter.api.Test;

import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    final PasswordPolicy policy = new ECoursePasswordPolicy();

    PasswordEncoder encoder = new PlainTextEncoder();

    @Test
    void EncodedAndValid(){
        Optional<Password> pass = Password.encodedAndValid("123456Ax", policy, encoder);

        assertTrue(pass.isPresent());
    }

    @Test
    void ensureNullIsNotValid(){
        Throwable thr = assertThrows(IllegalArgumentException.class, () -> {
            Password.encodedAndValid(null, policy, encoder);
        });
    }

    @Test
    void ensureEmptyStringIsNotValid(){
        Optional<Password> pass = Password.encodedAndValid("", policy, encoder);

        assertFalse(pass.isPresent());
    }

    @Test
    void ensurePasswordWithLessThanSixCharactersIsNotValid(){
        Optional<Password> pass = Password.encodedAndValid("1234A", policy, encoder);

        assertFalse(pass.isPresent());
    }

    @Test
    void ensurePasswordWithNoUpperCaseCharacterIsNotValid(){
        Optional<Password> pass = Password.encodedAndValid("1234567", policy, encoder);

        assertFalse(pass.isPresent());
    }

    @Test
    void toStringReturnsFakePass(){
        Password pass = Password.encodedAndValid("1234567X", policy, encoder).get();

        assertEquals("************", pass.toString());
        assertNotEquals("1234567X", pass.toString());
    }

    @Test
    void ensurePasswordsMatch(){
        Password pass = Password.encodedAndValid("1234567X", policy, encoder).get();
        Password pass2 = Password.encodedAndValid("1234567X", policy, encoder).get();

        assertTrue(pass.equals(pass2));

        pass = Password.encodedAndValid("1234567X", policy, encoder).get();
        pass2 = Password.encodedAndValid("1234567Y", policy, encoder).get();

        assertFalse(pass.equals(pass2));

        pass = Password.encodedAndValid("1234567X", policy, encoder).get();
        Object any = new String("1234567X");

        assertFalse(pass.equals(any));
    }

    @Test
    void valueReturnsEncodedPassword(){
        Password pass = Password.encodedAndValid("1234567X", policy, encoder).get();

        assertEquals("1234567X", pass.value()); // this encoder is used for tests and does not encode(change) the password
    }

}