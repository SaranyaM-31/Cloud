import java.math.BigInteger;
import java.util.Scanner;

public class rsa {
    static BigInteger e, d, n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== RSA Menu ====");
            System.out.println("1. Key Generation");
            System.out.println("2. Encryption");
            System.out.println("3. Decryption");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: keyGeneration(sc); break;
                case 2:
                    if (e == null || n == null) { System.out.println("Generate keys first."); break; }
                    System.out.print("Enter plaintext: ");
                    encrypt(sc.nextLine());
                    break;
                case 3:
                    if (d == null || n == null) { System.out.println("Generate keys first."); break; }
                    System.out.print("Enter cipher (space-separated): ");
                    decrypt(sc.nextLine().trim().split(" "));
                    break;
                case 4: System.out.println("Exiting RSA Program."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void keyGeneration(Scanner sc) {
        System.out.print("Enter n: ");
        int nInt = sc.nextInt();
        System.out.print("Enter e: ");
        int eInt = sc.nextInt();
        sc.nextLine();

        int p = 0, q = 0;
        for (int i = 2; i <= nInt / 2; i++) {
            if (nInt % i == 0) { p = i; q = nInt / i; break; }
        }

        n = BigInteger.valueOf(nInt);
        e = BigInteger.valueOf(eInt);
        System.out.println("p = " + p + ", q = " + q);

        int phi = (p - 1) * (q - 1);
        if (BigInteger.valueOf(eInt).gcd(BigInteger.valueOf(phi)).intValue() != 1) {
            System.out.println("e and φ(n) not coprime. Choose a different e."); return;
        }

        d = BigInteger.valueOf(simpleModInverse(eInt, phi));
        System.out.println("Public Key: { e = " + e + ", n = " + n + " }");
        System.out.println("Private Key: { d = " + d + ", n = " + n + " }");
    }

    private static void encrypt(String plaintext) {
        System.out.print("Cipher: ");
        for (char ch : plaintext.toCharArray()) {
            BigInteger m = BigInteger.valueOf((int) ch);
            System.out.print(m.modPow(e, n) + " ");
        }
        System.out.println();
    }

    private static void decrypt(String[] cipherNums) {
        StringBuilder sb = new StringBuilder();
        for (String s : cipherNums) {
            BigInteger c = new BigInteger(s);
            sb.append((char) c.modPow(d, n).intValue());
        }
        System.out.println("Decrypted text: " + sb.toString());
    }

    private static int simpleModInverse(int a, int m) {
        for (int x = 1; x < m; x++) if ((a * x) % m == 1) return x;
        return -1; // No inverse
    }
}
