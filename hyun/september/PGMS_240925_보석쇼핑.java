package two_pointer;

import java.util.*;

public class PGMS_240925_보석쇼핑 {
    int cnt = 1;
    int[] answer = {1,1};

    public void twoPointer(String[] gems){
        int left = 0;
        HashMap<String, Integer> hmap = new HashMap<>();
        hmap.put(gems[0],1);

        for(int right = 1; right < gems.length; right++){

            if(hmap.containsKey(gems[right])) hmap.put(gems[right], hmap.get(gems[right]) + 1);
            else hmap.put(gems[right], 1);

            for(int i=left; i < right; i++){

                if(hmap.get(gems[i]) - 1 != 0){
                    hmap.put(gems[i], hmap.get(gems[i]) - 1);
                }
                else {
                    left = i;
                    break;
                }
            }

            // 정답 갱신
            if(cnt < hmap.size()) {
                cnt = hmap.size();
                answer = new int[]{left+1, right+1};
            }
            else if(cnt == hmap.size()){
                if(answer[1] - answer[0] > right - left)
                    answer = new int[]{left+1, right+1};
            }

        }
    }

    public int[] solution(String[] gems) {

        twoPointer(gems);

        return answer;
    }
}