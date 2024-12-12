package aoc23;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    private final Day1 solver = new Day1();

    @Test
    void testExtractCalibrationValueWithMixedNumbers() {
        assertEquals(29, solver.extractCalibrationValue("two1nine"));
        assertEquals(83, solver.extractCalibrationValue("eightwothree"));
        assertEquals(13, solver.extractCalibrationValue("abcone2threexyz"));
    }

    @Test
    void testExtractCalibrationValueWithOnlyDigits() {
        assertEquals(12, solver.extractCalibrationValue("1abc2"));
        assertEquals(38, solver.extractCalibrationValue("pqr3stu8vwx"));
        assertEquals(15, solver.extractCalibrationValue("a1b2c3d4e5f"));
    }

    @Test
    void testExtractCalibrationValueWithNoDigits() {
        assertEquals(0, solver.extractCalibrationValue("trebuchet"));
    }

    // Additional test cases can be added as needed.
}
