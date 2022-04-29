import java.io.*;
import java.util.*;


public class Main {

    record Pair(Long left, Integer right) {
    }

    /*
    Solution for O(n*log(n))
    Из кэша удаляем элемент который встретится максимально поздно относительно остальных элементов
     */
    public static int findServerRequests(final String inputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
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
        reader.close();
        int count = 0;
        SortedSet<Pair> cache = new TreeSet<>(Comparator.comparing(o -> o.right));
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
            if (cache.size() >= cacheSize) {
                cache.remove(cache.last());
            }
            currentPair = new Pair(currentRequest, currentRequestNextPositions.first());
            cache.add(currentPair);
        }
        return count;
    }

    public static void main(String[] args) {
        final String inputFilePath = "src/main/resources/input.txt";
        final String outputFilePath = "src/main/resources/output.txt";
        int count;
        try {
            count = findServerRequests(inputFilePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            writer.write(count);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
