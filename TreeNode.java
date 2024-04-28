import java.util.Stack;

class TreeNode {
    char value;
    TreeNode left;
    TreeNode right;

    TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class ExpressionTree {

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static TreeNode createExpressionTree(String prefixExpression) {
        Stack<TreeNode> stk = new Stack<>();

        for (int i = prefixExpression.length() - 1; i >= 0; i--) {
            char c = prefixExpression.charAt(i);
            TreeNode node = new TreeNode(c);

            if (isOperator(c)) {
                node.left = stk.pop();
                node.right = stk.pop();
            }

            stk.push(node);
        }

        return stk.peek();
    }

    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    public static void deleteTree(TreeNode root) {
        if (root == null) {
            return;
        }

        deleteTree(root.left);
        deleteTree(root.right);
    }

    public static void main(String[] args) {
        String prefixExpression = "+--a*bc/def";
        TreeNode root = createExpressionTree(prefixExpression);

        System.out.print("Post-order traversal: ");
        postOrderTraversal(root);
        System.out.println();

        deleteTree(root);
    }
}


