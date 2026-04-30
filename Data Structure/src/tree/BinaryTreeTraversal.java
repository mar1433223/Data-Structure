package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 二叉树遍历全解
 */
public class BinaryTreeTraversal {

    /**
     * 二叉树节点
     */
    static class BTNode {
        char data;
        BTNode l, r;

        BTNode(char data) {
            this.data = data;
        }
    }

    private BTNode root;

    public BinaryTreeTraversal(char rootData) {
        this.root = new BTNode(rootData);
    }

    // --- 递归查找节点 ---
    public BTNode find(BTNode node, char target) {
        if (node == null) return null;
        if (node.data == target) return node;
        BTNode res = find(node.l, target);
        if (res != null) return res;
        return find(node.r, target);
    }

    // --- 插入节点 ---
    public void insert(char x, char fx, int flag) {
        BTNode parent = find(root, fx);
        if (parent == null) return;
        BTNode newNode = new BTNode(x);
        if (flag == 0) parent.l = newNode;
        else parent.r = newNode;
    }

    // -----------------------------------------------------------
    // 1. 先序遍历 (根-左-右)
    // -----------------------------------------------------------
    public void preOrder(BTNode node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.l);
        preOrder(node.r);
    }

    // -----------------------------------------------------------
    // 2. 中序遍历 (左-根-右)
    // -----------------------------------------------------------
    public void inOrder(BTNode node) {
        if (node == null) return;
        inOrder(node.l);
        System.out.print(node.data + " ");
        inOrder(node.r);
    }

    // -----------------------------------------------------------
    // 3. 后序遍历 (左-右-根)
    // -----------------------------------------------------------
    public void postOrder(BTNode node) {
        if (node == null) return;
        postOrder(node.l);
        postOrder(node.r);
        System.out.print(node.data + " ");
    }

    // -----------------------------------------------------------
    // 4. 层序遍历 (BFS)
    // Java 特色：直接使用内置 Queue 接口和 LinkedList 实现
    // -----------------------------------------------------------
    public void levelOrder() {
        if (root == null) return;

        // Java 中的 Queue 是接口，LinkedList 实现了它
        Queue<BTNode> queue = new LinkedList<>();
        queue.offer(root); // 入队

        while (!queue.isEmpty()) {
            BTNode current = queue.poll(); // 出队
            System.out.print(current.data + " ");

            if (current.l != null) queue.offer(current.l);
            if (current.r != null) queue.offer(current.r);
        }
        System.out.println();
    }

    // --- 主函数 ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 此处省略建树的输入过程，逻辑与你提供的 C 代码一致
        // 假设已经根据你的示例数据建好了树

        BinaryTreeTraversal tree = new BinaryTreeTraversal('A');
        // ... 执行一系列 tree.insert ...

        System.out.println("先序遍历:");
        tree.preOrder(tree.root);

        System.out.println("\n层序遍历:");
        tree.levelOrder();

        sc.close();
    }
}