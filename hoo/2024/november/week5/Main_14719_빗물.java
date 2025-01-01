package twentytwentyfour.november.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719_빗물 {

    public static void main(String[] args) throws IOException {
        int[] blocks = init();
        countRainGathered(blocks);
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) blocks[i] = Integer.parseInt(st.nextToken());

        return blocks;
    }

    static void countRainGathered(int[] blocks) {
        int rainGathered = 0;   // 고인 빗물의 총량
        int leftHighest, rightHighest;  // 현재 칸의 왼쪽과 오른쪽에서 가장 높은 벽 저장할 변수
        for (int i = 0; i < blocks.length; i++) {
            leftHighest = blocks[i];
            rightHighest = blocks[i];

            for (int j = 0; j < i; j++) leftHighest = Math.max(leftHighest, blocks[j]);
            for (int j = i+1; j < blocks.length; j++) rightHighest = Math.max(rightHighest, blocks[j]);
            if (leftHighest < blocks[i] || rightHighest < blocks[i]) continue;  // 현재 칸이 왼쪽이나 오른쪽 가장 높은 칸보다 높으면 빗물 안고임
            rainGathered += (Math.min(leftHighest, rightHighest) - blocks[i]);  // 한 웅덩이(?)에서 모인 빗물의 양
        }
        System.out.println(rainGathered);
    }

}
