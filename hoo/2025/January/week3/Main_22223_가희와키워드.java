package twentytwentyfive.january.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_22223_가희와키워드 {

    static int N, M;    // 최대 200_000
    static Map<String, Integer> memos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        printRemainKeywords(br);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memos = new HashMap<>();
        for (int i = 0; i < N; i++) memos.put(br.readLine(), 1);
    }

    static void printRemainKeywords(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        int remainKeywords = N;  // 최초 키워드 개수는 N개로
        String[] inputLine;
        for (int i = 0; i < M; i++) {
            inputLine = br.readLine().split(",");
            for (String s : inputLine) {
                if (!memos.containsKey(s) || memos.get(s) == 0) continue;

                memos.put(s, 0);
                remainKeywords--;
            }
            sb.append(remainKeywords).append("\n");
        }

        System.out.println(sb);
    }

}
