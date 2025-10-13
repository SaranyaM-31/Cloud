import java.util.Scanner;

public class Bob {
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
        long b = 15; // Bob's private key

        long B = modPow(g, b, p); // Bob's public key
        System.out.println("Publicly shared values: p = " + p + ", g = " + g);
        System.out.println("Bob public key (send this to Alice): " + B);

        // Now, enter Alice’s public key (which Alice prints in her program)
        System.out.print("Enter Alice’s public key (A): ");
        long A = sc.nextLong();

        long sharedKey = modPow(A, b, p);
        System.out.println("Shared secret key (computed by Bob): " + sharedKey);
    }
}
