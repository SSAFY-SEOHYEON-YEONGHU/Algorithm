package dp;

import java.io.*;
import java.util.*;

public class BOJ_250625_가장긴증가하는부분수열2 {
    static int N;
    static int[] input;

    public static int lowerBound(int cnt, int kijun, int[] map){
        int low = -1;
        int high = cnt;

        while(low+1 < high){
            int mid = (low + high) / 2;
            if(kijun > map[mid]) low = mid;
            else high = mid;
        }

        return high;
    }

    public static void simulation(){
        int[] map = new int[N];
        map[0] = input[0];
        int idx = 1;
        for (int i = 1; i < N; i++) {
            if(map[idx-1] < input[i]) map[idx++] = input[i];
            else {
                map[lowerBound(idx, input[i],map)] = input[i];
            }
        }

        System.out.println(idx);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //
        simulation();
    }
}
