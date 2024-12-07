package aoc22;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class Day6 {
    private static List<String> loadFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("6.txt");
//        return fileReader.readStrings();
        return new ArrayList<>();
    }

    public static int solve(int marker) throws IOException {
        String s = loadFile().get(0);

        int bound = s.length() - 1;
        for (int i = 0; i < bound; i++) {
            if (s.substring(i, i + marker)
                    .chars()
                    .distinct().count() == marker) {
                return OptionalInt.of(i).getAsInt() + marker;
            }
        }
        return OptionalInt.empty().getAsInt() + marker;

    }

    public static void main(String[] args) {
        try {
            System.out.println( "part 1 : "+ solve(4));
            System.out.println( "part 2 : "+ solve(14));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
