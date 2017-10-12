package com.example.mutants.service;

import java.util.List;

/**
 * Created by mnsantos on 10/2/17.
 */
public interface MutantsDetector {

    /**
     * @param dna Una lista de Strings que representan
     * cada fila de una tabla de (NxN) con la secuencia del ADN.
     * @return Retorna true si la secuencia de ADN es mutante, false en caso contrario.
     * La secuencia es mutante si existe más de una secuencia de cuatro letras
     * iguales​, de forma oblicua, horizontal o vertical.
     */

    boolean isMutant(List<String> dna);
}
