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
        if(contientArc(source, destination)){
            throw new IllegalArgumentException("l'arc" + source + "-" + destination + "existe deja.");
        }

        if(valeur < 0){
            throw new IllegalArgumentException(("La valeur est strictement inférieure a 0 " + "(" + valeur + ")"));
        }

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
            throw new IllegalArgumentException("L'arc" + source + "-" + destination +" n'existe pas");
        }
        hhadj.get(source).remove(destination);

        HashMap<String, Integer> som = new HashMap<>();

        for(String s : hhadj.keySet()){
            if(hhadj.get(s).containsKey(null)) {
                som.put(s, null);
            }
        }
        for(String s : som.keySet()){
            hhadj.remove(s);
        }

    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(hhadj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        if (!contientSommet(sommet)){
            return new ArrayList<>();
        }
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
    
    @Override
    public boolean contientArc(String src, String dest) {
        if(hhadj.get(src) != null){
            return hhadj.get(src).containsKey(dest);
        }
        return false;
    }
}