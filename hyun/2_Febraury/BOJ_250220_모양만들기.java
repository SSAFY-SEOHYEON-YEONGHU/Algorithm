package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250220_모양만들기 {
    static int N,M;
    static int[][] input;
    static int[] group;
    static ArrayList<Integer>[][] groupNum;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static void findGroup(int x, int y, int cnt, boolean[][] visited){
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(x,y));
        visited[x][y] = true;
        int su = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();
            su++;

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                if(input[nx][ny] == 0) {
                    groupNum[nx][ny].add(cnt);
                    continue;
                }

                q.add(new Node(nx,ny));
                visited[nx][ny] = true;
            }
        }

        group[cnt] = su;
    }
    static void simulation(){

        boolean[][] visited = new boolean[N][M];
        int groupCnt = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(input[i][j] == 0 || visited[i][j]) continue;
                findGroup(i,j,groupCnt++, visited);
            }
        }


        // 그룹 합치기
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int sum = 0;
                Set<Integer> dup = new HashSet<>();
                for(int g : groupNum[i][j]){
                    if(dup.contains(g)) continue;
                    dup.add(g);
                    sum += group[g];
                }
                answer = Math.max(answer, sum + 1);
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][M];
        group = new int[N*M + 1];
        groupNum = new ArrayList[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                groupNum[i][j] = new ArrayList<>();
            }
        }

        //
        simulation();
    }
}
