package tree;

import java.util.Scanner;

/**
 * 树的孩子兄弟表示法：将普通树转换为二叉链表
 */
public class ChildSiblingTree {

    /**
     * 结点类：对应 C 中的 Node
     */
    private static class Node {
        char data;
        Node firstChild; // 指向第一个孩子 (对应 C 中的 son)
        Node nextSibling; // 指向右侧相邻的兄弟 (对应 C 中的 bro)

        Node(char data) {
            this.data = data;
            this.firstChild = null;
            this.nextSibling = null;
        }
    }

    private Node root;

    public ChildSiblingTree(char rootData) {
        this.root = new Node(rootData);
    }

    /**
     * 递归查找结点：对应 C 中的 Find
     * 核心逻辑：先查根，再递归查孩子，最后递归查兄弟
     */
    public Node find(Node root, char fx) {
        if (root == null) return null;
        if (root.data == fx) return root;

        // 递归去孩子里找
        Node ans = find(root.firstChild, fx);
        if (ans != null) return ans;

        // 递归去兄弟里找
        return find(root.nextSibling, fx);
    }

    /**
     * 插入结点：对应 C 中的 Insert
     * @param x 新结点数据
     * @param fx 父亲结点数据
     */
    public void insert(char x, char fx) {
        Node parent = find(root, fx);
        if (parent == null) {
            System.out.println("未找到父节点 " + fx);
            return;
        }

        Node newNode = new Node(x);

        // 分两种情况
        if (parent.firstChild == null) {
            // 1. 如果父亲还没有孩子，新节点成为长子
            parent.firstChild = newNode;
        } else {
            // 2. 如果已经有孩子，找到孩子链表的末尾（最右边的兄弟）
            Node k = parent.firstChild;
            while (k.nextSibling != null) {
                k = k.nextSibling;
            }
            k.nextSibling = newNode;
        }
    }

    /**
     * 打印孩子结点
     */
    public void printChildren(char x) {
        Node p = find(root, x);
        if (p == null) {
            System.out.println("该结点不存在");
            return;
        }

        Node k = p.firstChild;
        if (k == null) {
            System.out.println(x + " 是叶子结点");
        } else {
            System.out.print(x + " 的孩子有：");
            while (k != null) {
                System.out.print(k.data + " ");
                k = k.nextSibling; // 顺着兄弟指针走
            }
            System.out.println();
        }
    }

    // --- 主测试函数 ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入结点总数:");
        int n = sc.nextInt();

        System.out.println("请输入根节点数据:");
        char rootData = sc.next().charAt(0);
        ChildSiblingTree tree = new ChildSiblingTree(rootData);

        System.out.println("请输入 " + (n - 1) + " 组数据 (子 亲):");
        for (int i = 0; i < n - 1; i++) {
            char x = sc.next().charAt(0);
            char fx = sc.next().charAt(0);
            tree.insert(x, fx);
        }

        System.out.println("请输入要查询孩子的结点:");
        char query = sc.next().charAt(0);
        tree.printChildren(query);

        sc.close();
    }
}
