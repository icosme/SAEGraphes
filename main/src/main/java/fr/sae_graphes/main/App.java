package fr.sae_graphes.main;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;



public class App {
    public static void main(String[] args) {
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
        
        // Si le graphe contient des sommets, c'est déjà un bon signe
        if (graphe.vertexSet().size() > 0) {
            System.out.println("✅ Le graphe contient des acteurs");
        } else {
            System.out.println("❌ Aucun acteur trouvé dans le graphe");
        }
        
        // Si le graphe contient des arêtes, c'est encore mieux
        if (graphe.edgeSet().size() > 0) {
            System.out.println("✅ Le graphe contient des collaborations");
        } else {
            System.out.println("❌ Aucune collaboration trouvée");
        }
        
        // VÉRIFICATION DES ACTEURS
        System.out.println("\n=== EXEMPLES D'ACTEURS ===");
        // Affichage des 5 premiers acteurs
        List<String> listeActeurs = new ArrayList<>(graphe.vertexSet());
        int nombreAAfficher = Math.min(5, listeActeurs.size());
        
        for (int i = 0; i < nombreAAfficher; i++) {
            String acteur = listeActeurs.get(i);
            System.out.println("- " + acteur + " (degré: " + graphe.degreeOf(acteur) + ")");
        }
        
        // VÉRIFICATION DES COLLABORATIONS
        System.out.println("\n=== EXEMPLES DE COLLABORATIONS ===");
        // Affichage de 5 collaborations (si elles existent)
        List<DefaultEdge> listeAretes = new ArrayList<>(graphe.edgeSet());
        nombreAAfficher = Math.min(5, listeAretes.size());
        
        for (int i = 0; i < nombreAAfficher; i++) {
            DefaultEdge arete = listeAretes.get(i);
            String source = graphe.getEdgeSource(arete);
            String target = graphe.getEdgeTarget(arete);
            System.out.println("- " + source + " a collaboré avec " + target);
        }
        
        // VÉRIFICATION D'UN ACTEUR SPÉCIFIQUE
        // Si vous savez qu'un acteur particulier doit être présent
        String acteurTest = "Tom Hanks"; // Remplacez par un acteur que vous savez présent dans votre dataset
        if (graphe.containsVertex(acteurTest)) {
            System.out.println("\n=== VÉRIFICATION POUR " + acteurTest + " ===");
            System.out.println("✅ " + acteurTest + " est présent dans le graphe");
            System.out.println("Nombre de collaborations: " + graphe.degreeOf(acteurTest));
            
            System.out.println("Collaborateurs de " + acteurTest + ":");
            for (DefaultEdge arete : graphe.edgesOf(acteurTest)) {
                String collaborateur = graphe.getEdgeTarget(arete);
                if (collaborateur.equals(acteurTest)) {
                    collaborateur = graphe.getEdgeSource(arete);
                }
                System.out.println("- " + collaborateur);
                // Limitons à 5 collaborateurs pour l'affichage
                if (--nombreAAfficher <= 0) break;
            }
        }
        
        System.out.println("\n=== CONCLUSION ===");
        if (graphe.vertexSet().size() > 0 && graphe.edgeSet().size() > 0) {
            System.out.println("✅ La conversion semble avoir réussi !");
        } else {
            System.out.println("❌ La conversion a probablement échoué.");
        }
    }
}