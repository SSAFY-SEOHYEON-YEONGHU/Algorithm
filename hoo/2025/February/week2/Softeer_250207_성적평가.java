import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        calcRank(br, N);
    }

    static void calcRank(BufferedReader br, int N) throws Exception {
        PriorityQueue<int[]> scores = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) { // 점수(인덱스 0) 기준 내림차순 정렬
                return i2[0] - i1[0];
            }
        });
        int[] totalScore = new int[N];  // 참가자들의 총 점수

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] participantRanks = new int[N];  // 한 대회에 대한 참가자들의 등수
        for (int i = 0; i < 3; i++) { // 3개의 대회에 대해
            st = new StringTokenizer(br.readLine());
            int participantNumber = 0; // 참가자 번호
            for (int j = 0; j < N; j++) scores.offer(new int[] {Integer.parseInt(st.nextToken()), participantNumber++});  // 각 참가자들 정보 pq에 삽입

            sb = getParticipantRank(sb, N, scores, participantRanks, totalScore);
        }

        for (int i = 0; i < N; i++) scores.offer(new int[] {totalScore[i], i}); // 총 점수 기반 등수도 구해주기
        sb = getParticipantRank(sb, N, scores, participantRanks, totalScore);

        System.out.println(sb);
    }

    static StringBuilder getParticipantRank(StringBuilder sb, int N, PriorityQueue<int[]> scores, int[] participantRanks, int[] totalScore) {
        int rank = 0;
        int prevScore = 0;  // 이전 참가자의 점수
        int sameRankCount = 1;  // 동점자 수
        int[] participant; // 각 참가자의 점수와 번호를 저장하는 배열
        for (int j = 0; j < N; j++) { // 각 참가자들의 순위 저장
            participant = scores.poll();
            if (participant[0] == prevScore) {  // 동점인 경우
                participantRanks[participant[1]] = rank;
                sameRankCount++;
            } else {
                rank += sameRankCount;
                participantRanks[participant[1]] = rank;
                prevScore = participant[0];  // 이전 점수 갱신
                sameRankCount = 1;  // 동점자 수 초기화
            }
            totalScore[participant[1]] += participant[0];  // 총 점수에도 합해줌
        }
        for (int j = 0; j < N; j++) sb.append(participantRanks[j]).append(" ");
        sb.append("\n");

        return sb;
    }

}
