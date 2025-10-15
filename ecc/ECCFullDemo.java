import java.util.Scanner;

class Point {
    long x, y;
    boolean isInfinity;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
        this.isInfinity = false;
    }

    Point() {
        isInfinity = true;
    }
}

public class ECCFullDemo {

    static long p = 17; // Modulo
    static long a = 2;  // Curve parameter
    static long b = 2;  // Curve parameter

    // Point addition
    static Point add(Point P, Point Q) {
        if (P.isInfinity) return Q;
        if (Q.isInfinity) return P;

        long lambda;
        if (P.x == Q.x && P.y == Q.y) {
            lambda = ((3 * P.x * P.x + a) * modInverse(2 * P.y, p)) % p;
        } else {
            lambda = ((Q.y - P.y) * modInverse(Q.x - P.x, p)) % p;
        }
        if (lambda < 0) lambda += p;

        long xr = (lambda * lambda - P.x - Q.x) % p;
        if (xr < 0) xr += p;
        long yr = (lambda * (P.x - xr) - P.y) % p;
        if (yr < 0) yr += p;

        return new Point(xr, yr);
    }

    // Scalar multiplication
    static Point multiply(Point P, long k) {
        Point R = new Point();
        Point Q = P;
        while (k > 0) {
            if (k % 2 == 1) R = add(R, Q);
            Q = add(Q, Q);
            k /= 2;
        }
        return R;
    }

    // Modular inverse
    static long modInverse(long a, long m) {
        a = (a % m + m) % m;
        for (long x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point G = new Point(5, 1); // Base point

        // ===== ECC Key Exchange =====
        System.out.print("Enter Alice private key (1-16): ");
        long alicePrivate = sc.nextLong();

        System.out.print("Enter Bob private key (1-16): ");
        long bobPrivate = sc.nextLong();

        Point alicePublic = multiply(G, alicePrivate);
        Point bobPublic = multiply(G, bobPrivate);

        System.out.println("\nAlice Public Key: (" + alicePublic.x + "," + alicePublic.y + ")");
        System.out.println("Bob Public Key: (" + bobPublic.x + "," + bobPublic.y + ")");

        Point aliceSecret = multiply(bobPublic, alicePrivate);
        Point bobSecret = multiply(alicePublic, bobPrivate);

        System.out.println("Shared secret (Alice): (" + aliceSecret.x + "," + aliceSecret.y + ")");
        System.out.println("Shared secret (Bob): (" + bobSecret.x + "," + bobSecret.y + ")");

        // ===== Encryption & Decryption =====
        System.out.print("\nEnter message as number (0-16): ");
        int message = sc.nextInt();

        int sharedX = (int) aliceSecret.x; // Using shared secret x-coordinate
        int cipher = (message + sharedX) % 17;
        System.out.println("Encrypted message (cipher): " + cipher);

        int decrypted = (cipher - sharedX + 17) % 17;
        System.out.println("Decrypted message: " + decrypted);

        sc.close();
    }
}
