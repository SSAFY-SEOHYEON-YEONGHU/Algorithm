package implementation;

import java.util.*;

public class PGMS_240919_귤고르기 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        HashMap<Integer,Integer> hmap = new HashMap<>();
        for(int su: tangerine){
            if(hmap.containsKey(su)) hmap.put(su, hmap.get(su) + 1);
            else hmap.put(su, 1);
        }

        ArrayList<Integer> cnt = new ArrayList<>();
        for(int key : hmap.keySet()) cnt.add(hmap.get(key));

        Collections.sort(cnt, Collections.reverseOrder());

        for(int su : cnt){
            answer++;
            k-=su;

            if(k <= 0) break;
        }



        return answer;
    }
}