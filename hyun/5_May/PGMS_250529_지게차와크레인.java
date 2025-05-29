package bfs;



import java.util.*;


public class PGMS_250529_지게차와크레인 {
    int N,M;
    char[][] map;
    ArrayList<Node>[] crain;

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void pringMap(char[][] map){
        System.out.println("--------------------------");
        for(int i=0; i<=N; i++){
            for(int j=0; j<=M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void zigae(char c){
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+2][M+2];
        Queue<Node> erase = new ArrayDeque<>();

        q.add(new Node(0,0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            for(int k=0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N+2 || ny < 0 || ny >= M+2 || visited[nx][ny] ) continue;
                if(map[nx][ny] == '#'){
                    q.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                }
                else if(map[nx][ny] == c){
                    erase.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }

        for(Node cur : erase) map[cur.x][cur.y] = '#';
    }

    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        map = new char[N+2][M+2];
        crain = new ArrayList[30];
        for(int i=0; i<30; i++) crain[i] = new ArrayList<>();

        for(int i=0; i<N+2; i++){
            for(int j=0; j<M+2; j++){
                if(i==0 || i== N+1 || j==0 || j==M+1) {
                    map[i][j] = '#';
                    continue;
                }
                map[i][j] = storage[i-1].charAt(j-1);
                crain[map[i][j] - 'A'].add(new Node(i,j));
            }
        }

        for(String s : requests){
            if(s.length() == 1){ // 지게차
                zigae(s.charAt(0));
            }
            else{ // 크레인
                for(Node cur : crain[s.charAt(0) - 'A']){
                    map[cur.x][cur.y] = '#';
                }
            }
            //pringMap(map);
        }

        // 세어주기
        int answer = 0;
        for(int i=1; i<=N; i++)
            for(int j=1; j<=M;j++)
                if(map[i][j] != '#') answer++;

        return answer;
    }
}
