package main.java.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj extends Graphe {
    private Map<String, List<Arc>> liste_adj;
    private Map<String, List<String>> liste_succ;

    public GrapheLAdj() {
        liste_adj = new HashMap<>();
        liste_succ = new HashMap<>();
    }

    public GrapheLAdj(String str) {
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            liste_adj.put(noeud, new ArrayList<>());
            liste_succ.put(noeud, new ArrayList<>());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (valeur < 0 || contientArc(source, destination)) {
            throw new IllegalArgumentException("fheaoghea");
        }
        if (!liste_adj.containsKey(source)) {
            ajouterSommet(source);
        }
        liste_adj.get(source).add(new Arc(source, destination, valeur));
        liste_succ.get(source).add(destination);
    }

    @Override
    public void oterSommet(String noeud) {
        if (liste_adj.containsKey(noeud)) {
            for (List<Arc> ensembleArcs : liste_adj.values()) {
                ensembleArcs.removeIf(arc -> arc.getDestination().equals(noeud) || arc.getSource().equals(noeud));
            }
            liste_adj.remove(noeud);
            liste_succ.remove(noeud);
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
            liste_succ.get(arcASupprimer.getSource()).remove(arcASupprimer.getDestination());
        }
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(liste_adj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        if (liste_succ.containsKey(sommet)) {
            return liste_succ.get(sommet);
        }
        return new ArrayList<>();
    }

    @Override
    public int getValuation(String src, String dest) {
        if (contientArc(src, dest)) {
            for (Arc a : liste_adj.get(src)) {
                if (a.getDestination().equals(dest)) {
                    return a.getValuation();
                }
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
        if (liste_succ.containsKey(src)) {
            return liste_succ.get(src).contains(dest);
        }
        return false;
    }
}