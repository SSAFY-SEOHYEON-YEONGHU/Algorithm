package two_pointer;

import java.io.*;
import java.util.*;

public class BOJ_250109_같이눈사람만들래 {
    static int N;
    static int[] input;
    static long answer = Integer.MAX_VALUE;

    static void twoPointer(int[] num){
        long sum = (long)input[num[0]] + input[num[1]];
        System.out.println(num[0] + " :" + input[num[0]] + " " + num[1] + " :" + input[num[1]]);

        int left = 0;
        int right = N-1;

        while(left < right){

            if(left == num[0] || left == num[1]) {
                left++;
                continue;
            }
            if(right == num[0] || right == num[1]) {
                right--;
                continue;
            }
            else{
                long tmp = (long)input[left] + input[right];
                answer = Math.min(Math.abs(sum-tmp), answer);

                System.out.println(left + " " + right);
                System.out.println("answer " + answer);


                if(sum > tmp) left++;
                else right--;

                System.out.println("변화된후" + left + " " + right);
                System.out.println();
            }
        }
    }

    static void choiceTwo(int idx, int cnt, int[] numIdx){
        if(cnt == 2){
            twoPointer(numIdx);
            return;
        }
        for (int i = idx; i < N; i++) {
            numIdx[cnt] = i;
            choiceTwo(i+1,cnt+1,numIdx);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);
        choiceTwo(0,0,new int[2]);

        System.out.println(answer);
    }
}
