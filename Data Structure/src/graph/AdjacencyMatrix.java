package graph;

import java.util.Arrays;
import java.util.Scanner;

public class AdjacencyMatrix {
    //0<=n<=100 0<=w<=10000
    static char v[]=new char[105];
    static int e[][]=new int[105][105];
    static int n,m;
    static int find(char x){

        for(int i=1;i<=n;i++){
            if(v[i]==x){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        for(int[] row:e) Arrays.fill(row,Integer.MAX_VALUE/2);
        for(int i=1;i<=n;i++){
            e[i][i]=0;
        }
        String s=sc.next();
        for(int i=1;i<=n;i++){
            v[i]=s.charAt(i-1);
        }
       char x,y;
        int w,xi,yi;
        for(int i=1;i<=m;i++){
             x=sc.next().charAt(0);
             y=sc.next().charAt(0);
             w=sc.nextInt();
             xi=find(x);
             yi=find(y);
             if(xi!=-1&&yi!=-1){
                 e[xi][yi]=e[yi][xi]=w;
             }
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