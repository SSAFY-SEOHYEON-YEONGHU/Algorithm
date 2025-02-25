package twentytwentyfive.february.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16508_전공책 {

    static String T;
    static int N;
    static String[][] books;
    static int minCost;

    public static void main(String[] args) throws IOException {
        init();
        int[][] containAlphabetArrayForEachBook = makeContainAlphabetArrayForEachBook();
        for (int i = 1; i <= N; i++) {
            calcMinCost(0, i, new boolean[N], containAlphabetArrayForEachBook);
        }

        System.out.println( (minCost == Integer.MAX_VALUE)? -1:minCost );
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine();
        N = Integer.parseInt(br.readLine());

        books = new String[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) books[i][j] = st.nextToken();
        }

        minCost = Integer.MAX_VALUE;
    }

    static int[][] makeContainAlphabetArrayForEachBook() { // 각 책별로 가지고 있는 알파벳을 표시하는 배열 생성
        int[][] containAlphabetArrayForEachBook = new int[N][26];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < books[i][1].length(); j++) {
                containAlphabetArrayForEachBook[i][books[i][1].charAt(j)-'A']++;
            }
        }

        return containAlphabetArrayForEachBook;
    }

    static void calcMinCost(int start, int selectedCount, boolean[] isSelected, int[][] containAlphabetArrayForEachBook) {
        if (selectedCount == 0) { // 기저, 모든 숫자에 대한 선택 작업이 다 끝난 경우
            int totalCost = 0;
            for (int i = 0; i < N; i++) if (isSelected[i]) totalCost += Integer.parseInt(books[i][0]);
            if (canMakeT(isSelected, containAlphabetArrayForEachBook)) minCost = Math.min(minCost, totalCost);
            return;
        }

        for (int i = start; i < N; i++) {
            isSelected[i] = true;
            calcMinCost(i+1, selectedCount-1, isSelected, containAlphabetArrayForEachBook);
            isSelected[i] = false;
        }

    }

    static boolean canMakeT(boolean[] isSelected, int[][] containAlphabetArrayForEachBook) { // 최종적으로 만들어진 배열에 대해 T를 만들 수 있는 지 여부 판단
        int[] containAlphabetArray = makeContainAlphabetArray(isSelected, containAlphabetArrayForEachBook);
        for (int i = 0; i < T.length(); i++) {
            if (containAlphabetArray[T.charAt(i)-'A'] == 0) return false; // 없는 알파벳 하나라도 발견되면 거짓 반환
            else containAlphabetArray[T.charAt(i)-'A']--; // 있는 알파벳이면 개수 -1
        }

        return true;
    }

    static int[] makeContainAlphabetArray(boolean[] isSelected, int[][] containAlphabetArrayForEachBook) {
        int[] containAlphabetArray = new int[26];
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) {
                for (int j = 0; j < 26; j++) if (containAlphabetArrayForEachBook[i][j] >= 1) containAlphabetArray[j] += containAlphabetArrayForEachBook[i][j];
            }
        }

        return containAlphabetArray;
    }

}
