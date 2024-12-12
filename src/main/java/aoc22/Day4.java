package aoc22;

import java.io.IOException;
import java.util.List;

public class Day4 {
    public List<String> loadFile(String path) throws IOException {
        InputLoader inputLoader = new InputLoader(path);
        return inputLoader.readToStringList();
    }

    public String solve() throws IOException {
        List<String> input = loadFile("4.txt");
        int contains = 0;
        int overlap = 0;
        for (String s : input) {
            String[] parts = s.split(",");
            String[] sec1Parts = parts[0].split("-");
            String[] sec2Parts = parts[1].split("-");
            int sec1Lower = Integer.parseInt(sec1Parts[0]);
            int sec1Upper = Integer.parseInt(sec1Parts[1]);
            int sec2Lower = Integer.parseInt(sec2Parts[0]);
            int sec2Upper = Integer.parseInt(sec2Parts[1]);

            if (sec1Lower <= sec2Lower && sec1Upper >= sec2Upper)
                contains++;
            else if (sec1Lower >= sec2Lower && sec1Upper <= sec2Upper)
                contains++;

            if ((sec1Lower <= sec2Lower && sec1Upper >= sec2Lower) || (sec1Lower <= sec2Upper && sec1Upper >= sec2Upper))
                overlap++;
            else if ((sec2Lower <= sec1Lower && sec2Upper >= sec1Lower) || (sec2Lower <= sec1Upper && sec2Upper >= sec1Upper))
                overlap++;
        }
       System.out.println( contains);
        System.out.println( overlap);
        return ""+contains+" "+overlap;
    }

}
