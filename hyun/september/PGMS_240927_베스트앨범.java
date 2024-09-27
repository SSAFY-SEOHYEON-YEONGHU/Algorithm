package implementation;

import java.util.*;

public class PGMS_240927_베스트앨범 {
    ArrayList<Integer> result = new ArrayList<>();

    class Music{
        int idx;
        int play;
        Music(int idx, int play){
            this.idx = idx;
            this.play = play;
        }
    }

    class Node{
        String genre;
        int total;
        PriorityQueue<Music> pq;
        Node(String genre, int total, PriorityQueue<Music>pq){
            this.genre = genre;
            this.total = total;
            this.pq = pq;
        }

    }

    public void simulation(String[] genres, int[] plays){

        HashMap<String, Node> hmap = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            String cur = genres[i];
            int curp = plays[i];

            if(hmap.containsKey(cur)){
                Node getCur = hmap.get(cur);
                getCur.pq.add(new Music(i,curp));
                getCur.total += curp;
                hmap.put(cur, getCur);
            }
            else{
                PriorityQueue<Music> pq = new PriorityQueue<>((m1,m2) -> m2.play - m1.play);
                pq.add(new Music(i,curp));
                hmap.put(cur, new Node(cur, curp, pq));
            }
        }

        // PQ 에 담아주기
        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)->n2.total - n1.total);
        for(String g : hmap.keySet()){
            pq.add(hmap.get(g));
        }

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            int end = cur.pq.size() >= 2 ? 2 : 1;
            for(int i=0; i<end; i++) result.add(cur.pq.poll().idx);
        }

    }

    public int[] solution(String[] genres, int[] plays) {

        simulation(genres, plays);

        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) answer[i] = result.get(i);

        return answer;
    }
}