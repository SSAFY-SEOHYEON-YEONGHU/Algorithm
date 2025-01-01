package twentytwentyfour.october.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_4659_비밀번호발음하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while (true) {
            input = br.readLine();
            if (input.equals("end")) break;
            String answer = isAcceptable(input)? "acceptable.":"not acceptable.";
            sb.append("<" + input + "> is " + answer).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isAcceptable(String input) {
        // vowel 모음, consonant 자음
        boolean isThereAeiou = false;
        char mode = 'c';   // 전 글자의 모음, 자음 여부 v, c로 표현
        if (isVowel(input.charAt(0))) {
            mode = 'v'; // 첫 글자가 모음이면 모음 표시
            isThereAeiou = true;    // 모음 있음을 표시
        }
        int continuousCount = 1;   // 모음 || 자음이 연속으로 몇 개인 지 체크하는 변수
        char now;
        for (int i = 1; i < input.length(); i++) {
            now = input.charAt(i);
            if (isVowel(now)) { // 모음인 경우
                if (mode == 'v') continuousCount++; // 연속 모음이라면 체
                else continuousCount = 1;   // 그 전이 자음이었으면 연속 카운트 1로 초기화
                mode = 'v'; // 글자가 모음임을 표시
                isThereAeiou = true;    // 모음 있음을 표시
            } else {    // 자음인 경우
                if (mode == 'c') continuousCount++; // 연속 자음이라면 체
                else continuousCount = 1;   // 그 전이 모음이었으면 연속 카운트 1로 초기화
                mode = 'c'; // 글자가 모음임을 표시
            }
            if (continuousCount >= 3) return false; // 조건 2. 자 혹은 모음이 연속 세 개인 지 체크
            if ((now != 'e' && now != 'o') && input.charAt(i-1) == now) return false;   // 조건 3. ee나 oo가 아닌데 연속으로 같은 글자가 두 번 왔는 지 체크
        }

        return isThereAeiou?true:false; // 조건 1. 2, 3번 조건 충족하고 모음 하나라도 있다면 true 반환
    }

    static boolean isVowel(char c) {    // 모음 여부 판별 함수
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;

        return false;
    }

}
