package aoc22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class InputLoader {

    String pathToFile;

    public InputLoader(String path) {
        pathToFile = path;
    }

    public List<Integer> readToListInts() {

        List<Integer> integerList = new LinkedList<>();

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(pathToFile);
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();

            while (line != null) {

                integerList.add(Integer.parseInt(line));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return integerList;
    }

    public List<String> readToStringList() {

        List<String> stringList = new LinkedList<>();

        try {
            BufferedReader reader = getReader();

            String line = reader.readLine();

            while (line != null) {

                stringList.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return stringList;
    }

    public String readSingleLine(){
        String line = "";
        try {
            BufferedReader reader = getReader();
            line = reader.readLine();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return line;
    }

    private BufferedReader getReader() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(pathToFile);
        assert inputStream != null;
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}