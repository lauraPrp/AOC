package aoc22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterArray {

    //Attempt to solve day5 loading the input file in an Arraay
    public static void main(String[] args) {

        String fileName = "src/main/resources/5test.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Crea un array multidimensionale per contenere il contenuto del file
            char[][] letters = new char[12][12]; // 100 è una dimensione di esempio, potrebbe essere necessario modificarla
            int row = 0; // Indice per scorrere le righe dell'array multidimensionale
            // Legge il file riga per riga
            String line;
            int col = 0;
            int maxCurrentRow=3;

            while ((line = reader.readLine()) !=  null ) {
                  col = 0;
                 // Indice per scorrere le colonne dell'array multidimensionale
                // Aggiunge i caratteri della riga corrente all'array multidimensionale
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isLetter(c)) {
                        letters[row][col++] = c;
                    } else {
                        letters[row][col++] = 0;
                    }
                }
                row++;
                maxCurrentRow--;
                if(maxCurrentRow==0)
                    break;
            }
            // Stampa l'array multidimensionale
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(letters[i][j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}

