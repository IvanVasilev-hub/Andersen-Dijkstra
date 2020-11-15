package com.company;

public class Edge<T> {
  private double weight;
  private T destination;

  public Edge(T destination, double weight) {
    this.destination = destination;
    this.weight = weight;
  }

  public Edge(T destination) {
    this(destination, 0);
  }

  public double getWeight() {
    return weight;
  }

  public T getDestination() {
    return destination;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("destination ->  ").append(destination);
    sb.append(weight == 0 ? "" : ", weight = " + weight);
    return sb.toString();
  }
}
