package com.example.mutants.model.response;

/**
 * Created by mnsantos on 10/8/17.
 */
public class MutantsStatsResponse {
    private Long countMutantDna;
    private Long countHumanDna;
    private Double ratio;

    public MutantsStatsResponse(Long countMutantDna, Long countHumanDna, Double ratio) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    public Long getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
