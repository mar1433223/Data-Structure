package tree;

import java.util.Scanner;

/**
 * 二叉链表存储结构
 * 每个节点持有对左右子节点的引用
 */
public class BinaryTree {

    /**
     * 二叉树节点类：对应 C 中的 BTNode
     */
    private static class BTNode {
        char data;
        BTNode l; // 指向左孩子
        BTNode r; // 指向右孩子

        BTNode(char data) {
            this.data = data;
            this.l = null;
            this.r = null;
        }
    }

    private BTNode root;

    public BinaryTree(char rootData) {
        this.root = new BTNode(rootData);
    }

    /**
     * 递归查找节点：对应 C 中的 Find
     * 逻辑：根 -> 左子树 -> 右子树
     */
    public BTNode find(BTNode node, char fx) {
        if (node == null) return null;
        if (node.data == fx) return node;

        // 递归去左子树找
        BTNode ans = find(node.l, fx);
        if (ans != null) return ans;

        // 递归去右子树找
        return find(node.r, fx);
    }

    /**
     * 插入节点：对应 C 中的 Insert
     * @param x 新数据
     * @param fx 父亲数据
     * @param flag 0 为左孩子，1 为右孩子
     */
    public void insert(char x, char fx, int flag) {
        BTNode parent = find(root, fx);
        if (parent == null) {
            System.out.println("未找到父节点: " + fx);
            return;
        }

        BTNode newNode = new BTNode(x);
        if (flag == 0) {
            parent.l = newNode;
        } else {
            parent.r = newNode;
        }
    }

    /**
     * 查询并打印节点的孩子信息
     */
    public void queryChildren(char x) {
        BTNode p = find(root, x);
        if (p == null) {
            System.out.println("该节点不存在");
            return;
        }

        System.out.print(x + " 的左孩子: " + (p.l == null ? "无" : p.l.data));
        System.out.println("，右孩子: " + (p.r == null ? "无" : p.r.data));
    }

    // --- 主测试函数 ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("输入节点总数:");
        int n = sc.nextInt();

        System.out.println("输入根节点:");
        char rootChar = sc.next().charAt(0);
        BinaryTree bTree = new BinaryTree(rootChar);

        System.out.println("输入 n-1 行 (子 亲 0左/1右):");
        for (int i = 0; i < n - 1; i++) {
            char x = sc.next().charAt(0);
            char fx = sc.next().charAt(0);
            int flag = sc.nextInt();
            bTree.insert(x, fx, flag);
        }

        System.out.println("输入要查询的节点:");
        char target = sc.next().charAt(0);
        bTree.queryChildren(target);

        sc.close();
    }
}