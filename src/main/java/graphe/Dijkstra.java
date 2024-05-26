package main.java.graphe;

import java.util.*;

public class Dijkstra {

    public static void dijkstra(IGraphe g, String source, Map<String, Integer> dist, Map<String, String> prev) {

        dist.put(source, 0);
        prev.put(source, "");

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        queue.add(source);

        while (!queue.isEmpty()) {
            String actuel = queue.poll();
            for (String succ : g.getSucc(actuel)) {
                int valuation = g.getValuation(actuel, succ);
                if (!dist.containsKey(succ) || dist.get(actuel) + valuation < dist.get(succ)) {
                    dist.put(succ, dist.get(actuel) + valuation);
                    prev.put(succ, actuel);
                    queue.add(succ);
                }
            }
        }
    }
}