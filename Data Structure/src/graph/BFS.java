package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//0<=n<=100
public class BFS {
    static class ENode{
        int s;//记录结点的下标
        ENode next;
        ENode(int s){
            this.s=s;
            this.next=null;
        }
    }
    static class graph{
        char data;//数据
        ENode first;
        graph(char data){
            this.data=data;
            this.first=null;
        }
    }
    static void bfs(int i){
        if(flag[i]==true){
            return;
        }
        queue.add(i);
        flag[i]=true;
        ENode q=null;
        while (!queue.isEmpty()){
            int x=queue.remove();
            System.out.print(x+" ");
            q=e[x].first;
            while(q!=null){
                if(flag[q.s]==false){
                    queue.add(q.s);
                    flag[q.s]=true;
                }
                q=q.next;
            }
        }
    }
    static int find(char data){
        for(int i=0;i<n;i++){
            if(e[i].data==data){
                return  i;
            }
        }
        return -1;
    }
    static Boolean[] flag=new Boolean[105];//标记该下标所对应的结点有没有被遍历过
    static graph[] e=new graph[105];
    static  Queue<Integer> queue=new LinkedList<>();
    static int n,m;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        String s=sc.next();
        for(int i=0;i<n;i++){
            flag[i]=false;
            e[i]=new graph(s.charAt(i));
        }
        int xi,yi;
        char x,y;
        for(int i=0;i<m;i++){
            x=sc.next().charAt(0);
            y=sc.next().charAt(0);
            xi=find(x);
            yi=find(y);
            if (xi == -1 || yi == -1) continue;
            ENode p=new ENode(xi);
            ENode q=e[yi].first;
            p.next=q;
            e[yi].first=p;

            p=new ENode(yi);
            q=e[xi].first;
            p.next=q;
            e[xi].first=p;
        }
        for(int i=0;i<n;i++){
            bfs(i);
        }
    }
}
/**
9 16
ABCDEFGHI
A B
A F
B G
G F
B C
B I
C I
C D
I D
D G
D H
D E
G H
H E
E F
F G
 */
