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
                    entry.setValue(l);
                }
            }
        }
    }

    @Override
    public void oterSommet(String noeud) {
        if (liste_adj.containsKey(noeud)) {

            liste_adj.remove(noeud);
            for (List<Arc> ensembleArcs : liste_adj.values()) {
                for (Arc arc : ensembleArcs) {
                    if (arc.getDestination().contentEquals(noeud) )
                        liste_adj.get(arc.getSource()).remove(arc);
                }
            }
        }
    }
    @Override
    public void oterArc(String source, String destination) {
        if(contientArc(source, destination)){
            for (List<Arc> ensembleArcs : liste_adj.values()) {
                for (Arc arc : ensembleArcs) {
                    if (arc.getDestination().contentEquals(destination) &&
                     arc.getSource().contentEquals(source))
                        liste_adj.get(arc.getSource()).remove(arc);
                }
            }
        }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(liste_adj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successors = new ArrayList<>();
        if (liste_adj.containsKey(sommet)) {
            for (Map.Entry<String, List<Arc>> entry : liste_adj.entrySet()) {
                if (entry.getKey().contentEquals(sommet)) {
                    for (Arc a : liste_adj.get(sommet))
                        successors.add(a.getDestination());
                }
            }
        }
        return successors;
    }

    @Override
    public int getValuation(String src, String dest) {
        if(contientArc(src , dest)){
            for (Arc a : liste_adj.get(src))
                if(a.getDestination().contentEquals(dest)){
                    return a.getValuation();
                }

        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        for(String s : liste_adj.keySet())
            if(s.contentEquals(sommet))
                return true;
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for(Arc a : liste_adj.get(src))
            if(a.getDestination().contentEquals(dest))
                return true;
        return false;
    }
}
