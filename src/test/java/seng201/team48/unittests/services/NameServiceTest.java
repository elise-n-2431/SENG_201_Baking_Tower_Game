package seng201.team48.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team48.services.NameService;

import static org.junit.jupiter.api.Assertions.*;

public class NameServiceTest {
    private NameService testNameService;

    @BeforeEach
    public void setupTest() {
        // Use CounterService directly
        testNameService = new NameService();
    }
    /**
     * Test that a valid name passes the check
     */
    @Test
    void validName() {
        assertTrue(testNameService.isValidName("Hannah"));
    }

    /**
     * Test that a 2-character name fails check (edge case)
     */
    @Test
    void tooShort() {
        assertFalse(testNameService.isValidName("Ha"));
    }

    /**
     * Test that a 3-character name passes check (edge case)
     */
    @Test
    void minLength() {
        assertTrue(testNameService.isValidName("eli"));
    }

    /**
     * Test that a 15-character name passes check (edge case)
     */
    @Test
    void maxLength() {
        assertTrue(testNameService.isValidName("15charactername"));
    }

    /**
     * Test that a 16-character name fails check (edge case)
     */
    @Test
    void tooLong() {
        assertFalse(testNameService.isValidName("a16charactername"));
    }

    /**
     * Test that a name containing a special character fails check
     */
    @Test
    void specialChars() {
        assertFalse(testNameService.isValidName("hannah-grace"));
    }

}
