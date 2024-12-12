package aoc22;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Static class and input, can't load file and solve otherwise :( */
public class Day5 {

    public static void main(String[] args) {
        String[] parts = INPUT.split("\n\n");
        List<Command> commands = parts[1].lines().map(Command::parse).toList();
        System.out.println(part1(parts[0], Collections.unmodifiableList(commands)));
        System.out.println(part2(parts[0], Collections.unmodifiableList(commands)));
    }

    public static String part1(String desc, List<Command> commands) {
        List<List<Character>> stacks = loadPlan(desc);
        for (Command c : commands) {
            c.moveCrates(stacks);
        }
        //System.out.println(getCollect(stacks));
        return getCollect(stacks);
    }

    public static String part2(String desc, List<Command> commands) {
        List<List<Character>> stacks = loadPlan(desc);
        for (Command c : commands) {
            c.solvep2(stacks);
        }
        //System.out.println(getCollect(stacks));
        return getCollect(stacks);
    }



    private static String getCollect(List<List<Character>> stacks) {
        StringBuilder sb = new StringBuilder();
        for (List<Character> l : stacks) {
            String s;
            if (l.isEmpty()) s = "";
            else s = "" + l.get(0);
            sb.append(s);
        }
        return sb.toString();
    }

    private static List<List<Character>> loadPlan(String desc) {
        List<List<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {
            stacks.add(new LinkedList<>());
        }
        List<String> lines = desc.lines().toList();
        lines = lines.subList(0, lines.size() - 1);
        for (String line : lines) {
            int j = 0;
            for (int i = 1; i < 38 && i < line.length(); i += 4, ++j) {
                char c = line.charAt(i);
                if (c != ' ') {
                    stacks.get(j).add(c);
                }
            }
        }
        return stacks;
    }

    private record Command(int count, int from, int to) {

        private static Pattern PATTERN = Pattern.compile("move (\\d+) from (\\d) to (\\d)");

        void moveCrates(List<List<Character>> stacks) {

            for (int i = 0; i < count; ++i) {
                stacks.get(to).add(0, stacks.get(from).remove(0));
            }

        }

        void solvep2(List<List<Character>> stacks) {
            List<Character> copy = new LinkedList<>();
            for (int i = 0; i < count; ++i) {
                copy.add(stacks.get(from).remove(0));
            }
            stacks.get(to).addAll(0, copy);
        }

