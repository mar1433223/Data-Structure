package sort;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a=new int[105];
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        int temp;
        boolean flag;
        for(int i=1;i<=n-1;i++){
            flag=false;
            for(int j=1;j<=n-i;j++){
                if(a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    flag=true;
                }
            }
            if(!flag) break;
        }
        for(int i=1;i<=n;i++){
            System.out.print(a[i]+" ");
        }
        sc.close();
    }
}
