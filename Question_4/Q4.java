package com.code.Interview.Question_4;

public class Q4 {
    public static int question_4(int[] arr) {
        int total = (arr.length * (arr.length + 1)) / 2;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (total - sum);
    }
    public static void main(String[] args){
        
    }
}
