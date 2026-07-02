package utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class InsertScores {

    /**
     * The file with all ranks.
     */
    private static final File FILE = new File("SuperMarioRunRanking.txt");

    /**
     * Insert the User name and score in a text file.
     * @param name
     * @param score
     * @throws IOException
     */
    public void insertOrderedUserScore(final String name, final String score) throws IOException {

        List<String> map = new ArrayList<>();

        if (!FILE.exists()) {       //Check if the File already EXISTS and IF NOT, Creates a New File
            FILE.createNewFile();
        }

        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE.getPath()))) {
            map = br.lines().collect(Collectors.toList());
        }

        //the line is composed by the name and the relative score
        String line = name + "," + score;

        boolean flag = false;
        String temp;
        String lastScore;
        int newIndex = map.size();

        for (int i = 0; i < map.size(); i++) {
            flag = false;
            temp = map.get(i);
            lastScore = "";
            char c;
            for (int j = 0; j < temp.length(); j++) {
                if (flag) {
                    lastScore = lastScore + temp.charAt(j);
                }
                c = temp.charAt(j);
                if (c == ',') {
                    flag = true;
                }
            }

            if (Integer.parseInt(lastScore) < Integer.parseInt(score)) {
                newIndex = i;
                i = map.size() + 1;
            }
        }

        map.add(newIndex, line);

        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE.getPath()))) {
            Iterator<String> it = map.iterator();
            while (it.hasNext()) {
                bw.write(it.next());
                bw.newLine();
            }
        }
    }
}
