package twentytwentyfive.february.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_12892_생일선물 {

    static int N, D;
    static int[][] presents;

    public static void main(String[] args) throws IOException {
        init();
        calcMaxHappiness();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        presents = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            presents[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(presents, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {  // 가격 기준 오름차순 정렬
                if (o1[0] == o2[0]) return o2[1]-o1[1]; // 가격 같을 경우 만족도 순 내림차순 정렬
                return o1[0]-o2[0];
            }
        });
    }

    static void calcMaxHappiness() {
        long satisfy = (long) presents[0][1]; // 해당 선물의 범위를 통해 만족하는 정도, 초기값은 제일 싼 선물의 만족도로
        long maxHappiness = satisfy;

        int left = 0, right = 0; // 투 포인터를 이용해 선택할 선물의 범위를 지정
        while (right < N) {
             if (presents[right][0] - presents[left][0] < D) { // 미안함을 느끼는 최소 격차보다 격차가 작은 경우
                 if (left != right) satisfy += (long) presents[right][1]; // 현재 우측 포인터와 좌측 포인터가 가르키는 선물이 다르면 행복에 값 더해주기

                 maxHappiness = Math.max(maxHappiness, satisfy); // 행복의 최댓값 갱신
                 right++;
             } else { // 미안함을 느끼기 시작한 경우
                satisfy -= (long) presents[left][1];
                left++;
                if (left == right) satisfy = presents[left][1]; // 만약 좌측포인터가 우측포인터와 같은 자리에 갔다면 해당 선물의 만족도로 값 갱신
             }
//            System.out.println(left + " " + right);
//            System.out.println(satisfy);
//            System.out.println(maxHappiness);
//            System.out.println("------------");
        }

        System.out.println(maxHappiness);
    }

}
