package main.java.graphe;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    public static void dijkstra(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {

        List<String> sommets = g.getSommets();
        sommets.remove(source);
        sommets.add(0,source);
        //jai supp puis ajouter la source au debut de la liste
        dist.put(source, 0);
        prev.put(source, "");


        String pred = "";
        String court = source;

        int min = g.getValuation(court, g.getSucc(court).get(0));
        for (String s : prev.keySet()) {
            for (int i = 1; i < g.getSucc(s).size(); ++i) {
                if (g.getValuation(s, g.getSucc(s).get(i)) < min) {
                    min = g.getValuation(s, g.getSucc(s).get(i));
                    pred = s;
                    court = g.getSucc(s).get(i);
                }
            }
            dist.put(court, min);
            prev.put(court, pred); // Nv ancien
        }
        // on utilise prev pour contnuer les boucles
        //regarder tt les succeseutr de source tant que ?
        // peut etre tant que ya pas de succeseurs ou tant que ya pas de valuation + basse
        //regarder tt les succceseurs et les valeur des arcs jusquau + petit il recommence la dest de larc le  plus petit je crois que ppoour repondre dcp au sommet qui a
        // dist sommet valuation dans prev le sommet dans lequel on est il faudras lister tt le chemins ????? ttles destinations les plus courtes dans dist

        /*for(int i=0 ; i< sommets.size();++i){
            for(int j=0 ; j< sommets.size();++i){

          if ( g.getSucc(sommets.get(i)).contains(sommets.get(j)) ){
              prev.put(sommets.get(j),sommets.get(i) );

          }
        }*/
    }
}
