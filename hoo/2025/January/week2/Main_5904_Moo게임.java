package twentytwentyfive.january.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5904_Moo게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

    }

    static void makeMoo(int goal, int nowCount, int madeMooLength) {    // count: 현재 moo 수열 깊이, goal: 목표 moo 수열 깊이, madeMooLength: 이제까지 만들어진 moo 수열 길이
        // 점화식 : S(k) = S(k-1) + moo + o*k + S(k-1)
        // 현재 moo 수열 길이 < N -> 더 만들어야 함
        // 현재 moo 수열 길이 >= N -> 길이 초과했음 -> 이미 만들어진 moo 수열 안에서 판별 가능
        // 1. 만약 goal이 이전 moo 수열 길이에 3+nowCount(moo + o*k개만큼의 길이)한 것보다 같거나 짧은 곳에 위치한다?
        // -> 이전 moo 수열 길이는 넘어서서, 중간의 moo + o*k인 곳에 위치하고 있음.
        // -> 이전 moo 수열 길이보다 1 길다 -> m, 나머지 경우 -> o
        // 2. 만약 goal이 이전 moo 수열 길이에 3+nowCount한 것보다 긴 곳에 위치한다?
        // -> 뒤의 S(k-1)에서 찾아줘야 함. 여기로 재귀를 들어가면 됨. 근데, goal이 원래 goal에서 moo 수열 길이에 3+nowCount한 것만큼 작아져야 함.
    }

}

