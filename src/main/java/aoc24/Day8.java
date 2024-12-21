package aoc24;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Day8 {
        public static void main(String[] args) throws IOException {

            Path inputPath = Paths.get("src/main/java/aoc24/day8input.txt");

            List<String> lines = Files.readAllLines(inputPath);

            char[][] map = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                map[i] = lines.get(i).toCharArray();
            }

            int uniqueAntinodes = calculateUniqueAntinodes(map);


            System.out.println("Unique antinode locations: " + uniqueAntinodes);

            printMapWithAntinodes(map, calculateAntinodesSet(map));
        }

    private static int calculateUniqueAntinodes(char[][] map) {
        Set<String> antinodes = calculateAntinodesSet(map);
        return antinodes.size();
    }

    private static Set<String> calculateAntinodesSet(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;

        Set<String> antinodes = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char antenna = map[r][c];
                if (antenna != '.' && antenna != '#') {
                    simulateAntinodes(map, rows, cols, r, c, antenna, antinodes);
                }
            }
        }
        return antinodes;
    }

    private static void simulateAntinodes(char[][] map, int rows, int cols, int r, int c, char antenna, Set<String> antinodes) {
        antinodes.add(r + "," + c);

        for (int d = -1; d <= 1; d += 2) { // -1 for up, 1 for down
            int distance = 1;
            while (true) {
                int nr = r + (distance * d);
                if (nr < 0 || nr >= rows) break; // Out of bounds
                if (map[nr][c] == '.' || map[nr][c] == antenna) {

                    antinodes.add(nr + "," + c);
                } else {

                    break;
                }
                distance++;
            }
        }
    }

    private static void printMapWithAntinodes(char[][] map, Set<String> antinodes) {
        int rows = map.length;
        int cols = map[0].length;
        char[][] resultMap = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            Arrays.fill(resultMap[r], '.');
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (map[r][c] != '.') {
                    resultMap[r][c] = map[r][c];
                }
            }
        }

        for (String antinode : antinodes) {
            String[] parts = antinode.split(",");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            resultMap[r][c] = '#';
        }

        for (char[] row : resultMap) {
            System.out.println(new String(row));
        }
    }
}