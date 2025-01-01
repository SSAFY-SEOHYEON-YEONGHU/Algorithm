package implementation;

import java.util.*;

// HashMap<차량 번호, (입고,출차 리스트-> 짝수)>
// ArrayList 에 주차요금 담고

public class PGMS_241018_주차요금계산 {
    int[] answer;
    class Node{
        String num;
        int fee;
        Node(String num, int fee){
            this.num = num;
            this.fee = fee;
        }
    }
    public void calTime(int[] fees, String[] records){
        HashMap<String, ArrayList<Integer>> hmap = new HashMap<>();
        for(String input : records){
            String[] original = input.split(" ");
            String[] time = original[0].split(":");
            int t = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            if(hmap.containsKey(original[1])){
                hmap.get(original[1]).add(t);
            }
            else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(t);
                hmap.put(original[1], arr);
            }
        }

        // hashMap 에서 꺼내서 계산해줌
        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)->n1.num.compareTo(n2.num));
        for(String key : hmap.keySet()){
            ArrayList<Integer> arr = hmap.get(key);
            if(arr.size() % 2 != 0) arr.add(23 * 60 + 59);

            double sum = 0; // 누적 주차 시간
            for(int i=0; i<arr.size(); i+=2) sum += (arr.get(i+1) - arr.get(i));
            int cost = fees[1];// 주차요금
            if(sum > fees[0]){
                cost += (Math.ceil((sum - fees[0])/fees[2]) * fees[3]);
            }

            pq.add(new Node(key,cost));
        }

        answer = new int[pq.size()];
        int size = pq.size();
        for(int i=0; i<size; i++){
            Node cur = pq.poll();
            answer[i] = cur.fee;
        }

    }
    public int[] solution(int[] fees, String[] records) {
        calTime(fees,records);
        return answer;
    }
}