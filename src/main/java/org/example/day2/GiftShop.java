package org.example.day2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GiftShop {
    List<String> inputValues;
    List<String> ranges;
    List<Long> tempList = new ArrayList<>();
    long totalInvalidIds = 0;

    {
        try {
            var resource = getClass().getClassLoader().getResource("day2.txt");
            inputValues = Files.readAllLines(Path.of(resource.toURI()));

            String rangeLine = inputValues.get(0);
            ranges = Arrays.asList(rangeLine.split(","));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        calculateInvalidIds();
    }

    public void calculateInvalidIds() {
        for (String range : ranges) {
            String[] s = range.trim().split("-");

            var firstRange = Long.parseLong(s[0]);
            var secondRange = Long.parseLong(s[1]);

            for (long j = firstRange; j <= secondRange; j++) {
                var jStr = String.valueOf(j);

                if (jStr.length() % 2 == 0) {
                    int half = jStr.length() / 2;

                    String firstHalf = jStr.substring(0, half);
                    String secondHalf = jStr.substring(half);

                    if (firstHalf.equals(secondHalf)) {
                        tempList.add(j);
                    }
                }
            }
        }

        totalInvalidIds = tempList.stream().mapToLong(Long::longValue).sum();
        System.out.println("Total invalid IDs sum: " + totalInvalidIds);
    }
}


