package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
public class GraphLayer {

    /**
     * Связи каждой вершины графа с другими вершинами.
     */
    private final Map<Integer, Set<Integer>> nodeRelations;

    public GraphLayer(int[][] matrix) {
        if (matrix.length < 2) {
            throw new IllegalArgumentException("matrix too small");
        }
        this.nodeRelations = convertMatrixToNodeRelations(matrix);
    }

    private Map<Integer, Set<Integer>> convertMatrixToNodeRelations(int[][] matrix) {
        var relations = new HashMap<Integer, Set<Integer>>();
        for (int i = 1; i < matrix.length; i++) {
            var node = matrix[i][0];
            var nodeRelations = new HashSet<Integer>();
            for (int j = 1; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    nodeRelations.add(matrix[0][j]);
                }
            }
            relations.put(node, nodeRelations);
        }
        System.out.println(relations);
        return relations;
    }

    public Set<Integer> getNodes() {
        return nodeRelations.keySet();
    }

    public boolean isLinked() {
        var nodes = new ArrayList<>(nodeRelations.keySet());
        System.out.println("----"+nodes);
        var node = nodes.get(0);
        var path = Collections.singletonList(node);
        for (int i = 1; i < nodes.size(); i++) {
            boolean pathExists = isPathExists(node, nodes.get(i), path);
            if (!pathExists) {
                return false;
            }
        }
        return true;
    }

    private boolean isPathExists(int fromNode, int toNode, List<Integer> path) {
        var relations = nodeRelations.get(fromNode);
        for (int nextFromNode : relations) {
            if (nextFromNode == toNode) {
                return true;
            }
            if (path.contains(nextFromNode)) {
                continue;
            }
            var newPath = new ArrayList<>(path);
            newPath.add(nextFromNode);
            boolean pathExists = isPathExists(nextFromNode, toNode, newPath);
            if (pathExists) {
                return true;
            }
        }
        return false;
    }
}

