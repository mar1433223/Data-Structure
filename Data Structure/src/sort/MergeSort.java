package sort;

import java.util.Scanner;

public class MergeSort {
    static void mergesort(int[] a,int i,int j){
        if(i>=j) return;
        int mid=(i+j) /2;
        mergesort(a,i,mid);
        mergesort(a,mid+1,j);
        int k=i;
        int q=mid+1;
        int[] cnt=new int[105];
        int p=1;
        while(k<=mid&&q<=j){
            if(a[k]<=a[q]){
                cnt[p++]=a[k];
                k++;
            }
            else{
                cnt[p++]=a[q];
                q++;
            }
        }
        while(k<=mid){
            cnt[p++]=a[k];
            k++;
        }
        while(q<=j){
            cnt[p++]=a[q];
            q++;
        }
        for(int  m=1;m<=p-1;m++){
            a[i+m-1]=cnt[m];
        }
    }
    public static void main(String[] args) {
        int[] a=new int[105];
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        mergesort(a,1,n);
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
    }
}
