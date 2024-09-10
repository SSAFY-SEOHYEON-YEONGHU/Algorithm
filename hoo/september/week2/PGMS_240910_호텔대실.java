package september.week2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PGMS_240910_호텔대실 {

    public int solution(String[][] book_time) {
        PriorityQueue<int[]> intBookTimePq = init(book_time);
        int answer = calcRoomCount(intBookTimePq);

        return answer;
    }

    PriorityQueue<int[]> init(String[][] book_time) {
        PriorityQueue<int[]> intBookTimePq = new PriorityQueue<>(new Comparator<int[]>() {  // 대실 시작 시간 기준 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] intBookTime;
        for (int i = 0; i < book_time.length; i++) {
            intBookTime = new int[2];
            intBookTime[0] = calcStringTimeToIntTime(book_time[i][0]);
            intBookTime[1] = calcStringTimeToIntTime(book_time[i][1]);
            intBookTimePq.offer(intBookTime);
        }

        return intBookTimePq;
    }

    int calcStringTimeToIntTime(String time) {
        int intTime = 0;
        intTime += Integer.parseInt(time.substring(0, 2)) * 60;
        intTime += Integer.parseInt(time.substring(3, 5));

        return intTime;
    }

    int calcRoomCount(PriorityQueue<int[]> intBookTimePq) {
        int roomCount = 0;
        PriorityQueue<int[]> usingRoomPq = new PriorityQueue<>(new Comparator<int[]>() {  // 대실 종료 시간 기준 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        usingRoomPq.offer(intBookTimePq.poll());   // 맨 처음 고객은 바로 대실 처리
        roomCount++;    // 대실한 방 개수 하나 추가

        int[] nowBook;
        while (!intBookTimePq.isEmpty()) {
            nowBook = intBookTimePq.poll(); // 현재 대실을 하려는 고객
            if (usingRoomPq.peek()[1]+10 <= nowBook[0]) usingRoomPq.poll();    // 현재 사용 중인 방 중 가장 종료 시간이 빠른 방이 대실 가능한 상태면, 기존에 쓰던 방 빼고 대실 처리
            else roomCount++;    // 아니라면 기존에 쓰던 방 그대로 두고, 방 하나 추가
            usingRoomPq.offer(nowBook);
        }

        return roomCount;
    }

}
