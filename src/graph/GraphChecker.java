package graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GraphChecker {

    public static void main(String[] args) throws IOException {
        var parser = new GraphParser();
        var file = "C:\\Users\\Артем\\IdeaProjects\\VKR2.0\\out\\production\\VKR2.0\\graph\\matrix.txt";
        var graphLayers = parser.parse(Files.readString(Path.of(file)));
        if (graphLayers.isLinked()) {
            System.out.println("Layered graph is linked");
        } else {
            System.out.println("Layered graph isn't linked");
        }
    }
}


