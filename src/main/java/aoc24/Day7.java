package aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) throws Exception {
        Path inputPath = Paths.get("src/main/java/aoc24/day7input.txt");
        List<String> linesAsStrings = Files.readAllLines(inputPath);

        List<long[]> lines = linesAsStrings.stream()
                .map(line -> parseLine(line))
                .collect(Collectors.toList());

        long part1 = lines.stream()
                .filter(Day7::valid1)
                .mapToLong(values -> values[0])
                .sum();

        long part2 = lines.stream()
                .filter(Day7::valid2)
                .mapToLong(values -> values[0])
                .sum();

        System.out.println("P1: " + part1);
        System.out.println("P2: " + part2);
    }

    private static long[] parseLine(String line) {
        String[] parts = line.split(":");
        long result = Long.parseLong(parts[0].trim());
        String[] numbers = parts[1].trim().split("\\s+");
        long[] values = new long[numbers.length + 1];
        values[0] = result; // The expected result is the first element
        for (int i = 0; i < numbers.length; i++) {
            values[i + 1] = Long.parseLong(numbers[i]);
        }
        return values;
    }

    private static boolean valid1(long[] values) {
        return valid(values, 2, values[1], false);
    }

    private static boolean valid2(long[] values) {
        return valid(values, 2, values[1], true);
    }

    private static boolean valid(long[] values, int i, long result, boolean part2) {
        if (i >= values.length) {
            return result == values[0];
        }
        if (result > values[0]) {
            return false;
        }
        return valid(values, i + 1, result + values[i], part2)
                || valid(values, i + 1, result * values[i], part2)
                || (part2 && valid(values, i + 1, concat(result, values[i]), part2));
    }

    private static long concat(long v1, long v2) {
        return Long.parseLong(Long.toString(v1) + v2);
    }
}
