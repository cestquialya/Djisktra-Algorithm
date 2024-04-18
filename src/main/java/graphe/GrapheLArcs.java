package main.java.graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs extends Graphe {

    private List<Arc> a;

    public GrapheLArcs() {

        a = new ArrayList<>();
    }

    public GrapheLArcs(String str) {
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {

        if (!contientSommet(noeud))

            a.add(new Arc(noeud, "", 0));

    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (contientArc(source, destination))
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);
        if (valeur < 0)
            throw new IllegalArgumentException("La valuation " + valeur + "  est negative");

        if (contientSommet(source) && contientSommet(destination)) {
            a.add(new Arc(source, destination, valeur));
        }


    }

    @Override
    public void oterSommet(String noeud) {

        if (contientSommet(noeud)) {

            for (Arc ax : a)
                if (ax.getSource().contentEquals(noeud))
                    ax = new Arc("0", "0", 0);
                else if (ax.getSource().contentEquals(noeud) || ax.getDestination().contentEquals(noeud))
                    ax = new Arc("0", "0", 0);
        }


    }

    @Override
    public void oterArc(String source, String destination) {

        if (contientSommet(source) && contientSommet(destination)) {
            for (Arc ax : a)
                if (contientArc(source, destination))
                    ax = new Arc("0", "0", 0);
        } else
            throw new IllegalArgumentException("L'Arc n'existe pas dans le graphe");
    }

    @Override
    public List<String> getSommets() {
        List<String> b = new ArrayList<>();
        for (Arc ax : a) {
            b.add(ax.getSource());
        }
        return b;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        for (Arc ax : a)
            if (ax.getSource().contentEquals(sommet))
                succ.add(ax.getDestination());
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        int val = -1;
        for (Arc ax : a)
            if (contientArc(src, dest))
                val = ax.getValuation();

        return val;
    }

    @Override
    public boolean contientSommet(String sommet) {

        for (Arc ax : a) {
            if (ax.getSource().contentEquals(sommet))
                return true;
        }
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc ax : a) {
            if (ax.getSource().contentEquals(src) && ax.getDestination().contentEquals(dest))
                return true;

        }
        return false;
    }
}