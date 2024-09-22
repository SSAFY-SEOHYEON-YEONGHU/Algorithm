package september.week3;

import java.util.HashSet;
import java.util.Set;

public class PGMS_240920_불량사용자 {

    Set<String> patternMatchSet;

    public int solution(String[] user_id, String[] banned_id) {
        patternMatchSet = new HashSet<>();
        findSamePattern(user_id, banned_id, 0, new boolean[user_id.length]);
        int answer = patternMatchSet.size();

        return answer;
    }

    void findSamePattern(String[] user_id, String[] banned_id, int count, boolean[] isSelected) {
        if (count == banned_id.length) {    // 기저, 유저 아이디 중 banned_id 만큼을 골라서 정렬했을 때
            String patternMatchString = "";
            for (int i = 0; i < isSelected.length; i++) {
                if (isSelected[i]) patternMatchString += user_id[i];
            }
            patternMatchSet.add(patternMatchString);    // 문제에서 순서는 중요하지 않다고 했으므로, 중복되는 것들 걸러주기 위해 Set에 저장

            return;
        }

        String banned = banned_id[count];
        String user;
        boolean isSame;
        for (int i = 0; i < user_id.length; i++) {
            user = user_id[i];
            isSame = true;
            if (user.length() != banned.length() || isSelected[i]) continue; // 지금 매칭시킬 banned_id와 다른 유저 아이디는 탐색하지 않음, 또한 이미 고른 유저는 고려 대상이 아님
            for (int j = 0; j < user.length(); j++) {
                if (banned.charAt(j) == '*') continue;    // *은 와일드카드니까 비교 안해도 됨
                else if (banned.charAt(j) != user.charAt(j)) {  // 같은 id 아니면 다음 id 탐색
                    isSame = false;
                    break;
                }
            }
            if (!isSame) continue;  // 같은 id 아니면 다음 id 탐색
            isSelected[i] = true;
            findSamePattern(user_id, banned_id, count+1, isSelected);
            isSelected[i] = false;
        }
    }

}
