import java.io.*;
import java.util.*;

public class ExternalSorting {
    private static int m = 1000; // Assuming m records can fit in memory
    
    public static void externalSort(String inputFile, String outputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            List<String> chunk = new ArrayList<>();

            int chunkNumber = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                chunk.add(line);
                if (chunk.size() >= m) {
                    Collections.sort(chunk); // Sort the chunk in memory
                    writeChunkToFile(chunk, "chunk_" + chunkNumber + ".txt");
                    chunk.clear();
                    chunkNumber++;
                }
            }
            if (!chunk.isEmpty()) {
                Collections.sort(chunk); // Sort the last chunk
                writeChunkToFile(chunk, "chunk_" + chunkNumber + ".txt");
            }
            reader.close();

            mergeChunks(outputFile, chunkNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeChunkToFile(List<String> chunk, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String line : chunk) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    private static void mergeChunks(String outputFile, int numChunks) throws IOException {
        PriorityQueue<BufferedReader> pq = new PriorityQueue<>(numChunks, Comparator.comparing(s -> {
            try {
                return s.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        for (int i = 0; i < numChunks; i++) {
            BufferedReader reader = new BufferedReader(new FileReader("chunk_" + i + ".txt"));
            pq.add(reader);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        while (!pq.isEmpty()) {
            BufferedReader reader = pq.poll();
            String line = reader.readLine();
            if (line != null) {
                writer.write(line);
                writer.newLine();
                pq.add(reader);
            } else {
                reader.close();
            }
        }
        writer.close();
    }

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        externalSort(inputFile, outputFile);
    }
}
