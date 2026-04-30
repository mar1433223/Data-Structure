package tree;

/**
 * 二叉搜索树实现
 */
public class BinarySearchTree {

    // 节点定义
    static class BSTNode {
        int data;
        BSTNode l, r;

        BSTNode(int data) {
            this.data = data;
        }
    }

    private BSTNode root;

    // --- 1. 查找 (递归版) ---
    public BSTNode search(BSTNode node, int x) {
        // 对应你的 Search2
        if (node == null || node.data == x) return node;
        if (x < node.data) return search(node.l, x);
        else return search(node.r, x);
    }

    // --- 2. 插入 (递归版：最推荐的 Java 写法) ---
    public void insert(int x) {
        this.root = insertRecursive(this.root, x);
    }

    private BSTNode insertRecursive(BSTNode node, int x) {
        // 对应你的 Insert2
        if (node == null) {
            return new BSTNode(x);
        }

        if (x < node.data) {
            node.l = insertRecursive(node.l, x); // 递归修改左子树并重新挂载
        } else if (x > node.data) {
            node.r = insertRecursive(node.r, x); // 递归修改右子树并重新挂载
        }

        return node; // 返回修改后的节点
    }

    // --- 3. 中序遍历 (验证 BST 性质) ---
    public void inOrder(BSTNode node) {
        if (node == null) return;
        inOrder(node.l);
        System.out.print(node.data + " ");
        inOrder(node.r);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] data = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        for (int x : data) {
            bst.insert(x);
        }

        System.out.print("中序遍历结果（应为升序）: ");
        bst.inOrder(bst.root);
        // 输出: 1 3 4 6 7 8 10 13 14
    }
}