package main.java.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj extends Graphe{
    private Map<String , List<String>> liste_adj;

    public GrapheLAdj(){
        liste_adj = new HashMap<>();
    }
    @Override
    public void ajouterSommet(String noeud) {
        liste_adj.put(noeud , getSucc(noeud));
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {

        for(Map.Entry<String, List<String>> entry : liste_adj.entrySet()){
            if(entry.getKey().contentEquals(source) ) {
                List<String> vertices = new ArrayList<>(entry.getValue());
                vertices.add(destination);
                entry.setValue(vertices);
            }
        }

    }

    @Override
    public void oterSommet(String noeud) {

    }

    @Override
    public void oterArc(String source, String destination) {

    }

    @Override
    public List<String> getSommets() {
        return null;
    }

    @Override
    public List<String> getSucc(String sommet) {
        return null;
    }

    @Override
    public int getValuation(String src, String dest) {
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return false;
    }
}
