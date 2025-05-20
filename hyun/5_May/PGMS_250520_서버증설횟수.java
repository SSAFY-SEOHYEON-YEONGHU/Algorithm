package implementation;

public class PGMS_250520_서버증설횟수 {

    public int simulation(int[] players, int M, int K){
        int answer = 0;

        int[][] map = new int[24][2];
        map[0][0] = players[0] / M;
        map[0][1] = players[0] / M;
        answer += map[0][1];

        for(int i=1; i<24; i++){
            map[i][0] = map[i-1][0];
            if(i-K > -1) map[i][0] -= map[i-K][1];


            int cnt = (players[i] - (map[i][0] * M)) / M;
            if(cnt > 0) {
                map[i][0] += cnt;
                map[i][1] = cnt;
                answer += cnt;
            }
        }

        // for(int i=0; i<24;i++){
        //     System.out.println(players[i] + " " + map[i][0] + " " + map[i][1]);
        // }

        return answer;
    }


    public int solution(int[] players, int m, int k) {
        return simulation(players, m, k);
    }
}
