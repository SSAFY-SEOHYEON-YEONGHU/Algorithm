package twentytwentyfive.january.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_2179_비슷한단어 {

    static int N;
    static String[] words;
//    static Map<String, Integer> wordPartMap;    // 각 단어들의 접두사를 저장하는 맵

    public static void main(String[] args) throws IOException {
        init();
        findMaxLengthSimilarWords();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
//        wordPartMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
//            for (int j = 1; j <= words[i].length(); j++) {
//                if (!wordPartMap.containsKey(words[i].substring(0, j))) wordPartMap.put(words[i].substring(0, j), 1);
//            }
        }
    }

    static void findMaxLengthSimilarWords() {
        int maxLength = 0;
        String[] answer = new String[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (words[j].equals(words[i])) continue;    // 같은 단어는 건너 뜀

                int sameCount = 0;
                for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) break;
                    sameCount++;
                }

                if (sameCount > maxLength) {
                    maxLength = sameCount;
                    answer = new String[] {words[i], words[j]};
                }
            }
        }
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

}
