package com.knubisoft.tasks.algorithm.charsinstring;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class CharsInStringImpl implements CharsInString {
    @Override
    public Map<Character, Integer> charactersCount(String text) {
        Map<String, Long> charCount = text.codePoints().mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        Map<Character, Integer> charCount2 = text.chars().boxed()
                .collect(toMap(k -> (char) k.intValue(), v -> 1,
                        Integer::sum));

        return charCount2;
    }
}
