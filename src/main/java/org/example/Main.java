package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {-1,-2,3,-2,1,4};

        Optional<Integer[]> result = Optional.ofNullable(twoSum(arr, 1));
        Optional<Integer> sum = Optional.ofNullable(maxSumSubarray(arr,3));

        String strArr = "abcdefadj";

        Optional<HashMap<Character,Integer>> optionalHashMap = Optional.ofNullable(countChar(strArr));
        Optional<Integer> maxLength = Optional.ofNullable(uniqueSubString(strArr));
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
}



