package greedy;

import java.util.*;

public class PGMS_250411_덧칠하기 {
    public int greedy(int n, int m, int[] section){
        int sectionSize = section.length;
        if(m==1) return sectionSize;

        int answer = 0;

        for(int i=0; i<sectionSize; i++){
            int end = section[i] + m - 1;
            for(int j=i+1; j<sectionSize; j++){
                if(section[j] > end) break;
                else i = j;
            }
            answer++;
        }
        return answer;
    }
    public int solution(int n, int m, int[] section) {
        return greedy(n,m,section);
    }
}