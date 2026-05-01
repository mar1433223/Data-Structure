package tree;

import java.util.Stack;

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

    // --- 1. 查找 (非递归版) ---
    public BSTNode searchNonRecursive(int x) {
        BSTNode node = this.root;
        while (node != null && node.data != x) {
            if (x < node.data) {
                node = node.l;
            } else {
                node = node.r;
            }
        }
        return node;
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
    // --- 2. 插入 (非递归版) ---
    public void insertNonRecursive(int x) {
        if (this.root == null) {
            this.root = new BSTNode(x);
            return;
        }

        BSTNode node = this.root;
        while (true) {
            if (x < node.data) {
                if (node.l == null) {
                    node.l = new BSTNode(x);
                    return;
                }
                node = node.l;
            } else if (x > node.data) {
                if (node.r == null) {
                    node.r = new BSTNode(x);
                    return;
                }
                node = node.r;
            } else {
                // 值已存在，不插入（可选）
                return;
            }
        }
    }
    //删除非递归
    public void delete1(int x){
        if(root==null) {
            System.out.println("根为空");
        } else if(search(root,x)==null) {
            System.out.println("找不到该结点");
        }else{
            BSTNode pre=null;
            BSTNode p=search(root,x);
            //用前驱来做代替
            if(p.l!=null&&p.r!=null){
                BSTNode y=p.l;
                BSTNode fy=p;
                while(y.r!=null){
                    fy=y;
                    y=y.r;
                }
                p.data=y.data;
                pre=fy;
                p=y;
            }

            BSTNode ch=null;
            if(p.l!=null) ch=p.l;
            else ch=p.r;
            if(pre==null){
                root=ch;
            }else{
                if(pre.l==p) pre.l=ch;
                else pre.r=ch;
            }
            p=null;
        }
    }
    public void delete2(int x){
        root=deleteRecursive(root,x);
    }
    public BSTNode deleteRecursive(BSTNode p,int x){
        if(p==null) return p;
        if(p.data>x){
            p.l=deleteRecursive(p.l,x);
        }else if(p.data<x){
            p.r=deleteRecursive(p.r,x);
        }else {
            if(p.l!=null&&p.r!=null){
                //用后继来代替删除结点
                BSTNode y=p.r;
                while(y.l!=null){
                    y=y.l;
                }
                p.data=y.data;
                p.r=deleteRecursive(p.r, y.data);
            }else {
                 if(p.l!=null) p=p.l;
                 else p=p.r;
            }

        }
        return p;
    }

    // --- 3. 中序遍历 (递归版) ---
    public void inOrder(BSTNode node) {
        if (node == null) return;
        inOrder(node.l);
        System.out.print(node.data + " ");
        inOrder(node.r);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] data = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        System.out.println("=== 递归版本 ===");
        for (int x : data) {
            bst.insert(x);
        }

        System.out.print("中序遍历结果（应为升序）: ");
        bst.inOrder(bst.root);
        System.out.println();

        System.out.println("\n=== 非递归版本 ===");
        BinarySearchTree bst2 = new BinarySearchTree();
        for (int x : data) {
            bst2.insertNonRecursive(x);
        }

        System.out.print("中序遍历结果（应为升序）: ");
        System.out.println();
        bst2.delete1(3);
        bst2.inOrder(bst2.root);
        bst.deleteRecursive(bst.root,3);
        System.out.println();
        bst.inOrder(bst.root);
        // 测试查找功能
        System.out.println("\n=== 查找测试 ===");
        System.out.println("查找 6: " + (bst2.searchNonRecursive(6) != null ? "找到" : "未找到"));
        System.out.println("查找 5: " + (bst2.searchNonRecursive(5) != null ? "找到" : "未找到"));
    }
}