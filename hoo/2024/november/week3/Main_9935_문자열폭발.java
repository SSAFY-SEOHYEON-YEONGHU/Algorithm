package twentytwentyfour.november.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Queue;
import java.util.Stack;

public class Main_9935_문자열폭발 {

    static String input;
    static String bomb;

    public static void main(String[] args) throws IOException {
        init();
        doBomb();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        bomb = br.readLine();
    }

    static void doBomb() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));    // 일단 스택에 넣어 줌
            if (stack.size() >= bomb.length() && checkStack(stack)) stack = removeBomb(stack);  // 스택의 크기가 폭탄 문자열보다 크고, 맨 뒤 문자들이 폭탄 문자열과 같으면 폭탄 문자열 제거
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) sb.append(stack.get(i));
        System.out.println(sb.toString().equals("")? "FRULA":sb);
    }

    static boolean checkStack(Stack<Character> stack) {
        for (int i = 0; i < bomb.length(); i++) {   // 폭탄 문자열의 길이만큼 확인
            if (stack.get((stack.size()-1)-i) != bomb.charAt((bomb.length()-1)-i)) return false;  // 폭탄 문자열과 같은 문자열이 아니면 거짓 반환
        }
        return true;
    }

    static Stack<Character> removeBomb(Stack<Character> stack) {
        for (int i = 0; i < bomb.length(); i++) stack.pop();
        return stack;
    }

}
