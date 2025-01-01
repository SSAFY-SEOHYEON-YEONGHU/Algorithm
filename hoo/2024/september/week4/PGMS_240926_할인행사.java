package september.week4;

import java.util.HashMap;
import java.util.Map;

public class PGMS_240926_할인행사 {

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = findDate(want, number, discount);

        return answer;
    }

    int findDate(String[] want, int[] number, String[] discount) {
        int answer = 0; // 만족하는 총 일수
        Map<String, Integer> wantMap = makeMap(want, number, 'w');
        Map<String, Integer> havingMap = makeMap(want, number, 'h'); // 일정 날짜 범위에서 정현이가 사게 되는 물품들의 개수를 저장하는 map

        int tempCount = 0;  // 한 날을 시작으로 열흘 간의 물품 중 정현이의 요구를 충족하는 물품의 종류 수
        String stuff;
        for (int i = 0; i < 10; i++) { // 첫째날을 기준으로 열흘에 대한 카운트 수행
            stuff = discount[i];
            if (!wantMap.containsKey(stuff)) continue;  // 정현이가 사려는 품목이 아니면 건너뜀
            havingMap.put(stuff, havingMap.get(stuff) + 1); // 해당 품목의 정현이가 가지고 있는 개수 +1
            if (havingMap.get(stuff) == wantMap.get(stuff)) tempCount++;    // 해당 품목 개수가 정현이가 사려는 개수를 충족했으면 충족 count +1
        }
        if (tempCount == want.length) answer++;  // 1일째에 만족했다면 만족 일수 +1
        System.out.println(tempCount);
        System.out.println(havingMap);

        String yesterdayStuff;
        for (int i = 0; i < discount.length - 10; i++) {    // 크기가 10인 슬라이딩 윈도우 방식으로 체크
            yesterdayStuff = discount[i]; // 전날 할인했던 상품
            stuff = discount[i+10]; // 새로 추가되는 상품
            if (wantMap.containsKey(yesterdayStuff)) {
                havingMap.put(yesterdayStuff, havingMap.get(yesterdayStuff)-1);
                if (havingMap.get(yesterdayStuff) == wantMap.get(yesterdayStuff)-1) tempCount--;   // 날짜를 옮김으로써 전날 할인했던 품목이 개수를 충족못하는 품목으로 변했다면 count -1
            }
            if (wantMap.containsKey(stuff)) {
                havingMap.put(stuff, havingMap.get(stuff)+1);
                if (havingMap.get(stuff) == wantMap.get(stuff)) tempCount++;   // 날짜를 옮김으로써 새로 구입하는 품목이 개수를 충족하는 품목으로 변했다면 count +1
            }
            if (tempCount == want.length) answer++;    // 원하는 품목 충족했으면 만족 일수 +1
            // System.out.println(tempCount);
            // System.out.println(havingMap);
        }

        return answer;
    }

    Map<String, Integer> makeMap(String[] want, int[] number, char mode) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) { // 맵에 정현이가 원하는 물품들을 key: 이름, value: 0으로 초기화
            if (mode == 'w') map.put(want[i], number[i]);   // 모드가 wantMap을 만들 때라면, value로 원하는 개수 할당
            else if (mode == 'h') map.put(want[i], 0);   // 모드가 havingMap을 만들 때라면, value로 0 할당
        }

        return map;
    }

}
