package org.example.day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lobby {
    List<String> inputValues;
    List<Integer> maxVoltagesList = new ArrayList<>();
    {
        try {
            var resource = getClass().getClassLoader().getResource("day3.txt");
            inputValues = Files.readAllLines(Path.of(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        findMaximumVoltage();
    }

    public void findMaximumVoltage() {
        for (final String value : inputValues) {
            final var batteries = Arrays.stream(value.split("")).map(Integer::parseInt).toList();
            int maxVal = 0;

            for (int i = 0; i < batteries.size() - 1; i++) {
                for (int j = i + 1; j < batteries.size(); j++) {
                    int current = batteries.get(i) * 10 + batteries.get(j);
                    if (current > maxVal) {
                        maxVal = current;
                    }
                }
            }
            maxVoltagesList.add(maxVal);
        }
       System.out.println(maxVoltagesList.stream().reduce(Integer::sum));
    }
}
