package fr.sae_graphes.main;

import java.util.Set;
import java.util.HashSet;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class CollaborateursProches {
            private static Set<String> getVoisins(Graph<String, DefaultEdge> graphe, String acteur) {
        Set<String> voisins = new HashSet<>();
        
        for (DefaultEdge edge : graphe.edgesOf(acteur)) {
            String voisin;
            if (graphe.getEdgeSource(edge).equals(acteur)) {
                voisin = graphe.getEdgeTarget(edge);
            } else {
                voisin = graphe.getEdgeSource(edge);
            }
            voisins.add(voisin);
        }
        
        return voisins;
    }
    public static Set<String> collaborateur_proches(Graph<String, DefaultEdge> G,String u,Integer k) throws PasDeTelActeurException{
        if (!G.containsVertex(u)){
            System.out.println(u+" est un illustre inconnu");
            throw new PasDeTelActeurException();
        }
        Set<String> col = new HashSet<>();
        col.add(u);
        for(int i = 0;i < k; ++i){
            Set<String> collaborateurs_directs = new HashSet<>();
            for (String c : col){
                for (String v : getVoisins(G,c)){
                    if (!collaborateurs_directs.contains(v)){
                        collaborateurs_directs.add(v);
                    }
                }
            }
            col.addAll(collaborateurs_directs);
        }
        return col;
    }
}
