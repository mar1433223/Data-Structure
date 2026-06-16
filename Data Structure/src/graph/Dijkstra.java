package graph;

import java.util.Scanner;

public class Dijkstra {
    static int[][] g=new int[105][105];
    static boolean[] flag=new boolean[105];
    static int[] pre=new int[105];
    static int[] dist=new int[105];
    static int n,m;
    static void dijkstra(int s){
        dist[s]=0;

        for(int i=0;i<n;i++){
            int min=-1;
            int t=Integer.MAX_VALUE/2;
            for(int j=0;j<n;j++){
                if(!flag[j]&&dist[j]<t){
                    min=j;
                    t=dist[j];
                }
            }
            flag[min]=true;
            for(int j=0;j<n;j++){
                if(!flag[j]&&dist[min]+g[min][j]<dist[j]){
                    dist[j]=dist[min]+g[min][j];
                    pre[j]=min;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        for(int i=0;i<n;i++){
            flag[i]=false;
            dist[i]=Integer.MAX_VALUE/2;
            pre[i]=-1;
            for(int j=0;j<n;j++){
                if(i==j){
                    g[i][j]=0;
                }else{
                    g[i][j]=Integer.MAX_VALUE/2;
                }
            }
        }
        int x,y,w;
        for(int i=0;i<m;i++){
            x=sc.nextInt();
            y=sc.nextInt();
            w=sc.nextInt();
            g[x][y]=g[y][x]=w;
        }
        int s;
        s=sc.nextInt();
        dijkstra(s);
        for(int i=0;i<n;i++){
            System.out.printf(s+"到"+i+"的最短路径长度为"+dist[i]);
            int p=i;
            System.out.print("具体路径为：");
            while (p!=-1){
                System.out.printf(p+" ");
                p=pre[p];
            }
            System.out.println();
        }
    }
}
/*
9 16
0 1 1
0 2 5
1 2 3
1 3 7
1 4 5
2 4 1
2 5 7
3 4 2
3 6 3
4 5 3
4 6 6
4 7 9
5 7 5
6 7 2
6 8 7
7 8 4
0
*/

