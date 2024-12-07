package aoc22;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Day1 {

    public static void main(String args[]) throws IOException {
        File file = new File("src/main/resources/1.txt");
        String input = Files.readString(file.toPath());
        String[] elves = input.split("\n\n");
        int[][] calories = Stream.of(elves).map((string) -> string.lines().mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        int max = Stream.of(calories).map(IntStream::of).mapToInt(IntStream::sum).max().getAsInt();
        System.out.println(max);
        int[] listSorted = Stream.of(calories).map(IntStream::of).mapToInt(IntStream::sum).sorted().toArray();

        int first3 = listSorted[listSorted.length - 1] + listSorted[listSorted.length - 2] + listSorted[listSorted.length - 3];
        System.out.println(first3);
    }
}

