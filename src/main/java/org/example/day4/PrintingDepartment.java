package org.example.day4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PrintingDepartment {
    private final List<String> inputValues;
    {
        try {
            var resource = getClass().getClassLoader().getResource("day4.txt");
            inputValues = Files.readAllLines(Path.of(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        countRolls();
    }

    public void countRolls() {
        int count = 0;

        for (int r = 0; r < inputValues.size(); r++) {
            String row = inputValues.get(r);
            for (int c = 0; c < row.length(); c++) {
                if (row.charAt(c) == '@') {
                    int adjacent = 0;

                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            if (dr == 0 && dc == 0) continue;

                            int nr = r + dr;
                            int nc = c + dc;

                            if (nr >= 0 && nr < inputValues.size() &&
                                    nc >= 0 && nc < inputValues.get(nr).length() &&
                                    inputValues.get(nr).charAt(nc) == '@') {
                                adjacent++;
                            }
                        }
                    }

                    if (adjacent < 4) count++;
                }
            }
        }

         System.out.println(count);
    }
}
