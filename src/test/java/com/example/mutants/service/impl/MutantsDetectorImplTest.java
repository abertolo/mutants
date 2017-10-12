package com.example.mutants.service.impl;

import com.example.mutants.service.MutantsDetector;
import com.example.mutants.service.MutantsDetectorTest;

/**
 * Created by mnsantos on 10/8/17.
 */
public class MutantsDetectorImplTest extends MutantsDetectorTest {
    @Override
    protected MutantsDetector createInstance() {
        MutantsDetectorImpl mutantsDetector = new MutantsDetectorImpl();
        mutantsDetector.setMutantSequencesRequired(2);
        mutantsDetector.setRepetitions(4);
        return mutantsDetector;
    }
}
