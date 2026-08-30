package sort;

import java.util.Scanner;

public class CountingSort {
    static int[] num=new int[10005];
    static int[] sum=new int[10005];

    public static void main(String[] args) {
        int[] a=new int[105];
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int max=0;
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
            if(a[i]>max){
                max=a[i];
            }
            num[a[i]]++;
        }
        sum[0]=num[0];
        for(int i=1;i<=max;i++){
            sum[i]=sum[i-1]+num[i];
        }
        int[] t=new int[105];
        for(int i=n;i>=1;i--){
            int k=a[i];
            t[sum[k]]=k;
            sum[k]--;
        }
        for(int i=1;i<=n;i++){
            System.out.print(t[i]+" ");
        }
    }
}
