package twentytwentyfive.january.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20366_같이눈사람만들래 {

    static int N;
    static int[] snowBalls;
    static int minDiff;

    public static void main(String[] args) throws IOException {
        init();
        makeSnowman();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        snowBalls = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) snowBalls[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(snowBalls);
        minDiff = Integer.MAX_VALUE;
    }

    static void makeSnowman() {
        int minDiff = Integer.MAX_VALUE;

        long outerSnowman, innerSnowman;
        for (int i = 0; i < N; i++) {   // 우선 바깥의 두 눈덩이를 골라 눈사람 하나를 만들어 줌. i는 맨 첫 눈덩이부터
            for (int j = N-1; j > i+2; j--) { // j는 끝의 눈덩이부터 i를 포함해서 4개를 뽑을 수 있을 때까지
                outerSnowman = (long) snowBalls[i] + (long) snowBalls[j];
                int left = i+1;
                int right = j-1;
                int diff;
                while (left < right) {  // left가 right보다 작을 동안 수행
                    innerSnowman = (long) snowBalls[left] + (long) snowBalls[right];
                    diff = (int) Math.abs(outerSnowman - innerSnowman);
                    minDiff = Math.min(minDiff, diff);

                    if (outerSnowman > innerSnowman) left++;    // 바깥 눈사람의 크기가 더 작았다면 바깥 눈사람 크기 키워주고
                    else right--;   // 반대라면 크기 줄여줌
                }
            }
        }

        System.out.println(minDiff);
    }

}