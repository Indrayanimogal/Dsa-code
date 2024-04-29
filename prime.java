import java.util.*;

// Class to represent a graph edge
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

// Class to represent a connected, undirected, weighted graph
class Graph {
    private final int V; // Number of vertices
    private final List<Edge> edges; // List of edges

    Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    // Function to add an edge to the graph
    void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    // Prim's algorithm to find the minimum spanning tree
    void primMST() {
        // Array to store constructed MST
        Edge[] mst = new Edge[V];

        // Boolean array to track vertices included in MST
        boolean[] inMST = new boolean[V];

        // Priority queue to store edges sorted by weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.weight));

        // Start with the first vertex
        int src = 0;
        inMST[src] = true;

        // Add all edges from src to pq
        for (Edge edge : edges) {
            if (edge.src == src) {
                pq.add(edge);
            }
        }

        // MST contains V-1 edges
        int edgeCount = 0;
        while (edgeCount < V - 1 && !pq.isEmpty()) {
            // Extract the minimum weight edge
            Edge edge = pq.poll();

            // Check if including this edge forms a cycle
            if (!inMST[edge.dest]) {
                mst[edgeCount++] = edge;
                inMST[edge.dest] = true;

                // Add all edges from the newly added vertex to pq
                for (Edge nextEdge : edges) {
                    if (nextEdge.src == edge.dest && !inMST[nextEdge.dest]) {
                        pq.add(nextEdge);
                    }
                }
            }
        }

        // Print the MST edges
        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println(mst[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int V = 5; // Number of vertices

        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 6);

        graph.primMST();
    }
}
