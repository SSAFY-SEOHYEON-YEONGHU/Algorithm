package twentytwentyfour.december.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9017_크로스컨트리 {

    static int[] result;
    static Map<Integer, Integer> playerCount;   // 각 팀별 선수 수
    static int lastTeamNumber;  // 마지막 팀의 번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            init(br);
            sb.append(calcWinTeam()).append("\n");
        }
        System.out.println(sb);
    }

    static int[] init(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        result = new int[N];
        playerCount = new HashMap<>();
        lastTeamNumber = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            result[i] = Integer.parseInt(st.nextToken());
            if (!playerCount.containsKey(result[i])) playerCount.put(result[i], 0);
            playerCount.put(result[i], playerCount.get(result[i])+1);
            lastTeamNumber = Math.max(lastTeamNumber, result[i]);
        }

        return result;
    }

    static int calcWinTeam() {
        int score = 1;  // 1점부터 시작
        List<List<Integer>> teamScoreList = new ArrayList<>();
        for (int i = 0; i <= lastTeamNumber; i++) teamScoreList.add(new ArrayList<>());
        for (int i = 0; i < result.length; i++) {
            if (playerCount.get(result[i]) < 6) continue;   // 팀원 수가 6명이 안되는 팀은 건너 뜀

            List<Integer> teamScore = teamScoreList.get(result[i]);
            if (teamScore.size() == 0) teamScore.add(score);
            else teamScore.add(teamScore.get(teamScore.size()-1)+score); // 누적합 형태로 저장
            score++;
        }

        int[] minTeamInfo =  new int[2];    // 가장 점수가 낮은 팀의 정보( {팀 번호, 점수} )
        int scoreIndex = 3; // 비교할 점수의 인덱스
        List<Integer> compareTeamList = new ArrayList<>();    // 비교를 수행할 팀들의 리스트(처음 값은 팀원 수 6명 이상인 팀)
        for (int i = 1; i < teamScoreList.size(); i++) if (playerCount.get(i) >= 6) compareTeamList.add(i);

        while (scoreIndex <= 4) {   // 5번째 선수까지만 비교
            minTeamInfo = new int[] {0, Integer.MAX_VALUE};    // 가장 점수가 낮은 팀의 정보( {팀 번호, 점수} )
            List<Integer> sameList = new ArrayList<>(); // 동점팀 저장할 리스트

            for (Integer compareTeam : compareTeamList) {
                if (minTeamInfo[1] > teamScoreList.get(compareTeam).get(scoreIndex)) {    // 점수 낮은 팀 나올 시 팀 정보 갱신 후 동점팀 없음을 표시
                    minTeamInfo = new int[] {compareTeam, teamScoreList.get(compareTeam).get(scoreIndex)};
                    sameList.clear();   // 동점 팀 리스트 비우고
                    sameList.add(compareTeam);
                } else if (minTeamInfo[1] == teamScoreList.get(compareTeam).get(scoreIndex)) sameList.add(compareTeam);  // 동점 팀 발견되면 최고 점수가 동점임을 표시
            }

            if (sameList.size() == 1) break; // 동점 팀 없으면 반복 종료
            scoreIndex++;  // 동점 팀 존재 시 5번째 선수 비교하러 감
            compareTeamList = sameList; // 동점 팀 리스트를 다음 비교 팀 리스트로
        }

        return minTeamInfo[0];  // 우승 팀 번호 반환
    }

}
