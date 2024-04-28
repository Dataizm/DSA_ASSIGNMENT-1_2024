import java.util.HashMap;
import java.util.Map;

public class LZWCompression {
    public static void main(String[] args) {
        String message = "Hello from Device1";

        // Compress message
        String compressedMessage = compress(message);
        System.out.println("Compressed message: " + compressedMessage);

        // Decompress message
        String decompressedMessage = decompress(compressedMessage);
        System.out.println("Decompressed message: " + decompressedMessage);
    }

    public static String compress(String message) {
        Map<String, Integer> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char)i, i);
        }

        String w = "";
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc)) {
                w = wc;
            } else {
                result.append(dictionary.get(w)).append(" ");
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        if (!w.equals("")) {
            result.append(dictionary.get(w)).append(" ");
        }

        return result.toString();
    }

    public static String decompress(String compressedMessage) {
        String[] parts = compressedMessage.split(" ");
        Map<Integer, String> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char)i);
        }

        StringBuilder result = new StringBuilder();
        int prevCode = Integer.parseInt(parts[0]);
        result.append(dictionary.get(prevCode));
        for (int i = 1; i < parts.length; i++) {
            int code = Integer.parseInt(parts[i]);
            String entry;
            if (dictionary.containsKey(code)) {
                entry = dictionary.get(code);
            } else if (code == dictSize) {
                entry = dictionary.get(prevCode) + dictionary.get(prevCode).charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed message");
            }

            result.append(entry);
            dictionary.put(dictSize++, dictionary.get(prevCode) + entry.charAt(0));
            prevCode = code;
        }
        return result.toString();
    }
}

