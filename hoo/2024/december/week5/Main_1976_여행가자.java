package twentytwentyfour.december.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1976_여행가자 {

    static int N, M;
    static int[][] adjArr;  // 문제에서 주어지는 인접행렬
    static int[] plan;  // 문제에서 주어진 여행 계획

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(judgeIsPossible()?"YES":"NO");
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjArr = new int[N+1][N+1]; // 도시가 1번부터 시작하므로 크기 N+1로 초기화
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                adjArr[i][j] = Integer.parseInt(st.nextToken());
                if (i == j) adjArr[i][j] = 1;   // 자기 자신으로의 경로는 1로 처리
                if (adjArr[i][j] == 0) adjArr[i][j] = N+1;    // 플로이드 워셜 알고리즘 수행을 위해 경로가 없는 곳 일단 N으로 처리
            }
        }
        plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());
    }

    static boolean judgeIsPossible() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k]+adjArr[k][j]);
            }
        }

        for (int i = 0; i < M-1; i++) {
            if (adjArr[plan[i]][plan[i+1]] >= N+1) return false;
        }

        return true;   // 모든 도시를 계획대로 방문할 수 없음이 판명되면 중단
    }

}
