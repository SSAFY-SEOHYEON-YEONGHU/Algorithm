package twentytwentyfour.october.week3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PGMS_241017_주차요금계산 {

    public int[] solution(int[] fees, String[] records) {
        int[] answer = calcParkingFees(fees, records);

        return answer;
    }

    int[] calcParkingFees(int[] fees, String[] records) {   // fees의 0: 기본 시간, 1: 기본 요금, 2: 단위 시간, 3: 단위 요금
        Map<String, Integer> inTimeMap = new HashMap<>();   // 차량이 입차한 시간 저장할 맵
        Map<String, Integer> totalTimeMap = new HashMap<>(); // 차량의 총 주차시간을 저장할 맵

        String[] record;    // 입, 출차 기록을 배열 형태로 변환한 것
        int convertedTime;   // 분의 총합으로 변환한 시간
        for (int i = 0; i < records.length; i++) {  // 차량 입출입 기록에 대해 수행
            record = records[i].split(" ");
            switch (record[2]) {
                case "IN":  // 입차인 경우
                    inTimeMap.put(record[1], convertTimeToMinuteSum(record[0]));
                    break;
                case "OUT": // 출차인 경우
                    totalTimeMap = doCarOut(inTimeMap, totalTimeMap, fees, record[1], record[0]);
                    inTimeMap.put(record[1], -1);    // 차량이 출차했음을 기록
                    break;
            }
        }

        PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {   // 차번호순 사전순 정렬을 위함
            @Override
            public int compare(String[] s1, String[] s2) {
                return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
            }
        });
        for (String car : inTimeMap.keySet()) { // 입차 후 출차 기록이 없는 차에 대해 요금 계산
            if (inTimeMap.get(car) != -1) totalTimeMap = doCarOut(inTimeMap, totalTimeMap, fees, car, "23:59");;  // 출차하고 요금 정산이 안된 차량 23:59 출차 기준으로 총 주차시간 갱신

            pq.offer(new String[] {car, String.valueOf(calcOneCarFee(fees, totalTimeMap.get(car)))});
        }
        int[] answer = new int[pq.size()];
        int index = 0;
        while (!pq.isEmpty()) answer[index++] = Integer.parseInt(pq.poll()[1]);

        return answer;
    }

    Map<String, Integer> doCarOut(Map<String, Integer> inTimeMap, Map<String, Integer> totalTimeMap, int[] fees, String car, String outTime) {   // 차량 출차 처리
        if (!totalTimeMap.containsKey(car)) totalTimeMap.put(car, convertTimeToMinuteSum(outTime) - inTimeMap.get(car)); // 해당 차량의 요금이 계산된 적 없는 경우의 계산
        else totalTimeMap.put(car, totalTimeMap.get(car) + convertTimeToMinuteSum(outTime) - inTimeMap.get(car));

        return totalTimeMap;
    }

    int convertTimeToMinuteSum(String time) {
        int convertedTime = 0;
        convertedTime += Integer.parseInt(time.substring(0, 2)) * 60;
        convertedTime += Integer.parseInt(time.substring(3, 5));

        return convertedTime;
    }

    int calcOneCarFee(int[] fees, int totalTime) {   // 차량이 출차할 때 요금 계산
        if (totalTime <= fees[0]) return fees[1];
        int fee = fees[1] + ((int) Math.ceil((totalTime-fees[0])*1.0/fees[2]*1.0) * fees[3]);   // ceil 함수의 파라미터는 정수형으로 입력될 시 내부 계산 결과가 정수형으로 나오므로, 이미 버린 숫자의 올림을 하게 됨. 이 점을 주의할 것

        return fee;
    }

}
