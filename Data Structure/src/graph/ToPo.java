package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ToPo {
    static int n,m;
    static class Node{
        int  data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    static class e{
        char data;
        Node first;
        e(char data){
            this.data=data;
            this.first=null;
        }
    }
    static int[] degree=new int[105];
    static e[] graph=new e[105];
    static int[] topo=new int[105];
    static Queue<Integer> queue=new LinkedList<>();
    static  int cnt=0,j;
    static Node node=null;
    static void topoSort(){
        for(int i=0;i<n;i++){
            if(degree[i]==0){
                queue.add(i);
            }
        }

        for(int i=0;i<n;i++){
            if(queue.isEmpty()){
                break;
            }
            j=queue.remove();
            topo[cnt++]=j;
            node=graph[j].first;
            while(node!=null){
                degree[node.data]--;
                if(degree[node.data]==0){
                    queue.add(node.data);
                }
                node=node.next;
            }
        }
    }
    static int  find(char x){
         for(int i=0;i<n;i++){
             if(graph[i].data==x){
                return i;
             }
         }
         return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        String s=sc.next();
        char x;
        for(int i=0;i<n;i++){
            x=s.charAt(i);
            e enode=new e(x);
            graph[i]=enode;
        }
        char y;
        int xi,yi;
        for(int i=0;i<m;i++){
            x=sc.next().charAt(0);
            y=sc.next().charAt(0);
            xi=find(x);
            yi=find(y);
            Node node=new Node(yi);
            degree[yi]++;
            node.next=graph[xi].first;
            graph[xi].first=node;
        }
        topoSort();
        if(cnt == n){
            System.out.println("拓扑排序成功");
            for(int i = 0; i < cnt; i++){
                System.out.print(graph[topo[i]].data + " ");
            }
        }
        else{
            System.out.println("错的");
        }
    }
}
/*
6 8
ABCDEF
A B
A C
A D
C B
C E
F D
F E
D E
*/


