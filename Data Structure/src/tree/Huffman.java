package tree;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
      static class Node{
        char data;
        int weight;
        Node left,right;
        public Node(char data,int weight){
            this.data=data;
            this.weight=weight;
        }
        public Node(int weight,Node left,Node right){
            this.weight=weight;
            this.left=left;
            this.right=right;
        }
    }
    public static Node buildTree(char[] val,int[] w){
        PriorityQueue<Node> pq=new PriorityQueue<>((a,b)->a.weight=b.weight);
        for (int i=0;i<val.length;i++){
            pq.offer(new Node(val[i], w[i]));
        }
        while (pq.size()>1){
            Node left=pq.poll();
            Node right=pq.poll();
            pq.offer(new Node(left.weight+right.weight,left,right));
        }
        return pq.poll();
    }
    public static void generateCodes(Node root, String path, HashMap<Character,String> map){
          if(root==null) return;
          if(root.left==null&&root.right==null) {
              map.put(root.data, path);
              return;
          }
          generateCodes(root.left,path+'0',map);
          generateCodes(root.right,path+'1',map);
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
        Node root=buildTree(val,w);
        HashMap<Character,String> map=new HashMap<>();
        generateCodes(root,"",map);
        map.forEach((ch,path)-> System.out.println((""+ch+ ' '+path)));
    }
}
