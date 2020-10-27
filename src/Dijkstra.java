import java.util.*;

public class Dijkstra {
  private static Map<String, Map<String, Double>> graph = new HashMap<>();
  private static List<String> processed = new ArrayList<>();

  public static void main(String[] args) {
    graph.put("start", new HashMap<>());
    graph.get("start").put("a", 6.0);
    graph.get("start").put("b", 2.0);

    graph.put("a", new HashMap<>());
    graph.get("a").put("fin", 1.0);

    graph.put("b", new HashMap<>());
    graph.get("b").put("a", 3.0);
    graph.get("b").put("fin", 5.0);

    graph.put("fin", Collections.emptyMap());

    Map<String, Double> costs = new HashMap<>();
    costs.put("a", 6.0);
    costs.put("b", 2.0);
    costs.put("fin", Double.POSITIVE_INFINITY);

    Map<String, String> parents = new HashMap<>();
    parents.put("a", "start");
    parents.put("b", "start");
    parents.put("fin", null);

    upgradeCosts(costs, parents);

    System.out.println("Cost from the start to each node:");
    System.out.println(costs);
  }

  private static void upgradeCosts(Map<String, Double> costs, Map<String, String> parents) {
    String node = findLowestCostNode(costs);

    while (node != null) {
      Double cost = costs.get(node);
      Map<String, Double> neighbors = graph.get(node);

      for (String neighbor : neighbors.keySet()) {
        Double newCost = cost + neighbors.get(neighbor);

        if (costs.get(neighbor) > newCost) {
          costs.put(neighbor, newCost);
          parents.put(neighbor, node);
        }
      }

      processed.add(node);
      node = findLowestCostNode(costs);
    }
  }

  private static String findLowestCostNode(Map<String, Double> costs) {
    Double lowestCost = Double.POSITIVE_INFINITY;
    String lowestCostNode = null;

    for (Map.Entry<String, Double> node : costs.entrySet()) {
      Double cost = node.getValue();
      if (cost < lowestCost && !processed.contains(node.getKey())) {
        lowestCost = cost;
        lowestCostNode = node.getKey();
      }
    }

    return lowestCostNode;
  }
}
