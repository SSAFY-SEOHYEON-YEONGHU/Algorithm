package implementation;

public class BOJ_250521_유연근무제 {
    public int nextNum(int n){
        n++;
        if(n > 7) n = 1;
        return n;
    }

    public int simulation(int[] schedules, int[][] timelogs, int startday){
        int N = schedules.length;

        for(int i=0; i<N; i++){
            schedules[i] = ((schedules[i] / 100) * 100 + (schedules[i] % 100) + 10);
        }

        boolean[] failed = new boolean[N];

        for(int i=0; i<7; i++){
            if(startday > 5) {
                startday = nextNum(startday);
                continue;
            }

            for(int k=0; k<N; k++){ // k명
                if(failed[k]) continue;

                int t = ((timelogs[k][i] / 100) * 100 + (timelogs[k][i] % 100));
                if(t > schedules[k]) failed[k] = true;
            }

            startday = nextNum(startday);
        }

        int answer = 0;
        for(int i=0; i<N; i++) if(!failed[i]) answer++;

        return answer;
    }

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        return simulation(schedules, timelogs, startday);
    }
}