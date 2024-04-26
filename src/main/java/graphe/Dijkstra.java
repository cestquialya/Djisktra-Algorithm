package main.java.graphe;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    public static void dijkstra(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {

    List<String> sommets= g.getSommets();
        dist.put(source,0);
        prev.put(source,"");

        String pred= source;
        String court= source;
        for (String s: sommets) {
            int min = g.getValuation(court, g.getSucc(court).get(0));
            for (int i = 1; i < g.getSucc(court).size(); ++i) {
                min = g.getValuation(court, g.getSucc(court).get(0));
                if (g.getValuation(court, g.getSucc(court).get(i)) < min) {
                    min = g.getValuation(court, g.getSucc(court).get(i));
                    court = g.getSucc(court).get(i);
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

        for(int i=0 ; i< sommets.size();++i){
            for(int j=0 ; j< sommets.size();++i){

          if ( g.getSucc(sommets.get(i)).contains(sommets.get(j)) ){
              prev.put(sommets.get(j),sommets.get(i) );

          }
        }
    }
}}
