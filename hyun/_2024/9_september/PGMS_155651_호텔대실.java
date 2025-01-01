package implementation;

import java.util.*;

public class PGMS_155651_호텔대실 {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] time = new int[book_time.length][2];

        for(int i=0; i<book_time.length; i++){
            String[] start = book_time[i][0].split(":");
            String[] end = book_time[i][1].split(":");

            time[i][0] = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
            time[i][1] = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;
        }

        Arrays.sort(time, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        boolean[] visited = new boolean[time.length];

        for(int i=0; i<time.length; i++){
            if(visited[i]) continue;

            int et = time[i][1];
            visited[i] = true;
            answer++;

            for(int j=0; j<time.length; j++){
                int nst = time[j][0];

                if(visited[j]) continue;
                if(et <= nst) {
                    visited[j] = true;
                    et = time[j][1];
                }
            }
        }

        return answer;
    }
}
