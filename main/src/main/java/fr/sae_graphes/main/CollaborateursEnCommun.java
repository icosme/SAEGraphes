package fr.sae_graphes.main;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.*;

public class CollaborateursEnCommun {
    private Graph<String, DefaultEdge> graphe;

    public CollaborateursEnCommun() {
        this.graphe = new SimpleGraph<>(DefaultEdge.class);
    }

    public Graph<String, DefaultEdge> getGraphe() {
        return graphe;
    }

    public Set<String> getCollaborateursEnCommun(String nom1, String nom2) {
        Set<String> col1 = new HashSet<>();
        Set<String> col2 = new HashSet<>();
        Set<DefaultEdge> edges1 = graphe.outgoingEdgesOf(nom1);
        Set<DefaultEdge> edges2 = graphe.outgoingEdgesOf(nom2);
        for (DefaultEdge edge : edges1) {
            String collaborateur1 = graphe.getEdgeTarget(edge);
            col1.add(collaborateur1);
        }
        for (DefaultEdge edge : edges2) {
            String collaborateur2 = graphe.getEdgeTarget(edge);
            col2.add(collaborateur2);
        }
        col1.retainAll(col2);
        return col1;
    }
}