package com.code.Interview;

public class Q5 {
    public static double merge(int[] a, int[] b) {
       int[] merge_array = new int[a.length + b.length];
       int index_a = 0;
       int index_b = 0;
       for (int i = 0; i < merge_array.length; i++){
           if (index_a < a.length && index_b < b.length){
               if (a[index_a]  < b[index_b]){
                   merge_array[i] = a[index_a];
                   index_a++;
               }
               else{
                   merge_array[i] = b[index_b];
                   index_b++;
               }
           } else if (index_a < a.length){
               merge_array[i] = a[index_a];
               index_a++;
           }    
           else{
                merge_array[i] = b[index_b];
                index_b++;
           }
          
       }

       int mid = merge_array.length / 2;
       if (merge_array.length % 2 != 0){
           return merge_array[mid];
       }
       else{
            double result = (double) (merge_array[(int) mid] + merge_array[(int) (mid - 1)]) / 2;
            return result;
       }


    }
    public static void print(int[] arr){
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 6};
        int[] b = {1, 2, 10};
        System.out.println(merge(a, b));
    }
}
