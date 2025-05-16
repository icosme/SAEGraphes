package fr.sae_graphes.main;

import com.google.gson.Gson;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ConvertisseurJSON {
    

    class Film {
        private String title;
        private List<String> cast;  
        private List<String> directors;
        private List<String> producers;
        private List<String> companies;

        public String getTitle() {
            return title;
        }
        public List<String> getCast() {
            return cast;
        }
        public List<String> getDirectors() {
            return directors;
        }
        public List<String> getProducers() {
            return producers;
        }
        public List<String> getCompanies() {
            return companies;
        }
        public int getYear() {
            return year;
        }
        private int year;
    }

    public Graph<String, DefaultEdge> jsonVersGraphe(String cheminFichier) {

        Graph<String, DefaultEdge> graphe = new SimpleGraph<>(DefaultEdge.class);
        
        try {

            BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier));
            List<String> lignes = new ArrayList<>();
            String ligne = lecteur.readLine();

            while (ligne != null) {
                lignes.add(ligne);
                ligne = lecteur.readLine();
            }
            
            lecteur.close();
            
            Gson gson = new Gson();
        
            for (String ligneJSON : lignes) {
                Film film = gson.fromJson(ligneJSON, Film.class);
                
                if (film.getCast() != null && !film.getCast().isEmpty()) {

                    for (String acteur : film.getCast()) {
                        if (!graphe.containsVertex(acteur)) {
                            graphe.addVertex(acteur);
                        }
                    }
                    
                    for (String acteur1 : film.getCast()) {
                        for (String acteur2 : film.getCast()) {

                            if (!acteur1.equals(acteur2) && !graphe.containsEdge(acteur1, acteur2)) {
                                graphe.addEdge(acteur1, acteur2);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du fichier: " + e.getMessage());
        }
        
        return graphe;
    }}