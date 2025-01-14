package twentytwentyfive.january.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_2179_비슷한단어 {

    static int N;
    static String[] words;
    static Map<String, Integer> wordPartMap;    // 각 단어들의 접두사를 저장하는 맵

    public static void main(String[] args) throws IOException {
        init();
        findMaxLengthSimilarWords();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        wordPartMap = new HashMap<>();  // key는 접두사, value는 해당 접두사가 나온 인덱스
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (int j = 1; j <= words[i].length(); j++) {
                if (!wordPartMap.containsKey(words[i].substring(0, j))) wordPartMap.put(words[i].substring(0, j), i);
            }
        }
    }

    static void findMaxLengthSimilarWords() {
        int maxLength = 0;
        int maxLengthPrefixIndex = -1;  // 가장 긴 접두사를 가진 단어의 인덱스
        String[] answer = new String[2];
        int[] answerIndex = new int[] {20_001, 20_001}; // 정답으로 판별된 단어들의 인덱스를 저장하는 배열
        for (int i = 0; i < N; i++) {   // 모든 단어에 대해
            int tempLength = 0;
            for (int j = 1; j <= words[i].length(); j++) {  // 해당 단어의 접두사들 모두 조사
                if (wordPartMap.containsKey(words[i].substring(0, j))) {
                    if (wordPartMap.get(words[i].substring(0, j)) == i) continue;   // 만약 현재 단어에서 파생된 접두사면 건너 뜀
                    tempLength = j;
                }
            }

            if (tempLength != 0 && tempLength >= maxLength) {  // 입력 순서 상 먼저 오는 걸 우선으로 해야하므로, 길이가 같은 경우도 판별해줘야 함
                maxLengthPrefixIndex = wordPartMap.get(words[i].substring(0, tempLength));
                if (tempLength == maxLength && answerIndex[0] < maxLengthPrefixIndex) continue;  // 접두사의 길이가 같은 경우, S의 인덱스가 낮은 경우만 갱신함. 나머지 경우는 건너 뜀
                else if (tempLength == maxLength && answerIndex[0] == maxLengthPrefixIndex && answerIndex[1] < i) continue;

                maxLength = tempLength;
                answer[0] = words[maxLengthPrefixIndex];
                answer[1] = words[i];
                answerIndex[0] = maxLengthPrefixIndex;
                answerIndex[1] = i;
            }
        }
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

}
