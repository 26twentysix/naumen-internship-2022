import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {


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
    }
}
