package com.example.mutants.service;

import com.example.mutants.model.response.MutantsStatsResponse;

/**
 * Created by mnsantos on 10/8/17.
 */
public interface MutantsService extends MutantsDetector {

    /**
     * @return devuelve las estadisticas de las verificaciones de ADN.
     * Solo hay un registro por ADN consultado.
     */
    MutantsStatsResponse stats();
}
