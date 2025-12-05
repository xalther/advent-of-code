package org.example.day5;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cafeteria {
    List<String> inputValuesRanges;
    List<String> ingredientIds;

    {
        try {
            var ranges = getClass().getClassLoader().getResource("day5ranges.txt");
            var ids = getClass().getClassLoader().getResource("day5ids.txt");
            inputValuesRanges = Files.readAllLines(Path.of(ranges.toURI()));
            ingredientIds = Files.readAllLines(Path.of(ids.toURI()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        countFreshIngredients();
    }

    public void countFreshIngredients() {
        Set<Long> freshIngredients = new HashSet<>();

        for (String inputValuesRange : inputValuesRanges) {
            String[] split = inputValuesRange.split("-");
            long start = Long.parseLong(split[0]);
            long end = Long.parseLong(split[1]);

            for (String ingredientId : ingredientIds) {
                long val = Long.parseLong(ingredientId);

                if (val >= start && val <= end) {
                    freshIngredients.add(val);
                }
            }
        }
        System.out.println(freshIngredients.size());
    }
}
