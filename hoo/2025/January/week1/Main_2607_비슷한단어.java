package twentytwentyfive.january.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2607_비슷한단어 {

    static String[] words;

    public static void main(String[] args) throws IOException {
        init();
        findSimilarWords();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        words = new String[Integer.parseInt(br.readLine())];
        for (int i = 0; i < words.length; i++) words[i] = br.readLine();
    }

    static void findSimilarWords() {
        int similarCount = 0;
        int[] wordCountsInFirstWord = countWordsInWord(0);

        for (int i = 1; i < words.length; i++) {
            if (Math.abs(words[i].length()-words[0].length()) > 1) continue;    // 길이 자체가 1넘게 차이나면 애초에 비슷한 단어가 아님
            if (judgeIsSimilar(wordCountsInFirstWord, i)) {
                similarCount++;
//                System.out.println(words[i]);
            }
        }

        System.out.println(similarCount);
    }

    static boolean judgeIsSimilar(int[] wordCountInFirstWord, int compareWordIndex) {
        int[] wordCountsInCompareWord = countWordsInWord(compareWordIndex);
        int sameCount = 0;
        for (int i = 0; i < 26; i++) {
            if (wordCountInFirstWord[i] > 0 && wordCountsInCompareWord[i] > 0) sameCount += Math.min(wordCountInFirstWord[i], wordCountsInCompareWord[i]);

        }

        return Math.abs(Math.max(words[0].length(), words[compareWordIndex].length()) - sameCount) <= 1;
    }

    static int[] countWordsInWord(int numberIndex) {
        int[] wordCounts = new int[26]; // 첫 번째 단어에 들어있는 알파벳 개수 카운트해서 저장하는 배열
        for (int i = 0; i < words[numberIndex].length(); i++) wordCounts[words[numberIndex].charAt(i) - 'A']++;

        return wordCounts;
    }

}
