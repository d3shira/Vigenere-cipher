import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //INT SEED
        System.out.print("Message (int32 seed): ");
        String message = scanner.nextLine();
        VigenereCipherClass cipherInt = new VigenereCipherClass(12345);
        String encrypted = cipherInt.encrypt(message);
        System.out.println("Encrypted message using an int32 as a seed: " + encrypted);
        String decrypted = cipherInt.decrypt(encrypted);
        System.out.println("Decrypted message using an int32 as a seed: " + decrypted);

        //STRING SEED
        VigenereCipherClass cipherString = new VigenereCipherClass("STRINGSEED");
        System.out.print("Message (String seed): ");
        String message1 = scanner.nextLine();
        String encrypted1 = cipherString.encrypt(message1);
        System.out.println("Encrypted message using a string as a seed: " + encrypted1);
        String decrypted1 = cipherString.decrypt(encrypted1);
        System.out.println("Decrypted message using a string as a seed: " + decrypted1);
    }

}
class VigenereCipherClass{
    private int[] key;

    public VigenereCipherClass(int seed) {
        Random rand = new Random(seed);
        this.key = new int[100];
        for (int i = 0; i < this.key.length; i++) {
            this.key[i] = rand.nextInt(26);
        }
    }

    public VigenereCipherClass(String seed) {
        int sum = 0;
        for (int i = 0; i < seed.length(); i++) {
            sum += (int)Character.toUpperCase(seed.charAt(i));
        }
        Random rand = new Random(sum);
        this.key = new int[100];
        for (int i = 0; i < this.key.length; i++) {
            this.key[i] = rand.nextInt(26);
        }
    }
    public String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int key = this.key[keyIndex];
                if (Character.isUpperCase(c)) {
                    char newChar = (char) (((c - 'A' + key) % 26) + 'A');
                    encrypted.append(newChar);
                } else {
                    char newChar = (char) (((c - 'a' + key) % 26) + 'a');
                    encrypted.append(newChar);
                }
                keyIndex = (keyIndex + 1) % this.key.length;
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String message) {
        StringBuilder decrypted = new StringBuilder();
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int key = this.key[keyIndex];
                if (Character.isUpperCase(c)) {
                    char newChar = (char) (((c - 'A' - key + 26) % 26) + 'A');
                    decrypted.append(newChar);
                } else {
                    char newChar = (char) (((c - 'a' - key + 26) % 26) + 'a');
                    decrypted.append(newChar);
                }
                keyIndex = (keyIndex + 1) % this.key.length;
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }


}
