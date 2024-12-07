package aoc22;

import aoc22.Day4;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {
@Test
    void shouldReturnOverlapTot() throws IOException {
    Day4 day4 = new Day4();
  String res =   day4.solve();
    assertEquals("",res);
}
}