import java.util.Scanner;

public class CaesarFreqAttack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ciphertext: ");
        String cipher = sc.nextLine();

        int[] freq = new int[26];
        for (char c : cipher.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') freq[c - 'a']++;
        }

        // find most frequent letter index
        int maxIdx = 0;
        for (int i = 1; i < 26; i++) {
            if (freq[i] > freq[maxIdx]) maxIdx = i;
        }

        // assume most frequent maps to 'e' (index 4)
        int assumedE = 'e' - 'a'; // 4
        int guessedKey = (maxIdx - assumedE + 26) % 26; // key used in encryption
        // To decrypt we shift back by guessedKey.
        String plain = decrypt(cipher, guessedKey);

        System.out.println("\nFrequency table (letter:count):");
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) System.out.printf("%c:%d ", (char)('a'+i), freq[i]);
        }
        System.out.println();

        System.out.println("Most frequent ciphertext letter: " + (char)('a' + maxIdx));
        System.out.println("Guessed key (assuming it maps to 'e'): " + guessedKey);
        System.out.println("Decrypted text (using guessed key):");
        System.out.println(plain);

        sc.close();
    }

    public static String decrypt(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ((c - 'A' - key + 26) % 26 + 'A'));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ((c - 'a' - key + 26) % 26 + 'a'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
