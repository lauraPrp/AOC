package aoc24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
    public static int calculateTotalDistance(int[] leftList, int[] rightList) {
        Arrays.sort(leftList);
        Arrays.sort(rightList);

        int totalDistance = 0;
        for (int i = 0; i < leftList.length; i++) {
            totalDistance += Math.abs(leftList[i] - rightList[i]);
        }

        return totalDistance;
    }


    public static int calculateSimilarityScore(int[] leftList, int[] rightList) {
        Map<Integer, Integer> rightFrequencyMap = new HashMap<>();

        for (int num : rightList) {
            rightFrequencyMap.put(num, rightFrequencyMap.getOrDefault(num, 0) + 1);
        }

        int similarityScore = 0;
        for (int num : leftList) {
            int frequency = rightFrequencyMap.getOrDefault(num, 0);
            similarityScore += num * frequency;
        }

        return similarityScore;
    }

    public static int[][] readInputFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split("\\s+");
            leftList.add(Integer.parseInt(values[0]));
            rightList.add(Integer.parseInt(values[1]));
        }
        reader.close();

        int[] leftArray = leftList.stream().mapToInt(i -> i).toArray();
        int[] rightArray = rightList.stream().mapToInt(i -> i).toArray();

        return new int[][] {leftArray, rightArray};
    }

    public static void main(String[] args) {
        try {
            String filename = "src/main/java/aoc24/day1input.txt";

            int[][] lists = readInputFromFile(filename);
            int[] leftList = lists[0];
            int[] rightList = lists[1];


            int totalDistance = calculateTotalDistance(leftList, rightList);
            System.out.println("Total distance: " + totalDistance);


            int similarityScore = calculateSimilarityScore(leftList, rightList);
            System.out.println("Similarity score: " + similarityScore);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
