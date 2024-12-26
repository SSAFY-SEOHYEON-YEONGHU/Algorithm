package sliding_window;

import java.io.*;
import java.util.*;
public class BOJ_241226_ListOfUniqueNumbers {
    static int N;
    static int[] input;

    static void simulation(){
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[100010];

        long answer = 0;

        for (int i = 0; i < N; i++) {
            int cur = input[i];

            if(visited[cur]){
                while(!dq.isEmpty()){
                    int su = dq.pollFirst();
                    if(su == cur) break;
                    visited[su] = false;
                }
            }
            else visited[cur] = true;

            answer += (dq.size() + 1);
            dq.add(cur);
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //
        simulation();
    }
}
