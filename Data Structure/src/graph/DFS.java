package graph;

import javax.script.ScriptContext;
import java.util.Arrays;
import java.util.Scanner;

public class DFS {
    static boolean[] flag=new boolean[105];
    static char[] value=new char[105];
    static int[][] e=new int[105][105];
    static int n,m;
    static int find(char x){

        for(int i=1;i<=n;i++){
            if(value[i]==x){
                return i;
            }
        }
        return -1;
    }
    static void dfs(int i){
        if(flag[i]==true){
            return;
        }
        flag[i]=true;
        System.out.print(value[i]+" ");
        for(int j=1;j<=n;j++){
            if(e[i][j]==1&&!flag[j]){
                dfs(j);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        for(int i=0;i<=104;i++){
            flag[i]=false;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                e[i][j]=0;
            }
        }
        String s=sc.next();
        for(int i=1;i<=n;i++){
            value[i]=s.charAt(i-1);
        }
        char x,y;
        int xi,yi;
        for(int i=1;i<=m;i++){
            x=sc.next().charAt(0);
            y=sc.next().charAt(0);
            xi=find(x);
            yi=find(y);
            e[xi][yi]=e[yi][xi]=1;
        }
        for(int i=1;i<=n;i++){
            dfs(i);
        }
    }
}
/*
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