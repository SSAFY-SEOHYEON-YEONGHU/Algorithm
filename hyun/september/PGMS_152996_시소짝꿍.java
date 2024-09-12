package implementation;

import java.util.*;

public class PGMS_152996_시소짝꿍 {
    public long solution(int[] weights) {
        long answer = 0;

        ArrayList<Integer>[] cnt = new ArrayList[5000];
        for(int i=0; i<5000; i++) cnt[i] = new ArrayList<>();

        for(int i=0; i<weights.length; i++){
            int su = weights[i];
            cnt[su]. add(i);
            cnt[su*2].add(i);
            cnt[su*3].add(i);
            cnt[su*4].add(i);
        }

        // Arrays.sort(weights);
        int[] dx = {1,2,3,4};
        boolean[][] visited = new boolean[weights.length][weights.length];
        for(int i=0; i<weights.length; i++){
            visited[i][i] = true;
            for(int add : dx){
                int nNum = weights[i] * add;
                for(int idx : cnt[nNum]){
                    //System.out.println("현재 " + i + " " + nNum + " " + idx);
                    if(visited[idx][i]) continue;
                    visited[idx][i] = true;
                    visited[i][idx] = true;
                    answer++;
                    //System.out.println("정답");
                }
                //System.out.println();
            }
        }


        return answer;
    }
}