package aoc22;

import java.io.IOException;
import java.util.List;

public class Day2 {
    //move in utils class
    static List<String> input;

    private static List<String> loadFile() throws IOException {
        InputLoader inputLoader = new InputLoader("2.txt");
        return inputLoader.readToStringList();

    }

    public static void main(String[] args) {
        try {
            input = loadFile();
        } catch (IOException e) {
            System.out.println(e);
        }
        int result1 = 0;
        int result2 = 0;

        for (String line : input) {
            String elfPlay = line.split(" ")[0];
            String myPlay = line.split(" ")[1];
//A ROCK B PAPER C SCISSORS
            switch (elfPlay) {

                case "A" -> {

                    if (myPlay.equals("Y")) result1 += 8;
                    else if (myPlay.equals("X")) result1 += 4;
                    else  result1 += 3; //Z


                    //Part2
                    if (myPlay.equals("Y")) result2 += 4;
                    else if (myPlay.equals("X")) result2 += 3;
                    else  result2 += 8; //Z

                }

                case "B" -> {

                    if (myPlay.equals("Y")) result1 += 5;
                    else if (myPlay.equals("X")) result1 += 1;
                    else  result1 += 9; //Z


                    //Part 2
                    if (myPlay.equals("Y")) result2 += 5;
                    else if (myPlay.equals("X")) result2 += 1;
                    else result2 += 9; //Z

                }
                case "C" -> {

                    if (myPlay.equals("Y")) result1 += 2;
                    else if (myPlay.equals("X")) result1 += 7;
                    else  result1 += 6;


                    //Part2
                    if (myPlay.equals("Y")) result2 += 6;
                    else if (myPlay.equals("X")) result2 += 2;
                    else  result2 += 7;

                }
            }
        }

        System.out.println("First puzzle: " + result1);

        System.out.println("2ND puzzle: " + result2);


    }
}
