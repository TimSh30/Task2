import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class ComplexExamplesTest {

    @Test
    void checkEqualArrayWithSixElement() {
        assertEquals(asList(1, 3), ComplexExamples.outSumma(new Integer[]{1, 2, 3, 4, 5, 4}, 4));
    }

    @Test
    void checkEqualArrayWithOneElement() {
        assertEquals(asList(), ComplexExamples.outSumma(new Integer[]{2}, 4));
    }

    @Test
    void checkEqualArrayWidthZeroElement() {
        assertNotNull(ComplexExamples.outSumma(new Integer[]{}, 4));
    }

    @Test
    void checkNotEqualArrayToNumber() {
        assertEquals(asList(), (ComplexExamples.outSumma(new Integer[]{4, 9, 15, 1, 3, 8, 7}, 157)));
    }

    @Test
    void checkArrayOnNull() {
        assertNotNull((ComplexExamples.outSumma(new Integer[]{4, 9, 15, 1, 3, 8, 7}, 157)));
    }

    @Test
    void checkfuzzySearchWithOrderdString() {
        assertTrue(ComplexExamples.fuzzySearch("tri", "tokprnhi67we"));
    }

    @Test
    void checkFuzzySearchInNotOrderString() {
        assertTrue(ComplexExamples.fuzzySearch("tri", "okprnth67wie"));
    }
    @Test
    void checkFuzzySearchOnNull() {
        assertFalse((ComplexExamples.fuzzySearch(null, "okprnth67wie")));
    }
    @Test
    void checkFuzzySearchInNullString() {
        assertFalse((ComplexExamples.fuzzySearch("dit", null)));
    }
    @Test
    void checkFuzzySearchInRepeatedPatern() {
        assertTrue((ComplexExamples.fuzzySearch("dddd", "grdiklkdosdpsid")));
    }
}