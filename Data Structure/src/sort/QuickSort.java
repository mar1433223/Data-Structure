package sort;

import java.util.Scanner;

public class QuickSort {
    static int[] a=new int[105];
    static int n;
    static void quickSort(int l,int r){
        if(l>r) return;
        int i=l;
        int j=r;
        int p=a[i];
        while(i<j){
            while(i<j&&a[j]>p) j--;
            if(i<j){
                a[i]=a[j];
                i++;
            }
            while(i<j&&a[i]<p)  i++;
            if(i<j){
                a[j]=a[i];
                j--;
            }
        }
        a[i]=p;
        quickSort(l,i-1);
        quickSort(i+1,r);
    }
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        quickSort(1,n);
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
        sc.close();
    }
}
