import java.util.Scanner;

public class Alice {
    static long modPow(long base, long exp, long mod) {
        long result = 1;
        for (long i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long p = 23; // Public prime
        long g = 5;  // Primitive root
        long a = 6;  // Alice's private key

        long A = modPow(g, a, p); // Alice's public key
        System.out.println("Publicly shared values: p = " + p + ", g = " + g);
        System.out.println("Alice public key (send this to Bob): " + A);

        // Now, enter Bob’s public key (which Bob prints in his program)
        System.out.print("Enter Bob’s public key (B): ");
        long B = sc.nextLong();

        long sharedKey = modPow(B, a, p);
        System.out.println("Shared secret key (computed by Alice): " + sharedKey);
    }
}
