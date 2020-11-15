package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DijkstraAlgorithm<T> {
  private Graph<T> graph;
  private Map<T, Double> weights;
  private Map<T, T> parents;
  private List<T> processed;

  public DijkstraAlgorithm(Graph<T> graph) {
    this.graph = graph;
    weights = new HashMap<>();
    parents = new HashMap<>();
    processed = new LinkedList<>();
  }

  public Map<T, Double> search(T start, T finish) {
    fillParentsAndWeights(start, finish);
    T vertex = findLowestWeightVertex();
    while (vertex != null) {
      Double weight = weights.get(vertex);
      List<Edge<T>> edges = graph.getEdges(vertex);

      for (Edge<T> edge : edges) {
        Double newWeight = weight + edge.getWeight();
        if (weights.get(edge.getDestination()) > newWeight) {
          weights.put(edge.getDestination(), newWeight);
          parents.put(edge.getDestination(), vertex);
        }
      }
      processed.add(vertex);
      vertex = findLowestWeightVertex();
    }
    return weights;
  }

  private void fillParentsAndWeights(T start, T finish) {
    List<Edge<T>> edges = graph.getEdges(start);
    for (Edge<T> edge : edges) {
      weights.put(edge.getDestination(), edge.getWeight());
      parents.put(edge.getDestination(), start);
    }
    if (!weights.containsKey(finish)) {
      weights.put(finish, Double.POSITIVE_INFINITY);
    }
    if (!parents.containsKey(finish)) {
      parents.put(finish, null);
    }
  }

  private T findLowestWeightVertex() {
    Double lowestWeight = Double.POSITIVE_INFINITY;
    T lowestWeightVertex = null;
    for (Map.Entry<T, Double> edge : weights.entrySet()) {
      Double weight = edge.getValue();
      if (weight < lowestWeight && !processed.contains(edge.getKey())) {
        lowestWeight = weight;
        lowestWeightVertex = edge.getKey();
      }
    }
    return lowestWeightVertex;
  }
}
