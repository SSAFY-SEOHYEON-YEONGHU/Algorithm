package implementation;

import java.util.*;

public class PGMS_240926_할인행사 {
    HashMap<String,Integer> hmap = new HashMap<>();
    int answer = 0;

    public void isAnswer(String[] want, int[] number){
        boolean isFlag = true;
        for(int i=0; i<number.length; i++){
            if(!hmap.containsKey(want[i]) || hmap.get(want[i]) < number[i]){
                isFlag = false;
                break;
            }
        }

        if(isFlag) answer++;
    }

    public int solution(String[] want, int[] number, String[] discount) {

        for(int i=0; i<10; i++){
            if(hmap.containsKey(discount[i])) hmap.put(discount[i], hmap.get(discount[i]) + 1);
            else hmap.put(discount[i], 1);
        }

        // 확인
        isAnswer(want,number);

        for(int i=10; i < discount.length; i++){
            // 넣고
            //System.out.println(discount[i] + " 넣음");
            if(hmap.containsKey(discount[i])) hmap.put(discount[i], hmap.get(discount[i]) + 1);
            else hmap.put(discount[i], 1);

            // 빼고
            //System.out.println(discount[i-10] + " 뺌");
            if(hmap.get(discount[i-10]) - 1 == 0) hmap.remove(discount[i-10]);
            else hmap.put(discount[i-10], hmap.get(discount[i-10]) - 1);

            // 확인
            isAnswer(want,number);
        }

        return answer;
    }
}
