package november.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12919_A와B2 {

    static String start, goal;
    static boolean isMade;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        start = br.readLine();
        goal = br.readLine();
        isMade = false;
        makeWord(goal);

        System.out.println(isMade? 1:0);
    }

    static void makeWord(String nowWord) {  // goal => start로 탐색하는 재귀함수
        if (nowWord.length() < start.length() || isMade) return; // 글자가 이미 만들어진 함수 바로 종료

        if (nowWord.equals(start)) { // 현재 글자가 목표 글자면 만들어졌음을 표시 후 함수 종료
            isMade = true;
            return;
        }
        if (nowWord.charAt(nowWord.length()-1) == 'A') {    // 지금 만들어진 단어가 이전 단어에서 A를 추가해서 만들어진 단어라면
            makeWord(nowWord.substring(0, nowWord.length()-1)); // 맨 뒤 알파벳(A)만 빼고 다음 단어 탐색
        }
        if (nowWord.charAt(0) == 'B') { // 위의 경우도 따져보고, 이전 단어에서 B를 추가하고 뒤집은 단어일 수도 있으니 이것도 따져봄
            String temp = nowWord.substring(1, nowWord.length());   // 맨 처음 알파벳(B) 뺀 단어 만들고
            String nextWord = "";
            for (int i = temp.length()-1; i >= 0; i--) nextWord = nextWord + temp.charAt(i);    // 뒤집어 줌
            makeWord(nextWord);
        }
    }

}
