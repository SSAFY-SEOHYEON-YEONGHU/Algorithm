package twentytwentyfour.november.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1484_다이어트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        calcPossible(G);
    }

    static void calcPossible(int G) {
        StringBuilder sb = new StringBuilder();
        int left = 1, right = 2;    // right : 현재 몸무게, left : 기억하고 있던 몸무게
        int tempG = right*right - left*left;    // 그 제곱의 차
        while (left < G) {
            tempG = right*right - left*left;
            if (tempG == G) sb.append(right).append("\n");
            if (right == G || tempG > G) left++; // 현재 몸무게가 끝에 다다랐거나 tempG가 G보다 크면 left 증가
            else right ++;  // 아니면 right 증가
        }
        if (sb.length() == 0) System.out.println(-1);
        else System.out.println(sb);
    }

}
