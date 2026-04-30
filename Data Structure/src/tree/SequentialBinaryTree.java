package tree;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 二叉树顺序存储实现
 * 利用下标性质：左孩子 = 2*i, 右孩子 = 2*i+1, 父亲 = i/2
 */
public class SequentialBinaryTree {
    private char[] tree;
    private final int MAX_SIZE = 1005;

    public SequentialBinaryTree() {
        tree = new char[MAX_SIZE];
        // 初始化为空格，代表该位置没有结点
        Arrays.fill(tree, ' ');
    }

    /**
     * 查找结点所在的下标
     */
    public int findIndex(char target) {
        for (int i = 1; i < MAX_SIZE; i++) {
            if (tree[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 设置根结点
     */
    public void setRoot(char r) {
        tree[1] = r;
    }

    /**
     * 插入结点
     * @param x 新结点
     * @param fx 父亲结点
     * @param flag 0为左孩子，1为右孩子
     */
    public void insert(char x, char fx, int flag) {
        int parentIdx = findIndex(fx);
        if (parentIdx == -1) {
            System.out.println("未找到父结点: " + fx);
            return;
        }

        if (flag == 0) {
            tree[2 * parentIdx] = x;
        } else {
            tree[2 * parentIdx + 1] = x;
        }
    }

    /**
     * 查询结点信息
     */
    public void query(char x) {
        int i = findIndex(x);
        if (i == -1 || tree[i] == ' ') {
            System.out.println("结点不存在");
            return;
        }

        // 1. 找父亲
        if (i == 1) {
            System.out.println(x + " 是根结点");
        } else {
            System.out.println(x + " 的父亲是: " + tree[i / 2]);
        }

        // 2. 找左孩子
        if (2 * i < MAX_SIZE && tree[2 * i] != ' ') {
            System.out.println(x + " 的左孩子是: " + tree[2 * i]);
        } else {
            System.out.println(x + " 没有左孩子");
        }

        // 3. 找右孩子
        if (2 * i + 1 < MAX_SIZE && tree[2 * i + 1] != ' ') {
            System.out.println(x + " 的右孩子是: " + tree[2 * i + 1]);
        } else {
            System.out.println(x + " 没有右孩子");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SequentialBinaryTree myTree = new SequentialBinaryTree();

        System.out.println("输入结点总数:");
        int n = sc.nextInt();

        System.out.println("输入根结点:");
        char r = sc.next().charAt(0);
        myTree.setRoot(r);

        System.out.println("输入 n-1 行 (子 亲 类型[0左1右]):");
        for (int i = 0; i < n - 1; i++) {
            char x = sc.next().charAt(0);
            char fx = sc.next().charAt(0);
            int flag = sc.nextInt();
            myTree.insert(x, fx, flag);
        }

        System.out.println("输入要查询的结点:");
        char target = sc.next().charAt(0);
        myTree.query(target);

        sc.close();
    }
}