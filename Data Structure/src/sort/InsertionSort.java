package sort;

import java.util.Scanner;

public class InsertionSort {
    static int[] a=new int[105];
    static int n;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        int j,x;
        for(int i=1;i<n;i++){
            x=a[i+1];
            for(j=i;j>=1;j--){
                if(a[j]>x){
                    a[j+1]=a[j];
                }
                else break;
            }
            a[j+1]=x;
        }
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
