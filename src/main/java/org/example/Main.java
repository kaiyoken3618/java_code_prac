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

        Optional<HashMap<Character,Integer>> optionalHashMap = Optional.ofNullable(countChar(strArr));
        Optional<Integer> maxLength = Optional.ofNullable(uniqueSubString(strArr));

        List<LocationObj> locationObjs = new ArrayList<>();
        locationObjs.add(new LocationObj(1.2,3.2));
        locationObjs.add(new LocationObj(2.2,4.2));
        locationObjs.add(new LocationObj(0.1,0.05));

        LocationObj userLocation = new LocationObj(1.7,3.5);
        nearestDriver(locationObjs,userLocation);


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
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }
}



