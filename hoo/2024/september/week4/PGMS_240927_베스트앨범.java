package september.week4;

import java.util.*;

public class PGMS_240927_베스트앨범 {

    class Song implements Comparable<Song> {
        String genre;   // 노래의 장르
        int plays;  // 재생 횟수
        int genrePlays; // 장르의 총 재생 횟수
        int number; // 고유 번호

        public Song(String genre, int plays, int genrePlays, int number) {
            this.genre = genre;
            this.plays = plays;
            this.genrePlays = genrePlays;
            this.number = number;
        }

        @Override
        public int compareTo(Song s) {
            if (this.genrePlays == s.genrePlays) {  // 장르의 재생 횟수가 같다면
                if (this.plays == s.plays) return this.number - s.number;   // 그 와중에 노래의 재생횟수가 같다면 고유번호 오름차순 정렬
                else return s.plays - this.plays;   // 아니라면 노래 재생횟수 내림차순 정렬
            }

            return s.genrePlays - this.genrePlays;  // 장르 재생 횟수순 내림차순 정렬
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreMap = makeGenreMap(genres, plays);
        int[] answer = sortSongs(genres, plays, genreMap);

        return answer;
    }

    Map<String, Integer> makeGenreMap(String[] genres, int[] plays) {
        Map<String, Integer> genreMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if (!genreMap.containsKey(genres[i])) genreMap.put(genres[i], plays[i]);    // 맵에 장르를 처음 넣을 때
            else genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]);
        }

        return genreMap;
    }

    int[] sortSongs(String[] genres, int[] plays, Map<String, Integer> genreMap) {
        Map<String, Integer> bestAlbumCountMap = new HashMap<>();  // 베스트 앨범에 들어간 장르별 노래 카운트
        PriorityQueue<Song> pq = new PriorityQueue<>();
        for (int i = 0; i < genres.length; i++) {
            if (!bestAlbumCountMap.containsKey(genres[i])) bestAlbumCountMap.put(genres[i], 0);   // 베스트 앨범에 들어간 장르별 노래 카운트 0으로 초기화
            pq.offer(new Song(genres[i], plays[i], genreMap.get(genres[i]), i));
        }

        List<Integer> bestAlbumSongList = new ArrayList<>();    // 베스트 앨범에 넣을 노래 번호를 저장할 리스트
        Song nowSong;
        while (!pq.isEmpty()) {
            nowSong = pq.poll();
            if (bestAlbumCountMap.get(nowSong.genre) == 2) continue;   // 이미 해당 장르의 노래를 두 곡 수록했다면 건너 뜀
            bestAlbumSongList.add(nowSong.number);
            bestAlbumCountMap.put(nowSong.genre, bestAlbumCountMap.get(nowSong.genre) + 1);
        }

        int[] answer = new int[bestAlbumSongList.size()];   // 정답 배열에 담기
        for (int i = 0; i < bestAlbumSongList.size(); i++) {
            answer[i] = bestAlbumSongList.get(i);
        }

        return answer;
    }

}
