package main.java.graphe;

import java.util.*;

public class Dijkstra {

    public static void dijkstra(IGraphe g, String source, Map<String, Integer> dist, Map<String, String> prev) {

        List<String> sommets = g.getSommets();
        sommets.remove(source);
        sommets.add(0, source);

        dist.put(source, 0);
        prev.put(source, "");

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        queue.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String succ : g.getSucc(current)) {
                int valuation = g.getValuation(current, succ);
                if (!dist.containsKey(succ) || dist.get(current) + valuation < dist.get(succ)) {
                    dist.put(succ, dist.get(current) + valuation);
                    prev.put(succ, current);
                    queue.add(succ);
                }
            }
        }
    }
}