package december.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14567_선수과목 {

    static class Subject {
        int prev;   // 선수과목 번호
        int number;

        public Subject(int prev, int number) {
            this.prev = prev;
            this.number = number;
        }
    }

    static int N, M;
    static List<List<Subject>> subjectList; // 과목 번호에 해당하는 인덱스의 리스트에, 해당 과목을 선수과목으로 하는 과목들 저장
    static int[] prevCounts;    // 각 과목들의 선수과목이 얼마나 남았는 지 저장하는 배열

    public static void main(String[] args) throws IOException {
        init();
        calcTerms();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        subjectList = new ArrayList<>();
        for (int i = 0; i <= N; i++) subjectList.add(new ArrayList<>());
        prevCounts = new int[N+1];

        int prev, number;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            prev = Integer.parseInt(st.nextToken());
            number = Integer.parseInt(st.nextToken());
            subjectList.get(prev).add(new Subject(prev, number));   // prev를 선수과목으로 하므로, prev의 인덱스에 해당하는 리스트에 해당 과목 저장
            prevCounts[number]++;   // 해당 과목의 필요한 선수과목 개수 +1
        }
    }

    static void calcTerms() {
        Queue<Subject> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) if (prevCounts[i] == 0) q.offer(new Subject(-1, i));   // 제일 처음 선수과목이 아무것도 없는 과목들 큐에 삽입

        int term = 1;   // 학기 카운트하는 변수
        int[] whichTerm = new int[N+1]; // 각 과목별로 몇학기에 들을 수 있는 지 저장할 배열
        int qSize;  // 해당 학기에 들을 수 있는 과목들의 개수
        while (!q.isEmpty()) {
            qSize = q.size();
            Subject now;
            for (int i = 0; i < qSize; i++) {   // 해당 학기에 들을 수 있는 과목들만 큐에서 뽑아서 처리함
                now = q.poll();
                whichTerm[now.number] = term;

                List<Subject> nextSubjectList = subjectList.get(now.number);
                for (Subject s : nextSubjectList) {
                    prevCounts[s.number]--;
                    if (prevCounts[s.number] != 0) continue;    // 아직 선수과목 다 못채웠으면 넘어감
                    q.offer(s); // 선수과목 다 채운 과목은 다음학기에 수강 가능
                }
            }
            term++; // 학기 카운트 + 1
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(whichTerm[i]).append(" ");
        System.out.println(sb);
    }

}
