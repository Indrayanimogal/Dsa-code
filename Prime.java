import java.util.*;

// Define a class for storing edges
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

public class MinimumSpanningTree {
    private int V; // Number of vertices
    private List<Edge> edges; // List of edges

    public MinimumSpanningTree(int vertices) {
        V = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    // Find the MST using Kruskal's algorithm
    public void kruskalMST() {
        // Sort edges based on weight
        Collections.sort(edges);

        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        List<Edge> result = new ArrayList<>();
        int e = 0;
        int i = 0;

        while (e < V - 1) {
            Edge nextEdge = edges.get(i++);
            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            if (x != y) {
                result.add(nextEdge);
                union(parent, x, y);
                e++;
            }
        }

        // Print the minimum spanning tree
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }
    }

    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    private void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[xRoot] = yRoot;
    }

    public static void main(String[] args) {
        MinimumSpanningTree graph = new MinimumSpanningTree(6);
        // Add edges
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 8);
        graph.addEdge(4, 5, 7);

        graph.kruskalMST();
    }
}
