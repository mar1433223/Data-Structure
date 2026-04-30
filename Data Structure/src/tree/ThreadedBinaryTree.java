package tree;

import java.util.Scanner;

public class ThreadedBinaryTree {

    // 1. 结点结构
    static class TNode {
        char data;
        TNode l, r;
        // flag = 0 表示指向孩子，flag = 1 表示线索
        int lflag, rflag;

        TNode(char data) {
            this.data = data;
            this.lflag = 0;
            this.rflag = 0;
        }
    }

    private TNode root;
    private TNode pre = null; // 必须是全局或类成员，用于记录遍历过程中的前一个结点

    public ThreadedBinaryTree(char rootData) {
        this.root = new TNode(rootData);
    }

    // --- 查找与插入 (与普通二叉树类似，但需注意 flag 判断) ---
    public TNode find(TNode node, char target) {
        if (node == null || node.data == target) return node;
        TNode res = null;
        if (node.lflag == 0) res = find(node.l, target);
        if (res == null && node.rflag == 0) res = find(node.r, target);
        return res;
    }

    public void insert(char x, char fx, int flag) {
        TNode f = find(root, fx);
        if (f == null) return;
        TNode newNode = new TNode(x);
        if (flag == 0) f.l = newNode;
        else f.r = newNode;
    }

    // --- 2. 线索化过程 (中序) ---
    public void inOrderThreading(TNode node) {
        if (node == null) return;

        // 线索化左子树
        if (node.lflag == 0) inOrderThreading(node.l);

        // 访问并建立线索 (对应你的 Visit)
        if (node.l == null) {
            node.l = pre;
            node.lflag = 1;
        }
        if (pre != null && pre.r == null) {
            pre.r = node;
            pre.rflag = 1;
        }
        pre = node;

        // 线索化右子树
        if (node.rflag == 0) inOrderThreading(node.r);
    }

    // --- 3. 寻找前驱和后继 ---
    public void printSuccessorAndPredecessor(char x) {
        TNode p = find(root, x);
        if (p == null) return;

        // 找前驱
        if (p.l == null) System.out.println("没有前驱");
        else if (p.lflag == 1) System.out.println("前驱是: " + p.l.data);
        else {
            // 左子树最右边的结点
            TNode q = p.l;
            while (q.rflag == 0 && q.r != null) q = q.r;
            System.out.println("前驱是: " + q.data);
        }

        // 找后继
        if (p.r == null) System.out.println("没有后继");
        else if (p.rflag == 1) System.out.println("后继是: " + p.r.data);
        else {
            // 右子树最左边的结点
            TNode q = p.r;
            while (q.lflag == 0 && q.l != null) q = q.l;
            System.out.println("后继是: " + q.data);
        }
    }

    public static void main(String[] args) {
        // 构建过程参考你的输入示例
        ThreadedBinaryTree tree = new ThreadedBinaryTree('A');
        // ... 此处省略 insert 调用 ...

        // 线索化
        tree.inOrderThreading(tree.root);

        // 查询
        tree.printSuccessorAndPredecessor('A');
    }
}