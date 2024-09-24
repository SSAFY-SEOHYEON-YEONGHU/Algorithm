package implementation;

import java.util.*;

public class PGMS_240924_연속부분수열합의개수 {
    public int solution(int[] elements) {
        Set<Integer> num = new HashSet<>();

        for(int i=0; i<elements.length; i++){
            int sum = 0;
            int idx = i;
            for(int k=1; k<elements.length; k++){
                sum += elements[idx];
                idx = (idx+1)%elements.length;
                num.add(sum);
            }
        }

        int sum = 0;
        for(int su : elements) sum += su;
        num.add(sum);

        return num.size();
    }
}