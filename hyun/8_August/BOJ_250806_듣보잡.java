package hash;

import java.io.*;
import java.util.*;

public class BOJ_250806_듣보잡 {
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Set<String> s = new HashSet<>();
        for (int i = 0; i < N; i++) {
            s.add(br.readLine());
        }

        ArrayList<String > answer = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String tmp = br.readLine();
            if(s.contains(tmp)) answer.add(tmp);
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }
}
