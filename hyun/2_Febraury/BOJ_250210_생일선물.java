package two_pointer;

import java.io.*;
import java.util.*;
public class BOJ_250210_생일선물 {
    static int N,D;
    static long[][] input;

    static void simulation(){
        // 정렬
        Arrays.sort(input, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[0], o2[0]);
            }
        });

        // 투포인터
        int leftIdx = 0;
        long sum = 0;
        long answer = 0;
        for(int i=0; i<N; i++){
            sum += input[i][1];

            if(Math.abs(input[i][0] - input[leftIdx][0]) >= D){
                for (int j = leftIdx; j <= i; j++) {
                    if(Math.abs(input[i][0] - input[j][0]) < D) {
                        leftIdx = j;
                        break;
                    }
                    else sum -= input[j][1];
                }
            }



            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        input = new long[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Long.parseLong(st.nextToken());
            input[i][1] = Long.parseLong(st.nextToken());
        }

        //
        simulation();
    }
}
