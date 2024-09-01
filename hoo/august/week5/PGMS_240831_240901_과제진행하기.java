package august.week5;

import java.util.*;

public class PGMS_240831_240901_과제진행하기 {

    class Subject {
        String name;
        int start;
        int playtime;   // 이 시간은 스택에 들어간 Subject의 남은 시간이 0이 됐는 지 체크하는 용도로도 사용 됨

        public Subject(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public String toString() { return this.name + " " + this.start + " " + this.playtime; }
    }

    public String[] solution(String[][] plans) {
        List<Subject> subjectList = init(plans);
        String[] answer = calcSubjectEnd(subjectList);

        return answer;
    }

    List<Subject> init(String[][] plans) {  // 과제를 객체로 만든 후, 시작 시간 기준 정렬한 리스트화
        List<Subject> subjectList = new ArrayList<>();
        for (int i = 0; i < plans.length; i++) {
            subjectList.add(new Subject(plans[i][0], calcToMinute(plans[i][1]), Integer.parseInt(plans[i][2])));
        }

        Collections.sort(subjectList, new Comparator<Subject>() {
            public int compare(Subject s1, Subject s2) {
                return s1.start - s2.start;
            }
        });

        return subjectList;
    }

    int calcToMinute(String time) { // 문자로 주어진 시간을 분으로 치환
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }

    String[] calcSubjectEnd(List<Subject> subjectList) {
        String[] answer = new String[subjectList.size()];
        int answerIndex = 0;

        Stack<Subject> subjectStack = new Stack<>(); // 진행 중인 과제를 담는 스택

        Subject now, next;
        int amongTime;  // 현재 과제 시작 시간과 다음 과제 시작 시간 사이의 시간
        for (int i = 0; i < subjectList.size()-1; i++) {  // 과제들에 대해
            now = subjectList.get(i);
            next = subjectList.get(i+1);
            amongTime = next.start - now.start;

            subjectStack.push(now); // 우선 스택에 현재 과제 삽입
            Subject doing;
            while (!subjectStack.isEmpty()) {   // 스택이 모두 비기 전까지 수행
                doing = subjectStack.pop();
                if (doing.playtime <= amongTime) {
                    answer[answerIndex++] = doing.name; // 다음 과제 시작 전 처리가 가능한 작업이면 정답에 추가
                    amongTime -= doing.playtime;    // 다음 과제에 대해서도 남은 시간으로 처리 가능한 지 판별해야 하므로 수행
                } else {  // 수행 불가면 그냥 진행한 시간만큼 차감하고 스택에 둠
                    subjectStack.push(new Subject(doing.name, doing.start, doing.playtime-amongTime));
                    break;
                }
            }
        }
        answer[answerIndex++] = subjectList.get(subjectList.size()-1).name; // 마지막 과제는 끊기지 않고 수행됨
        while (!subjectStack.isEmpty()) answer[answerIndex++] = subjectStack.pop().name;

        return answer;
    }

}
