import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static Integer findFoo(Map<Integer, Integer> dict, List<Integer> cache) {
        Integer bestIndex = null;
        Integer bestIndexCount = Integer.MAX_VALUE;
        for (Integer key : cache) {
            if (dict.get(key) < bestIndexCount) {
                bestIndex = key;
                bestIndexCount = dict.get(key);
            }
        }
        return bestIndex;
    }


    public static void main(String[] args) throws IOException {
        final String filePath = "src/main/resources/input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String currentLine = reader.readLine();
        String[] nums = currentLine.split(" ");
        int cacheSize = Integer.parseInt(nums[0]);
        Map<Integer, Integer> dict = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < Integer.parseInt(nums[1]); i++) {
            currentLine = reader.readLine();
            Integer currentInt = Integer.parseInt(currentLine);
            queue.add(currentInt);
            if (!dict.containsKey(currentInt)) {
                dict.put(currentInt, 1);
            } else {
                dict.put(currentInt, dict.get(currentInt) + 1);
            }
        }
        int count = 0;
        List<Integer> cache = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer currElem = queue.poll();
            dict.put(currElem, dict.get(currElem) - 1);
            if (!cache.contains(currElem)) {
                count++;
                if (cache.size() >= cacheSize) {
                    cache.remove(findFoo(dict, cache));
                }
                cache.add(currElem);
            }
        }
        System.out.println(count);
    }
}
