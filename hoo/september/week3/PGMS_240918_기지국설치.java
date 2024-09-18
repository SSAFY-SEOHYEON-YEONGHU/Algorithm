package september.week3;

import java.util.ArrayList;
import java.util.List;

public class PGMS_240918_기지국설치 {

    public int solution(int n, int[] stations, int w) {
        int answer = construct5G(n, stations, w);

        return answer;
    }

    int construct5G(int n, int[] stations, int w) {
        int answer = 0;
        List<Integer> neededLength = calcNeededLength(n, stations, w);
        int nowNeed;
        for (int i = 0; i < neededLength.size(); i++) { // 기지국이 커버하지 못하는 범위들에 대해서
            nowNeed = neededLength.get(i);

            answer += (nowNeed%(2*w+1) == 0)? nowNeed/(2*w+1):nowNeed/(2*w+1)+1;    // 그 범위들의 길이를 커버할 수 있는 기지국의 최소 개수를 정답에 더해줌
        }

        return answer;
    }

    List<Integer> calcNeededLength(int n, int[] stations, int w) {
        List<Integer> neededLength = new ArrayList<>(); // 초기에 기지국들이 커버하는 범위를 제외한, 전파를 전달해야 하는 곳의 길이를 저장할 리스트
        if (stations[0]-(w+1) >= 1) neededLength.add(stations[0]-(w+1));    // 첫 기지국 전까지 전파가 안닿는 길이 삽입
        for (int i = 1; i < stations.length; i++) {
            if ((stations[i-1]+(w+1)) <= (stations[i]-(w+1))) neededLength.add((stations[i]-w) - (stations[i-1]+(w+1))); // 이전 기지국과 지금 기지국의 범위가 커버하지 못하는 범위가 있다면 리스트에 넣어줌
        }

        if (stations[stations.length-1]+(w+1) <= n) neededLength.add(n - (stations[stations.length-1]+w)); // 마지막 기지국에서 마지막 아파트까지 범위 중 전파가 안닿는 길이 삽입

        return neededLength;
    }

}