        static Command parse(String line) throws IllegalStateException {
            Matcher matcher = PATTERN.matcher(line);
            if (matcher.matches()) {
                return new Command(Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)) - 1,
                        Integer.parseInt(matcher.group(3)) - 1);
            }
            throw new IllegalStateException("line: '" + line + "' does not match");
        }
    }

    private static final String INPUT = """
                    [G]         [D]     [Q]    
            [P]     [T]         [L] [M] [Z]    
            [Z] [Z] [C]         [Z] [G] [W]    
            [M] [B] [F]         [P] [C] [H] [N]
            [T] [S] [R]     [H] [W] [R] [L] [W]
            [R] [T] [Q] [Z] [R] [S] [Z] [F] [P]
            [C] [N] [H] [R] [N] [H] [D] [J] [Q]
            [N] [D] [M] [G] [Z] [F] [W] [S] [S]
             1   2   3   4   5   6   7   8   9 

            move 7 from 6 to 8
            move 5 from 2 to 6
            move 2 from 4 to 1
            move 1 from 4 to 5
            move 5 from 7 to 6
            move 7 from 6 to 3
            move 5 from 9 to 2
            move 6 from 2 to 3
            move 2 from 7 to 9
            move 20 from 3 to 1
            move 11 from 1 to 6
            move 1 from 9 to 8
            move 3 from 8 to 2
            move 8 from 1 to 5
            move 10 from 8 to 4
            move 7 from 6 to 4
            move 1 from 8 to 3
            move 8 from 1 to 7
            move 16 from 4 to 8
            move 1 from 9 to 8
            move 1 from 5 to 2
            move 4 from 7 to 4
            move 5 from 6 to 7
            move 1 from 6 to 1
            move 8 from 7 to 4
            move 1 from 6 to 9
            move 12 from 4 to 5
            move 3 from 2 to 5
            move 1 from 6 to 2
            move 1 from 3 to 7
            move 1 from 3 to 2
            move 1 from 9 to 3
            move 1 from 7 to 8
            move 1 from 7 to 5
            move 1 from 3 to 2
            move 4 from 5 to 7
            move 5 from 5 to 7
            move 1 from 4 to 3
            move 1 from 3 to 9
            move 3 from 1 to 8
            move 1 from 9 to 1
            move 2 from 2 to 1
            move 2 from 2 to 7
            move 8 from 8 to 1
            move 3 from 5 to 2
            move 8 from 7 to 5
            move 7 from 1 to 3
            move 3 from 1 to 7
            move 1 from 1 to 5
            move 1 from 3 to 7
            move 7 from 5 to 8
            move 2 from 2 to 8
            move 1 from 3 to 2
            move 1 from 2 to 4
            move 1 from 4 to 8
            move 13 from 8 to 1
            move 13 from 5 to 9
            move 2 from 5 to 2
            move 7 from 9 to 3
            move 12 from 8 to 3
            move 4 from 9 to 3
            move 1 from 3 to 4
            move 2 from 2 to 3
            move 1 from 1 to 6
            move 1 from 2 to 3
            move 1 from 5 to 9
            move 7 from 7 to 4
            move 10 from 1 to 8
            move 1 from 1 to 4
            move 1 from 9 to 5
            move 2 from 5 to 1
            move 1 from 6 to 5
            move 3 from 8 to 9
            move 5 from 4 to 3
            move 4 from 4 to 1
            move 7 from 1 to 6
            move 2 from 5 to 7
            move 35 from 3 to 4
            move 4 from 9 to 1
            move 19 from 4 to 8
            move 1 from 7 to 6
            move 1 from 9 to 2
            move 10 from 4 to 5
            move 2 from 4 to 7
            move 3 from 4 to 3
            move 1 from 2 to 8
            move 1 from 1 to 9
            move 3 from 3 to 6
            move 4 from 8 to 6
            move 4 from 5 to 2
            move 2 from 8 to 3
            move 3 from 5 to 9
            move 12 from 6 to 1
            move 8 from 8 to 6
            move 2 from 9 to 1
            move 1 from 4 to 1
            move 1 from 3 to 8
            move 3 from 7 to 8
            move 2 from 9 to 7
            move 1 from 6 to 7
            move 10 from 6 to 8
            move 4 from 2 to 5
            move 1 from 3 to 7
            move 7 from 5 to 7
            move 13 from 8 to 1
            move 29 from 1 to 4
            move 8 from 7 to 8
            move 1 from 1 to 3
            move 3 from 7 to 6
            move 1 from 1 to 9
            move 15 from 4 to 1
            move 1 from 3 to 6
            move 10 from 1 to 6
            move 10 from 6 to 7
            move 1 from 4 to 9
            move 1 from 9 to 1
            move 1 from 9 to 7
            move 6 from 7 to 8
            move 1 from 1 to 6
            move 5 from 6 to 5
            move 21 from 8 to 9
            move 5 from 1 to 9
            move 2 from 9 to 5
            move 3 from 5 to 6
            move 3 from 7 to 9
            move 4 from 4 to 6
            move 6 from 8 to 7
            move 6 from 6 to 3
            move 2 from 7 to 9
            move 1 from 7 to 2
            move 6 from 3 to 2
            move 1 from 6 to 4
            move 4 from 5 to 9
            move 1 from 4 to 5
            move 9 from 4 to 6
            move 7 from 6 to 4
            move 10 from 9 to 2
            move 5 from 7 to 5
            move 10 from 2 to 7
            move 2 from 5 to 4
            move 2 from 5 to 9
            move 4 from 9 to 4
            move 1 from 8 to 6
            move 7 from 7 to 2
            move 1 from 5 to 4
            move 2 from 7 to 1
            move 1 from 5 to 7
            move 3 from 6 to 2
            move 4 from 4 to 5
            move 1 from 2 to 7
            move 10 from 4 to 7
            move 3 from 7 to 3
            move 17 from 9 to 4
            move 1 from 1 to 4
            move 1 from 1 to 5
            move 5 from 2 to 7
            move 1 from 9 to 2
            move 5 from 4 to 8
            move 2 from 9 to 7
            move 4 from 8 to 1
            move 3 from 4 to 8
            move 1 from 2 to 5
            move 1 from 9 to 2
            move 6 from 4 to 8
            move 3 from 7 to 5
            move 1 from 4 to 9
            move 1 from 9 to 1
            move 3 from 1 to 9
            move 4 from 8 to 5
            move 2 from 9 to 8
            move 4 from 2 to 5
            move 8 from 7 to 2
            move 5 from 8 to 5
            move 2 from 7 to 8
            move 1 from 3 to 5
            move 1 from 1 to 2
            move 1 from 1 to 6
            move 2 from 3 to 6
            move 5 from 2 to 8
            move 4 from 7 to 1
            move 7 from 8 to 5
            move 1 from 1 to 5
            move 3 from 8 to 3
            move 1 from 9 to 3
            move 7 from 2 to 3
            move 2 from 2 to 8
            move 2 from 4 to 8
            move 1 from 8 to 5
            move 1 from 1 to 4
            move 2 from 4 to 7
            move 2 from 7 to 1
            move 3 from 2 to 3
            move 3 from 5 to 2
            move 1 from 8 to 3
            move 3 from 3 to 2
            move 5 from 2 to 1
            move 17 from 5 to 8
            move 9 from 8 to 1
            move 11 from 3 to 5
            move 8 from 8 to 5
            move 2 from 8 to 5
            move 16 from 1 to 4
            move 13 from 4 to 7
            move 6 from 5 to 2
            move 2 from 4 to 8
            move 5 from 7 to 9
            move 2 from 1 to 2
            move 7 from 7 to 1
            move 1 from 1 to 4
            move 1 from 9 to 8
            move 7 from 2 to 8
            move 1 from 4 to 7
            move 2 from 9 to 4
            move 1 from 4 to 1
            move 1 from 3 to 5
            move 2 from 9 to 8
            move 11 from 8 to 7
            move 2 from 6 to 5
            move 1 from 6 to 9
            move 1 from 1 to 9
            move 1 from 9 to 1
            move 4 from 1 to 4
            move 2 from 1 to 8
            move 1 from 1 to 2
            move 1 from 9 to 5
            move 2 from 4 to 3
            move 2 from 2 to 7
            move 2 from 3 to 9
            move 1 from 9 to 1
            move 1 from 9 to 1
            move 5 from 5 to 1
            move 19 from 5 to 6
            move 5 from 1 to 4
            move 1 from 2 to 9
            move 1 from 1 to 3
            move 7 from 5 to 8
            move 1 from 3 to 6
            move 8 from 7 to 3
            move 7 from 4 to 8
            move 3 from 8 to 5
            move 1 from 4 to 1
            move 1 from 9 to 4
            move 1 from 4 to 9
            move 1 from 5 to 2
            move 2 from 5 to 6
            move 2 from 8 to 2
            move 7 from 8 to 1
            move 1 from 1 to 7
            move 3 from 6 to 9
            move 2 from 3 to 2
            move 1 from 2 to 1
            move 1 from 8 to 7
            move 2 from 9 to 6
            move 2 from 9 to 5
            move 1 from 5 to 6
            move 1 from 2 to 8
            move 2 from 1 to 7
            move 1 from 4 to 3
            move 3 from 2 to 5
            move 7 from 1 to 3
            move 10 from 3 to 4
            move 3 from 5 to 4
            move 1 from 3 to 8
            move 3 from 3 to 2
            move 1 from 8 to 1
            move 1 from 1 to 3
            move 3 from 8 to 3
            move 5 from 4 to 6
            move 1 from 2 to 3
            move 4 from 6 to 4
            move 1 from 5 to 7
            move 4 from 3 to 4
            move 1 from 2 to 8
            move 12 from 7 to 6
            move 1 from 8 to 2
            move 2 from 2 to 7
            move 1 from 8 to 4
            move 23 from 6 to 3
            move 14 from 3 to 6
            move 15 from 4 to 6
            move 1 from 8 to 6
            move 10 from 3 to 7
            move 2 from 4 to 2
            move 11 from 7 to 8
            move 2 from 2 to 6
            move 44 from 6 to 9
            move 21 from 9 to 3
            move 12 from 3 to 6
            move 1 from 7 to 4
            move 1 from 4 to 7
            move 9 from 3 to 2
            move 2 from 8 to 6
            move 3 from 2 to 4
            move 17 from 9 to 1
            move 3 from 4 to 6
            move 2 from 2 to 9
            move 4 from 9 to 2
            move 10 from 6 to 9
            move 1 from 7 to 6
            move 4 from 9 to 5
            move 4 from 2 to 4
            move 14 from 1 to 5
            move 4 from 4 to 3
            move 3 from 2 to 9
            move 9 from 9 to 7
            move 1 from 2 to 5
            move 9 from 8 to 5
            move 8 from 7 to 2
            move 4 from 3 to 8
            move 5 from 6 to 2
            move 3 from 1 to 6
            move 1 from 7 to 1
            move 4 from 2 to 4
            move 3 from 6 to 4
            move 3 from 8 to 3
            move 13 from 5 to 2
            move 2 from 3 to 5
            move 12 from 5 to 9
            move 1 from 3 to 5
            move 1 from 5 to 9
            move 1 from 8 to 3
            move 4 from 9 to 5
            move 6 from 4 to 5
            move 12 from 9 to 7
            move 1 from 9 to 3
            move 1 from 3 to 2
            move 12 from 5 to 6
            move 12 from 7 to 2
            move 1 from 3 to 7
            move 1 from 4 to 8
            move 33 from 2 to 8
            move 1 from 7 to 5
            move 1 from 1 to 2
            move 4 from 5 to 4
            move 3 from 2 to 5
            move 34 from 8 to 6
            move 1 from 4 to 3
            move 1 from 5 to 7
            move 1 from 7 to 5
            move 3 from 4 to 9
            move 2 from 9 to 7
            move 1 from 9 to 4
            move 1 from 3 to 7
            move 1 from 5 to 8
            move 1 from 5 to 1
            move 1 from 5 to 7
            move 1 from 4 to 8
            move 1 from 1 to 4
            move 1 from 4 to 2
            move 3 from 7 to 5
            move 2 from 8 to 5
            move 1 from 2 to 8
            move 4 from 6 to 2
            move 1 from 8 to 6
            move 1 from 7 to 9
            move 29 from 6 to 7
            move 4 from 2 to 3
            move 2 from 5 to 8
            move 1 from 9 to 5
            move 2 from 8 to 1
            move 23 from 7 to 5
            move 2 from 6 to 1
            move 23 from 5 to 6
            move 1 from 3 to 6
            move 4 from 5 to 9
            move 2 from 1 to 3
            move 5 from 3 to 8
            move 2 from 6 to 5
            move 2 from 1 to 4
            move 1 from 9 to 8
            move 1 from 9 to 1
            move 1 from 4 to 6
            move 2 from 5 to 6
            move 6 from 7 to 8
            move 2 from 9 to 2
            move 18 from 6 to 5
            move 21 from 6 to 4
            move 1 from 1 to 6
            move 2 from 6 to 7
            move 2 from 7 to 9
            move 2 from 2 to 8
            move 7 from 4 to 3
            move 12 from 5 to 3
            move 1 from 9 to 5
            move 1 from 9 to 4
            move 6 from 5 to 2
            move 17 from 3 to 4
            move 3 from 4 to 3
            move 1 from 2 to 4
            move 5 from 2 to 8
            move 1 from 5 to 8
            move 19 from 8 to 7
            move 1 from 3 to 6
            move 1 from 8 to 4
            move 1 from 6 to 1
            move 15 from 4 to 6
            move 1 from 1 to 4
            move 3 from 3 to 5
            move 4 from 6 to 7
            move 1 from 4 to 7
            move 10 from 6 to 7
            move 16 from 4 to 5
            move 24 from 7 to 2
            move 8 from 7 to 8
            move 1 from 4 to 2
            move 6 from 8 to 7
            move 1 from 8 to 7
            move 1 from 6 to 9
            move 14 from 5 to 4
            move 9 from 7 to 8
            move 4 from 5 to 1
            move 2 from 1 to 5
            move 3 from 8 to 6
            move 2 from 6 to 9
            move 2 from 2 to 8
            move 6 from 2 to 7
            move 3 from 4 to 6
            move 1 from 3 to 4
            move 3 from 5 to 7
            move 1 from 6 to 9
            move 5 from 7 to 2
            move 4 from 9 to 1
            move 1 from 7 to 9
            move 9 from 8 to 4
            move 5 from 1 to 2
            move 2 from 6 to 1
            move 6 from 4 to 7
            move 1 from 7 to 3
            move 1 from 3 to 9
            move 1 from 9 to 7
            move 1 from 6 to 7
            move 9 from 4 to 5
            move 7 from 7 to 9
            move 3 from 7 to 5
            move 1 from 9 to 2
            move 6 from 9 to 8
            move 4 from 4 to 5
            move 1 from 4 to 2
            move 1 from 4 to 2
            move 2 from 1 to 2
            move 1 from 9 to 8
            move 10 from 2 to 4
            move 8 from 2 to 7
            move 12 from 2 to 9
            move 6 from 7 to 4
            move 1 from 1 to 2
            move 8 from 9 to 8
            move 7 from 5 to 1
            move 9 from 4 to 3
            move 14 from 8 to 4
            move 1 from 8 to 4
            move 1 from 1 to 5
            move 1 from 5 to 2
            move 3 from 2 to 4
            move 1 from 7 to 1
            move 1 from 7 to 3
            move 2 from 1 to 7
            move 3 from 5 to 7
            move 2 from 7 to 6
            move 1 from 6 to 5
            move 3 from 7 to 1
            move 1 from 6 to 8
            move 1 from 8 to 7
            move 1 from 3 to 6
            move 1 from 7 to 1
            move 4 from 1 to 4
            move 6 from 3 to 2
            move 3 from 1 to 2
            move 3 from 3 to 6
            move 3 from 2 to 6
            move 6 from 6 to 5
            move 1 from 1 to 4
            move 1 from 9 to 6
            move 5 from 2 to 1
            move 3 from 1 to 2
            move 2 from 9 to 8
            move 3 from 1 to 5
            move 1 from 9 to 7
            move 25 from 4 to 1
            move 1 from 1 to 7
            move 2 from 8 to 3
            move 13 from 1 to 9
            move 2 from 3 to 5
            move 8 from 5 to 9
            move 4 from 2 to 1
            move 2 from 6 to 7
            move 10 from 5 to 9
            move 4 from 7 to 2
            move 2 from 2 to 3
            move 9 from 9 to 2
            move 4 from 4 to 5
            move 4 from 5 to 4
            move 5 from 1 to 4
            move 10 from 4 to 5
            move 22 from 9 to 1
            move 2 from 2 to 7
            move 3 from 2 to 1
            move 6 from 2 to 6
            move 1 from 7 to 1
            move 10 from 5 to 7
            move 15 from 1 to 4
            move 13 from 1 to 5
            move 3 from 6 to 8
            move 1 from 8 to 9
            """;
}