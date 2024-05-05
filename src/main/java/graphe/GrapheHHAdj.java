package main.java.graphe;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrapheHHAdj extends Graphe{
    private HashMap<String, HashMap<String, Integer>> hhadj;

    public GrapheHHAdj(){
        hhadj = new HashMap<>();
    }

    public GrapheHHAdj(String str) {
        this();
        this.peupler(str);
    }


    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud))
            hhadj.put(noeud, new HashMap<>());
    }


    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(hhadj.containsKey(source) && hhadj.get(source).containsKey(destination) || valeur < 0)
            throw new IllegalArgumentException("hdntsrrs,enhzr");

        if(!hhadj.containsKey(source))
            hhadj.put(source, new HashMap<>());
        hhadj.get(source).put(destination, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        if(contientSommet(noeud)) {
            hhadj.remove(noeud);

            for(String n : hhadj.keySet()){
                hhadj.get(n).remove(noeud);
                if(hhadj.get(n).containsKey(null)) {
                    hhadj.remove(n);
                }
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
            if(!contientArc(source, destination)){
                throw new IllegalArgumentException("ezrhrzhrzhrzh");
            }
            hhadj.get(source).remove(destination);

            HashMap<String, Integer> donneluiunnom = new HashMap<>();

            for(String s : hhadj.keySet()){
                if(hhadj.get(s).containsKey(null)) {
                    donneluiunnom.put(s, null);
                }
            }
            for(String s : donneluiunnom.keySet()){
                hhadj.remove(s);
            }

    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(hhadj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        return new ArrayList<>(hhadj.get(sommet).keySet());
    }

    @Override
    public int getValuation(String src, String dest) {
        if(contientArc(src, dest)) {
            return hhadj.get(src).get(dest);
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return hhadj.containsKey(sommet);
    }

    //PROBLEME
    @Override
    public boolean contientArc(String src, String dest) {
        return hhadj.get(src).containsKey(dest);
    }
}
