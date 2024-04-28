import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adj;
    private boolean direction;
    private int n, e;

    public Graph(int n, int e, boolean direction) {
        this.n = n;
        this.e = e;
        this.direction = direction;
        adj = new HashMap<>();
    }

    public void addEdges(int u, int v) {
        if (!adj.containsKey(u)) {
            adj.put(u, new ArrayList<>());
        }
        adj.get(u).add(v);

        if (!direction) {
            if (!adj.containsKey(v)) {
                adj.put(v, new ArrayList<>());
            }
            adj.get(v).add(u);
        }
    }

    public void printAdj() {
        for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            int count = 0;
            for (int j : entry.getValue()) {
                System.out.print(j);
                if (++count < entry.getValue().size()) {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void dfs(int start) {
        Stack<Integer> stk = new Stack<>();
        boolean[] visited = new boolean[n];
        stk.push(start);
        visited[start] = true;

        while (!stk.isEmpty()) {
            int u = stk.pop();
            System.out.print(u + " ");
            for (int i : adj.get(u)) {
                if (!visited[i]) {
                    visited[i] = true;
                    stk.push(i);
                }
            }
        }
    }

    public void BFS(int s) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            s = q.poll();
            System.out.print(s + " ");

            for (int i : adj.get(s)) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = scanner.nextInt();
        System.out.print("Enter number of edges: ");
        int e = scanner.nextInt();
        System.out.print("Enter 1->Directed Graph and 0->Undirected Graph: ");
        boolean direction = scanner.nextInt() == 1;

        Graph g1 = new Graph(n, e, direction);

        System.out.println("Enter edges: ");
        for (int i = 0; i < e; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g1.addEdges(u, v);
        }

        g1.printAdj();

        System.out.println("DFS traversal sequence: ");
        g1.dfs(1);

        System.out.println("BFS traversal sequence: ");
        g1.BFS(1);

        scanner.close();
    }
}


