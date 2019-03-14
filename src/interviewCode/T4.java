package interviewCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class T4 {
    public static void solve1() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] chars = str.toCharArray();
        int K = chars.length / 4;
        char[] spaces = new char[K - 1];

        for (int i = 0; i < K - 1; i++) // 中间的空白
            spaces[i] = ' ';
        String spaceStr = new String(spaces);

        for (int i = 0; i < K + 1; i++) {
            if (i == 0) {
                System.out.println(str.substring(0, K + 1)); // 第一行
            } else if (i > 0 && i < K) { // 中间的
                System.out.println(chars[4 * K - i] + spaceStr + chars[K + i]);
            } else {
                // 最后一行
                System.out.println(new StringBuilder(str.substring(2 * K, 3 * K + 1)).reverse().toString());
            }
        }
    }

    public static void solve2(){
        Scanner scanner = new Scanner(System.in);
        String numStr = scanner.nextLine();
        char[] nums = numStr.toCharArray();
        int count = 0;
        for(int i = 0;i < nums.length-1;i++){
            int leftCount = halfCount(nums,0,i);
            if(leftCount == 0) continue;//提高效率；左段不存在；右段就不用统计了
            int rightCount = halfCount(nums,i + 1,nums.length-1);
            count += leftCount*rightCount;
        }
        System.out.println(count);
    }

    public static int halfCount(char[] nums,int from ,int end){
        if(from == end) return 1;//一位数肯定就一种
        if(nums[from] == '0'&& nums[end] == '0') return 0;//前后都有0，肯定不合法
        if(nums[from] == '0'|| nums[end] == '0') return 1;//以0开头只能为0.xxx;以0结尾只能为整数
        return end-from+1;//如345,：345,3.45,34.5
    }

    public static void solve3(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();//N个用户
        int target = scanner.nextInt();//目标用户（要找朋友的人）

        //设置存储用户和对应的朋友列表的map
        HashMap<Integer, ArrayList<Integer>> totalMap = new HashMap<>();

        scanner.nextLine();//处理换行进入循环输入，如果不添加，会提示报错 ：java.lang.NumberFormatException: For input string: ""
        for(int i = 0;i < N;i++ ) {
            String tempNumStr = scanner.nextLine();
            ArrayList<Integer> tempNumList = new ArrayList<>();//存储各个用户对应的朋友列表
            for(String str : tempNumStr.split(" ")) {
                //将得到的"1 2 3"切割，做成一个字符串数组
                tempNumList.add(Integer.parseInt(str));
            }
            //升级下map：升级规则如下
            //如果当前输入，就是用户自己，那么将他的所有朋友放进map中
            //如果当前的输入的不是用户自己，而是别的用户，但是他们的朋友列表如果包含了目标用户，则不放tempList

            //也即，只要不包含了目标用户的，输入用户他的朋友列表都放进来，那些才是目标用户有可能潜在的朋友
            if (target == i || !tempNumList.contains(target)) {
                totalMap.put(i, tempNumList);
            }
        }
        //获得目标用户的他原先的朋友列表，以便能在后面的比对，别人的不包含目标用户的朋友列表
        ArrayList<Integer> targetFriends = totalMap.get(target);
        System.out.println(findPossibleFriend(target,totalMap,targetFriends));
        scanner.close();
    }

    private static int findPossibleFriend(int target, HashMap<Integer, ArrayList<Integer>> totalMap,
                                          ArrayList<Integer> targetFriends) {
        //判断用户的可能朋友，要找类似朋友最多的那个，设定三个指标。
        int maxSameFriends = 0; //表示最多共同朋友数

        int index = 0; //确定目标用户可能朋友的索引

        //遍历map有三种方法（使用keySet，使用entrySet，使用迭代器），这里使用entrySet
        for(Map.Entry<Integer,ArrayList<Integer>> entry : totalMap.entrySet()) {
            ArrayList<Integer> tempFriends = entry.getValue();
            int tempCount = 0;//临时变量，表示每个用户与目标用户的相同朋友个数

            //如果entry的一个用户不是target，则进行比对
            if (entry.getKey() != target) {
                for(int i = 0 ;i < tempFriends.size();i++) {
                    //比对相似用户，如果有一个一致则maxSameFriends++
                    if (targetFriends.contains(tempFriends.get(i))) {
                        tempCount++;
                    }
                }
            }

            //更新maxSameFriends与index
            if(tempCount > maxSameFriends ) {
                maxSameFriends =tempCount;
                index = entry.getKey();
            }

            //假设这个时候有多个人的共同朋友数是一样的,则只取前面最开始朋友数相同，但是索引下标最小的那个人
            if (maxSameFriends == tempCount && entry.getKey() < index) {
                index = entry.getKey();
            }
        }
        return index;
    }



}

