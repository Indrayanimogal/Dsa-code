
import.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;

    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    void insert(int value) {
        root = insertHelper(root, value);
        System.out.println("Value " + value + " inserted.");
        printTree();
    }

    Node insertHelper(Node node, int value) {
        if (node == null) {
            node = new Node(value);
            return node;
        }

        if (value < node.data) {
            node.left = insertHelper(node.left, value);
        } else if (value > node.data) {
            node.right = insertHelper(node.right, value);
        }

        return node;
    }

    void mirror() {
        mirrorHelper(root);
        System.out.println("Mirror image created.");
        printTree();
    }

    void mirrorHelper(Node node) {
        if (node == null) {
            return;
        }

        mirrorHelper(node.left);
        mirrorHelper(node.right);

        // Swap the left and right child nodes
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    void printLeafNodes() {
        printLeafNodesHelper(root);
        System.out.println();
    }

    void printLeafNodesHelper(Node node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.print(node.data + " ");
        }

        printLeafNodesHelper(node.left);
        printLeafNodesHelper(node.right);
    }

    int height() {
        return heightHelper(root);
    }

    int heightHelper(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    void preorder() {
        preorderHelper(root);
        System.out.println();
    }

    void preorderHelper(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        preorderHelper(node.left);
        preorderHelper(node.right);
    }

    int minimum() {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    boolean search(int value) {
        return searchHelper(root, value);
    }

    boolean searchHelper(Node node, int value) {
        if (node == null) {
            return false;
        }

        if (node.data == value) {
            return true;
        }

        if (value < node.data) {
            return searchHelper(node.left, value);
        } else {
            return searchHelper(node.right, value);
        }
    }

    void printTree() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        System.out.println("Tree:");
        printTreeHelper(root);
        System.out.println();
    }

    void printTreeHelper(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(node);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                Node curr = q.poll();

                System.out.print(curr.data + " ");

                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }

            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);
        int option, value;

        do {
            System.out.println("\n-------------------------");
            System.out.println("Binary Search Tree Program");
            System.out.println("-------------------------");
            System.out.println("1. Insert");
            System.out.println("2. Mirror");
            System.out.println("3. Print Leaf Nodes");
            System.out.println("4. Height");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Minimum Value");
            System.out.println("7. Search");
            System.out.println("8. Exit");
            System.out.print("Enter your option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    value = scanner.nextInt();
                    tree.insert(value);
                    break;
                case 2:
                    tree.mirror();
                    break;
                case 3:
                    System.out.print("Leaf Nodes: ");
                    tree.printLeafNodes();
                    break;
                case 4:
                    System.out.println("Height of the tree: " + tree.height());
                    break;
                case 5:
                    System.out.print("Preorder Traversal: ");
                    tree.preorder();
                    break;
                case 6:
                    System.out.println("Minimum value: " + tree.minimum());
                    break;
                case 7:
                    System.out.print("Enter the value to search: ");
                    value = scanner.nextInt();
                    if (tree.search(value)) {
                        System.out.println("Value found in the tree.");
                    } else {
                        System.out.println("Value not found in the tree.");
                    }
                    break;
                case 8:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option != 8);

        scanner.close();
    }
}

