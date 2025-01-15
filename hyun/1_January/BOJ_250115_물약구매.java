package topological_sort;

import java.io.*;
import java.util.*;
public class BOJ_250115_물약구매 {
    static int N;
    static int[] price;
    static ArrayList<Node>[] adj;

    static int answer = Integer.MAX_VALUE;
    static int[] order;

    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        int[] tmpPrice = new int[N+1];
        tmpPrice = price.clone();
        int answer = 0;

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            if(tmpPrice[cur] < 1) answer += 1;
            else answer += tmpPrice[cur];

            for(Node nxt : adj[cur]){
                if(visited[nxt.x]) continue;

                q.add(nxt.x);
                visited[nxt.x] = true;
                tmpPrice[nxt.x] -= nxt.y;
            }
        }

        for (int i = 1; i <= N ; i++) {
            if(!visited[i]) {
                if(tmpPrice[i] < 1) answer += 1;
                else answer += tmpPrice[i];
            }
        }

        return answer;
    }
    static void simulation(){ // bfs
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= N ; i++) {
            answer = Math.min(answer, bfs(i));
        }
        System.out.println(answer);
    }

    static void dfs(int start, boolean[] visited, int[] tmpPrice){
        for(Node nxt : adj[start]){
            if(visited[nxt.x]) continue;

            visited[nxt.x] = true;
            tmpPrice[nxt.x] -= nxt.y;
            dfs(nxt.x, visited, tmpPrice);

        }
    }


    static void setOrder(int cnt, boolean[] visited){
        if(cnt > N){
            simulation2();
            return;
        }
        for (int i = 1; i <= N ; i++) {
            if(visited[i]) continue;
            order[cnt] = i;
            visited[i] = true;
            setOrder(cnt+1, visited);
            visited[i] = false;
        }
    }


    static void simulation2(){ // dfs
        int[] tmpPrice = price.clone();
        boolean[] visited = new boolean[N+1];
        for (int i = 1; i <= N ; i++) {
            int item = order[i];
            visited[item] = true;
            for(Node nxt : adj[item]) if(!visited[nxt.x]) tmpPrice[nxt.x] -= nxt.y;
        }
        int sum = 0;
        for (int i = 1; i <= N ; i++) {
            if(tmpPrice[i] < 1 ) sum += 1;
            else sum += tmpPrice[i];
        }

        answer = Math.min(answer, sum);

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        price = new int[N+1];
        adj = new ArrayList[N+1];
        order = new int[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i+1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                adj[i].add(new Node(a,d));
            }
        }

        //
        setOrder(1, new boolean[N+1]);
        System.out.println(answer);
    }
}
