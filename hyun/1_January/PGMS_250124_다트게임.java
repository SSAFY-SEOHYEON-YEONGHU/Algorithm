package implementation;

import java.util.*;

public class PGMS_250124_다트게임 {

    public int zonePow(char c){
        if(c == 'S') return 1;
        else if(c == 'D') return 2;
        return 3;
    }
    public int solution(String dartResult) {
        dartResult += '-'; // 더미값
        int answer = 0;
        int before = 0;

        for(int i=0; i<dartResult.length() - 2; i++){
            int score = dartResult.charAt(i) - '0';
            char zone;
            char option;

            if(score == 1 && dartResult.charAt(i+1) - '0'== 0){
                score = 10;
                zone = dartResult.charAt(i+2);
                option = dartResult.charAt(i+3);
                i++;
            }else{
                zone = dartResult.charAt(i+1);
                option = dartResult.charAt(i+2);
            }


            if(option == '*'){
                int value = (int)Math.pow(score,zonePow(zone)) * 2;
                answer += (value + (before));
                before = value;
                i += 2;
            }
            else if(option == '#'){
                int value = (int)Math.pow(score,zonePow(zone)) * (-1);
                answer += value;
                before = value;
                i += 2;
            }
            else{
                int value = (int)Math.pow(score,zonePow(zone));
                answer += value;
                before = value;
                i++;
            }

            // System.out.println(answer);
            //  System.out.println(before);

        }
        return answer;
    }
}