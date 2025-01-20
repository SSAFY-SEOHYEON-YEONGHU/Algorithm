
package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ_250119_가회와키워드2 {
    static int N,M;
    static Set<String> original = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            original.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(",");
            for (int j = 0; j < input.length; j++) {
                String cur = input[j];

                if(!original.contains(cur)) continue;
                else {
                    original.remove(cur);
                }
            }

            sb.append(original.size()).append("\n");
        }

        System.out.println(sb);
    }
}
