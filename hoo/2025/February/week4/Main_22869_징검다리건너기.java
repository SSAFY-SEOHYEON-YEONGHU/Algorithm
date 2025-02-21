package twentytwentyfive.february.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22869_징검다리건너기 {

    static int N, K;
    static int[] stones;

    public static void main(String[] args) throws IOException {
        init();
        calcIfCan();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stones = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) stones[i] = Integer.parseInt(st.nextToken());
    }

    static void calcIfCan() {
        int[] cost = new int[N];
        cost[0] = 1;

        for (int i = 0; i < N-1; i++) {
            if (cost[i] == 0) continue; // 도달 불가능했던 돌은 건너 뜀

            for (int j = i+1; j < N; j++) { // 우측에 있는 돌들에 대해 수행
                int needEnergy = calcNeedEnergy(i, j);
                if (needEnergy <= K) cost[j] = needEnergy; // k 이하의 에너지를 써서 이동 가능하면 갈 수 있음을 표시하기 위해 필요한 에너지를 j돌에 저장
            }
        }

        System.out.println((cost[N-1]==0)? "NO":"YES");
    }

    static int calcNeedEnergy(int i, int j) { return (j - i) * ( 1 + Math.abs(stones[i] - stones[j]) ); }

}
