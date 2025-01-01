package implementation;

import java.util.*;

public class PGMS_241015_n진수게임 {
    public String change(int num, int n){ // n진법으로
        //System.out.print(num + " ");
        String result = "";

        while(num >= n){
            int remainder = num % n;

            if(n>10){
                if(remainder == 10) result += "A";
                else if(remainder == 11) result += "B";
                else if(remainder == 12) result += "C";
                else if(remainder == 13) result += "D";
                else if(remainder == 14) result += "E";
                else if(remainder == 15) result += "F";
                else result+= String.valueOf(remainder);
            }
            else result+= String.valueOf(remainder);

            num = num / n;
        }

        if(n > 10){
            if(num == 10) result += "A";
            else if(num == 11) result += "B";
            else if(num == 12) result += "C";
            else if(num == 13) result += "D";
            else if(num == 14) result += "E";
            else if(num == 15) result += "F";
            else result += String.valueOf(num);
        }
        else result += String.valueOf(num);

        StringBuilder sb = new StringBuilder(result);
        //System.out.println(sb.reverse().toString());
        return sb.reverse().toString();
    }

    public String solution(int n, int t, int m, int p) {
        String answer = "";

        int num = 0;
        String result = "";

        while(true){
            result += change(num++,n);

            if(result.length() >= (p-1) + (m * t)) break;
        }
        System.out.println(result);
        int cnt = 0;
        int idx = p-1;
        while(cnt != t){
            answer += String.valueOf(result.charAt(idx));
            idx += m;
            cnt++;
        }


        return answer;
    }
}
