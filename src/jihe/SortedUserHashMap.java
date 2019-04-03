package jihe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SortedUserHashMap {
    public static HashMap<Integer, User> sortHashMap(HashMap<Integer, User> map){
        Set<Map.Entry<Integer, User>> entrySet = map.entrySet();
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().age - o1.getValue().age; // 从大到小
            }
        });
        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<Integer, User>();
        for(Map.Entry<Integer, User> entry: list){
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }


        return linkedHashMap;

    }
    public static void main(String[] args){
        Map<Integer, Integer> m = new TreeMap<>();
        m.put(1,null);
    }
}

class User{
    public int age;
    public String name;
    public User(String name, int age){
        this.age = age;
        this.name = name;
    }
}
