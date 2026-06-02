package graph;

import java.util.Scanner;

public class AdjacencyMultilist {
    static class ENode{
        int w;
        int x;
        ENode xnext;
        int y;
        ENode ynext;
        ENode(int w,int x,int y){
            this.w=w;
            this.x=x;
            this.y=y;
            this.xnext=this.ynext=null;
        }
    }
    static class e{
        char data;
        ENode first;
        e(char data){
            this.data=data;
            first=null;
        }
    }
    static int find(char x){
        for(int i=1;i<=n;i++){
            if(graph[i].data==x){
                return i;
            }
        }
        return -1;
    }
    static int n,m;
    static e[] graph=new e[105];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        String s=sc.next();
        for(int i=1;i<=n;i++){
            graph[i]=new e(s.charAt(i-1));
        }
        char x,y;
        int w,xi,yi;
        for(int i=1;i<=m;i++){
            x=sc.next().charAt(0);
            y=sc.next().charAt(0);
            w=sc.nextInt();
            xi=find(x);
            yi=find(y);
            ENode eNode=new ENode(w,xi,yi);
            eNode.xnext=graph[xi].first;
            graph[xi].first=eNode;
            eNode.ynext=graph[yi].first;
            graph[yi].first=eNode;
        }
    }
}
