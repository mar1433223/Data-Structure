package tree;

import java.util.Scanner;

public class AVLTree {
    private static class AVLNode{
        int data;
        AVLNode left;
        AVLNode right;
        int h;
        public AVLNode(int data){
            this.data=data;
            left=right=null;
            h=1;
        }
    }
    //获取高度
    private int get_H(AVLNode node){
     return node==null?0:node.h;
    }

    private AVLNode root=null;
    //查找
    private AVLNode search(AVLNode node, int x) {
        if (node == null || node.data == x) return node;
        if (x < node.data) return search(node.left, x);
        else return search(node.right, x);
    }
    //LL情况旋转,右旋
    private AVLNode rotateLL(AVLNode node){
        AVLNode node1=node.left;
        node.left= node1.right;
        node1.right=node;
        node.h=Math.max(get_H(node.left),get_H(node.right))+1;
        node1.h=Math.max(get_H(node1.left),get_H(node1.right))+1;
        return node1;
    }
    //RR情况旋转，左旋
    private AVLNode rotateRR(AVLNode node){
        AVLNode node1=node.right;
        node.right=node1.left;
        node1.left=node;
        node.h=Math.max(get_H(node.left),get_H(node.right))+1;
        node1.h=Math.max(get_H(node1.left),get_H(node1.right))+1;
        return node1;
    }
    //LR情况旋转，先左旋再右旋
    private AVLNode rotateLR(AVLNode node){
        node.left=rotateRR(node.left);
        return rotateLL(node);
    }
    //RL情况旋转，先右旋转再左旋
    private AVLNode rotateRL(AVLNode node){
        node.right=rotateLL(node.right);
         return rotateRR(node);
    }
    //插入
    private void insert(int x) {
        this.root = insertRecursive(this.root, x);
    }

    private AVLNode insertRecursive(AVLNode node, int x) {

        if (node == null) {
            return new AVLNode(x);
        }

        if (x < node.data) {
            node.left = insertRecursive(node.left, x);// 递归修改左子树并重新挂载
            if(get_H(node.left)-get_H(node.right)>1){
                if(x < node.left.data){
                    node=rotateLL(node);
                }else {
                    node=rotateLR(node);
                }
            }
        } else if (x > node.data) {
            node.right = insertRecursive(node.right, x); // 递归修改右子树并重新挂载
            if(get_H(node.right)-get_H(node.left)>1){
                if(x > node.right.data){
                    node=rotateRR(node);
                }else{
                    node=rotateRL(node);
                }
            }
        }
       node.h=Math.max(get_H(node.left),get_H(node.right))+1;
        return node; // 返回修改后的节点
    }
    public void delete(int x){
        root=deleteRecursive(root,x);
    }
    public AVLNode deleteRecursive(AVLNode p, int x){
        if(p==null) return p;
        if(p.data>x){
            p.left=deleteRecursive(p.left,x);
            if(get_H(p.right)-get_H(p.left)>1){
                AVLNode right=p.right;
                if(get_H(right.right)>=get_H(right.left)){
                    p=rotateRR(p);
                }
                else p=rotateRL(p);
            }
        }else if(p.data<x){
            p.right=deleteRecursive(p.right,x);
            if(get_H(p.left)-get_H(p.right)>1){
                AVLNode left=p.left;
                if(get_H(left.left)>=get_H(left.right)){
                    p=rotateLL(p);
                }else{
                    p=rotateLR(p);
                }
            }
        }else {
            if(p.left!=null&&p.right!=null){
                //用后继来代替删除结点
                AVLNode y=p.right;
                while(y.left!=null){
                    y=y.left;
                }
                p.data=y.data;
                p.right=deleteRecursive(p.right, y.data);
                if(get_H(p.left)-get_H(p.right)>1) {
                    AVLNode left = p.left;
                    if (get_H(left.left) >= get_H(left.right)) {
                        p = rotateLL(p);
                    } else {
                        p = rotateLR(p);
                    }
                }
            }else {
                if(p.left!=null) p=p.left;
                else p=p.right;
            }

        }
        if(p!=null) {
            p.h = Math.max(get_H(p.left),get_H(p.right))+1;
        }
        return p;
    }
    private void inorder(AVLNode node){
        if(node==null)
            return;
        inorder(node.left);
        System.out.println(node.data + " " + (get_H(node.left)-get_H(node.right)));
       inorder(node.right);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        AVLTree avl=new AVLTree();
        int n, x;//假设要插入n个数据
        n=sc.nextInt();
        for(int i=0;i<n;i++){
          x=sc.nextInt();
          avl.insert(x);
        }
        avl.inorder(avl.root);
        while(avl.root!=null){
            int a=sc.nextInt();
            avl.delete(a);
            avl.inorder(avl.root);
        }
    }
}
