package aoc22;

import aoc22.Day3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class Day3Test {
    List<String> fileContentasList;
    Day3 day3;

    @BeforeEach
    void setup() {
        day3 = new Day3();
    }

    @Test
    void shouldReturnaListOfStringwithTheContentinTheTestFile() throws IOException {
        fileContentasList = day3.loadFile("3test.txt");
        assertTrue(fileContentasList.size() == 6);
    }

    @Test
    void shouldSplitEachStringInTwoEqualLenghtSegments() throws IOException {
        fileContentasList = day3.loadFile("3test.txt");
        int rows = fileContentasList.size();
        String[][] containers = new String[rows][2];
        containers = day3.splitString(fileContentasList);

        assertEquals(containers[0][0].length() ,containers[0][1].length());
        assertEquals(containers[1][0].length() , containers[1][1].length());
        assertEquals(containers[2][0].length() , containers[2][1].length());
        assertEquals(containers[3][0].length() , containers[3][1].length());
        assertEquals(containers[4][0].length() , containers[4][1].length());
        assertEquals(containers[5][0].length() , containers[5][1].length());
    }

    @Test
     void shouldReturnDuplicatedLettersinBothContainers() throws IOException {

        fileContentasList = day3.loadFile("3test.txt");
        int rows = fileContentasList.size();
        String[][] containers = new String[rows][2];
        containers = day3.splitString(fileContentasList);

      String duplicates=  day3.extractDuplicates(containers);
      assertEquals("pLPvts", duplicates);
    }
    @Test
     void shouldReturn157AsPrioritiesValue(){
        String dupes = "pLPvts";
        int expectedValue = 157;
        int calculatedValue =day3.calculatePrioritiesValue(dupes);
        assertEquals(expectedValue, calculatedValue);
    }
     @Test
     void shouldReturnRealValueAsPrioritiesValue(){

         String dupes= "csfSzFmCcSZFdtHTrrTtdbQdTsmCFCwqcPjZpvGntslDMgmcCzPcMphlrmTqctrsQcVWqwfncScJnslpZSbjQqzhGQClgHwqsmRSsSthfrJvQVWSCHMBTWNpPFMGRjMVHMLdgvnjblBHzhVBqwmGnpfrVBdpjRrqlRGMwzVrLjDRJJdPbjFGbFNdFmMTNZSVwqnRwDgrWmJNFTjHQgLnHdDfWgdcNWbcTDlHbdvWPtJdvwDGPvQwqHMGWDplwMzngbhhbwdJzsbhBbSjlQRpfVDdQcZgf";
         int expectedValue = 157;
         int calculatedValue =day3.calculatePrioritiesValue(dupes);
         assertEquals(expectedValue, calculatedValue);
     }

 @Test
     void shouldReturnRealvalueFromRealInput() throws IOException {
     fileContentasList = day3.loadFile("3.txt");
     int rows = fileContentasList.size();
     String[][] containers = new String[rows][2];
     containers = day3.splitString(fileContentasList);

     String duplicates=  day3.extractDuplicates(containers);
     int resultPArt1 =day3.calculatePrioritiesValue(duplicates);
     assertEquals(7446, resultPArt1);
 }

 @Test
void shouldReturnTotal() throws IOException {
        int total = day3.part2();
        assertEquals(0, total);
 }

 }
