package com.example.mutants.model.db;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by mnsantos on 10/7/17.
 */
public interface DnaSampleDao extends CrudRepository<DnaSample, Integer> {

    DnaSample findByDna(String dna);

    long countByMutant(boolean mutant);

}
