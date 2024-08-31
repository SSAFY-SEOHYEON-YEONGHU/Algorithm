package dp;

public class PGMS_42898_등굣길 {
    int[] dx = {0,1};
    int[] dy = {1,0};

    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m+1][n+1];
        for(int i=0; i<puddles.length;i++) map[puddles[i][0]][puddles[i][1]] = -1;

        map[1][1] = 1;
        for(int i =1 ;i <= m ; i++){
            for(int j=1; j<=n; j++){
                if(map[i][j] == -1) continue;
                for(int k=0; k<2;k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 1 || nx > m || ny < 1 || ny > n || map[nx][ny] == -1) continue;
                    map[nx][ny] = (map[nx][ny] + map[i][j]) % 1000000007;
                }
            }
        }

        return map[m][n];
    }
}