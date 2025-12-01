package org.example.day1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SecretEntrance {
    List<String> inputValues;
    int totalDialPoints = 0;
    int currentDial = 50;
    int dialMax = 99;
    LinkedHashMap<String, Integer> dialInstructionsMap = new LinkedHashMap<>();

    {
        try {
            var resource = getClass().getClassLoader().getResource("day1.txt");
            inputValues = Files.readAllLines(Path.of(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        parseDialInstructionsToMap();
        encryptPassword();
    }

    public void parseDialInstructionsToMap() {
        for (int i = 0; i < inputValues.size(); i++) {
            var el = inputValues.get(i);
            var direction = "" + el.charAt(0) + i;
            var dialValue = Integer.parseInt(el.substring(1));
            dialInstructionsMap.put(direction, dialValue);
        }
    }

    public int encryptPassword() {
        for (Map.Entry<String, Integer> entry : dialInstructionsMap.entrySet()) {
            var direction = entry.getKey().charAt(0);
            var value = entry.getValue();
            System.out.println("Starting at: " + currentDial);

            if (direction == 'L') {
                currentDial = (currentDial - value + (dialMax + 1)) % (dialMax + 1);
            } else {
                currentDial = (currentDial + value) % (dialMax + 1);
            }

            if (currentDial == 0) {
                totalDialPoints++;
            }

            System.out.println(direction + " -> " + value);
            System.out.println("The dial is rotated to: " + currentDial);

        }
        System.out.println(totalDialPoints);
        return totalDialPoints;
    }
}
