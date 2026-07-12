package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CriticalPath {
    static int[] ltv=new int[105];
    static int[] etv=new int[105];
    static int[] topo=new int[105];
    static class Node{
        int data;
        int w;
        Node next;
        Node(int data,int w){
            this.data=data;
            this.w=w;
            this.next=null;
        }
    }
    static class e{
        char adj;
        Node first;
        e(char adj){
            this.adj=adj;
            this.first=null;
        }
    }
    static int find(char x){
         for(int i=0;i<n;i++){
             if(graph[i].adj==x){
                 return i;
             }
         }
         return -1;
    }
    static void TopoSort(){
        for(int i=0;i<n;i++){
            if(degree[i]==0){
                queue.add(i);
            }
        }
        for(int i=0;i<n;i++){
            if(queue.isEmpty()){
                break;
            }
            u=queue.remove();
            topo[++k]=u;
            Node node=graph[u].first;
            while(node!=null){
                v=node.data;
                degree[v]--;
                if(degree[v]==0){
                    queue.add(v);
                }
                etv[v]=Math.max(etv[v],etv[u]+node.w);
                node=node.next;
            }
        }
    }
    static void CriticalPath(){
        int end=topo[k];
        for(int i=0;i<n;i++){
            ltv[i]=etv[end];
        }
        int i,j;
        for(int q=k;q>=1;q--){
            i=topo[q];
            Node node=graph[i].first;
            while(node!=null){
                j=node.data;
                ltv[i]=Math.min(ltv[i],ltv[j]-node.w);
                node=node.next;
            }
        }
        int ete,lte;
        for(i=0;i<n;i++){
          Node node=graph[i].first;
          while(node!=null){
              j=node.data;
              ete=etv[i];
              lte=ltv[j]-node.w;
              if(ete==lte){
                  System.out.println(""+graph[i].adj+graph[j].adj);
              }
              node=node.next;
          }
        }
    }
    static int[] degree=new int[105];
    static e[] graph=new e[105];
    static int n,m,u,v,k;
    static Queue<Integer> queue=new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        String s=sc.next();
        for(int i=0;i<n;i++){
            char x=s.charAt(i);
            e enode=new e(x);
            graph[i]=enode;
        }
        char x,y;
        int w,xi,yi;
        for(int i=0;i<m;i++){
            x=sc.next().charAt(0);
            y=sc.next().charAt(0);
            w=sc.nextInt();
            xi=find(x);
            yi=find(y);
            Node node=new Node(yi,w);
            degree[yi]++;
            node.next=graph[xi].first;
            graph[xi].first=node;
        }
        TopoSort();
        CriticalPath();
    }
}
/*
9 11
ABCDEFGHY
A B 6
A C 4
A D 5
B E 1
C E 1
D F 2
E G 9
E H 7
F H 4
G Y 2
H Y 4
*/
