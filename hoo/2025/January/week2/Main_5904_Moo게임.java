package twentytwentyfive.january.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5904_Moo게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) System.out.println("m");    // 1~3까지는 그냥 바로 moo 중에서 골라서 출력해주고 종료
        else if (N == 2 || N == 3) System.out.println("o");
        else {  // 4부터는 재귀를 통해 문자 알아내기
            makeMoo(N, 0, 0);
        }
    }

    static void makeMoo(int goal, int nowCount, int madeMooLength) {    // count: 현재 moo 수열 깊이, goal: 목표 moo 수열 깊이, madeMooLength: 이제까지 만들어진 moo 수열 길이


    }

}
