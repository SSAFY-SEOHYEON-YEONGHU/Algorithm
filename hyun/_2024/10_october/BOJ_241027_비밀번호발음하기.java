package implementation;

import java.io.*;
import java.util.*;

public class BOJ_241027_비밀번호발음하기 {
    public static boolean isMo(char c){
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = br.readLine();
            if(input.equals("end")) break;

            char[] arr = input.toCharArray();

            boolean isAnswer = true;
            boolean isOne = false;
            for(int i=0; i<arr.length; i++){
                if(isMo(arr[i])) isOne = true; // 모음 확인

                if(i < arr.length-1) {// 연속 2개 확인
                    if (arr[i] != 'e' && arr[i] != 'o') {
                        if (arr[i] == arr[i + 1]) {
                            isAnswer = false;
                            break;
                        }
                    }
                }
                if(i < arr.length - 2) {// 연속 3개 확인
                    int mo = 0;
                    int ja = 0;
                    for (int j = i; j < i + 3; j++) {
                        if (isMo(arr[j])) mo++;
                        else ja++;
                    }
                    if (mo * ja == 0) {
                        isAnswer = false;
                        break;
                    }
                }
            }

            if(isAnswer && isOne) sb.append("<").append(input).append("> is acceptable.").append("\n");
            else sb.append("<").append(input).append("> is not acceptable.").append("\n");

        }

        System.out.println(sb);
    }
}
