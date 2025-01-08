package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250108_성곽 {
    static int N,M;
    static boolean[][][] input;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static void getDirection(int x, int y, int num){

        int dir = 0;
        while(num!=0){
            if(num % 2 == 1) input[x][y][dir] = true;
            dir++;
            num /= 2;
        }
    }
    static ArrayList<Node> bfs(boolean[][] visited, int x, int y){
        Queue<Node> q = new ArrayDeque<>();
        ArrayList<Node> axios = new ArrayList<>();

        q.add(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            axios.add(new Node(cur.x,cur.y));

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                if(input[cur.x][cur.y][k]) continue;

                q.add(new Node(nx,ny));
                visited[nx][ny] = true;
            }
        }

        return axios;
    }

    static void markGroupNum(int[][] groupNum, int num, ArrayList<Node> axios){
        for(Node cur : axios){
            groupNum[cur.x][cur.y] = num;
        }
    }

    static int getTwoGroup(int[][] groupNum, int[] groupCnt){
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if(input[i][j][k] && groupNum[i][j] != groupNum[nx][ny]){
                        answer = Math.max(answer, groupCnt[groupNum[i][j]] + groupCnt[groupNum[nx][ny]]);
                    }
                }
            }
        }
        return answer;
    }
    static void simulation(){
        boolean[][] visited = new boolean[N][M];
        int cnt = 0; // 방의 갯수
        int largestRoom = 0; // 방 넓이
        int[][] groupNum = new int[N][M]; // 두개의 그룹 합치기
        int[] groupCnt = new int[N*M+10];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) continue;
                ArrayList<Node> axios = bfs(visited, i, j);

                cnt++;
                largestRoom = Math.max(largestRoom, axios.size());
                markGroupNum(groupNum, cnt, axios);
                groupCnt[cnt] = axios.size();

            }
        }

        // 두개의 그룹 합쳐야함
        int largetTwoGroup = getTwoGroup(groupNum, groupCnt);

        System.out.println(cnt);
        System.out.println(largestRoom);
        System.out.println(largetTwoGroup);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        input = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int su = Integer.parseInt(st.nextToken());
                getDirection(i,j,su);
            }
        }
        //
        simulation();

    }
}
