package com.example.mutants.service.impl;

import com.example.mutants.service.MutantsDetector;
import com.example.mutants.utils.Coordinate;
import com.example.mutants.utils.MatrixBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnsantos on 10/2/17.
 */

@Service("MutantsDetectorImpl")
public class MutantsDetectorImpl implements MutantsDetector {

    private enum Direction {
        RIGHT, DOWN, OBLIQUE_RIGHT_DOWN, OBLIQUE_LEFT_DOWN
    }

    @Value("${com.example.mutants.service.impl.MutantsDetectorImpl.repetitions:4}")
    private Integer repetitions;
    @Value("${com.example.mutants.service.impl.MutantsDetectorImpl.mutantSequencesRequired:2}")
    private Integer mutantSequencesRequired;

    /**
     * {@link MutantsDetector#isMutant(List)}
     * Idea del algoritmo: recorrer la matriz de izquierda a derecha comenzando desde arriba a la izquierda
     * casillero por casillero. En cada casillero se verifica si existe una secuencia
     * de caracteres repetidos en 4 direcciones: DERECHA, ABAJO, DIAGONAL ABAJO IZQUIERDA, DIAGONAL ABAJO DERECHA.
     * No se verifican las direcciones restantes (IZQUIERDA, ARRIBA, DIAGONAL ARRIBA IZQUIERDA, DIAGONAL ARRIBA DERECHA)
     * para evitar contar dos veces la ocurrencia de una secuencia repetida y porque además es innecesario por la forma en que
     * se recorre la matriz (de derecha izquierda y de arriba hacia abajo). Si en algun punto del recorrido de la matriz se
     * supera la cantidad necesaria de secuencias repetidas (mutantSequencesRequired) se corta la busqueda retornando true.
     * Si al finalizar el recorrido de la matriz no se encuentra mas de una secuencia repetida, se retorna false.
     */

    @Override
    public boolean isMutant(List<String> dna) {
        char[][] matrix = MatrixBuilder.buildMatrix(dna);
        int n = matrix.length;
        int mutantSequencesFound = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Coordinate position = new Coordinate(i, j);
                char charAtPosition = matrix[i][j];
                List<List<Coordinate>> routes = generatePossibleRoutes(position, n);
                for (List<Coordinate> route : routes) {
                    if (isMutantSequence(route, charAtPosition, matrix)) {
                        mutantSequencesFound += 1;
                        if (mutantSequencesFound >= mutantSequencesRequired) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param position coordenada inicial
     * @param n tamaño de la matriz
     * @return una lista de rutas (lista de coordenadas) posibles en cada
     * direccion siempre y cuando no se escape de los limites de la matriz
     */

    private List<List<Coordinate>> generatePossibleRoutes(Coordinate position, int n) {
        List<List<Coordinate>> possibleRoutes = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            if (isReachableFromPosition(position, n, direction)) {
                possibleRoutes.add(generateRoute(position, direction));
            }
        }
        return possibleRoutes;
    }

    /**
     *
     * @param position coordenada inicial
     * @param n tamaño de la matriz
     * @param direction
     * @return si es posible llegar al casillero más alejado
     * en la direccion recibida como parametro
     */

    private boolean isReachableFromPosition(Coordinate position, int n, Direction direction) {
        int x = position.getX();
        int y = position.getY();
        switch (direction) {
            case RIGHT:
                return (y + repetitions - 1) < n;
            case OBLIQUE_LEFT_DOWN:
                return ((x + repetitions - 1) < n && (y - repetitions + 1) >= 0);
            case OBLIQUE_RIGHT_DOWN:
                return ((x + repetitions - 1) < n && (y + repetitions - 1) < n);
            case DOWN:
                return (x + repetitions - 1) < n;
            default:
                return false;
        }
    }

    /**
     *
     * @param position
     * @param direction
     * @return una ruta (lista de coordenadas)
     * para la direccion recibida como parametro
     */

    private List<Coordinate> generateRoute(Coordinate position, Direction direction) {
        int x = position.getX();
        int y = position.getY();
        List<Coordinate> coordinates = new ArrayList<>();
        switch (direction) {
            case RIGHT:
                for (int i = 1; i < repetitions; i++) {
                    coordinates.add(new Coordinate(x, y + i));
                }
                return coordinates;
            case OBLIQUE_LEFT_DOWN:
                for (int i = 1; i < repetitions; i++) {
                    coordinates.add(new Coordinate(x + i, y - i));
                }
                return coordinates;
            case OBLIQUE_RIGHT_DOWN:
                for (int i = 1; i < repetitions; i++) {
                    coordinates.add(new Coordinate(x + i, y + i));
                }
                return coordinates;
            case DOWN:
                for (int i = 1; i < repetitions; i++) {
                    coordinates.add(new Coordinate(x + i, y));
                }
                return coordinates;
            default:
                return coordinates;
        }
    }

    /**
     *
     * @param route
     * @param character
     * @param matrix
     * @return si una ruta es una secuencia mutante, es decir, si todas las coordenadas
     * contienen el caracter pasado como parametro
     */

    private boolean isMutantSequence(List<Coordinate> route, char character, char[][] matrix) {
        for (Coordinate coordinate : route) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if (character != matrix[x][y]) {
                return false;
            }
        }
        return true;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public void setMutantSequencesRequired(Integer mutantSequencesRequired) {
        this.mutantSequencesRequired = mutantSequencesRequired;
    }
}
