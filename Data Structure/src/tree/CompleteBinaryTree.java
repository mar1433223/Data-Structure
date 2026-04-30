package tree;

import java.util.*;

/**
 * 二叉树综合类：包含存储、递归遍历与非递归遍历
 */
public class CompleteBinaryTree {

    // 1. 节点结构
    static class BTNode {
        char data;
        BTNode l, r;

        BTNode(char data) {
            this.data = data;
        }
    }

    private BTNode root;

    public CompleteBinaryTree(char rootData) {
        this.root = new BTNode(rootData);
    }

    // -----------------------------------------------------------
    // 2. 辅助功能：查找与插入
    // -----------------------------------------------------------
    public BTNode find(BTNode node, char target) {
        if (node == null || node.data == target) return node;
        BTNode res = find(node.l, target);
        return (res != null) ? res : find(node.r, target);
    }

    public void insert(char x, char fx, int flag) {
        BTNode parent = find(root, fx);
        if (parent == null) return;
        if (flag == 0) parent.l = new BTNode(x);
        else parent.r = new BTNode(x);
    }

    // -----------------------------------------------------------
    // 3. 递归三兄弟 (先序、中序、后序)
    // -----------------------------------------------------------
    public void recursivePreOrder(BTNode node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        recursivePreOrder(node.l);
        recursivePreOrder(node.r);
    }

    public void recursiveInOrder(BTNode node) {
        if (node == null) return;
        recursiveInOrder(node.l);
        System.out.print(node.data + " ");
        recursiveInOrder(node.r);
    }

    public void recursivePostOrder(BTNode node) {
        if (node == null) return;
        recursivePostOrder(node.l);
        recursivePostOrder(node.r);
        System.out.print(node.data + " ");
    }

    // -----------------------------------------------------------
    // 4. 迭代遍历 (使用 Deque 作为栈)
    // -----------------------------------------------------------

    // 迭代中序：左-根-右
    public void iterativeInOrder() {
        Deque<BTNode> stack = new ArrayDeque<>();
        BTNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.l;
            } else {
                p = stack.pop();
                System.out.print(p.data + " ");
                p = p.r;
            }
        }
        System.out.println();
    }

    // 迭代后序：左-右-根 (使用 pre 指针逻辑)
    public void iterativePostOrder() {
        Deque<BTNode> stack = new ArrayDeque<>();
        BTNode p = root;
        BTNode pre = null;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.l;
            }
            p = stack.peek();
            if (p.r != null && p.r != pre) {
                p = p.r;
            } else {
                p = stack.pop();
                System.out.print(p.data + " ");
                pre = p;
                p = null; // 关键：重置p避免重复压栈
            }
        }
        System.out.println();
    }

    // -----------------------------------------------------------
    // 5. 层序遍历 (使用 Queue)
    // -----------------------------------------------------------
    public void levelOrder() {
        if (root == null) return;
        Queue<BTNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode curr = queue.poll();
            System.out.print(curr.data + " ");
            if (curr.l != null) queue.offer(curr.l);
            if (curr.r != null) queue.offer(curr.r);
        }
        System.out.println();
    }

    // -----------------------------------------------------------
    // 6. 主测试
    // -----------------------------------------------------------
    public static void main(String[] args) {
        CompleteBinaryTree tree = new CompleteBinaryTree('A');
        // 构建你示例中的树
        tree.insert('B', 'A', 0); tree.insert('E', 'A', 1);
        tree.insert('C', 'B', 1); tree.insert('D', 'C', 0);
        tree.insert('F', 'E', 1); tree.insert('G', 'F', 0);
        tree.insert('H', 'G', 0); tree.insert('K', 'G', 1);

        System.out.print("递归后序: "); tree.recursivePostOrder(tree.root);
        System.out.print("\n迭代后序: "); tree.iterativePostOrder();
        System.out.print("层序遍历: "); tree.levelOrder();
    }
}