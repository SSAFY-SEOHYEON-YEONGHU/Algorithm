package implementation;

import java.util.*;

public class PGMS_241204_실패율 {
    class Node{
        double fail;
        int stage;
        Node(double fail, int stage){
            this.fail = fail;
            this.stage = stage;
        }
    }

    public int[] simulation(int N, int[] stages){
        int[] answer = new int[N];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.fail == n2.fail) return n1.stage - n2.stage;
                return Double.compare(n2.fail, n1.fail);
            }
        });

        Arrays.sort(stages);

        int[][] failCnt = new int[N+1][2];

        int total = stages.length;
        int idx = stages.length - 1;
        int cnt = 0;
        for(int num=N; num>= 1; num--){

            boolean isChange = false;
            for(int i=idx; i>=0; i--){
                if(stages[i] == num) cnt++;
                else if(stages[i] < num) {
                    failCnt[num][0] = cnt;
                    failCnt[num][1] = total-1 - i;
                    idx = i;
                    cnt = 0;
                    isChange = true;
                    break;
                }
            }

            if(!isChange) {
                failCnt[num][0] = cnt;
                failCnt[num][1] = total;
                idx = -1;
                cnt = 0;
            }
        }

        for(int i=1; i<= N; i++){
            //System.out.println(i+"번째 " + failCnt[i][0] + " / " + failCnt[i][1]);
            if(failCnt[i][0] == 0) pq.add(new Node(0.0, i));
            else pq.add(new Node((double)failCnt[i][0] / failCnt[i][1] , i));
        }

        int ii = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            answer[ii++] = cur.stage;
        }

        return answer;

    }
    public int[] solution(int N, int[] stages) {

        return simulation(N,stages);
    }
}