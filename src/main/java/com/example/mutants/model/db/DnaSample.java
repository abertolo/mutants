package com.example.mutants.model.db;

import javax.persistence.*;

/**
 * Created by mnsantos on 10/7/17.
 */

@Entity
@Table(name = "DnaSample", indexes = {@Index(name = "mutant", columnList = "mutant")})
public class DnaSample {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String dna;
    private boolean mutant;

    public DnaSample() {

    }

    public DnaSample(String dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DnaSample dnaSample = (DnaSample) o;

        if (mutant != dnaSample.mutant) return false;
        if (id != null ? !id.equals(dnaSample.id) : dnaSample.id != null) return false;
        return dna != null ? dna.equals(dnaSample.dna) : dnaSample.dna == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dna != null ? dna.hashCode() : 0);
        result = 31 * result + (mutant ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DnaSample{" +
                "id=" + id +
                ", dna='" + dna + '\'' +
                ", mutant=" + mutant +
                '}';
    }
}
