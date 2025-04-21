package implementation;


import java.util.*;

public class PGMS_250421_둘만의암호 {
    /*
    s 한 문자마다
        문자를 늘리는데 만약 문자 skip 에 포함되있다면 길이 하나 더 늘리기
    */
    Set<Character> skips = new HashSet<>();

    public String func(String s, int index){
        String answer = "";

        for(int i=0; i<s.length(); i++){
            char cur = s.charAt(i);
            int size = index;
            for(int j=0; j<size; j++){
                cur++;
                if(cur > 122) cur -= 26;
                if(skips.contains(cur)) size++;
            }
            answer += cur;
        }
        return answer;
    }
    public String solution(String s, String skip, int index) {

        for(int i=0; i<skip.length(); i++) skips.add(skip.charAt(i));
        return func(s,index);
    }
}