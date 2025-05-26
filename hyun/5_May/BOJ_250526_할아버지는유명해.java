package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250526_할아버지는유명해 {
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N==0 && M==0) break;

            int[][] input = new int[N][M];
            HashMap<Integer,Integer> hmap = new HashMap<>();
            int maxScore = 1;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int cur = Integer.parseInt(st.nextToken());
                    if(!hmap.containsKey(cur)) hmap.put(cur,1);
                    else {
                        hmap.put(cur, hmap.get(cur) + 1);
                        maxScore = Math.max(maxScore, hmap.get(cur));
                    }
                }
            }

            ArrayList<int[]> result = new ArrayList<>();
            for(int key : hmap.keySet()){
                if(hmap.get(key) == maxScore) continue;
                result.add(new int[]{hmap.get(key),key});
            }

            Collections.sort(result, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] == o2[0]) return o1[1] - o2[1];
                    return o2[0] - o1[0];
                }
            });

            int secodeScore = result.get(0)[0];
            for(int[] cur: result){
                if(cur[0] != secodeScore) break;
                sb.append(cur[1]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
