package implementation;

import java.io.*;
import java.util.*;
public class BOJ_241203_틱택토 {
    static StringBuilder sb = new StringBuilder();

    static void getAnswer(String s){
        char[] input = s.toCharArray();

        int ocnt = 0;
        int xcnt = 0;
        int obingo = 0;
        int xbingo = 0;

        for(char c : input) {
            if(c=='O') ocnt++;
            else if(c=='X')xcnt++;
        }

        // 가로빙고
        for (int i = 0; i < 7 ; i+=3) {
            String tmp = String.valueOf(input[i]) + String.valueOf(input[i+1]) + String.valueOf(input[i+2]);

            if(tmp.equals("OOO")) obingo++;
            else if(tmp.equals("XXX")) xbingo++;
        }

        // 세로빙고
        for (int i = 0; i < 3 ; i++) {
            String tmp = String.valueOf(input[i]) + String.valueOf(input[i+3]) + String.valueOf(input[i+6]);

            if(tmp.equals("OOO")) obingo++;
            else if(tmp.equals("XXX")) xbingo++;
        }

        // 대각선 빙고
        String tmp = String.valueOf(input[0]) + String.valueOf(input[4]) + String.valueOf(input[8]);
        if(tmp.equals("OOO")) obingo++;
        else if(tmp.equals("XXX")) xbingo++;

        tmp = String.valueOf(input[2]) + String.valueOf(input[4]) + String.valueOf(input[6]);
        if(tmp.equals("OOO")) obingo++;
        else if(tmp.equals("XXX")) xbingo++;

        // -------------- 기준

        boolean isAnswer = false;
        if(ocnt == xcnt){
            if(obingo == 1 && xbingo == 0) isAnswer = true;
        }
        else if(xcnt == ocnt + 1){
            if(ocnt + xcnt == 9){
                if(xbingo == 2 && obingo == 0 && input[4] == 'X') isAnswer = true;
                if(xbingo <= 1 && obingo == 0) isAnswer = true;
            }
            else{
                if(xbingo == 1 && obingo == 0) isAnswer = true;
            }
        }

        if(isAnswer) sb.append("valid");
        else sb.append("invalid");

        sb.append("\n");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String s = br.readLine();
            if(s.equals("end")) break;
            getAnswer(s);
        }

        System.out.println(sb);
    }
}
