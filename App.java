package fr.sae_graphes.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

public class App {
    public static void main(String[] args) {
        try {
            // Chemin vers le fichier de données
            String cheminFichier = "data_100.txt"; // Ajustez ce chemin selon l'emplacement de votre fichier
            
            // Création d'une instance de ConvertisseurJSON
            ConvertisseurJSON convertisseur = new ConvertisseurJSON();
            
            // Conversion du fichier JSON en graphe
            Graph<String, DefaultEdge> graphe = convertisseur.jsonVersGraphe(cheminFichier);
            
            // VÉRIFICATIONS DE BASE
            System.out.println("=== VÉRIFICATIONS DE BASE ===");
            System.out.println("Nombre d'acteurs (sommets) : " + graphe.vertexSet().size());
            System.out.println("Nombre de collaborations (arêtes) : " + graphe.edgeSet().size());
            
            if (graphe.vertexSet().size() > 0) {
                System.out.println("✅ Le graphe contient des acteurs");
            } else {
                System.out.println("❌ Aucun acteur trouvé dans le graphe");
            }
            
            if (graphe.edgeSet().size() > 0) {
                System.out.println("✅ Le graphe contient des collaborations");
            } else {
                System.out.println("❌ Aucune collaboration trouvée");
            }
            
            System.out.println("\n=== EXEMPLES D'ACTEURS ===");
            List<String> listeActeurs = new ArrayList<>(graphe.vertexSet());
            int nombreAAfficher = Math.min(5, listeActeurs.size());
            
            for (int i = 0; i < nombreAAfficher; i++) {
                String acteur = listeActeurs.get(i);
                System.out.println("- " + acteur + " (degré: " + graphe.degreeOf(acteur) + ")");
            }
            
            System.out.println("\n=== EXEMPLES DE COLLABORATIONS ===");
            List<DefaultEdge> listeAretes = new ArrayList<>(graphe.edgeSet());
            nombreAAfficher = Math.min(5, listeAretes.size());
            
            for (int i = 0; i < nombreAAfficher; i++) {
                DefaultEdge arete = listeAretes.get(i);
                String source = graphe.getEdgeSource(arete);
                String target = graphe.getEdgeTarget(arete);
                System.out.println("- " + source + " a collaboré avec " + target);
            }
            
            String acteurTest = "[[Al Pacino]]"; 
            if (graphe.containsVertex(acteurTest)) {
                System.out.println("\n=== VÉRIFICATION POUR " + acteurTest + " ===");
                System.out.println("✅ " + acteurTest + " est présent dans le graphe");
                System.out.println("Nombre de collaborations: " + graphe.degreeOf(acteurTest));
                
                System.out.println("Collaborateurs de " + acteurTest + ":");
                int compteur = 0;
                for (DefaultEdge arete : graphe.edgesOf(acteurTest)) {
                    String collaborateur = graphe.getEdgeTarget(arete);
                    if (collaborateur.equals(acteurTest)) {
                        collaborateur = graphe.getEdgeSource(arete);
                    }
                    System.out.println("- " + collaborateur);
                    compteur++;
                    if (compteur >= 5) break;
                }
            }
            
            System.out.println("\n=== CONCLUSION ===");
            if (graphe.vertexSet().size() > 0 && graphe.edgeSet().size() > 0) {
                System.out.println("✅ La conversion semble avoir réussi !");
            } else {
                System.out.println("❌ La conversion a probablement échoué.");
            }
            
            System.out.println("\n=== GÉNÉRATION DU FICHIER DOT ===");
            
            Set<String> acteursInactifs = new HashSet<>();
            for (String v : graphe.vertexSet()) {
                if (graphe.degreeOf(v) < 5) { // Seuil ajustable
                    acteursInactifs.add(v);
                }
            }
            graphe.removeAllVertices(acteursInactifs);
            System.out.println("Nombre d'acteurs après filtrage : " + graphe.vertexSet().size());
            
            DOTExporter<String, DefaultEdge> exporter = new DOTExporter<>();
            exporter.setVertexAttributeProvider((x) -> Map.of("label", new DefaultAttribute<>(x, AttributeType.STRING)));
            exporter.exportGraph(graphe, new FileWriter("acteurs_graph.dot"));
            System.out.println("✅ Fichier DOT généré avec succès : acteurs_graph.dot");
            
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier ou de l'exportation : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
