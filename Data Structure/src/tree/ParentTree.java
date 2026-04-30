package tree;

import java.util.Scanner;

/**
 * 树的双亲表示法：利用数组存储，记录每个节点的父亲下标
 */
public class ParentTree {

    // 节点类：对应 C 中的 struct Node
    private static class Node {
        char data;
        int parent; // 双亲节点的下标

        Node(char data, int parent) {
            this.data = data;
            this.parent = parent;
        }
    }

    private Node[] nodes;
    private int len;
    private final int MAX_SIZE = 100;

    public ParentTree() {
        this.nodes = new Node[MAX_SIZE];
        this.len = 0;
    }

    /**
     * 查找数据所在的下标：对应 C 中的 Find
     */
    public int findIndex(char data) {
        for (int i = 0; i < len; i++) {
            if (nodes[i].data == data) {
                return i;
            }
        }
        return -1; // 未找到
    }

    /**
     * 插入节点：对应 C 中的 Insert
     */
    public void insert(char x, char fx) {
        if (len >= MAX_SIZE) {
            System.out.println("数组已满");
            return;
        }
        int parentIdx = findIndex(fx);
        nodes[len] = new Node(x, parentIdx);
        len++;
    }

    /**
     * 设置根节点
     */
    public void setRoot(char r) {
        nodes[len++] = new Node(r, -1);
    }

    /**
     * 找父亲和孩子逻辑
     */
    public void query(char x) {
        int i = findIndex(x);
        if (i == -1) {
            System.out.println("节点不存在");
            return;
        }

        // 找父亲
        if (nodes[i].parent == -1) {
            System.out.println(x + " 是根节点");
        } else {
            System.out.println(x + " 的父亲是: " + nodes[nodes[i].parent].data);
        }

        // 找孩子 (由于双亲表示法没存孩子信息，只能全表扫描)
        System.out.print(x + " 的孩子是: ");
        boolean hasChild = false;
        for (int j = 0; j < len; j++) {
            if (nodes[j].parent == i) {
                System.out.print(nodes[j].data + " ");
                hasChild = true;
            }
        }
        if (!hasChild) System.out.print("无 (叶子节点)");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParentTree tree = new ParentTree();

        System.out.println("输入节点总数:");
        int n = sc.nextInt();

        System.out.println("输入根节点:");
        char r = sc.next().charAt(0);
        tree.setRoot(r);

        System.out.println("输入子父关系 (子 亲):");
        for (int i = 1; i < n; i++) {
            char x = sc.next().charAt(0);
            char fx = sc.next().charAt(0);
            tree.insert(x, fx);
        }

        System.out.println("输入要查询的节点:");
        char target = sc.next().charAt(0);
        tree.query(target);

        sc.close();
    }
}