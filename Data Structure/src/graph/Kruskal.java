package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 给定一个带权无向连通图：该图中含有n(10<=n<=100)个点，m条边，
   n个点的数据刚好是0~~n-1
   求该图的最小生成树：从m条边中，选出n-1条边，使得图连通并且边权和最小。
   输出最小生成树的边权和。 */
class Edge implements Comparable<Edge>{
    int x,y;
    int w;
    Edge(int x, int y, int w){
        this.x=x;
        this.y=y;
        this.w=w;
    }

    @Override
    public int compareTo(Edge other) {
        return this.w-other.w;
    }
}
public class Kruskal {
    static int[] flag=new int[105];
    static int n,m,sum,cnt;
    static List<Edge> list=new ArrayList<>();
    static int find(int x){
        if(flag[x]==x) return x;
        else return flag[x]=find(flag[x]);
    }
    static void kruskal(){
        for(int i=0;i<n;i++){
            flag[i]=i;
        }
        int x,y,xi,yi;
        for (Edge edge : list) {
            x=edge.x;
            y=edge.y;
            xi=find(x);
            yi=find(y);
            if(xi!=yi){
                flag[xi]=yi;
                System.out.println(x+" "+y+" "+edge.w);
                cnt++;
                sum+=edge.w;
            }
            if(cnt==n-1) break;
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        int x,y,w;
        for(int i=0;i<m;i++){
            x=sc.nextInt();
            y=sc.nextInt();
            w=sc.nextInt();
            Edge edge=new Edge(x,y,w);
            list.add(edge);
        }
        Collections.sort(list);
        kruskal();
    }
}
/*
9 15
0 1 3
0 5 4
1 6 6
6 5 7
1 2 8
1 8 5
2 8 2
2 3 12
8 3 11
6 3 14
6 7 9
5 4 18
3 7 6
7 4 1
3 4 10
*/