import javax.swing.plaf.basic.BasicBorders;
import java.io.*;
import java.util.*;

// v1 : substring 활용
// v2 : 연결리스트 처럼 구현
// v3 : 스택
public class Main {
    static String explode;
    static char[] input;
    static int[] nxt;

    public static void explode(){
        Stack<Character> st = new Stack<>();
        Stack<Character> tmp;


        char end = explode.charAt(explode.length()-1);
        for(int i=0; i<input.length; i++){
            char ch = input[i];
            st.add(ch);

            if(ch == end){ // 스택에서 빼주기
                boolean isExplode = true;

                if(st.size() < explode.length()) continue;
                else {
                    int stIdx = st.size()-1;
                    int exIdx = explode.length()-1; // explode 기준

                    for (int j = 0; j < explode.length(); j++) {
                        if(st.get(stIdx--) != explode.charAt(exIdx--)) isExplode = false;
                    }
                }

                if(isExplode){
                    for (int j = 0; j < explode.length(); j++) {
                        st.pop();
                    }
                }

            }
        }


        if(st.isEmpty()) System.out.println("FRULA");
        else{
            StringBuilder sb = new StringBuilder();
            for(char c : st) sb.append(c);
            System.out.println(sb);
        }


    }
    public static void simulation(){
        explode();
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        explode = br.readLine();

        simulation();
    }
}