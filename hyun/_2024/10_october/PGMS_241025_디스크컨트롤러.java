package implementation;

import java.util.*;

public class PGMS_241025_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;

        Arrays.sort(jobs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int time = jobs[0][0];
        boolean[] visited = new boolean[jobs.length];
        int cnt = 0;
        int sum = 0;
        while(true){
            int value = Integer.MAX_VALUE;
            int start = 0;
            int idx = 0;
            boolean isExist = false;
            for(int j=0; j<jobs.length; j++){
                if(visited[j]) continue;
                if(time >= jobs[j][0] && value > jobs[j][1]){
                    value = jobs[j][1];
                    idx = j;
                    isExist = true;
                }
            }

            if(isExist){
                visited[idx] = true;
                time += jobs[idx][1];
                sum += (time - jobs[idx][0]);
            }
            else{
                int j=0;
                for(j=0; j<jobs.length; j++) if(!visited[j]) break;
                time = jobs[j][0] + jobs[j][1];
                sum += (jobs[j][1]);
            }

            cnt++;

            if(cnt == jobs.length) break;

        }

        System.out.println(sum/jobs.length);
        return sum/jobs.length;
    }
}