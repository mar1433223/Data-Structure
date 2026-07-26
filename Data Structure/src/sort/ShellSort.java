package sort;

import java.util.Scanner;

public class ShellSort {
    public static void main(String[] args) {
        int[] a=new int[105];
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        int d,x,j,k=0;
        for(d=n/2;d>=1;d/=2){
            k++;
            for(int i=d+1;i<=n;i++){
                x=a[i];
                for(j=i-d;j>=1;j-=d){
                    if(a[j]>x){
                        a[j+d]=a[j];
                    }
                    else break;
                }
                a[j+d]=x;
            }
            System.out.println("这是第"+k+"趟排序");
            for(int i=1;i<=n;i++){
                System.out.print(a[i]+" ");
            }
            System.out.println();
        }

    }
}
