package com.example.testappliction.questions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.testappliction.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.sort;


/**
 *  Programers 기출 액티비티
 *
 * */
public class QuestionProgramersActivity extends Activity {
    private String TAG = "QuestionProgramersActivity";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTest ();
    }

    ///////////////////////////////////////////////////

    private void mainTest(){

    }

    ////////////////////////////////////////
    /**
     * XX 시에는 네트워크 망이 있습니다.
     * 어느 날 악천후로 인하여 몇몇 PC 사이의 네트워크 연결이 끊어지게 되었습니다.
     * 끊어진 네트워크 연결을 복구하여 모든 PC 간 통신이 가능하게 하려 합니다.
     * 이때 필요한 최소한의 비용을 구해주세요.
     *
     * a번 PC부터 b번 PC까지 도달하는 경로가 존재할 때, a번 PC와 b번 PC가 통신이 가능하다고 합니다.
     * 전체 PC 수 n,
     * 현재 네트워크 망 배열 network,
     * 복구하려는 네트워크 배열 repair 가 주어졌을 때,
     * 복구하는데 드는 최소 비용을 return 하도록 solution 함수를 작성해주세요.
     *
     * <제한사항>
     * n은 3 이상 100,000 이하인 정수입니다.
     * network와 repair의 길이는 1 이상 300,000 이하입니다.
     * network의 원소는 [a, b] 형태이며, 이는 a번 PC와 b번 PC가 연결되어 있다는 뜻입니다.
     * a와 b는 1 이상 n이하인 정수입니다.
     * repair의 원소는 [a, b, cost] 형태이며, 이는 a번 PC와 b번 PC의 네트워크 연결을 복구하는데 cost만큼 비용이 든다는 뜻입니다.
     * a와 b는 1 이상 n이하인 정수입니다.
     * cost는 1 이상 100,000 이하인 정수입니다.
     * 모든 PC 간 통신이 불가능한 경우 -1을 return 해주세요.
     *
     * Ex >
     * n	network								repair						return
     * 6	[[1, 2], [3, 5], [4, 2], [5, 6]]	[[3, 2, 10], [5, 4, 15]]	10
     * 4	[[1, 2]]							[[2, 3, 10], [3, 1, 12]]	-1
     *
     * 입출력 예 설명
     * 예제 #1
     * 2번과 3번 PC를 연결하는 네트워크를 복구하는 경우 모든 PC 간 통신이 가능해지며 최소 비용이 듭니다.
     *
     * 예제 #2
     * 어떤 네트워크를 복구해도 4번 PC와 통신이 불가능합니다.
     *
     * */
    private void testNetwork(int n, int[][] network, int[][] repair) {

    }


    ////////////////////////////////////////
    /**
     * r x c 크기의 지도가 주어졌습니다.
     * 지도에는 땅과 바다의 정보가 표시되어 있습니다.
     * 1 x 1 크기의 각 칸에는 1 또는 0으로 표시되어 있고,
     * 1은 땅에 해당하는 공간, 0은 바다에 해당하는 공간입니다.
     * 이때, 땅이 상하좌우로 인접해 있을 때 인접한 땅들을 하나의 섬이라고 합니다.
     *
     * 예를 들어, 4 x 5 크기의 지도가 주어졌을 때,
     * 섬이 2개가 있다고 판단합니다.
     *
     * 이와 같은 지도가 주어졌을 때, 우리는 각 섬의 둘레를 모두 구하여 합하려고 합니다.
     * 각 칸의 모서리의 길이가 1을 나타냅니다.
     * 즉, 위의 지도에서 섬 A의 둘레는 16이 되고, 섬 B의 둘레는 8이 되어, 모두 합하면 24가 됩니다.
     *
     * 지도가 매개변수 maps로 주어졌을 때,
     * 섬들의 둘레를 모두 합하여 return 하는 solution 함수를 완성해 주세요.
     * 위의 예시에서는 24를 return 하면 됩니다.
     *
     * 제한사항>
     * 지도 maps는 2차원 배열로 주어지며, 0과 1 이외에 다른 숫자는 주어지지 않습니다.
     * r, c : 100 이하의 자연수
     *
     * Ex>
     * maps												    answer
     * [[0,0,1,0,0],[0,1,1,0,1],[0,0,1,0,1],[1,1,1,0,1]]	24
     * [[1,0,1,1],[0,0,1,1],[1,1,0,1],[1,1,0,0]]			22
     *
     * 입출력 예 #2
     * 지도에 주어진 섬의 개수는 3개이고, 섬 A의 둘레는 4가 되고,
     * 섬 B의 둘레는 10이 되고, 섬 C의 둘레는 8이 되어 모두 합하면 22가 됩니다.
     * */
    private void testMap(int[][] maps){
        int result = 0;
        for(int i = 0; i < maps.length; i++){
            for(int j = 0; j< maps[i].length; j++ ){
                if(maps[i][j] == 1){
                    result = result + getMapSize(maps,i, j );
                }

            }
        }
        Log.d(TAG, "[testMap]" + result);
    }

    /**
     * 각 아이템의 둘레 길이 리턴
     * */
    private int getMapSize(int[][] maps, int i, int j){

        int result = 0;

        if(i == 0 || i == maps.length-1){
            Log.d(TAG, "[getMapSize] - 1");
            result ++;
        }
        if(j == 0 || j == maps[i].length-1){
            Log.d(TAG, "[getMapSize] - 2");
            result ++;
        }
        if(i < maps.length-1 && maps[i+1][j] == 0){
            Log.d(TAG, "[getMapSize] - 3");
            result ++;
        }
        if(i >0 && maps[i-1][j] == 0){
            Log.d(TAG, "[getMapSize] - 3");
            result ++;
        }

        if(j < maps[i].length-1 && maps[i][j+1] == 0 ){
            Log.d(TAG, "[getMapSize] - 4");
            result ++;
        }

        if(j > 0 && maps[i][j-1] == 0 ){
            Log.d(TAG, "[getMapSize] - 4");
            result ++;
        }

        Log.d(TAG, "[getMapSize]" + i +" / "+j + "/ result : "+result);
        return result;
    }
    ////////////////////////////////////////
    /**
     * 임의의 자연수 n으로 나누었을 때,
     * 그 몫과 나머지가 같아지는 자연수들을 모두 더한 값을 반환하도록 solution 함수를 완성하세요.
     *
     * 제한사항>
     * n은 100,000 이하의 자연수입니다.
     *
     * Ex >
     * n	result
     * 2	3
     * 3	12
     *
     * 입출력 예 #1
     * n이 2일 때,
     * 3을 2로 나누면 몫이 1이고, 나머지가 1
     * 나머지가 2 이상일 수 없으므로 3을 반환합니다.
     *
     * 입출력 예 #2
     * n이 3일 때,
     *
     * 4를 3으로 나누면 몫이 1이고, 나머지가 1
     * 8을 3으로 나누면 몫이 2이고, 나머지가 2
     * 나머지가 3 이상일 수 없으므로 4와 8을 더한 12를 반환합니다.
     * */
    private void testDevide(long n){
        Log.d(TAG, "[testDevide]");
        long result = 0;
        int max = 10;

        int cnt = 0;
        while (cnt > (cnt /n)) {
            int temp = (int)(cnt % n);
            int temp2 = (int)(cnt / n);

            Log.d(TAG, "[testDevide] while : "+cnt
                    + " / temp  : "+temp
                    +"  / temp2 : "+temp2);

            if(temp2 == temp && temp < n){
                result = result + cnt;
            }
            cnt ++;
        }


//        for(int count = 0; count<max; count++){
//            int temp = (int)(count % n);
//            int temp2 = (int)(count / n);
//
//            Log.d(TAG, "[testDevide] count : "+count
//                    + " / temp  : "+temp
//                    +"  / temp2 : "+temp2);
//
//            if(n <= temp2){
//                break;
//            }
//            if(temp != 0 && temp2 == temp && temp < n){
//                result = result + count;
//            }
//        }
        Log.d(TAG, "[testDevide] : "+result);
    }

    private void testDevide2(long n){
        Log.d(TAG, "[testDevide]");
        long result = 0;
        int max = Integer.MAX_VALUE;
        for(int count = 0; count<max; count++){
            int temp = (int)(count % n);
            int temp2 = (int)(count / n);

            Log.d(TAG, "[testDevide] count : "+count
                    + " / temp  : "+temp
                    +"  / temp2 : "+temp2);

            if(n <= temp2){
                break;
            }
            if(temp != 0 && temp2 == temp && temp < n){
                result = result + count;
            }
        }
        Log.d(TAG, "[testDevide] : "+result);
    }


