package math;

import java.util.*;

class public class PGMS_250120_기사단원의무기 {
    public int simulation(int number, int limit, int power){
        int answer = 1;// 1은 무조건 포함

        for(int i=2; i<=number; i++){
            int cnt = 0;
            for(int k=1; k<= Math.sqrt(i); k++){
                if(i % k == 0) {
                    cnt++;
                    if(i / k != k) cnt++;
                }
            }

            // System.out.print(cnt + " ");
            if(cnt > limit) answer += power;
            else answer += cnt;
        }

        return answer;

    }
    public int solution(int number, int limit, int power) {

        return simulation(number, limit, power);
    }
}