package tree;

import java.util.Scanner;

/**
 * B树（5阶）的插入与打印实现
 * 常量 M = 5，每个节点最多4个关键字，最少2个关键字（根节点除外）
 */
public class BTree {
    private static final int M=5;

    private static final int  MIN_NUM = (M + 1) / 2 - 1;

    private static BTNode root; // 根节点
    private static class BTNode{
        int[] key;
        BTNode[] ptr;
        BTNode fa;
        int keynum;
        BTNode(){
            this.key=new int[M+1];
            this.ptr=new BTNode[M+1];
            fa=null;
            keynum=0;
        }
    }

    public static class Result{
        BTNode z;
        int i;
        Boolean tag;
    }

    public static int searchNode(BTNode p,int k){
        int i=1;
        while(i<=p.keynum&&p.key[i]<k){
            i++;
        }
        return i;
    }
    public static Result searchBTree(int k){
       Result r=new Result();
       BTNode p=root;
       BTNode pre=null;
       int i=0;
       boolean found=false;
       while(!found&&p!=null){
           i=searchNode(p,k);
           if(i<=p.keynum&&p.key[i]==k){
               found=true;
               break;
           }
           else{
               pre=p;
               p=p.ptr[i-1];
           }
       }
       if(found){
           r.z=p;
           r.i=i;
           r.tag=true;
       }
       else {
           r.z=pre;
           r.i=i;
           r.tag=false;
       }
      return r;
    }

    public static BTNode newRoot(BTNode z,int k,BTNode ap){
         BTNode s=new BTNode();
         s.key[1]=k;
         s.ptr[0]=z;
         s.ptr[1]=ap;
         s.keynum=1;
         if(z!=null) z.fa=s;
         if(ap!=null) ap.fa=s;
         return s;
    }
    public static void printBTree() {
        if (root == null) {
            System.out.println("(空树)");
            return;
        }
        System.out.println("-------------------- B树 --------------------");
        java.util.Queue<BTNode> q = new java.util.LinkedList<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            System.out.print("第" + level + "层: ");
            for (int i = 0; i < size; i++) {
                BTNode t = q.poll();
                System.out.print("[");
                for (int j = 1; j <= t.keynum; j++) {
                    System.out.print(t.key[j]);
                    if (j < t.keynum) System.out.print(" ");
                }
                System.out.print("] ");
                for (int j = 0; j <= t.keynum; j++) {
                    if (t.ptr[j] != null) q.offer(t.ptr[j]);
                }
            }
            System.out.println();
            level++;
        }
        System.out.println("----------------------------------------------");
    }
    public  static void insert(BTNode z,int i,int k,BTNode ap){
        for(int j=z.keynum;j>=i;j--){
            z.key[j+1]=z.key[j];
            z.ptr[j+1]=z.ptr[j];
        }
        z.key[i]=k;
        z.ptr[i]=ap;
        if(ap!=null) ap.fa=z;
        z.keynum++;
    }
    public static BTNode spilt(BTNode z,int s){
        BTNode ap=new BTNode();
        int j=0;
        ap.ptr[0]=z.ptr[s];
        if (z.ptr[s] != null) z.ptr[s].fa = ap;
        for(int i=s+1;i<=M;i++){
            j++;
            ap.key[j]=z.key[i];
            ap.ptr[j]=z.ptr[i];
            if (z.ptr[i] != null) z.ptr[i].fa = ap;
        }
        ap.keynum=j;
        z.keynum=s-1;
        return ap;
    }
    public static void insertKey(int k){
        if(root==null){
            root=newRoot(null,k,null);
            return;
        }
        Result r=searchBTree(k);
        if (r.tag == true) {
            System.out.println("关键字 " + k + " 已存在，不重复插入");
            return;
        }
       BTNode ap=null;
        int s=0;
        BTNode z=r.z;
        int i=r.i;
        while (true){
            insert(z,i,k,ap);
            if(z.keynum<M){
                break;
            }
            else {
                s=(M+1)/2;
                k=z.key[s];
                ap=spilt(z,s);
                if(z.fa!=null){
                    z=z.fa;
                    i=searchNode(z,k);
                }
                else{
                    root=newRoot(z,k,ap);
                    break;
                }
            }
        }
    }
    public static void insertKeyOperation(){
        Scanner sc=new Scanner(System.in);
        int k;
        char c;
        while (true){
            System.out.println("请输入要插入的数据");
            k=sc.nextInt();
            insertKey(k);
            System.out.println("===============B树===============");
            printBTree();
            System.out.println("================================");
            System.out.println("是否要继续插入是按y否按n");
            c=sc.next().charAt(0);
            if(c=='n'){
                break;
            }
        }
    }
    public static void deleteKeyOperation(){
        int k;
        Result r;

    }
    public static void main(String[] args) {
        insertKeyOperation();

    }
}
