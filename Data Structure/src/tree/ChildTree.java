package tree;

import java.util.Scanner;

/**
 * 树的孩子表示法：体现 Java 的组合与封装
 */
public class ChildTree {

    /**
     * 孩子链表的结点：对应 C 中的 SonNode
     */
    private static class SonNode {
        int sonIndex;   // 孩子在数组中的下标
        SonNode next;

        SonNode(int sonIndex) {
            this.sonIndex = sonIndex;
            this.next = null;
        }
    }

    /**
     * 树结点的定义：对应 C 中的 Tree 结构体
     */
    private static class TreeNode {
        char data;
        SonNode firstChild; // 指向第一个孩子的指针

        TreeNode(char data) {
            this.data = data;
            this.firstChild = null;
        }
    }

    private TreeNode[] nodes; // 存储结点的数组
    private int len;          // 当前结点个数
    private int maxSize;      // 最大容量

    public ChildTree(int maxSize) {
        this.maxSize = maxSize;
        this.nodes = new TreeNode[maxSize];
        this.len = 0;
    }

    /**
     * 查找结点数据对应的下标
     */
    private int findIndex(char data) {
        for (int i = 0; i < len; i++) {
            if (nodes[i].data == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 插入结点 x 作为 fx 的孩子
     */
    public void insert(char x, char fx) {
        if (len >= maxSize) {
            System.out.println("数组已满");
            return;
        }

        // 1. 先把新结点 x 放入数组
        nodes[len] = new TreeNode(x);

        // 2. 找到父亲 fx 的下标
        int parentIdx = findIndex(fx);
        if (parentIdx == -1) {
            System.out.println("未找到父结点: " + fx);
            return;
        }

        // 3. 将新结点的下标 len 插入到父亲的孩子链表中 (头插法)
        SonNode s = new SonNode(len);
        s.next = nodes[parentIdx].firstChild;
        nodes[parentIdx].firstChild = s;

        len++;
    }

    /**
     * 打印某个结点的所有孩子
     */
    public void printChildren(char x) {
        int idx = findIndex(x);
        if (idx == -1) return;

        SonNode p = nodes[idx].firstChild;
        if (p == null) {
            System.out.println(x + " 是叶子结点");
        } else {
            System.out.print(x + " 的孩子有: ");
            while (p != null) {
                System.out.print(nodes[p.sonIndex].data + " ");
                p = p.next;
            }
            System.out.println();
        }
    }

    /**
     * 打印某个结点的父亲
     */
    public void printParent(char x) {
        int targetIdx = findIndex(x);
        if (targetIdx == -1) return;

        for (int i = 0; i < len; i++) {
            SonNode p = nodes[i].firstChild;
            while (p != null) {
                if (p.sonIndex == targetIdx) {
                    System.out.println(x + " 的父亲是: " + nodes[i].data);
                    return;
                }
                p = p.next;
            }
        }
        System.out.println(x + " 是根结点");
    }

    // 设置根结点
    public void setRoot(char rootData) {
        nodes[len++] = new TreeNode(rootData);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入结点总数:");
        int n = sc.nextInt();

        ChildTree tree = new ChildTree(100);

        System.out.println("输入根结点数据:");
        char root = sc.next().charAt(0);
        tree.setRoot(root);

        System.out.println("输入 n-1 行 (孩子 父亲):");
        for (int i = 1; i < n; i++) {
            char x = sc.next().charAt(0);
            char fx = sc.next().charAt(0);
            tree.insert(x, fx);
        }

        System.out.println("查询目标结点:");
        char target = sc.next().charAt(0);

        tree.printChildren(target);
        tree.printParent(target);

        sc.close();
    }
}