import java.util.*;

class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = calcLatestTime(n, t, m, timetable);

        return answer;
    }

    String calcLatestTime(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable); // 크루들 도착시간 시간 순 정렬
        int[] integerTimeTable = new int[timetable.length]; // 분의 총 합으로 변환된 시간을 저장하는 배열
        for (int i = 0; i < timetable.length; i++) integerTimeTable[i] = calcIntegerTime(timetable[i], t, 0);

        int count = 0;  // 버스 순환 횟수
        int crueIndex = 0;  // 태울 크루 인덱스
        Queue<Integer> busQueue = new ArrayDeque<>();    // 버스 탑승 대기 큐
        while (count < n) { // 버스 순환 횟수만큼 반복
            while (crueIndex < timetable.length) {
                if (integerTimeTable[crueIndex] > calcIntegerTime("09:00", t, count)) break;    // 출발 시각보다 늦게 온 크루 차례라면 버스 대기 큐에 넣기 종료
                busQueue.offer(integerTimeTable[crueIndex++]);
            }

            List<Integer> onBusList = new ArrayList<>();    // 현재 타임에 버스를 탄 크루 리스트
            while (!busQueue.isEmpty() && onBusList.size() < m) onBusList.add(busQueue.poll());    // 버스 대기 큐에 있던 크루들 버스에 태움

            if (count == n-1) { // 마지막 버스 타임인 경우
                if (onBusList.size() >= m) {  // 근데 지금 버스에 최대치보다 많은 사람이 타있는 경우, 제일 끝사람보다 1분 빨리 도착해야함
                    return makeStringTime(onBusList.get(onBusList.size()-1)-1);
                } else {    // 그게 아니라면 현재 버스 출발 시각 리턴
                    return makeStringTime(calcIntegerTime("09:00", t, count));
                }
            }

            count++;
        }

        return "";
    }

    int calcIntegerTime(String stringTime, int t, int offset) {    // 문자열 시간을 분의 총 합으로 변환하는 함수, offset은 버스 회차
        int integerTime = 0;
        integerTime += Integer.parseInt(stringTime.substring(0, 2)) * 60;
        integerTime += Integer.parseInt(stringTime.substring(3, 5));
        integerTime += t*offset;

        return integerTime;
    }

    String makeStringTime(int integerTime) {
        String hour = String.valueOf(integerTime/60);
        String minute = String.valueOf(integerTime%60);

        return ( (Integer.parseInt(hour)>=10)? hour : "0"+hour ) + ":" + ( (Integer.parseInt(minute)>=10)? minute : "0"+minute );
    }

}