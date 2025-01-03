package math;

import java.util.*;

public class PGMS_241123_점찍기 {
    public long solution(int k, int d) {
        long answer = 0;

        double end = ((long)d*d) / ((long)k*k);
        for(long a=0; a*a<=end; a++){

            long b = (long)(Math.floor(Math.sqrt(end - (a*a))));

            answer += (b+1);
        }

        return answer;
    }
}