////////////////////////////////////////
    /**
     * ○○피자집은 손님이 피자를 한 번 살 때마다 쿠폰 1장을 줍니다.
     * 이 쿠폰을 3장 모으면 다음 주문 때 스파게티를 무료로 받습니다.
     * (쿠폰을 2개 갖고 있을 때 피자를 주문하면 새 쿠폰을 받아 쿠폰이 3개이지만, 이 상황에서는 쿠폰을 쓸 수 없습니다)
     * 무료로 스파게티를 받았을 때는 피자를 사도 쿠폰을 주지 않으며, 손님은 쿠폰을 4개 이상 모을 수 없습니다.
     *
     * 예를 들어, [1,3,3,2,4,3,3,2,4,2,2,4] 순으로 주문했다고 가정해봅시다.
     * 괄호 안의 숫자는 손님 고유의 ID를 나타내며, 왼쪽부터 순서대로 주문한 것을 나타냅니다.
     * 이를 표로 나타내어 첫번째 행을 손님 고유의 ID,
     * 두번째 행을 해당 손님의 쿠폰 개수라 하면 다음과 같습니다.
     *
     *
     * 이 경우엔 3번 손님, 2번 손님은 스파게티를 무료로 받습니다.
     * 4번 손님은 쿠폰 3장을 모았지만, 이 쿠폰은 다음번 피자를 주문할 때 쓸 수 있습니다.
     *
     * 피자를 산 손님들의 ID가 매개변수 people로 주어졌을 때,
     * 스파게티를 무료로 받은 손님들의 ID를 return 하는 solution 함수를 완성해 주세요.
     * 예를 들어, 위의 예시에서는 3번 손님, 2번 손님 순으로 스파게티를 무료로 받았기 때문에 답은 [3,2]입니다.
     * 단, 어떠한 손님도 스파게티를 무료로 받지 못한 경우에는 1차원 배열에 -1을 넣어서 return 해주세요.
     *
     * 제한사항>
     * people의 길이 : 100,000 이하의 자연수
     * 손님 ID : 1,000,000,000 이하의 자연수
     *
     * ex >
     * people						answer
     * [1,3,3,2,4,3,3,2,4,2,2,4]	[3,2]
     * [1,1,3,3,3,3,1,3,3,3,3,2]	[3,3]
     * [1,2,3,4]	[-1]
     *
     * 입출력 예 #2
     * 3번 손님이 두 번 연속으로 스파게티를 받았습니다.
     *
     * 입출력 예 #3
     * 어떠한 손님도 스파게티를 무료로 받지 못한 경우입니다.
     * */
    private void testPizza(int[] people){
        Log.d(TAG, "[testPizza]");
        Map<Integer, Integer> personMap = new HashMap<>();
        ArrayList<Integer> tempArray= new ArrayList<Integer>();
        int[] result = null;

        for(int person : people){
            if(personMap.containsKey(person)){
                int count = personMap.get(person) +1;
                // 쿠폰 받을시
                if(count == 3){
                    tempArray.add(person);
                    count = 0;
                }
                personMap.put(person,count);
            }else{
                personMap.put(person,0);
            }
        }


        //for(int count = 0; count<result){}
//        int[]result = tempArray.toArray(new Integer[]);

        if(tempArray.size() == 0){
            result = new int[1];
            result[0] = -1;
            Log.d(TAG, "[testPizza] item : "+result[0]);
        }else{
            result = new int[tempArray.size()];
            int count = 0;
            for(int item : tempArray){
                Log.d(TAG, "[testPizza] item : "+item);
                result [count] =  item;
                count++;
            }
        }


    }
