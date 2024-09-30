package implementation;

import java.util.*;
public class PGMS_240928_240929_HIndex {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        int idx = 0;
        int su = -1;

        for(int h=0; h<=citations.length; h++){
            if(citations[idx] == h){
                //System.out.println("--h " + h + " idx " + idx + " su " + su);
                if((citations.length - idx) >= h && su != citations[idx]) {
                    answer = h;
                    su = citations[idx];
                }

                boolean isChange = false;
                for(int j=idx; j<citations.length; j++){
                    if(citations[j] != su){
                        idx = j;
                        isChange = true;
                        break;
                    }
                }
                if(!isChange) break;
            }
            else{
                //System.out.println("h " + h + " idx " + idx + " su " + su);
                if((citations.length - idx) >= h) answer = h;
            }


        }

        return answer;
    }
}