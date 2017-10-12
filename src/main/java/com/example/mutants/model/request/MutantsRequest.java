package com.example.mutants.model.request;

import com.example.mutants.validator.DnaSequenceConstraint;

import java.util.List;

/**
 * Created by mnsantos on 10/2/17.
 */
public class MutantsRequest {
    @DnaSequenceConstraint
    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "MutantsRequest{" +
                "dna=" + dna +
                '}';
    }
}
