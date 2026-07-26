package sort;

import java.util.Scanner;

public class HeapSort {
    static void swap(int[] a,int i,int j){
        int temp=a[j];
        a[j]=a[i];
        a[i]=temp;
    }
    static void downAdjust(int[] a,int i,int j){
        int now=i;
        int next;
        while(now*2<=j){
           next=2*now;
           if(next + 1 <= j &&a[next]<a[next+1]){
               next=next+1;
           }
           if(a[now]<a[next]){
               swap(a,now,next);
               now=next;
           }
           else break;
        }
    }
    static void upAdjust(int[] a,int j){
        int now=j;

        while (now>1){
            int next=now/2;
            if(a[now]>a[next]){
                swap(a,now,next);
                now=next;
            }
            else break;
        }
    }
    public static void main(String[] args) {
        int[] a=new int[105];
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
           // upAdjust(a,i);
        }
        for(int i=n/2;i>=1;i--){
            downAdjust(a,i,n);
        }
        for(int i=1;i<n;i++){
           swap(a,1,n-i+1);
           downAdjust(a,1,n-i);
        }
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
    }
}

