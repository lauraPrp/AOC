package aoc22;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class Day6Test {
@Test
    void shouldREturnActualResults() throws IOException {
        assertEquals(0,Day6.solve(4));
        assertEquals(0,Day6.solve(14));
}



}
