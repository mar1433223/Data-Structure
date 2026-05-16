package tree;

import java.util.Scanner;

public class DSU {
    int[] f=new int[105];
    int[] h=new int[105];
    public void init(int n){
        for(int i=1;i<=n;i++){
            f[i]=i;
        }
        for(int i=1;i<=n;i++){
            h[i]=1;
        }
    }
    public int find(int x){
        if(f[x]==x) return x;
        return f[x]=find(f[x]);
    }
    public void union(int x,int y){
        int fx=find(x);
        int fy=find(y);
        if(fx==fy) return;
        if(h[fx]>=h[fy]){
            f[fy]=fx;
            h[fx]=Math.max(h[fx],h[fy]+1);
        }
        else{
            f[fx]=fy;
            h[fy]=Math.max(h[fy],h[fx]+1);
        }
    }
    public void check(int x,int y){
        if(find(x)==find(y)){
            System.out.println("yes");
        }
        else System.out.println("no");
    }
    public static void main(String[] args) {
        DSU dsu=new DSU();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        dsu.init(n);
        for(int i=1;i<=m;i++){
            int op = sc.nextInt();
            if(op==1){
                int a = sc.nextInt();
                int b = sc.nextInt();
                dsu.union(a,b);
            } else if(op==2) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                dsu.check(a,b);
            }
        }
    }
}
