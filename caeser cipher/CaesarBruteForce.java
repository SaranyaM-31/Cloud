import java.util.Scanner;

public class CaesarBruteForce {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ciphertext: ");
        String cipher = sc.nextLine();

        System.out.println("\nTrying all 26 shifts:");
        for (int key = 0; key < 26; key++) {
            System.out.printf("Key %2d: %s%n", key, decrypt(cipher, key));
        }
        sc.close();
    }

    // decrypt by shifting back 'key' positions
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
