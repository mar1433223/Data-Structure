package graph;

import java.util.Scanner;

public class Prim {
    static int n,m;
    static int[][] e=new int[105][105];
    static boolean[] flag=new boolean[105];
    static int[] dist=new int[105];
    static int sum=0;
    static void prim(){
        int s=0;
        dist[s]=0;
        for(int i=0;i<n;i++){
            int min=-1;
            int t=Integer.MAX_VALUE/2;
            for(int j=0;j<n;j++){
                if(dist[j]<t&&!flag[j]){
                    min=j;
                    t=dist[min];
                }
            }
            System.out.println("点"+min+"用权值为"+t+"的边加入到图中");
            flag[min]=true;
            sum+=dist[min];
            for(int j=0;j<n;j++){
                if(!flag[j]){
                    dist[j]=Math.min(dist[j],e[min][j]);
                }
            }
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        for(int i=0;i<n;i++){
            flag[i]=false;
        }
        for(int i=0;i<n;i++){
            dist[i]=Integer.MAX_VALUE/2;
            for(int j=0;j<n;j++){
                 e[i][j]=Integer.MAX_VALUE/2;
            }
        }
        int x,y,w;
        for(int i=0;i<m;i++){
            x=sc.nextInt();
            y=sc.nextInt();
            w=sc.nextInt();
            e[x][y]=e[y][x]=w;
        }
        prim();
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