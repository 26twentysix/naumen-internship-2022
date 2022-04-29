import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    record Pair(Long left, Integer right) {
    }

    public static void main(String[] args) throws IOException {
        final String filePath = "src/main/resources/input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String currentLine = reader.readLine();
        String[] nums = currentLine.split(" ");
        int cacheSize = Integer.parseInt(nums[0]);
        Map<Long, TreeSet<Integer>> requestsFrequency = new HashMap<>();
        Queue<Long> requestsQueue = new ArrayDeque<>();
        for (int i = 0; i < Integer.parseInt(nums[1]); i++) {
            currentLine = reader.readLine();
            Long currentLong = Long.parseLong(currentLine);
            requestsQueue.add(currentLong);
            if (!requestsFrequency.containsKey(currentLong)) {
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(i);
                requestsFrequency.put(currentLong, ts);
            } else {
                requestsFrequency.get(currentLong).add(i);
            }
        }
        int count = 0;
        SortedSet<Pair> cache = new TreeSet<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.right.compareTo(o2.right);
            }
        });
        for (int i = 0; i < Integer.parseInt(nums[1]); i++) {
            Long currentRequest = requestsQueue.poll();
            var currentRequestNextPositions = requestsFrequency.get(currentRequest);
            Pair currentPair = new Pair(currentRequest, currentRequestNextPositions.first());
            currentRequestNextPositions.remove(currentRequestNextPositions.first());
            if (currentRequestNextPositions.size() == 0) {
                currentRequestNextPositions.add(100001);
            }
            if (cache.contains(currentPair)) {
                cache.remove(currentPair);
                currentPair = new Pair(currentRequest, currentRequestNextPositions.first());
                cache.add(currentPair);
                continue;
            }
            count++;
            if (cache.size() < cacheSize) {
                currentPair = new Pair(currentRequest, currentRequestNextPositions.first());
                cache.add(currentPair);
            } else {
                cache.remove(cache.last());
                currentPair = new Pair(currentRequest, currentRequestNextPositions.first());
                cache.add(currentPair);
            }
        }
        System.out.println(count);
    }
}
