package hash;

import java.util.*;

public class PGMS_241216_추억점수 {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        HashMap<String, Integer> hmap = new HashMap<>();
        for(int i=0; i<yearning.length; i++){
            hmap.put(name[i], yearning[i]);
        }

        int idx = 0;
        for(String[] input : photo){
            int sum = 0;
            for(String s : input) {
                if(hmap.containsKey(s)) sum += hmap.get(s);
            }

            answer[idx++] = sum;
        }
        return answer;
    }
}