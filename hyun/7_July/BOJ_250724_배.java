package sort;

import java.io.*;
import java.util.*;

public class BOJ_250724_배 {
    static int N,M;
    static int[] crain;
    static Queue<Integer> box = new ArrayDeque<>();

    static void simulation(){
        int time = 0;
        Arrays.sort(crain);

        if(box.peek() > crain[N-1]){
            System.out.println(-1);
            return;
        }

        while(!box.isEmpty()){
            time++;

            int cnt = 0;
            for (int i = N-1; i >= 0 && !box.isEmpty(); i--) {
                if(cnt == box.size()) break;
                int cur = box.poll();

                if(crain[i] < cur) {
                    box.add(cur);
                    i++;
                    cnt++;
                }
                else cnt = 0;
            }
        }

        System.out.println(time);
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        crain = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) crain[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        int[] tmp = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) tmp[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(tmp);
        for (int i = M-1; i >= 0; i--) box.add(tmp[i]);
        simulation();
    }
}
