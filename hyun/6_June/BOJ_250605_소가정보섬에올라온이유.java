package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250605_소가정보섬에올라온이유 {
    static int N,Q;
    static int[] sum;
    static int originalSum = 0;
    static StringBuilder sb = new StringBuilder();

    public static int[] returnIndex(int idx){
        int[] result = new int[4];
        for (int k = 0; k >= -3 ; k--) {
            int nxt = idx + k;
            if( 0 < nxt) result[-1 * k] = nxt;
            else result[-1 * k] = nxt + N;
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sum = new int[N + 1];
        for (int i = 0; i <= N; i++) sum[i] = 1;

        for (int i = 1; i <= N ; i++) {
            int Ai = Integer.parseInt(st.nextToken());

            int[] idxs = returnIndex(i);
            for(int idx : idxs) sum[idx] *= Ai;
        }

        for (int i = 1; i <= N ; i++) originalSum += sum[i];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int Ai = Integer.parseInt(st.nextToken());
            int[] idxs = returnIndex(Ai);

            for(int idx : idxs) {
                originalSum -= sum[idx];
                originalSum += ((-1) * sum[idx]);
                sum[idx] *= -1;
            }

            sb.append(originalSum).append("\n");
        }

        System.out.println(sb);

    }
}
