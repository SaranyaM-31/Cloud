import java.util.Scanner;

public class sha1 {

    public static String stringToHex(String inputString) {
        StringBuilder hexStringBuilder = new StringBuilder();
        byte[] bytes = inputString.getBytes();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexStringBuilder.append('0');
            }
            hexStringBuilder.append(hex);
        }
        return hexStringBuilder.toString();
    }

    public static int leftRotate(int value, int bits) {
        return (value << bits) | (value >>> (32 - bits));
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter message:");
        String msg = sc.nextLine();

        System.out.println("Enter A:");
        int A = (int) Long.parseLong(sc.nextLine(), 16);

        System.out.println("Enter B:");
        int B = (int) Long.parseLong(sc.nextLine(), 16);

        System.out.println("Enter C:");
        int C = (int) Long.parseLong(sc.nextLine(), 16);

        System.out.println("Enter D:");
        int D = (int) Long.parseLong(sc.nextLine(), 16);

        System.out.println("Enter E:");
        int E = (int) Long.parseLong(sc.nextLine(), 16);

        msg = stringToHex(msg);
        msg = stringToHex(msg);

        if (msg.length() > 8) {
            msg = msg.substring(0, 8);
        } 

        int W0 = (int) Long.parseLong(msg, 16);

        int f = (B ^ C ^ D);
        int K = 0x5A827999;

        int temp = leftRotate(A, 5) + f + E + K + W0;

        System.out.printf("A: %08x\n", temp);
        System.out.printf("B: %08x\n", A);
        System.out.printf("C: %08x\n", leftRotate(B, 30));
        System.out.printf("D: %08x\n", C);
        System.out.printf("E: %08x\n", D);

        sc.close();
    }
}
