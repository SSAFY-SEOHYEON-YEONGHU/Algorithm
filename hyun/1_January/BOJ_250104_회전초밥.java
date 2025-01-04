package sliding_window;

import java.io.*;
import java.util.*;
public class BOJ_250104_회전초밥 {
    static int N,d,k,c;
    static int[] sushi;
    static int answer = 0;

    static void slidingWindow(){
        HashMap<Integer,Integer> hmap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            if(!hmap.containsKey(sushi[i])) hmap.put(sushi[i], 1);
            else hmap.put(sushi[i], hmap.get(sushi[i]) + 1);
        }

        int left = -1;
        int right = k-1;
        if(k < N) {
            while (true) {
                // 검사
                if(!hmap.containsKey(c)) answer = Math.max(answer, hmap.size()+1);
                else answer = Math.max(answer, hmap.size());

                if(left == N-1) break;

                left++;
                right = (right + 1) % N;

                if(hmap.get(sushi[left]) - 1 == 0) hmap.remove(sushi[left]);
                else hmap.put(sushi[left], hmap.get(sushi[left]) - 1);

                if(!hmap.containsKey(sushi[right])) hmap.put(sushi[right], 1);
                else hmap.put(sushi[right], hmap.get(sushi[right]) + 1);
            }
        }
        else{
            // 정답 처리
            if(!hmap.containsKey(c)) answer = Math.max(answer, hmap.size() + 1);
            else answer = Math.max(answer, hmap.size());
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        slidingWindow();
    }
}
