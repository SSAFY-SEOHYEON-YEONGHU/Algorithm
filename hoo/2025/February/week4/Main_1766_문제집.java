package twentytwentyfive.february.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1766_문제집 {

    static class Problem implements Comparable<Problem> {
        int prev; // 이전에 풀어야하는 문제 번호
        int now; // 이 문제의 번호

        public Problem(int prev, int now) {
            this.prev = prev;
            this.now = now;
        }

        @Override
        public int compareTo(Problem p) { // 문제 번호 순(쉬운 문제 순) 오름차순 정렬
            return this.now - p.now;
        }

        @Override
        public String toString() { return this.prev + " " + this.now; }
    }

    static int N, M;
    static List<List<Problem>> problemList; // 문제간 연결돼있는 정보를 저장하는 리스트
    static int[] prevCount; // 해당 문제를 풀기 전 해결해야하는 문제 개수(진입차수) 저장한 배열

    public static void main(String[] args) throws IOException {
        init();
        solveProblems();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        problemList = new ArrayList<>();
        for (int i = 0; i <= N; i++) problemList.add(new ArrayList<>());
        prevCount = new int[N+1];
        int prev, now;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            prev = Integer.parseInt(st.nextToken());
            now = Integer.parseInt(st.nextToken());
            problemList.get(prev).add(new Problem(prev, now));
            prevCount[now]++;
        }
    }

    static void solveProblems() {
//        for (List<Problem> pl : problemList) for (Problem p : pl) System.out.println(p);
//        System.out.println(Arrays.toString(prevCount));
        PriorityQueue<Problem> pq = new PriorityQueue<>(); // 문제는 쉬운 순서대로 풀어야 하므로 우선순위 큐 사용
        for (int i = 1; i <= N; i++) if (prevCount[i] == 0) pq.offer(new Problem(0, i)); // 이전에 수행해야할 문제가 없는 문제는 이전 수행 문제 번호를 0으로 해서 pq에 삽입

        StringBuilder sb = new StringBuilder();
        Problem now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            sb.append(now.now).append(" ");

            List<Problem> nextProblemList = problemList.get(now.now);
            for (Problem p : nextProblemList) {
                prevCount[p.now]--;
                if (prevCount[p.now] == 0) pq.offer(p);
            }
        }

        System.out.println(sb);
    }

}
