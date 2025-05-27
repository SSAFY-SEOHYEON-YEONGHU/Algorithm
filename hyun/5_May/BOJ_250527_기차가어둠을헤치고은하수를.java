package bit;

import java.io.*;
import java.util.*;

public class BOJ_250527_기차가어둠을헤치고은하수를 {
    static int N,M;
    static int[] train;

    static void changeTrain(int op, int tnum, int pnum){
        if(op == 1){
            train[tnum] |= (1<<pnum);
        }
        else if(op == 2){
            train[tnum] &= ~(1<<pnum);
        }
        else if(op == 3){
            train[tnum] &= ~(1<<19); // 맨 끝자리 없애줌
            train[tnum] *= 2;
        }
        else train[tnum] /= 2;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        train = new int[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int tnum = Integer.parseInt(st.nextToken());
            if (op <= 2) {
                int pnum = Integer.parseInt(st.nextToken()) - 1;
                changeTrain(op, tnum, pnum);
            } else {
                changeTrain(op, tnum, 0);
            }
        }

        Set<Integer> s = new HashSet<>();
        int answer = 0;
        for (int i = 1; i <= N ; i++) {
            if(!s.contains(train[i])){
                answer++;
                s.add(train[i]);
            }
        }
        System.out.println(answer);
    }
}
