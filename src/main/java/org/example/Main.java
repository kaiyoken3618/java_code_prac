package org.example;

import org.example.entity.LocationObj;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Integer[] arr = {-1,-2,3,-2,1,4};

        Optional<Integer[]> result = Optional.ofNullable(twoSum(arr, 1));
        Optional<Integer> sum = Optional.ofNullable(maxSumSubarray(arr,3));

        String strArr = "abcdefadj";
        String p = "()";
        Optional<HashMap<Character,Integer>> optionalHashMap = Optional.ofNullable(countChar(strArr));
        Optional<Integer> maxLength = Optional.ofNullable(uniqueSubString(strArr));

        List<LocationObj> locationObjs = new ArrayList<>();
        locationObjs.add(new LocationObj(1.2,3.2));
        locationObjs.add(new LocationObj(2.2,4.2));
        locationObjs.add(new LocationObj(0.1,0.05));

        LocationObj userLocation = new LocationObj(1.7,3.5);
        nearestDriver(locationObjs,userLocation);

        System.out.println("valid parenthesis: " + validParenthesis(p));
        System.out.println("actual valid parenthesis: " + concurrentValidParenthesis(p));

        if(result.isPresent()){
            System.out.println(Arrays.stream(result.get()).toList());
        }
        if(sum.isPresent()){
            System.out.println(sum.get());
        }
        if(optionalHashMap.isPresent()){
            System.out.println(optionalHashMap.get());
        }
        if(maxLength.isPresent()){
            System.out.println(maxLength.get());
        }

    }

    //2 sum problem
    private static Integer[] twoSum(Integer[] arr, Integer target){

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i< arr.length ; i++){
            int complement = target - arr[i];
            if(map.containsKey(complement)){
                return new Integer[]{map.get(complement),i};
//                return values instead of index
//                return new Integer[]{complement,arr[i]};

            }
            else{
                map.put(arr[i],i);
            }
        }

        return null;
    }

    //max subarray using sliding window
    private static Integer maxSumSubarray(Integer[] arr, Integer num){
        Integer prevMaxSum=0;
        Integer newMaxSum=0;
        for(int i=0; i< num; i++){
            prevMaxSum+=arr[i];
        }
        newMaxSum = prevMaxSum;
        for(int i=num ; i< arr.length; i++){
            newMaxSum+= arr[i] - arr[i-num];
            Math.max(prevMaxSum,newMaxSum);
        }

        return newMaxSum;
    }

    //occurance of each character
    private static HashMap<Character,Integer> countChar(String s){

        HashMap<Character,Integer> unique = new HashMap<>();

        for(int i =0 ; i<s.length(); i++) {
            if(unique.containsKey(s.charAt(i))){
                unique.put(s.charAt(i),unique.get(s.charAt(i))+1);
                continue;
            }
            unique.put(s.charAt(i),1);
        }

        return unique;
    }

    //occurance of each character
    private static Integer uniqueSubString(String s){

        HashSet<Character> unique = new HashSet<>();
        Integer maxLength= 0;
        Integer left= 0;

        for(int right=0; right<s.length(); right++) {
            while(unique.contains(s.charAt(right))){
                unique.remove(s.charAt(left));
                left++;
            }
            unique.add(s.charAt(right));
            maxLength = Math.max(maxLength,right-left+1);
        }
        return maxLength;
    }

    private static void nearestDriver(List<LocationObj> locationObjs, LocationObj locationObj){
        Double min = Double.MAX_VALUE;
        LocationObj result = new LocationObj();
        for (LocationObj locationObj1 : locationObjs) {
            Double currentMin = haversine(locationObj1.getLatitude(), locationObj1.getLatitude(), locationObj.getLatitude(), locationObj.getLongitude());
            if (currentMin < min) {
                min = currentMin;
                result= locationObj1;
            }
        }
        System.out.println("nearest locaton lat: " + result.getLatitude() + " & lon: "  + result.getLongitude());
    }

    // Haversine formula to calculate the distance between two points on the Earth's surface
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }

    private static Boolean validParenthesis(String s){
        Boolean flag = false;
        if(s.length() <1){
            return true;
        }
        char[] pattern = {'(',')','[',']','{','}'};
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        for(int i =0; i< s.length(); i++){
            for(int j=0; j < pattern.length; j++){
                if(s.charAt(i) == pattern[j]){
                    if(map.containsKey(s.charAt(i))){
                        map.put(pattern[j],map.get(pattern[j]) + 1);
                    }else{
                        map.put(pattern[j],1);
                    }
                    break;
                }
            }
        }
        System.out.println("Key: "+ "value");

        map.keySet().stream().forEach(key -> {
            System.out.println(key.toString() +"  : " + map.get(key).toString());
        });
        HashMap<String,Boolean> res = new HashMap<>();
        if((map.containsKey('(') && map.containsKey(')')) && ((map.get('(') == map.get(')')))){
            res.put("first bracket", true);
        } else if ((!map.containsKey('(') && !map.containsKey(')'))) {
            res.put("first bracket", true);
        } else {
            res.put("first bracket", false);

        }
        if((map.containsKey('{') && map.containsKey('}')) && ((map.get('{') == map.get('}')))){
            res.put("second bracket", true);
        } else if ((!map.containsKey('{') && !map.containsKey('}'))) {
            res.put("second bracket", true);
        } else {
            res.put("second bracket", false);
        }
        if((map.containsKey('[') && map.containsKey(']')) && ((map.get('[') == map.get(']')))){
            res.put("third bracket", true);
        } else if ((!map.containsKey('[') && !map.containsKey(']'))) {
            res.put("third bracket", true);
        } else {
            res.put("third bracket", false);

        }
        System.out.println("result");

        System.out.println("Key: "+ "value");

        res.keySet().stream().forEach(key -> {
            System.out.println(key +"  : " + res.get(key));
        });
        flag = res.values().stream().allMatch(x -> x);

        return flag;
    }

    private static Boolean concurrentValidParenthesis(String s){
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            else{
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if(c == ')' && open!= '('){
                    return false;
                }
                if(c == '}' && open!= '{'){
                    return false;
                }
                if(c == ']' && open!= '['){
                    return false;
                }
            }
        }
        return stack.empty();
    }
}



