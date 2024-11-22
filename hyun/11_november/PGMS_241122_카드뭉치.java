package implementation;


import java.util.*;

public class PGMS_241122_카드뭉치 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        int i1 = 0;
        int i2 = 0;

        int len1 = cards1.length;
        int len2 = cards2.length;

        for(String cur : goal){
            if(i1 < len1 && cards1[i1].equals(cur)){
                i1++;
                continue;
            }

            if(i2 < len2 && cards2[i2].equals(cur)){
                i2++;
                continue;
            }

            else {
                answer = "No";
                break;
            }
        }

        return answer;
    }
}