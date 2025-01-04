package backtracking;

import java.io.*;
import java.util.*;
public class BOJ_250103_계란으로계란치기  {
    static int N;
    static int[][] eggs;
    static int answer = 0;
    static class Node{
        int hard, weight;
        boolean broke;
        Node(int hard, int weight, boolean broke){
            this.hard = hard;
            this.weight = weight;
            this.broke = broke;
        }
    }

    static void start(int cnt){
        // 갯수 세기


        if(cnt == N) {
            int sum = 0;
            for(int i=0; i<N; i++) if(eggs[i][0] <= 0) sum++;
            answer = Math.max(answer,sum);
            return;
        }

        if(eggs[cnt][0] <= 0) {
            start(cnt+1);
            return;
        }

        boolean isBroke = false;
        for (int i = 0; i < N; i++) {
            if(i==cnt || eggs[i][0] <= 0) continue;

            eggs[cnt][0] -= eggs[i][1];
            eggs[i][0] -= eggs[cnt][1];
            isBroke = true;
            start(cnt+1);

            eggs[cnt][0] += eggs[i][1];
            eggs[i][0] += eggs[cnt][1];
        }

        if(isBroke == false) start(cnt+1);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        start(0);
        System.out.println(answer);
    }
}
