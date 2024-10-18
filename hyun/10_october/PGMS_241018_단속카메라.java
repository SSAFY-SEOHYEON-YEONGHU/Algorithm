package greedy;

import java.util.*;

public class PGMS_241018_단속카메라 {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o1[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int end = routes[0][1];
        for(int i=1; i<routes.length; i++){
            if(routes[i][0] > end) {
                answer++;
                end = routes[i][1];
            }
        }

        return answer+1;
    }
}