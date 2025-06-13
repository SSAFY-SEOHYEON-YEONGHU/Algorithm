package string;

import java.io.*;
import java.util.*;

public class BOJ_250613_단어공부 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int[] map = new int[30];
        int maxValue = -1;
        for(int i=0; i<input.length(); i++) {
            int idx = input.charAt(i) - 65;
            if(idx >= 32) idx -= 32;

            map[idx]++;
            maxValue = Math.max(maxValue, map[idx]);
        }
        char answer='?';
        for(int i=0; i<30; i++){
            if(map[i] == maxValue){
                if(answer != '?'){
                    System.out.println("?");
                    return;
                }
                answer = (char)(i+65);
            }
        }
        System.out.println(answer);
    }
}
