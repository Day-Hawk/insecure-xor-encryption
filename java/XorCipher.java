import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class to perform XOR encryption and decryption.
 */
public class XorCipher {
    /**
     * The main method to demonstrate encryption and decryption.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        final String message = "Here is the text.";

        final Pair p = encrypt(message);

        System.out.println(Arrays.toString(p.key()));
        System.out.println(Arrays.toString(p.cypher()));

        System.out.println(decrypt(p));
    }

    /**
     * Encrypts a message using a randomly generated key.
     *
     * @param message the message to be encrypted
     * @return a Pair containing the encryption key and the encrypted message
     */
    public static Pair encrypt(final String message) {
        final byte[] content = message.getBytes(StandardCharsets.UTF_8);
        final byte[] cypher = new byte[content.length];
        final int[] key = key(content.length);

        for (int i = 0; i < content.length; i++) {
            final byte b = content[i];
            cypher[i] = (byte) (b ^ key[i]);
        }

        return new Pair(key, cypher);
    }

    /**
     * Decrypts a message using the provided key.
     *
     * @param pair a Pair containing the encryption key and the encrypted message
     * @return the decrypted message as a String
     */
    public static String decrypt(final Pair pair) {
        final byte[] message = new byte[pair.cypher().length];

        for (int i = 0; i < pair.cypher().length; i++) {
            final int key = pair.key()[i];
            final int c = pair.cypher()[i];
            message[i] = (byte) (key ^ c);
        }

        return new String(message, StandardCharsets.UTF_8);
    }

    /**
     * Generates a random key of the specified length.
     *
     * @param length the length of the key to be generated
     * @return an array of integers representing the key
     */
    public static int[] key(int length) {
        final int[] key = new int[length];

        for (int i = 0; i < length; i++) {
            key[i] = ThreadLocalRandom.current().nextInt(2);
        }

        return key;
    }

    /**
     * A record to hold the encryption key and the encrypted message.
     *
     * @param key    an array of integers representing the key
     * @param cypher an array of bytes representing the encrypted message
     */
    public static record Pair(int[] key, byte[] cypher) {
    }
}