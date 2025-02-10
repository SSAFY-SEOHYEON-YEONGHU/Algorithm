import java.io.*;
import java.util.*;

public class Main {

    static int W, N;
    // static int[][] jewels;
    static PriorityQueue<int[]> jewels;

    public static void main(String[] args) throws Exception {
        init();
        calcMaxCost();
    }

    static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // jewels = new int[N][2];
        jewels = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] j1, int[] j2) {  // 무게 당 가격 기준 내림차순 정렬
                return j2[1] - j1[1];
            }
        });
        // for (int i = 0; i < N; i++) {
        //     st = new StringTokenizer(br.readLine());
        //     jewels[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        // }
        // Arrays.sort(jewels, new Comparator<int[]>() {
        //     @Override
        //     public int compare(int[] j1, int[] j2) {  // 무게 당 가격 기준 내림차순 정렬
        //         return j2[1] - j1[1];
        //     }
        // });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
    }

    static void calcMaxCost() {
        int totalWeight = 0;  // 현재까지 담은 보석의 무게
        int totalCost = 0;  // 현재까지 담은 보석의 가격

        // for (int[] j : jewels) {
        int[] j;
        while (!jewels.isEmpty()) {
            j = jewels.poll();
            // System.out.println(totalWeight);
            // System.out.println(totalCost);
            if (j[0] + totalWeight <= W) {
                totalWeight += j[0];
                totalCost += (j[1]*j[0]);
            } else {
                totalCost += ( j[1] * (W - totalWeight) );  // 남은 무게만큼 담음
                break;
            }
        }

        System.out.println(totalCost);
    }

}
