package greedy;

import java.io.*;
import java.util.*;

public class PGMS_12987_숫자게임 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int idx = 0;
        int answer = 0;
        for(int Anum : A){
            if(idx >= B.length) break;

            while(true){
                if(B[idx++] > Anum){
                    answer++;
                    break;
                }

                if(idx >= B.length) break;
            }
        }

        return answer;

    }
}
