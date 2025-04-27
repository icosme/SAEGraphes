import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.util.Pair;

import java.util.*;

public class CollaborateursEnCommun {
    public static Set<String> collaborateursEnCommun(Graph<String, DefaultEdge> graphe, String acteur1, String acteur2) {
        Set<String> voisinsActeur1 = new HashSet<>(Graphs.neighborListOf(graphe, acteur1));
        Set<String> voisinsActeur2 = new HashSet<>(Graphs.neighborListOf(graphe, acteur2));

        voisinsActeur1.retainAll(voisinsActeur2);
        return voisinsActeur1;
    }
}