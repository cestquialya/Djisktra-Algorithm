package main.java.graphe;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GrapheLAdj extends Graphe{
    private Map<String , List<Arc>> liste_adj;

    public GrapheLAdj(){
        liste_adj = new HashMap<>();
    }
    @Override
    public void ajouterSommet(String noeud) {
        if(contientSommet(noeud))
            liste_adj.put(noeud,new ArrayList<Arc>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!contientArc(source ,destination) && valeur>0) {
            for (Map.Entry<String, List<Arc>> entry : liste_adj.entrySet()) {
                if (entry.getKey().contentEquals(source)) {
                    List<Arc> l = new ArrayList<>(entry.getValue());
                    l.add(new Arc(source,destination,valeur));
                }
            }
        }
    }

    @Override
    public void oterSommet(String noeud) {
        if (liste_adj.containsKey(noeud)) {

            liste_adj.remove(noeud);
            for (List<Arc> ensembleArcs : this.liste_adj.values()) {
                for (Arc arc : ensembleArcs) {
                    if (arc.getDestination() == noeud)
                        this.liste_adj.get(arc.getSource()).remove(arc);
                }
            }
        }
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
