package implementation;

import java.util.*;

public class PGMS_241101_셔틀버스 {
    int N,T,M;
    int[] input;
    String answer = "";

    public void simulation(){
        int time = 9 * 60;
        int cnt = 0;

        int i=0;
        for(i=0; i<input.length; i++){
            int crew = input[i];
            if(crew <= time) cnt++;
            else {
                N--;

                if(N==0 || i==input.length-1) break;

                time+=T;
                cnt=0;
                i--;
                continue;

            }

            if(cnt == M){
                N--;
                if(N==0 || i==input.length-1) { // N==0 이거나 배열 마지막이거나
                    break;
                }
                time += T;
                cnt = 0;
            }
        }

        if(cnt == M) time = input[i] - 1;

        int a = time / 60;
        int b = time % 60;

        if(a < 10) answer += ("0" + String.valueOf(a) + ":");
        else answer += (String.valueOf(a) + ":");
        if(b < 10) answer += ("0" + String.valueOf(b));
        else answer += (String.valueOf(b));



    }

    public String solution(int n, int t, int m, String[] timetable) {
        N = n;
        T = t;
        M = m;
        input = new int[timetable.length];
        for(int i=0; i<timetable.length; i++){
            String[] tmp = timetable[i].split(":");
            input[i] = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
        }
        Arrays.sort(input);

        simulation();
        return answer;
    }
}