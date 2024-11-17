import javax.swing.plaf.basic.BasicBorders;
import java.io.*;
import java.util.*;

// v1 : substring 활용
// v2 : 연결리스트 처럼 구현
public class Main {
    static String explode;
    static char[] input;
    static int[] nxt;
    public static void makeNextIdx(){
        nxt = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            nxt[i] = i+1;
        }
        nxt[input.length-1] = -1; // 마지막 표시
    }

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
                    tmp = new Stack<>();
                    int exIdx = explode.length()-1; // explode 기준

                    for (int j = 0; j < explode.length(); j++) {
                        char cur = st.pop();
                        if(cur != explode.charAt(exIdx--)) isExplode = false;
                        tmp.add(cur);
                    }
                }

                if(!isExplode){
                    while(!tmp.isEmpty()) st.add(tmp.pop());
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
        //makeNextIdx();
        explode();
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        explode = br.readLine();

        simulation();
    }
}