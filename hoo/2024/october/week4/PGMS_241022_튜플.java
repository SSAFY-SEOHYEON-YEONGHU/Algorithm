package twentytwentyfour.october.week4;

import java.util.*;

public class PGMS_241022_튜플 {

    public int[] solution(String s) {
        int[] answer = makeTuple(s);

        return answer;
    }

    int[] makeTuple(String s) { // s길이가 100만이므로 대략적으로 가장 긴 집합의 원소 개수가 1000개임을 유념
        PriorityQueue<List<Integer>> setPq = makeSetPq(s);

        List<Integer> tupleList = new ArrayList<>();
        Map<Integer, Boolean> checkMap = new HashMap<>();   // 튜플에 저장된 숫자인 지 판별하기 위해 사용하는 Map
        List<Integer> now;
        while (!setPq.isEmpty()) {  // 집합이 길이순으로 저장된 pq를 돌며 각 순서에서 추가된 원소를 튜플에 저장
            now = setPq.poll();

            int nowNumber;
            for (int i = 0; i < now.size(); i++) {
                nowNumber = now.get(i);
                if (checkMap.containsKey(nowNumber)) continue;
                tupleList.add(nowNumber);
                checkMap.put(nowNumber, true);
            }
        }

        int[] tuple = new int[tupleList.size()];
        for (int i = 0; i < tupleList.size(); i++) tuple[i] = tupleList.get(i);

        return tuple;
    }

    PriorityQueue<List<Integer>> makeSetPq(String s) {
        PriorityQueue<List<Integer>> setPq = new PriorityQueue<>(new Comparator<List<Integer>>() {    // 길이 순으로 정렬할 priority queue
            @Override
            public int compare(List<Integer> l1, List<Integer> l2) {
                return l1.size() - l2.size();
            }
        });
        List<Integer> tempList = new ArrayList<>();
        String tempNumber = "";
        for (int i = 1; i < s.length()-1; i++) {    // 집합 크기 순으로 정렬하기 위해 각 집합을 pq에 넣는 로직
            if (s.charAt(i) == '}') {   // 닫는 중괄호 나오면 pq에 추가
                tempList.add(Integer.parseInt(tempNumber));
                setPq.offer(tempList);
                tempNumber = "";
                tempList = new ArrayList<>();
            } else if(s.charAt(i) == ',' && !tempNumber.equals("")) { // 쉼표라면 tempList에 추가
                tempList.add(Integer.parseInt(tempNumber));
                tempNumber = "";
            } else if (s.charAt(i) != '{' && s.charAt(i) != '}' && s.charAt(i) != ',') { // 숫자라면 tempNumber에 추가
                tempNumber += String.valueOf(s.charAt(i));
            }
        }

        return setPq;
    }

}
