import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the key (shift): ");
        int key = sc.nextInt();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }

    public static String encrypt(String text, int key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                result += (char)((c - 'A' + key) % 26 + 'A');
            } else if (Character.isLowerCase(c)) {
                result += (char)((c - 'a' + key) % 26 + 'a');
            } else {
                result += c;
            }
        }
        return result;
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, 26 - key); // Decryption is just shifting back
    }
}
