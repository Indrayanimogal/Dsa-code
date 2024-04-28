class Node {
    int data;
    Node left, right;
    boolean isThreaded;

    public Node(int item) {
        data = item;
        left = right = null;
        isThreaded = false;
    }
}

public class ThreadedBinaryTree {
    Node root;

    public ThreadedBinaryTree() {
        root = null;
    }

    private void populateThreadedNodes(Node node, Node prev) {
        if (node == null) return;

        populateThreadedNodes(node.right, prev);

        if (node.right == null) {
            node.right = prev;
            node.isThreaded = true;
        }

        populateThreadedNodes(node.left, node);
    }

    public void convertToThreaded() {
        Node dummy = new Node(Integer.MIN_VALUE);
        dummy.right = root;
        dummy.isThreaded = true;
        populateThreadedNodes(root, dummy);
        root = dummy;
    }

    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        tree.convertToThreaded();

        // Now you can traverse the threaded tree
    }
}
