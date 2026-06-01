package graph;

import java.util.Scanner;

public class AdjacencyList {
    static class Node{
        int adj;
        int w;
        Node next;
        Node(int adj,int w){
            this.adj=adj;
            this.w=w;
            this.next=null;
        }
    }
    static class vv{
        char data;
        Node next;
        vv(char data){
            this.data=data;
            this.next=null;
        }
    }
    static vv[] v=new vv[105];
    static int find(char x){
        for(int i=1;i<=n;i++){
            if(v[i].data==x){
                return i;
            }
        }
        return -1;
    }
    static int n,m;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        String s=sc.next();
        for(int i=1;i<=n;i++){
            v[i]=new vv(sc.next().charAt(i-1));
        }
        char x,y;
        int w,xi,yi;
        for(int i=1;i<=m;i++){
            x=sc.next().charAt(0);
            y=sc.next().charAt(0);
            w=sc.nextInt();
            xi=find(x);
            yi=find(y);
            if (xi == -1 || yi == -1) continue;
            Node node=new Node(yi,w);
            Node first=v[xi].next;
            v[xi].next=node;
            node.next=first;

            node=new Node(xi,w);
            first=v[yi].next;
            v[yi].next=node;
            node.next=first;
        }
    }
}
