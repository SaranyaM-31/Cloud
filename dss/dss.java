import java.util.Scanner;

public class dss {
    static int modPow(int base, int exp, int mod) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }

    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number p: ");
        int p = sc.nextInt();
        System.out.print("Enter prime number q (q should divide p-1): ");
        int q = sc.nextInt();
        System.out.print("Enter generator g (g < p): ");
        int g = sc.nextInt();
        System.out.print("Enter private key x (1 < x < q): ");
        int x = sc.nextInt();
        System.out.print("Enter message hash H(M): ");
        int H = sc.nextInt();
        System.out.print("Enter random per-message value k (1 < k < q): ");
        int k = sc.nextInt();

        int y = modPow(g, x, p);
        System.out.println("\nPublic Key y = " + y);

        int r = modPow(g, k, p) % q;
        int kInv = modInverse(k, q);
        int s = (kInv * (H + x * r)) % q;
        System.out.println("\nGenerated Signature:");
        System.out.println("r = " + r);
        System.out.println("s = " + s);

        int w = modInverse(s, q);
        int u1 = (H * w) % q;
        int u2 = (r * w) % q;
        int v = ((modPow(g, u1, p) * modPow(y, u2, p)) % p) % q;
        System.out.println("\nVerification Details:");
        System.out.println("w = " + w);
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
        System.out.println("v = " + v);

        if (v == r) {
            System.out.println("\nSignature is VALID (r == v)");
        } else {
            System.out.println("\nSignature is INVALID (r != v)");
        }

        sc.close();
    }
}
