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


    public void importerDonnees(String fichier) throws IOException {

        JSONImporter<String, DefaultEdge> importer = new JSONImporter<>();
        try (Reader reader = new FileReader(fichier)) {
            importer.importGraph(graphe, reader);
        }
    }

    public Graph<String, DefaultEdge> getGraphe() {
        return graphe;
    }
}