package main.java.graphe;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    public static void dijkstra(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {

    List<String> sommets= g.getSommets();
        dist.put(source,0);
        prev.put(source,"");



}}
