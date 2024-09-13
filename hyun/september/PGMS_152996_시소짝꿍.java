package implementation;

import java.util.*;

class PGMS_152996_시소짝꿍 {
    int[] input;
    HashMap<Integer, Integer> hmap;
    long answer = 0;

    public void eraseDup(){
        hmap = new HashMap<>();

        for(int su : input){
            if(hmap.containsKey(su)) hmap.put(su, hmap.get(su) + 1);
            else hmap.put(su,1);
        }

        input = new int[hmap.size()];
        int idx = 0;
        for(int w : hmap.keySet()){
            if(hmap.get(w) > 1) {
                long cnt = hmap.get(w);
                answer += ((cnt * (cnt-1))/2);
            }
            input[idx++] = w;
        }

    }

    public long simulation(){

        ArrayList<Integer>[] cnt = new ArrayList[5000];
        for(int i=0; i<5000; i++) cnt[i] = new ArrayList<>();

        for(int i=0; i<input.length; i++){
            int su = input[i];
            // cnt[su]. add(i);
            cnt[su*2].add(i);
            cnt[su*3].add(i);
            cnt[su*4].add(i);
        }

        int[] dx = {2,3,4};
        boolean[][] visited = new boolean[input.length][input.length];
        for(int i=0; i<input.length; i++){
            visited[i][i] = true;
            for(int add : dx){
                int nNum = input[i] * add;
                for(int idx : cnt[nNum]){
                    //System.out.println("현재 " + i + ":" + nNum + " " + idx );
                    if(visited[idx][i]) continue;
                    visited[idx][i] = true;
                    visited[i][idx] = true;
                    long acnt = hmap.get(input[i]);
                    long bcnt = hmap.get(input[idx]);
                    answer+=(acnt * bcnt);
                    //System.out.println("정답");
                }
                //System.out.println();
            }
        }

        return answer;
    }

    public long solution(int[] weights) {
        input = weights;

        eraseDup();

        return simulation();
    }
}