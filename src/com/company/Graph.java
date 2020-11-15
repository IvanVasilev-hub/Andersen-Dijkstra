package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> {
  private Map<T, List<Edge<T>>> graph;

  public Graph() {
    graph = new HashMap<>();
  }

  public boolean addVertex(T vertex) {
    if (!graph.containsKey(vertex)) {
      graph.put(vertex, new LinkedList<>());
      return true;
    }
    return false;
  }

  public void addEdge(T vertex, T destination, double weight) {
    addVertex(vertex);
    graph.get(vertex).add(new Edge<>(destination, weight));
  }

  public void addEdge(T vertex, T destination) {
    addEdge(vertex, destination, 0);
  }

  public boolean containsVertex(T vertex) {
    return graph.containsKey(vertex);
  }

  public List<T> getAllVertexFromSource(T source) {
    List<T> list = new LinkedList<>();
    if (graph.containsKey(source)) {
      list = graph.get(source).stream()
          .map(Edge::getDestination)
          .collect(Collectors.toList());
    }
    return list;
  }

  public List<Edge<T>> getEdges(T vertex) {
    List<Edge<T>> edges = new LinkedList<>();
    if (containsVertex(vertex)) {
      edges.addAll(graph.get(vertex));
    }
    return edges;
  }


  public Map<T, List<Edge<T>>> getGraph() {
    return Collections.unmodifiableMap(graph);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (T key : graph.keySet()) {
      sb.append(key).append(" : \n");
      graph.get(key).stream().forEach(edge -> sb.append(edge).append("\n"));
    }
    return sb.toString();
  }
}
