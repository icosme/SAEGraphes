import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GrapheCollaborations {
    private Graph<String, DefaultEdge> graphe;

    public GrapheCollaborations() {
        this.graphe = new SimpleGraph<>(DefaultEdge.class);
    }

    public Graph<String, DefaultEdge> getGraphe() {
        return graphe;
    }
}
