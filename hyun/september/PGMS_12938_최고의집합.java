package implementation;

import java.util.*;

public class PGMS_12938_최고의집합 {
    public int[] solution(int n, int s) {
        int[] answer;

        if(n==s) return new int[]{s};
        else if(n > s) return new int[]{-1};
        else{
            int m = s / n;
            int r = s % n;
            answer = new int[n];
            for(int i=0; i<n; i++){
                if(i < r ) answer[i] = m + 1;
                else answer[i] = m;
            }
        }

        Arrays.sort(answer);
        return answer;
    }
}
