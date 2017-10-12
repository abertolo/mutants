package com.example.mutants.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mnsantos on 10/9/17.
 */
public class DnaSequenceValidator implements ConstraintValidator<DnaSequenceConstraint, List<String>> {

    private static final Set<Character> VALID_CHARACTERS = Stream.of('A', 'T', 'C', 'G').collect(Collectors.toCollection(HashSet::new));

    @Override
    public void initialize(DnaSequenceConstraint dnaSequenceConstraint) {
    }

    @Override
    public boolean isValid(List<String> stringList, ConstraintValidatorContext constraintValidatorContext) {
        return isNxN(stringList) && hasValidLetters(stringList);
    }

    private boolean hasValidLetters(List<String> stringList) {
        for (String string : stringList) {
            for (char c : string.toCharArray()) {
                if (!VALID_CHARACTERS.contains(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNxN(List<String> stringList) {
        int n = stringList.size();
        return stringList.stream().allMatch(list -> list.length() == n);
    }

}
