package dfs;

public class PGMS_43162_네트워크 {

    public void dfs(int num, boolean[] visited, int n, int[][] computers){
        visited[num] = true;

        for(int i=0; i<n; i++){
            if(num == i || visited[i] || computers[num][i] == 0) continue;
            dfs(i,visited,n,computers);
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            dfs(i,visited,n,computers);
            answer++;
        }

        return answer;
    }
}