package implementation;

import java.io.*;
import java.util.*;

public class PGMS_160585_혼자서하는틱택토 {

    public String cnt(String[] board){
        int o = 0;
        int x = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i].charAt(j) == 'O') o++;
                else if(board[i].charAt(j) == 'X') x++;
            }
        }

        System.out.println(o + " " + x);

        if(o==x) return "even";
        else if(x + 1 == o) return "odd";
        return "nothing";
    }

    public char bingo(String[] board){
        int o = 0;
        int x = 0;

        // 가로
        for(int i=0; i<3; i++){
            char c = board[i].charAt(0);
            if(c=='.') continue;

            boolean isBingo = true;
            for(int j=0; j<3;j++){
                if(board[i].charAt(j) != c){
                    isBingo = false;
                    break;
                }
            }
            if(isBingo){
                if(c=='O') o++;
                else x++;
            }
        }
        System.out.print(o + " " + x);
        // 세로
        for(int j=0; j<3; j++){
            char c = board[0].charAt(j);
            if(c=='.') continue;

            boolean isBingo = true;
            for(int i=0; i<3;i++){
                if(board[i].charAt(j) != c){
                    isBingo = false;
                    break;
                }
            }
            if(isBingo){
                if(c=='O') o++;
                else x++;
            }
        }
        System.out.print(o + " " + x);
        // 대각선
        char c = board[1].charAt(1);
        if(c != '.'){
            if(board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
                if(c=='O') o++;
                else x++;
            }
            if(board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
                if(c=='O') o++;
                else x++;
            }
        }

        System.out.print(o + " " + x);

        if(o == x && o == 0) return '.';
        else if(o > x && x == 0) return 'o';
        else if(x > o && o == 0) return 'x';
        return 'n';
    }


    public int solution(String[] board) {
        String rcnt = cnt(board);
        char rbingo = bingo(board);

        System.out.println(rcnt);

        int answer = 0;
        if(rcnt.equals("even") && (rbingo == '.' || rbingo == 'x')) answer = 1;
        if(rcnt.equals("odd") && (rbingo == '.' || rbingo == 'o')) answer = 1;

        return answer;
    }
}
