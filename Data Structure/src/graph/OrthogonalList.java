package graph;

import java.util.Scanner;

public class OrthogonalList {
    static class ENode{
        int w;
        int tail;
        ENode tnext;
        int head;
        ENode hnext;
        ENode(int w,int tail,int head){
            this.w=w;
            this.tail=tail;
            this.head=head;
            this.hnext=this.tnext=null;
        }
    }
    static class e{
        char data;
        ENode firstout;
        ENode firstin;
        e(char data){
            this.data=data;
            firstin=firstout=null;
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
    static e[] graph=new e[105];
    static int n,m;
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
            eNode.tnext= graph[xi].firstout;
            graph[xi].firstout=eNode;
            eNode.hnext= graph[yi].firstin;
            graph[yi].firstin=eNode;
        }
    }
}
/*
4 5
ABCD
A B 3
A D 6
A C 0
B D 9
D C 4
 */