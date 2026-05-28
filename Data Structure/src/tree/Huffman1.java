package tree;

import java.util.Scanner;

public class Huffman1 {
    private static class Node{
      int weight;
      int father;
      int left,right;
      public Node(int weight){
          this.weight=weight;
          this.left=-1;
          this.right=-1;
          this.father=-1;
      }
      public Node(int weight,int left,int right){
          this.weight=weight;
          this.left=left;
          this.right=right;
          this.father=-1;
      }
    }
    private static int[] find(Node[] tree, int x) {
        int s1 = -1, s2 = -1;
        // 找最小
        for (int i = 0; i <= x; i++) {
            if (tree[i].father == -1) {
                if (s1 == -1 || tree[i].weight < tree[s1].weight) {
                    s1 = i;
                }
            }
        }
        // 找次小
        for (int i = 0; i <= x; i++) {
            if (tree[i].father == -1 && i != s1) {
                if (s2 == -1 || tree[i].weight < tree[s2].weight) {
                    s2 = i;
                }
            }
        }
        return new int[]{s1, s2};
    }

    public static Node[] create(int[] w,int n){
        int m=2*n-1;//有n个子结点，会合并n-1次，一共2*n-1个结点
        Node[] tree=new Node[m];
        for(int i=0;i<n;i++){
            tree[i]=new Node(w[i]);
        }
        for(int i=n;i<m;i++){
            int[] min = find(tree, i - 1);
            int s1 = min[0], s2 = min[1];
            tree[i]=new Node(tree[s1].weight+tree[s2].weight,s1,s2);
            tree[s1].father=tree[s2].father=i;
        }
        return tree;
    }
    public static String[] createCode(Node[] tree,int n){
        String[] code=new String[n];
        for(int i=0;i<n;i++){
            code[i]="";
        }
        for(int i=0;i<n;i++){
            int p=i;
            while (tree[p].father!=-1){
                int father=tree[p].father;
                if(tree[father].left==p){
                    code[i]="0"+code[i];
                }
                else{
                    code[i]="1"+code[i];
                }
                p=father;
            }
        }
        return code;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        char[] val=new char[n];
        int[] w=new int[n];
        String s=sc.nextLine();
        for(int i=0;i<n;i++){
            val[i]=s.charAt(i);
        }
        for (int i=0;i<n;i++){
            w[i]=sc.nextInt();
        }
        Node[] tree=create(w,n);
        String[] code=createCode(tree,n);
        for(int i=0;i<n;i++){
            System.out.println(val[i]+" "+code[i]);
        }
        sc.close();
    }

}