////////////////////////////////////////
    /**
     * 주어진 행렬중에서 2칸마다 얼마나 성을 지을수 있는지 테스트
     * */
    private void testCastle(int[] A) {
        int result = 0;
        for (int c = 1; c < A.length; c++) {
            if (A[c] - A[c - 1] == 0) {
                result++;
            }
        }
        Log.d(TAG, "[testCastle] : "+result);
    }

////////////////////////////////////////
    /**
     * 주어진 행렬중 얼마나 많은 도시를 다녀오나 테스트
     * */
    Map<Integer, Boolean> mLocationsMap = null;
    private void testVacation2(int[] A) {
        int result = Integer.MAX_VALUE;
        mLocationsMap = new HashMap<>();

        for (int item : A) {
            if (!mLocationsMap.containsKey(item)) {
                mLocationsMap.put(item, false);
            }
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                if (!isVisited(A[j])) {
                    mLocationsMap.put(A[j], true);
                    if(isAllVisited()){
                        if (j - i + 1 < result) {
                            result = j - i + 1;
                        }
                        initLocationMap();
                        break;
                    }
                }
            }
        }
        Log.d(TAG, "[testVacation2] result : "+result);
    }


    /**
     * 해당 로케이션에 방문여부 체크
     * */
    private boolean isVisited(int location) {
        if (mLocationsMap.containsKey(location)) {
            return mLocationsMap.get(location);
        }
        return false;
    }

    /**
     * 모든 지역에 다녀왔는지 체크
     * */
    private boolean isAllVisited() {
        for (Map.Entry<Integer, Boolean> item : mLocationsMap.entrySet()) {
            if (!item.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 지역 map 초기화
     * */
    private void initLocationMap() {
        for (Map.Entry<Integer, Boolean> curr : mLocationsMap.entrySet()) {
            curr.setValue(false);
        }
    }

    ////////////////////////////////////////
    /**
     * 주어진 행렬중 얼마나 많은 도시를 다녀오나 테스트
     * */
    private void testVacation(int[] A){
        Solution obj = new Solution();
        Log.d(TAG, "[testVacation] Result : "+obj.solution(A));
    }

    public class Solution {
        private Map<Integer, Boolean> allDiffLocations;

        public Solution() {
            allDiffLocations = new HashMap<>();
        }

        public int solution(int[] A) {
            excludeDifferentLocations(A);
            int shortestRoad = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A.length; j++) {
                    if (!isLocationVisited(A[j])) {
                        markLocationAsVisited(A[j]);
                        if (isAllLocationsVisited()) {
                            if (j - i + 1 < shortestRoad) {
                                shortestRoad = j - i + 1;
                            }
                            rollbackAllVisitingMarks();
                            break;
                        }
                    }
                }
            }
            return shortestRoad;
        }



        private void excludeDifferentLocations(int[] locations) {
            for (int curr : locations) {
                if (!allDiffLocations.containsKey(curr)) {
                    allDiffLocations.put(curr, false);
                }
            }
        }

        private void markLocationAsVisited(int location) {
            allDiffLocations.put(location, true);
        }

        private boolean isLocationVisited(int location) {
            if (allDiffLocations.containsKey(location)) {
                return allDiffLocations.get(location);
            }
            return false;
        }

        private boolean isAllLocationsVisited() {
            for (Map.Entry<Integer, Boolean> curr : allDiffLocations.entrySet()) {
                if (!curr.getValue()) {
                    return false;
                }
            }
            return true;
        }

        private void rollbackAllVisitingMarks() {
            for (Map.Entry<Integer, Boolean> curr : allDiffLocations.entrySet()) {
                curr.setValue(false);
            }
        }
    }

    ////////////////////////////////////////
    private int testDistance(int[] A) {
        if (A.length == 1) {
            return -2;
        }
        Arrays.sort(A);

        long minDistance = Long.MAX_VALUE;
        for (int i = 1; i < A.length; i++) {
            long distance = (long) A[i] - A[i - 1];
            if(distance < minDistance){
                minDistance = distance;
            }
        }
        int result = minDistance > 10000 ? -1 : (int)minDistance;
        Log.d(TAG, "[testDistance] result : "+result);
        return result;
    }
///////////////////////////////////////////
    /**
     * 0/1 로 이루어진 코인중 최소한으로 쥐집어야 하는 테스크
     * */
    private void testCoin(int[] A){
        int coninMaxValue = 0;
        for (int i = 0; i < A.length; i++) {
            int[] copy = Arrays.copyOf(A, A.length);
            copy[i] = (copy[i] + 1) % 2;
            coninMaxValue = Math.max(coninMaxValue, coinPair(copy));
        }
        Log.d(TAG, "[testCoin] : "+(A.length - coninMaxValue));

    }

    private int coinPair(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                result = result + 1;
            }
        }
        return result;
    }
