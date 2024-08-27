package dp;

// dp(1부터 펄스 수열, -1부터 펄스수열) -> O(2 * sequence 길이) = O(2*10^5)

import java.util.*;

public class PGMS_161988_연속펄스부분수열의합 {
    public long func(int[] sequence, int first){
        int[] arr = sequence.clone();
        for(int i=0; i<arr.length; i++){
            arr[i] *= first;
            first *= (-1);
        }

        long sum = arr[0];
        long result = arr[0];
        for(int i=1; i<arr.length; i++){
            sum = Math.max(sum + arr[i], arr[i]);
            result = Math.max(result, sum);
            //System.out.print(sum + " ");
        }

        //System.out.println("==" + result);
        return result;

    }
    public long solution(int[] sequence) {
        long answer = Math.max(func(sequence,1), func(sequence,-1));
        return answer;
    }
}