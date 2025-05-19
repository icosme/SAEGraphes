package fr.sae_graphes.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

public class App {
    public static void main(String[] args) {
        try {
          
            String cheminFichier = "data_100.txt"; 
            ConvertisseurJSON convertisseur = new ConvertisseurJSON();
            
            Graph<String, DefaultEdge> graphe = convertisseur.jsonVersGraphe(cheminFichier);
          
            System.out.println("\n=== CONCLUSION ===");
            if (graphe.vertexSet().size() > 0 && graphe.edgeSet().size() > 0) {
                System.out.println("✅ La conversion semble avoir réussi !");
            } else {
                System.out.println("❌ La conversion a probablement échoué.");
            }
         
            
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
