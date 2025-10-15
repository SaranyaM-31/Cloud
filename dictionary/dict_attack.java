import java.io.*;
import java.security.*;
import java.util.*;

public class dict_attack {

    public static String getSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static void generatePasswords(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                for (char c3 = 'a'; c3 <= 'z'; c3++) {
                    for (char d = '0'; d <= '9'; d++) {
                        String pwd = "" + c1 + c2 + c3 + d;
                        fw.write(pwd + "\n");
                    }
                }
            }
        }
        fw.close();
        System.out.println("Passwords generated and stored in " + filename);
    }

    public static void main(String[] args) throws Exception {
        String filename = "passwords.txt";
        generatePasswords(filename);

        HashMap<String, String> hashTable = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String hash = getSHA256(line);
            hashTable.put(hash, line);
        }
        br.close();
        System.out.println("Hash table created with SHA-256 hashes.");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password to check: ");
        String inputPassword = sc.nextLine();
        String inputHash = getSHA256(inputPassword);
        if (hashTable.containsKey(inputHash)) {
            System.out.println("Password cracked: " + hashTable.get(inputHash));
        } else {
            System.out.println("Password not found in dictionary.");
        }
    }
}
