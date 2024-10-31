package october.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2467_용액 {

    static int N;
    static int[] liquids;

    public static void main(String[] args) throws IOException {
        init();
        findAlmostZero();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liquids = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) liquids[i] = Integer.parseInt(st.nextToken());
    }

    static void findAlmostZero() {
        int answerSum = Integer.MAX_VALUE;
        int[] answer = new int[2];

        int left = 0;
        int right = N-1;
        int sum;
        while (left < right) {
            sum = liquids[left] + liquids[right];
            if (Math.abs(0-answerSum) >= Math.abs(0-sum)) {
                answerSum = sum;
                answer = new int[] {liquids[left], liquids[right]};
            }
            if (sum > 0) right--;
            else left++;
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

}
