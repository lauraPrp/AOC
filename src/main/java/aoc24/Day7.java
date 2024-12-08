package aoc24;

import java.io.*;
import java.util.*;

public class Day7 {
    public static long evaluateExpression(List<Long> numbers, List<String> operators) {
        long result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("+")) {
                result += numbers.get(i + 1);
            } else if (operators.get(i).equals("*")) {
                result *= numbers.get(i + 1);
            } else if (operators.get(i).equals("||")) {
                result = Long.parseLong(result + "" + numbers.get(i + 1));
            }
        }
        return result;
    }

    public static List<List<String>> generateOperatorCombinations(int size) {
        List<List<String>> combinations = new ArrayList<>();
        generateOperatorCombinationsHelper(combinations, new ArrayList<>(), size);
        return combinations;
    }

    private static void generateOperatorCombinationsHelper(List<List<String>> combinations, List<String> current, int size) {
        if (current.size() == size) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        current.add("+");
        generateOperatorCombinationsHelper(combinations, current, size);
        current.remove(current.size() - 1);

        current.add("*");
        generateOperatorCombinationsHelper(combinations, current, size);
        current.remove(current.size() - 1);

        current.add("||");
        generateOperatorCombinationsHelper(combinations, current, size);
        current.remove(current.size() - 1);
    }

    public static boolean isEquationTrue(long testValue, List<Long> numbers) {
        int n = numbers.size();
        if (n == 1) return testValue == numbers.get(0);

        List<List<String>> operatorCombinations = generateOperatorCombinations(n - 1);

        for (List<String> operators : operatorCombinations) {
            long result = evaluateExpression(numbers, operators);
            if (result == testValue) {
                return true;
            }
        }
        return false;
    }

    public static long processEquations(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        long totalCalibrationResult = 0;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            long testValue = Long.parseLong(parts[0]);
            List<Long> numbers = new ArrayList<>();
            for (String num : parts[1].split(" ")) {
                numbers.add(Long.parseLong(num));
            }

            if (isEquationTrue(testValue, numbers)) {
                totalCalibrationResult += testValue;
            }
        }
        reader.close();
        return totalCalibrationResult;
    }

    public static void main(String[] args) {
        try {
            String filename = "src/main/java/aoc24/day7input.txt";
            long result = processEquations(filename);
            System.out.println("Total calibration result: " + result);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}