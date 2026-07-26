package sort;

import java.util.Scanner;

public class SelectionSort {
    static void swap(int[] a,int i,int j){
        int temp=a[i];
        a[i]=a[j];
         a[j]=temp;
    }
    public static void main(String[] args) {
        int[] a=new int[105];
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        int min;
        for(int i=1;i<n;i++) {
            min = i;
            for (int j = i + 1; j <= n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, min, i);
        }
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
