package aoc22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//Class to find the position of letter characters in a file
public class LetterPosition {
    public List<String> loadFile(String path) throws IOException{
        InputLoader fileReader = new InputLoader(path);
        return fileReader.readToStringList();
    }
    public String findLetterPositions(String filename, int row) throws IOException {

 List<String> input = loadFile("src/main/resources/5test.txt");
        int resume = 0;
        ArrayList<String> boxList = new ArrayList<>();
        ArrayList<Integer> idxList = new ArrayList<>();
        ArrayList<String> arrangedList = new ArrayList<>();

        for (int i = 0; i<input.size(); i++){
            if (input.get(i).equals("")){
                resume = i;
                break;
            }

            boxList.add(input.get(i));
        }

        for (int i = boxList.size()-1; i>=0; i--){
            if (i == boxList.size()-1){
                for (int idx = 0; idx<boxList.get(i).length(); idx++){
                    if((int)boxList.get(i).charAt(idx) >= 49 && (int)boxList.get(i).charAt(idx) <= 57){
                        idxList.add(idx);
                        arrangedList.add("");
                    }
                }
            }

            for (int u = 0; u<idxList.size(); u++){
                if(boxList.get(i).charAt(idxList.get(u)) != ' '){
                    arrangedList.set(u, arrangedList.get(u)+boxList.get(i).charAt(idxList.get(u)));
                }
            }
        }

        String[] split;
        String letters;
        int move, from, to;

        for (int i = resume+1; i<input.size(); i++){
            split = input.get(i).replaceAll("[^\\d.]", " ").strip().split("\\W+");
            move = Integer.parseInt(split[0]);
            from = Integer.parseInt(split[1]);
            to = Integer.parseInt(split[2]);
            letters = arrangedList.get(from-1);
            letters = letters.substring(letters.length()-move);

            StringBuffer reverse = new StringBuffer(letters);
            reverse.reverse();

            arrangedList.set(from-1, arrangedList.get(from-1).substring(0, arrangedList.get(from-1).length()-move));
            arrangedList.set(to-1, arrangedList.get(to-1)+reverse);
        }

        String result = "";

        for (int i = 0; i<arrangedList.size(); i++){
            result = result+arrangedList.get(i).substring(arrangedList.get(i).length()-1);
        }

//        System.out.println(result);
    return result;
    }



        /*try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int currentRow=0;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isLetter(c)) {
                        System.out.println("Found letter '" + c + "' at position " + i + " in line '" + currentRow + "'");
                    }
                }
                currentRow++;
                if(currentRow==row)
                    break;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }*/
    }



