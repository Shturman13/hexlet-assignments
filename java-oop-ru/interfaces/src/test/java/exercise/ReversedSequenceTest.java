package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReversedSequenceTest {
    private String initialString;

    @BeforeEach
    public void Setup() {
        initialString = "abcdef";
    }


    @Test
    public void testReversedSequence() {
        var expectedReversedSequence = "fedcba";
        var actualReversedSequence = ReversedSequence.reversedSequence(initialString);

        assertEquals(expectedReversedSequence, actualReversedSequence);
    }

    @Test
    public void testLength() {
        assertEquals(6, ReversedSequence.reversedSequence(initialString).length());
    }

    @Test
    public void testCharAt() {
        assertEquals('e', ReversedSequence.reversedSequence(initialString).charAt(1));
    }

    @Test
    public void testSubSequence() {
        assertEquals("edc", ReversedSequence.reversedSequence(initialString).subSequence(1, 4));
    }


}
