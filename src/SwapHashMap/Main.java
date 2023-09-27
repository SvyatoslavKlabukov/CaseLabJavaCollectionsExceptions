package SwapHashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("a", 1);
        myMap.put("b", 2);
        myMap.put("c", 3);
        myMap.put("d", 3);
        myMap.put("e", 4);
        myMap.put("f", 3);
        myMap.put("g", 2);
        myMap.put("h", 1);

        System.out.println(myMap.toString());

        HashMap<Integer, Set<String>> reversedHashMap = new HashMap<>();
        for (String key : myMap.keySet()){
            Integer value = myMap.get(key);
            Set<String> keySet = new HashSet<>();
            for (String innerKey : myMap.keySet()) {
                if (value.equals(myMap.get(innerKey))){
                    keySet.add(innerKey);
                }
            }
            reversedHashMap.put(value, keySet);
        }

        System.out.println(reversedHashMap.toString());

        HashMap<Integer, String> newMap = new HashMap<>();
        newMap = reverse(myMap);
        System.out.println(newMap.toString());

    }

    //работает для биективных отображений
    public static <K,V> HashMap<V,K> reverse(Map<K,V> map) {
        HashMap<V,K> rev = new HashMap<V, K>();
        for(Map.Entry<K,V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }


}
