package aoc23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

public class Day1 {
    public static void main(String[] args) {

        Day1 solver = new Day1();

        String filePath = "src/main/java/aoc23/day1.txt"; // Update this path to your file
        int finalSum = solver.calculateSum(filePath);
        System.out.println("Total sum of calibration values: " + finalSum);
    }

    public int calculateSum(String filePath) {
        int sum = 0;

        try {
            Scanner scanner = new Scanner(new File(filePath));
            int counter=0;
            while (scanner.hasNextLine()) {
                counter++;
                String line = scanner.nextLine();
                sum += extractCalibrationValue(line);
                System.out.println("line " + line+"\n sum: "+sum +"\n counter: "+counter );
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return sum;
    }

    public int extractCalibrationValue(String str) {
        System.out.println("extract calibration value :" + str);
        // Map of number words to their digit representations
        Map<String, String> numberWords = Map.of(
                "one", "1", "two", "2", "three", "3", "four", "4",
                "five", "5", "six", "6", "seven", "7", "eight", "8", "nine", "9"
        );

        // Replace spelled-out numbers with digits
        for (Map.Entry<String, String> entry : numberWords.entrySet()) {
            str = str.replaceAll(entry.getKey(), entry.getValue());
        }

        // Find the first and last digit in the processed string
        String firstDigit = null;
        String lastDigit = null;
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                if (firstDigit == null) {
                    firstDigit = String.valueOf(ch);
                }
                lastDigit = String.valueOf(ch);
            }
        }

        if (firstDigit != null && lastDigit != null) {
            return Integer.parseInt(firstDigit + lastDigit);
        }

        return 0;
    }
}
