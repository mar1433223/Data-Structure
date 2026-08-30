package search;

import java.util.Scanner;

class HashTable{
    int m;
    int[] a;
    HashTable(int m){
        this.m=m;
        a=new int[m];
        for(int i=0;i<=m-1;i++){
            a[i]=-1;
        }
    }
}
public class HashSearch {
    static int hash(int k){
        return k%13;
    }
    static int find(HashTable hashTable,int k){
        int i=hash(k);
        if(hashTable.a[i]==-1){
            return i;
        }
        else {
            int d = 1;
            int newi = (i + d) % hashTable.m;
            while (hashTable.a[(i + d) % hashTable.m] != -1) {
                d++;
                newi = (i + d) % hashTable.m;
            }
            return newi;
        }
    }
    static void insert(HashTable hashTable,int k){
         int i=find(hashTable,k);
         hashTable.a[i]=k;
    }
    static void search(int p,HashTable hashTable){
        int i=hash(p);
         if(hashTable.a[i]!=-1){
             System.out.print(i);
         }
         else{
             int d=0;
             int newi=(i+d)%hashTable.m;
             while (hashTable.a[newi]!=-1){
                 if(hashTable.a[newi]==p){
                     System.out.println(newi);
                 }
               d++;
               newi=(i+d)% hashTable.m;
             }
             System.out.println(-1);
         }
    }
    public static void main(String[] args) {
          int n,k;
          HashTable hashTable=new HashTable(15);
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            k=sc.nextInt();
            insert(hashTable,k);
        }
        int p=sc.nextInt();
        search(p,hashTable);
    }
}
/*
12
19 14 23 1 68 20 84 27 55 11 10 79
0
*/