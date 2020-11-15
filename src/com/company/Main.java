package com.company;

import java.util.Map;

public class Main {

  public static void main(String[] args) {
    Graph<String> graph = new Graph<>();
    graph.addEdge("Start", "A", 6);
    graph.addEdge("Start", "B", 2);
    graph.addEdge("A", "Finish", 1);
    graph.addEdge("B", "A", 3);
    graph.addEdge("B", "Finish", 5);
    graph.addVertex("Finish");
    DijkstraAlgorithm<String> da = new DijkstraAlgorithm<>(graph);
    Map<String, Double> weights = da.search("Start", "Finish");
    weights.entrySet().stream()
        .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
  }
}
