package twentytwentyfive.february.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11265_끝나지않는파티 {

    static int N, M;
    static int[][] needTimes; // i, j : i에서 j로 가는 데 걸리는 시간
    static int[][] mArray; // 0: A, 1: B, 2: C

    public static void main(String[] args) throws IOException {
        init();
        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= N; i++) System.out.println(Arrays.toString(needTimes[i]));

        for (int i = 0; i < M; i++) {
            if (needTimes[mArray[i][0]][mArray[i][1]] <= mArray[i][2]) sb.append("Enjoy other party");
            else sb.append("Stay here");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        needTimes = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) needTimes[i][j] = Integer.parseInt(st.nextToken());
        }
        makeNeedTimes();

        mArray = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) mArray[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void makeNeedTimes() {
        for (int k = 1; k <= N; k++) { // 경유지
            for (int i = 1; i <= N; i++) { // 출발지
                for (int j = 1; j <= N; j++) needTimes[i][j] = Math.min(needTimes[i][j], needTimes[i][k]+needTimes[k][j]); // 도착지
            }
        }
    }

}
