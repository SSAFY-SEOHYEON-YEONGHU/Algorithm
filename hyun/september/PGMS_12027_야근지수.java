package implementation;

import java.io.*;
import java.util.*;

public class PGMS_12027_야근지수 {
    class Node{
        int idx, amount;
        Node(int idx, int amount){
            this.idx = idx;
            this.amount = amount;
        }
    }

    public long solution(int n, int[] works) {
        Arrays.sort(works);
        long total = 0;
        for(int su : works) total += su;
        int avg = (int)total/works.length;
        System.out.println(avg);

        if(total <= n) return 0; // n시간 안에 모든 작업 완료할 수 있을때는 바로 정답 0 리턴

        int[] amounts = new int[works.length];
        int[] remain = new int[works.length];
        int amountsSum = 0; // 실제 총 작업량

        for(int i=works.length -1; i>=0; i--){
            if(works[i] <= avg) {
                remain[i] = works[i];
                amounts[i] = 0;
            }
            else{
                amounts[i] = works[i] - avg;
                remain[i] = avg;
                amountsSum += (works[i]-avg);
            }
        }


        boolean isFlag = true; // ture면 더하기, false면 빼기
        if(amountsSum < n) isFlag = false;

        // 작업량 다시 재분배
        if(amountsSum != 0){
            int a=0;
            if(isFlag){
                a = (amountsSum-n);
            }
            else{
                a = (n-amountsSum);
            }
            System.out.println("ab" + a + " ");
            for(int i=0; i<works.length; i++){

                int tavg = a / (works.length - i);
                System.out.println("tavg" + tavg);

                if(isFlag){
                    if(remain[i] + tavg >= works[i]){
                        a -= (works[i] - remain[i]);
                        remain[i] = works[i];
                    }
                    else{
                        remain[i] += tavg;
                        a -= tavg;
                    }
                    System.out.println(remain[i]);
                }
                else{
                    if(remain[i] - tavg <= 0){
                        remain[i] = 0;
                        a -= works[i];
                    }
                    else{
                        remain[i] -= tavg;
                        a -= tavg;
                    }
                }
            }
        }

        long answer = 0;
        for(int su : remain) answer += Math.pow(su,2);

        return answer;
    }
}