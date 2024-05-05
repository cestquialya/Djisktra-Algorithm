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

    public GrapheLAdj(String str) {
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud)){
            liste_adj.put(noeud,new ArrayList<Arc>());
        }

    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(valeur < 0 || contientArc(source , destination)){
            throw new IllegalArgumentException("fheaoghea");
        }
        if(!liste_adj.containsKey(source)){
            ajouterSommet(source);
        }
        liste_adj.get(source).add(new Arc(source, destination, valeur));

    }

    @Override
    public void oterSommet(String noeud) {
        if (liste_adj.containsKey(noeud)) {

            for (List<Arc> ensembleArcs : liste_adj.values()) {
                List<Arc> arcsASupprimer = new ArrayList<>();
                for (Arc arc : ensembleArcs) {
                    if (arc.getDestination().equals(noeud)) {
                        arcsASupprimer.add(arc);
                    }
                }
                ensembleArcs.removeAll(arcsASupprimer);
            }
            liste_adj.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!contientArc(source, destination)) {
            throw new IllegalArgumentException("L'arc entre " + source + " et " + destination + " n'existe pas.");
        }

        List<Arc> arcsASupprimer = new ArrayList<>();

        for (List<Arc> ensembleArcs : liste_adj.values()) {
            for (Arc arc : ensembleArcs) {
                if (arc.getDestination().equals(destination) && arc.getSource().equals(source)) {
                    arcsASupprimer.add(arc);
                }
            }
        }

        for (Arc arcASupprimer : arcsASupprimer) {
            liste_adj.get(arcASupprimer.getSource()).remove(arcASupprimer);
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
                if (entry.getKey().equals(sommet)) {
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
                if(a.getDestination().equals(dest)){
                    return a.getValuation();
                }

        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return liste_adj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for(String s : getSommets())
            if(s.equals(src) && getSucc(src).contains(dest))
                return true;
        return false;
    }
}
