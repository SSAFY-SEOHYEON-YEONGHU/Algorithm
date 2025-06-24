package implementation;

import java.util.*;

public class PGMS_250624_다른사람의풀이 {
    class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int simulation(char[][] map){
        ArrayList<Node> axios = new ArrayList<>();
        // 응시자 찾기
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(map[i][j] == 'P') axios.add(new Node(i,j));
            }
        }

        // 확인
        if(axios.size() < 2) return 1;
        for(int i=0; i<axios.size(); i++){
            Node cur = axios.get(i);
            for(int j=i+1; j<axios.size(); j++){
                if(i==j) continue;

                Node nxt = axios.get(j);
                int dx = Math.abs(cur.x - nxt.x);
                int dy = Math.abs(cur.y - nxt.y);
                if(dx + dy <= 2){
                    // 검사
                    if(dx == 1 && dy == 1){
                        int minX = Math.min(cur.x, nxt.x);
                        int maxX = Math.max(cur.x, nxt.x);

                        int minY = Math.min(cur.y, nxt.y);
                        int maxY = Math.max(cur.y, nxt.y);

                        int cnt = 0;
                        for(int a = minX; a<= maxX; a++){
                            for(int b = minY; b<=maxY; b++){
                                if(map[a][b] == 'X') cnt++;
                            }
                        }
                        if(cnt != 2) return 0;
                    }else{
                        if(cur.x == nxt.x && map[cur.x][(cur.y+nxt.y)/2] != 'X') return 0;

                        if(cur.y == nxt.y && map[(cur.x+nxt.x)/2][cur.y] != 'X') return 0;

                    }
                }
            }
        }

        return 1;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        int tc = places.length;
        for(int t=0; t<tc; t++){
            char[][] map = new char[5][5];
            for(int i=0; i<5; i++) map[i] = places[t][i].toCharArray();

            answer[t] = simulation(map);

        }


        return answer;
    }
}