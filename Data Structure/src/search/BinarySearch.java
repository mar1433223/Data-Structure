package search;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        int[] a=new int[105];
        int n,k;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        k=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        int i=1;
        int j=n;

        int p=-1;
        while(i<=j){
            int mid=(i+j)/2;
            if(a[mid]==k){
              p=mid;
              break;
            }
            else if(a[mid]>k){
                j=mid-1;
            }
            else {
                i=mid+1;
            }
        }
        System.out.println(p);
    }
}
