package implementation;

// 곡괭이 0~5개까지 가지고 있음
// 각 곡괭이 -> 광물 5개까지 캠
// 광물은 주어진 순서대로만 캘 수 있음

// 더 사용할 곡괭이가 없거나, 모든 광물 캘때까지 반복

// 피로도 최소


// 0: 다이아 1: 철 2: 돌

import java.io.*;
import java.util.*;

public class PGMS_172927_광물캐기 {
    class Node{
        int[] weapon;
        int tired;
        int idx;
        Node(int[] weapon, int tired, int idx){
            this.weapon = weapon;
            this.tired = tired;
            this.idx = idx;
        }

        @Override
        public String toString(){
            return weapon[0] + " " + weapon[1] + " " + weapon[2] + " " + tired + " " + idx + " ";
        }
    }

    public int get_tired(int p, String m){
        int[][] map = {{1,1,1}, {5,1,1}, {25,5,1}};
        if(m.equals("diamond")) return map[p][0];
        else if(m.equals("iron")) return map[p][1];
        return map[p][2];
    }


    public int solution(int[] picks, String[] minerals) {
        int answer = Integer.MAX_VALUE;

        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(new int[]{picks[0], picks[1], picks[2]}, 0,0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            //System.out.println(cur);

            if(cur.weapon[0] == 0 && cur.weapon[1] == 0 && cur.weapon[2] == 0){
                answer = Math.min(answer, cur.tired);
                continue;
            }

            for(int i=0;i <3; i++){ // 곡괭이 하나 선택 후
                if(cur.weapon[i] == 0) continue;

                int tsum = cur.tired; // 피로도 누적
                int k = cur.idx;
                boolean isEnd = false;
                for(k=cur.idx; k < cur.idx + 5 ; k++){
                    if(k == minerals.length) {
                        isEnd = true;
                        break;
                    }
                    tsum += get_tired(i, minerals[k]);
                }

                //System.out.println("========");
                //System.out.println(tsum);
                if(isEnd){
                    answer = Math.min(answer, tsum);
                }
                else{
                    int[] tmp = cur.weapon.clone();
                    tmp[i]--;
                    q.add(new Node(tmp, tsum, k));
                }
            }

        }

        return answer;
    }
}
