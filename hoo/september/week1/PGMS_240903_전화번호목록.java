package september.week1;

import java.util.HashMap;

public class PGMS_240903_전화번호목록 {

    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> phoneBookCache = initCache(phone_book);
        boolean answer = findCase(phone_book, phoneBookCache);

        return answer;
    }

    HashMap initCache(String[] phone_book) {
        HashMap<String, Integer> phoneBookCache = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) phoneBookCache.put(phone_book[i], 0);   // 전화번호를 키값으로 하여 hash map에 저장

        return phoneBookCache;
    }

    boolean findCase(String[] phone_book, HashMap<String, Integer> phoneBookCache) {
        String phoneNumber;
        for (int i = phone_book.length-1; i >= 0; i--) {    // 길이 긴 전화번호부터 map에 접두어 들어있는 지 체크
            phoneNumber = phone_book[i];
            for (int j = 0; j < phoneNumber.length()-1; j++) {  // 전체 길이까지는 아니고, 한 자리 짧은 번호까지만 따져야 접두어임
                if (phoneBookCache.containsKey(phoneNumber.substring(0, j+1))) return false;    // 접두인 경우 찾으면 false 리턴
            }
        }

        return true;    // 중간에 못찾은 경우 true 반환
    }

}
