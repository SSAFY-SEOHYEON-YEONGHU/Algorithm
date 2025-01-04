package sliding_window;

import java.io.*;
import java.util.*;
public class BOJ_250104_회전초밥_배열로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] cnt = new int[d + 1];

        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (i < k) {
                if (cnt[arr[i]] == 0) max++;
                cnt[arr[i]]++;
            }
        }

        int count = max;
        for (int i = 0; i < N; i++) {
            if (count >= max) {
                if (cnt[c] == 0) max = count + 1;
                else max = count;
            }
            if (--cnt[arr[i]] == 0) count--;
            if (cnt[arr[(i + k) % N]]++ == 0) count++;
        }
        System.out.println(max);
    }
}
