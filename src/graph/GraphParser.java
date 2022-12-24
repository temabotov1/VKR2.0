package graph;

import java.util.Arrays;
import java.util.stream.Collectors;
public class GraphParser {
    public MultiLayeredGraph parse(String graphData) {
        if (graphData.isBlank()) {
            throw new IllegalArgumentException("graphData should not be blank");
        }
        var layers = Arrays.stream(graphData.split("@"))
                .map(String::trim)
                .map(this::parseLayer)
                .collect(Collectors.toList());
        return new MultiLayeredGraph(layers);
    }
    private GraphLayer parseLayer(String graphLayer) {
        int[][] matrix = Arrays.stream(graphLayer.split("\r\n"))
                .map(this::parseLayerRow)
                .toArray(int[][]::new);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        return new GraphLayer(matrix);
    }
    private int[] parseLayerRow(String layerRow) {
        return Arrays.stream(layerRow.split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }
}


