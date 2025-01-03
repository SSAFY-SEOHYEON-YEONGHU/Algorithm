package hash;

import java.io.*;
import java.util.*;
public class BOJ_250103_비슷한단어 {
    static int N;
    static char[] input;

    static int answer = 0;
    static HashMap<Character, Integer> makeHashMap(char[] arr){
        HashMap<Character, Integer> original = new HashMap<>();
        for(char c : arr){
            if(!original.containsKey(c)) original.put(c,1);
            else original.put(c, original.get(c)+1);
        }

        return original;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();

        for (int i = 0; i < N-1; i++) {
            char[] tmp = br.readLine().toCharArray();
            HashMap<Character, Integer> original = makeHashMap(input);
            HashMap<Character, Integer> hashTmp = makeHashMap(tmp);

            // tmp 를 기준으로 input 제거
            for(char c : hashTmp.keySet()){
                if(!original.containsKey(c)){
                    original.put(c,-hashTmp.get(c));
                }
                else{
                    int result = original.get(c) - hashTmp.get(c);
                    if(result == 0) original.remove(c);
                    else original.put(c, original.get(c) - hashTmp.get(c));
                }
            }

            // original 을 순회하며 확인
            boolean isAnswer = false;
            if(original.size() == 1){
                for(char c : original.keySet()) if(Math.abs(original.get(c)) == 1) isAnswer = true;
            }
            else if(original.size() == 2){
                int[] su = new int[2];
                int idx = 0;
                for(char c : original.keySet()) su[idx++] = original.get(c);
                if(su[0] + su[1] == 0 && (su[0] == 1 || su[0]==-1)) isAnswer = true;
            }
            else if(original.size() == 0) isAnswer = true;

            if(isAnswer) answer++;

        }

        System.out.println(answer);

    }
}