/////////////////////////////////////////////////////
    /**
     * 주어진 START/ END TIME ITEM 중에
     * 중복된 시간이 없는지 체크
     * */
    public void testDuplicateTime(){
        Log.d(TAG, "[testDuplicateTime]");
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

            MovieNight niight = new MovieNight();
            ArrayList<Movie> movies = new ArrayList<Movie>();
            movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
            movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
            movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

//            movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 22:30")));
//            movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
//            movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

            Log.d(TAG, "[testDuplicateTime] : "+niight.canViewAll(movies));
        }catch (Exception e){
            Log.d(TAG, "[testDuplicateTime] Exception");
        }
    }

    public class MovieNight {
        public  Boolean canViewAll(Collection<Movie> movies) {
            boolean isOverlap = false;
            List<Movie> movie_list = (List<Movie>) movies;

            for (int i = 0; i < movie_list.size(); i++) {
                for (int j = i + 1; j < movie_list.size(); j++) {
                    Date startA = movie_list.get(i).getStart();
                    Date endA   = movie_list.get(i).getEnd();
                    Date startB = movie_list.get(j).getStart();
                    Date endB   = movie_list.get(j).getEnd();

                    // A StartTime이 B EndTime 보다 전일경우
                    boolean isStartABeforeEndB = (startA.compareTo(endB)) < 0;
                    // A EndTime이 B StartTime이 보다 후일경우
                    boolean isEndAAfterStartB = (endA.compareTo(startB)) > 0;

                    boolean isCurrentPairOverlap = false;
                    isCurrentPairOverlap = isStartABeforeEndB && isEndAAfterStartB;

                    Log.d(TAG, "[MovieNight] isStartABeforeEndB : "+isStartABeforeEndB
                            + "/ isEndAAfterStartB : "+isEndAAfterStartB
                            +" / isCurrentPairOverlap : "+isCurrentPairOverlap);
                    if (isCurrentPairOverlap) {
                        isOverlap = true;
                    }
                }
            }
            return !isOverlap;
        }
    }

    class Movie {
        private Date start, end;

        public Movie(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public Date getStart() {
            return this.start;
        }

        public Date getEnd() {
            return this.end;
        }
    }

/////////////////////////////////////////////////////
    /**
     * 입력 값의 3자리수씩 짤라 아래와 같이 더한수
     * 총 숫자값을 리턴하는 메소드
     * r: 4
     * w: 2
     * x : 1
     * - : 0
     *
     * ex > "rwxr-x-w-" - > 752 값이 리턴
     * rwx : 7
     * r-x : 5
     * -w- : 2
     * */
    private int testAddValue(String input){
        Log.d(TAG, "[testAddValue]");
        int result = 0;
        int count = 0;
        char[] arrayInput = input.toCharArray();
        ArrayList <Integer>retultArray = new ArrayList<Integer>();

        for(int i = 0; i< arrayInput.length; i++){
            count = count + getValue(arrayInput[i]);
            if(i%3 == 2){
                retultArray.add(count);
                count = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int item : retultArray){
            sb.append(item);
        }
        result = Integer.parseInt(sb.toString());
        return result;
    }

    private int getValue(char intput){
        if(intput == 'r' ){
            return 4;
        }if(intput == 'w' ){
            return 2;
        }if(intput == 'x' ){
            return 1;
        }if(intput == '-' ){
            return 0;
        }
        return 0;
    }
/////////////////////////////////////////////////////

    /**
     * 12시간 단위로 표시되는 시계가 있습니다.
     * 오전일 경우 AM , 오후일 경우 PM으로 표시되며, 시간은 시:분:초로 표시됩니다.
     * 예를 들어, 오전 11시 27분 35초일 경우 AM 11:27:35 로 표시가 되며,
     * 오후 8시 20분 4초일 경우 PM 08:20:04 로 표시가 됩니다.
     *
     * 이제부터, 우리는 시계에 표시된 시간에서 N초 후의 시간을 구하려고 합니다.
     * 단, N초 후의 시간 표시를 12시간 단위에서 24시간 단위 표시로 변경하려고 합니다.
     * 24시간 단위로 표시되므로 오전과 오후를 나타내는 AM, PM과 같은 문자열은 표시되지 않으며, 시:분:초만 표시됩니다.
     *
     * 예를 들어,
     * 12시간 단위 시계로 표시된 PM 01:00:00 에서 10초 후의 시간을 24시간 단위 표시로 변경하면 13:00:10 로 표시하게 됩니다.
     * 12시간 단위 시계로 표시된 PM 11:59:59 에서 1초 후의 시간을 24시간 단위 표시로 변경하면 00:00:00 로 표시하게 됩니다.
     * 12시간 단위로 표시된 현재 시각 P와 N이 매개변수로 주어졌을 때, N초 후의 시간 표시를 24시간 단위 표시로 변경하는 solution 함수를 완성해 주세요.
     * 단, 변경된 시각을 return 할 때는 string형으로 return 해주세요.
     *
     * 제한사항
     * 현재 시각 P는 string형으로 주어집니다. 오전과 오후는 각각 문자열 AM, PM으로 표시하고,
     * 시간은 12시간 단위로 표시되며, :를 기준으로 시, 분, 초를 나눠 시:분:초로 표시됩니다.
     *
     * 시, 분, 초는 각각 두자리 숫자로 이루어져 있으며, 24시간 단위 표시로 변환한 결과도 각각 두자리 숫자여야 합니다.
     * 한자리 숫자일 경우, 앞에 0을 붙이면 됩니다.
     *
     * (ex. 오후 5시 5분 5초를 12시간 단위로 표시하면 PM 5:5:5가 아닌 PM 05:05:05로 표시되며, 24시간 단위로 표시하면 20:05:05로 표시됩니다.)
     * AM 12:01:00은 24시간 단위 표시로 00:01:00 입니다.
     * PM 12:01:00은 24시간 단위 표시로 12:01:00 입니다.
     * N은 200,000 이하인 자연수입니다.
     *
     * 입출력 예 >
     * P	            N	    answer
     * PM 01:00:00	    10	    13:00:10
     * PM 11:59:59	    1	    00:00:00
     *
     * 입출력 예 설명
     * 입출력 예 #1, 2
     * 문제의 예시와 같습니다.
     * */
    private String testAddSeconds(String p, int n){
        Log.d(TAG, "[testAddSeconds]");
        String answer = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("aa hh:mm:ss");
        SimpleDateFormat writeFormat = new SimpleDateFormat( "HH:mm:ss");

        try {
            date = sdf.parse(p);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long inputTime = date.getTime()+ (n*1000);
        date.setTime(inputTime);

        if (date != null) {
            answer = writeFormat.format(date);
        }

        Log.d(TAG, "[testAddSeconds] formattedDate : "+answer);
        return answer;
    }
    ///////////////////////////////////////////////////

    /***
     * 스택은 먼저 넣은 자료가 나중에 나오는 형태의 자료구조입니다. 자료를 넣는 것을 '밀어 넣는다' 하여 푸시(push)라고 하고
     * 반대로 넣어둔 자료를 꺼내는 것을 팝(pop)이라고 합니다.
     * 예를 들어 1, 2, 3 순으로 스택에 자료를 넣고(push) 나서 꺼내기 작업(pop)을 하면 3, 2, 1 순으로 나오게 됩니다.
     * 1부터 N까지 정렬된 숫자가 들어있는 배열을 스택을 이용하여 순서를 바꿔 보려고 합니다.
     *
     * 예를 들어, 1을 push하고 바로 pop → 1
     * 2를 push, 3을 push하고, 2번 pop수행 → 3, 2
     * 위와 같이 진행하면 1, 3, 2의 순서로 배열이 변경됩니다.
     * 이렇게 스택을 이용하여 오름차순으로 정렬된 배열을 주어진 배열 arr로 바꿀 수 있는지 확인하는 solution 함수를 완성해 주세요.
     *
     * 제한사항
     * 배열의 크기 N : 100000이하의 자연수
     * 배열 원소의 크기 : 1이상 N이하의 정수(단, 같은 정수가 두 번 나오지 않는다.)
     *
     * 입출력 예 >>>
     * arr			result
     * [1, 3, 2]	true
     * [3, 1, 2]	false
     *
     * 입출력 예 설명
     * 입출력 예 #1
     * 1을 push하고 pop을 하여 1을 출력합니다. 그 다음 2와 3을 push 한 뒤, pop하여 3을 출력합니다.
     * 마지막으로 pop하여 2를 출력할 수 있으므로 true를 반환하면 됩니다.
     *
     * 입출력 예 #2
     * 1과 2를 push 한 다음, 3을 push하고 pop을 하면 3이 출력됩니다.
     * 그 다음으로 1을 출력해야 하지만 2가 존재하여 1을 먼저 pop을 할 수 없으므로 false를 반환하면 됩니다.
     * */
    private boolean testCheckStack(int[] arr){
        boolean answer = true;

        return answer;
    }

    ///////////////////////////////////////////////////
    /**
     * n x n 크기의 행렬의 각 칸에 1부터 n2 까지의 숫자가 지그재그 방향으로 채워져 있습니다.
     * 다음은 n = 5인 경우의 예시입니다.
     *
     * 이때 r 행, c 열의 칸에 어떤 숫자가 적혀있는지 구하려고 합니다.
     * 예를 들어 위 그림에서 r = 3, c = 2인 경우 3행, 2열의 위치에는 9가 들어있습니다.
     * 지그재그 행렬의 크기 n, 행의 위치 r, 열의 위치 c가 매개변수로 주어질 때,
     * n x n 크기의 지그재그 행렬의 r 행 c 열에 들어있는 숫자를 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     * n은 1 이상 10,000,000 이하의 자연수입니다.
     * r과 c는 각각 1 이상 n 이하의 자연수입니다.
     * 정답이 231 - 1보다 커질 수 있음에 주의하세요.
     *
     * 입출력 예
     * n	r	c	result
     * 5	3	2	9
     * 6	5	4	29
     * */
    private long testZigzag(int n, int r, int c) {
        Log.d(TAG, "[testZigzag]");
        long answer = 0;

        int[][] array = new int[n][n];
        int total = n * n;      // 2차원 배열의 전체 칸 수
        int x = 0, y = -1;
        int i, j;               // for 구문을 돌리기 위해 사용될 변수
        int cnt = 1;            // 초기값을 1로..

        int row = n;
        int column = n;

        int dir = 0;
        int dirUp = 1;
        int dirDown = 2;
        dir = dirUp;

        while (cnt <= total) {
            //2차원 배열의 오른쪽 끝에 도달하는 경우
            if (x == column - 1 && cnt <= total) {
                array[++y][x] = cnt++;
                dir = dirDown;
            }

            //2차원 배열의 아래쪽 끝에 도달하는 경우
            if (y == row - 1 && cnt <= total) {
                array[y][++x] = cnt++;
                dir = dirUp;
            }

            //2차원 배열의 위쪽 끝에 도달하는 경우
            if (y == 0 && cnt <= total) {
                array[y][++x] = cnt++;
                dir = dirDown;
            }

            //2차원 배열의 왼쪽 끝에 도달하는 경우
            if (x == 0 && cnt <= total) {
                array[++y][x] = cnt++;
                dir = dirUp;
            }

            //그 외 dir이 UP인 경우}
            if (dir == dirUp && x != column - 1 && y != 0 && cnt <= total) {
                array[--y][++x] = cnt++;
            }
            //그 외 dir이 DOWN인 경우
            if (dir == dirDown && y != row - 1 && x != 0 && cnt <= total) {
                array[++y][--x] = cnt++;
            }
        }

        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                Log.d(TAG, "[testZigzag] array[i][j] : "+array[i][j] +" / i "+i +" / j : "+j);
            }
            Log.d(TAG, "\n");
        }
        answer = array[r-1][c-1];
        return answer;
    }

//    private long testZigzag(int n, int r, int c) {
//        Log.d(TAG, "[testZigzag]");
//        long answer = 0;
//
//        // int n = 5;
//        int[][] array = new int[n][n];
//        // 배열에 입력할 증가값
//        int count = 1;
//        // 배열의 현재 위치
//        int currentX = 0;
//        int currentY = 0;
//
//        // 0, 0 위치를 1로 초기화
//        array[currentX][currentY] = count++;
//
//        while (count <= n * n) {
//            // x가 n보다 작은 동안은 x 증가, 이후에는 y 증가
//            if (currentX + 1 < n) {
//                currentX++;
//            } else {
//                currentY++;
//            }
//
//            array[currentX][currentY] = count++;
//
//            // 우상향으로 이동
//            while (currentX - 1 > -1 && currentY + 1 < n) {
//                array[--currentX][++currentY] = count++;
//            }
//
//            // y가 n 보다 작은 동안은 y 증가, 이후에는 x증가
//            if (currentY + 1 < n) {
//                currentY++;
//            } else {
//                currentX++;
//            }
//
//            array[currentX][currentY] = count++;
//
//            // 좌하향으로 이동
//
//            while (currentY - 1 > -1 && currentX + 1 < n) {
//                array[++currentX][--currentY] = count++;
//            }
//        }
//
//        // 결과 출력
//        for(int i=0; i< array.length; i++){
//            for(int j=0; j<array[i].length; j++){
//                Log.d(TAG, "[testZigzag] array[i][j] : "+array[i][j] +" / i "+i +" / j : "+j);
//            }
//        }
////        for (int[] xx : array) {
////            for (int nn : xx) {
////                System.out.printf("%d ", nn);
////            }
////        }
////        System.out.println();
//        answer = array[2][1];
//        return answer;
//    }

    ///////////////////////////////////////////////////
    /**
     * 다음은 게임 플레이어가 조종하는 유닛들이 2차원 평면 위에 놓여있는 모습을 나타낸 그림입니다.
     * 위 그림에서 각 빨간색 점은 현재 유닛의 위치를 나타냅니다.
     * 플레이어는 특정 영역을 지정하여 여러 개의 유닛을 한꺼번에 선택할 수 있습니다.
     * 위 그림에서 파란색 점은 플레이어가 유닛을 선택하기 위해 지정하는 영역의 시작 지점 s와 끝 지점 e를 나타냅니다.
     * 이때 s와 e를 마주 보는 꼭짓점으로 하며, 각 변이 x축, y축에 평행한 직사각형 모양의 영역 내부에 있는 유닛들이 모두 선택되게 됩니다.
     *
     * 예를 들어 위 그림에서는 (1, 4) 위치에 플레이어가 선택하는 영역의 시작 지점 s가 있으며,(4, 1) 위치에 끝 지점 e가 있습니다.
     * 이때 색칠된 부분이 플레이어가 선택한 영역이 되며, 색칠해진 영역 안에 있는 유닛은 (1, 1), (2, 2), (3, 3) 위치의 3개입니다.
     * (경계선에 위치한 유닛도 선택됩니다)
     *
     * 현재 유닛들의 위치를 담고 있는 배열 location과 플레이어가 선택한 영역의 시작 위치 s, 끝 위치 e가 매개변수로 주어질 때,
     * 선택되는 유닛의 개수를 return 하도록 solution 함수를 완성해 주세요.
     *
     * 제한 사항
     * location은 N(1 ≤ N ≤ 10,000)개의 유닛들 위치를 담고 있는 배열입니다.
     * location의 각 원소는 유닛들이 위치한 좌표(xi, yi)를 나타냅니다.
     * 유닛들이 위치한 좌표 (xi, yi)의 범위 : 0 ≤ xi, yi ≤ 100,000, xi, yi 는 정수
     * 같은 위치에 두 개 이상의 유닛이 있는 경우는 없습니다.
     * s는 플레이어가 선택하는 영역의 시작점 (xs, ys)를, e는 끝점 (xe, ye)를 나타냅니다(0 ≤ xs, ys, xe, ye ≤ 100,000, xs, ys, xe, ye는 정수).
     * s와 e는 xs ≠ xe, ys ≠ ye인 경우만 입력으로 주어집니다.
     *
     * 입출력 예
     * location	s	e	result
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	[1, 4]	[4, 1]	3
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	[3, 4]	[0, 0]	4
     *
     * int[][] location :
     * 입력값 〉	[[0, 3],
     * [1, 1],
     * [1, 5],
     * [2, 2],
     * [3, 3],
     * [4, 0]],
     * [3, 4],
     * [0, 0]
     * 기댓값 〉	4
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	    [1, 4]	[4, 1]	3
     * [[0, 3], [1, 1], [1, 5], [2, 2], [3, 3], [4, 0]]	    [3, 4]	[0, 0]	4
     *
     *
     int[][] location = {
     {0,3},
     {1,1},
     {1,5},
     {2,2},
     {3,3},
     {4,0}
     };
     int[] inputA = {3,4};
     int[] inputB = {0,0};
     int[] inputA = {1,4};
     int[] inputB = {4,1};
     solutionDrag(location, inputA, inputB)
     * */
    public int solutionDrag(int[][] location, int[] s, int[] e) {
        Log.d(TAG, "[solutionDrag] "
                +"\n s : "+s[0] + " / "+s[1]
                +"\n e : "+e[0] + " / "+e[1] );

        int answer = 0;
        int[] arrayX = new int[s.length]; // input X 축 배열
        int[] arrayY = new int[e.length]; // input Y 축 배열

//        arrayX[0] = s[0];
//        arrayX[1] = e[0];
//        arrayY[0] = s[1];
//        arrayY[1] = e[1];
        for(int x = 0; x < arrayX.length; x++){
            arrayX[x] = (x == 0) ? s[0] : e[0];
        }
        for(int y = 0; y < arrayY.length; y++){
            arrayY[y] = (y == 0) ? s[s.length - 1] : e[e.length -1];
        }

        // 오름 차순 정렬
        sort(arrayX);
        sort(arrayY);


        for (int i = 0; i < location.length; i++) {
            int itemX = location[i][0];
            int itemY = location[i][1];

            // x,y 축 비교
            if (arrayX[0] <= itemX && itemX <= arrayX[1]
                    && arrayY[0] <= itemY && itemY <= arrayY[1]) {
                answer++;
            }
        }
        return answer;
    }

}
