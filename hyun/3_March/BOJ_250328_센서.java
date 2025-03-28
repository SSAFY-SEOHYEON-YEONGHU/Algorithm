package binary_search;

import java.io.*;
import java.util.*;

/*
이분탐색은 안됨
기준점을 센서 사이의 거리로 했는데
한 그룹이라 치면 그룹 사이의 센서 거리가 천차만별
따라서 이분탐색으로는 안됨
 */
public class BOJ_250328_센서 {
    static int N,K;
    static int[] input;

    static int[] makeGroup(int dis){
        int[] result = new int[2]; // 그룹수, 거리영역
        for (int i = 0; i < N; i++) {
            int j=0;
            for (j = i+1; j < N; j++) {
                if(input[j] - input[i] > dis){
                    j = j-1;
                    break;
                }
            }

            result[0]++;
            if(j >= N) j = N-1;
            result[1] += (input[j]-input[i]);
            i = j;
        }

        return result;
    }

    static void binarySearch(){
        int low = 0;
        int high = N+1;

        while(low+1 < high){
            int mid = (low + high) / 2;

            int[] result = makeGroup(mid);
            if(result[0] > K) low = mid;
            else high = mid;
        }

        System.out.println(makeGroup(high)[1]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        Set<Integer> s = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int su = Integer.parseInt(st.nextToken());
            if(s.contains(su)) continue;
            s.add(su);
        }

        N = s.size();
        input = new int[N];
        int idx = 0;
        Iterator<Integer> it = s.iterator();
        while(it.hasNext()) input[idx++] = it.next();
        Arrays.sort(input);
        //
        binarySearch();
    }
}
