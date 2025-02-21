package implementation;

import java.io.*;
import java.util.*;
public class BOJ_250221_징검다리건너기small {
    static int N,K;
    static int[] input;

    static void simulation(){
        boolean[] visited = new boolean[N];
        visited[0] = true;
        for (int i = 0; i < N; i++) {
            if(!visited[i]) continue;
            for (int j = i+1; j < N; j++) {
                int power = (j-i) * (Math.abs(input[i] - input[j]) + 1);
                if(power <= K) visited[j] = true;
            }
            if(visited[N-1]) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //
        simulation();
    }
}
