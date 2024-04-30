package main.java.graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs extends Graphe {

    private final List<Arc> a;

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

        if (!destination.isEmpty()) {
            a.add(new Arc(source, destination, valeur));
        }


    }

    @Override
    public void oterSommet(String noeud) {
        if (contientSommet(noeud)) {

            List<Arc> arcsASupprimer = new ArrayList<>();


            for (Arc ax : a) {

                if (ax.getSource().equals(noeud) || ax.getDestination().equals(noeud)) {
                    arcsASupprimer.add(ax);
                }
            }

            for (Arc ax : arcsASupprimer) {
                a.remove(ax);
            }
        }
    }


    @Override
    public void oterArc(String source, String destination) {

        if (contientArc(source , destination)) {
            for(int i =0 ; i < a.size() ; i++){
                if(a.get(i).getSource().equals(source) && a.get(i).getDestination().equals(destination)){
                    a.remove(a.get(i));
                }
            }

        } else
            throw new IllegalArgumentException("L'Arc n'existe pas dans le graphe");
    }

    @Override
    public List<String> getSommets() {
        List<String> b = new ArrayList<>();
        for (Arc ax : a) {
            if(!b.contains(ax.getSource()))
                b.add(ax.getSource());
            if(!b.contains(ax.getDestination()) && !ax.getDestination().isEmpty())
                b.add(ax.getDestination());

        }
        return b;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        for (Arc ax : a)
            if (ax.getSource().equals(sommet) && !ax.getDestination().isEmpty())
                succ.add(ax.getDestination());
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc ax : a)
            if (ax.getSource().equals(src) && ax.getDestination().equals(dest))
                return ax.getValuation();

        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return getSommets().contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for(String s : getSommets()){
            if(src.equals(s) && getSucc(s).contains(dest))
                return true;
        }
        return false;
    }
}