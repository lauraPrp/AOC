package aoc24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day21 {
    private static final char[][] keypad = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'},
            {' ', '0', 'A'}
    };

    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    private static final char[] directionCommands = {'>', 'v', '<', '^'};

    private static Map<Character, int[]> positionMap;

    static {
        positionMap = new HashMap<>();
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[i].length; j++) {
                positionMap.put(keypad[i][j], new int[]{i, j});
            }
        }
    }

    private static List<Character> findPath(char start, char end) {
        int[] startPos = positionMap.get(start);
        int[] endPos = positionMap.get(end);
        boolean[][] visited = new boolean[4][3];
        Queue<int[]> queue = new LinkedList<>();
        Map<int[], List<Character>> pathMap = new HashMap<>();

        queue.add(startPos);
        visited[startPos[0]][startPos[1]] = true;
        pathMap.put(startPos, new ArrayList<>());

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            List<Character> path = pathMap.get(current);

            if (current[0] == endPos[0] && current[1] == endPos[1]) {
                return path;
            }

            for (int i = 0; i < directions.length; i++) {
                int[] newLocation = {current[0] + directions[i][0], current[1] + directions[i][1]};
                if (newLocation[0] >= 0
                        && newLocation[0] < 4
                        && newLocation[1] >= 0
                        && newLocation[1] < 3
                        && !visited[newLocation[0]][newLocation[1]]
                        && keypad[newLocation[0]][newLocation[1]] != ' ') {
                    visited[newLocation[0]][newLocation[1]] = true;
                    queue.add(newLocation);
                    List<Character> newPath = new ArrayList<>(path);
                    newPath.add(directionCommands[i]);
                    pathMap.put(newLocation, newPath);
                }
            }
        }
        return null; //no path found
    }

    private static String getPathCommands(char start, char end) {
        List<Character> path = findPath(start, end);
        if (path == null) return "No path available.";
        StringBuilder commands = new StringBuilder();
        for (char command : path) {
            commands.append(command);
        }
        commands.append('A');
        return commands.toString();
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/aoc24/day21input.txt";
        List<String> codes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                codes.add(line.trim());  // Add each code to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return;
        }

        int totalComplexity = 0;

        for (String code : codes) {
            StringBuilder totalCommands = new StringBuilder();
            char start = 'A';
            for (char digit : code.toCharArray()) {
                String commandsForDigit = getPathCommands(start, digit);
                totalCommands.append(commandsForDigit);
                start = digit;  // Update start to be the last digit for the next iteration
                System.out.println("Commands to move from " + start + " to " + digit + ": " + commandsForDigit);
            }

            int commandLength = totalCommands.length();
            int numericValue = Integer.parseInt(code.substring(0, code.length() - 1));
            int complexity = commandLength * numericValue;
            totalComplexity += complexity;


            System.out.println("Command sequence for '" + code + "': " + totalCommands);
            System.out.println("Complexity for '" + code + "': " + complexity);
        }

        System.out.println("P1: " + totalComplexity);
    }

}

