package graph;

import java.util.HashSet;
import java.util.List;

/**
 * Многослойный граф
 */
public class MultiLayeredGraph {
    private final List<GraphLayer> layers;
   public MultiLayeredGraph(List<GraphLayer> layers) {
        if (layers.isEmpty()) {
            throw new IllegalArgumentException("layers should more, than 0");
        }
        this.layers = layers;
    }

    public boolean isLinked() {
        for (GraphLayer layer : layers) {
            if (!layer.isLinked()) {
                return false;
            }
        }
        var intersection = new HashSet<>(layers.get(0).getNodes());
        for (int i = 1; i < layers.size(); i++) {
            intersection.retainAll(layers.get(i).getNodes());
        }
        System.out.println("==="+intersection);
        return !intersection.isEmpty();
    }
}


