package hash;

import java.io.*;
import java.util.*;
public class BOJ_250119_가회와키워드 {
    static int N,M;
    static Set<String> original = new HashSet<>();
    static Set<String> except = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            original.add(br.readLine());
        }

        int total = original.size();
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(",");
            int cnt = 0;
            for (int j = 0; j < input.length; j++) {
                String cur = input[j];

                if(!except.contains(cur) && original.contains(cur)){
                    cnt++;
                    except.add(cur);
                }
            }

            total -= cnt;
            sb.append(total).append("\n");
        }

        System.out.println(sb);
    }
}
