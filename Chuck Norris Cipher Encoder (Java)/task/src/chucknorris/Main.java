package chucknorris;


import java.util.Scanner;

public class Main {
    private static final String ENCODE_MENU_ITEM = "encode";
    private static final String DECODE_MENU_ITEM = "decode";
    private static final String EXIT_MENU_ITEM = "exit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChuckNorrisCrypto crypto = new ChuckNorrisCrypto();
        while (true) {
            try {
                System.out.println("Please input operation (encode/decode/exit):");
                String menuItem = readTypeOperation(scanner);
                if (menuItem.equalsIgnoreCase(ENCODE_MENU_ITEM)) {
                    String encodeString = readEncodeString(scanner);
                    String encodedValue = crypto.encode(encodeString);
                    System.out.println("Encoded string: \n" + encodedValue);
                } else if (menuItem.equalsIgnoreCase(DECODE_MENU_ITEM)) {
                    String decodeString = readDecodeString(scanner);
                    String decodedValue = crypto.decode(decodeString);
                    System.out.println("Decoded string: \n" + decodedValue);
                } else if (menuItem.equalsIgnoreCase(EXIT_MENU_ITEM)) {
                    System.out.println("Bye");
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static String readDecodeString(Scanner scanner) {
        System.out.println("Input encoded string: ");
        String encodeString = scanner.nextLine();
        if (!encodeString.matches("[0 ]*")){
            throw new IllegalArgumentException("Encoded string is not valid.");
        }
        return encodeString;
    }

    private static String readEncodeString(Scanner scanner) {
        System.out.println("Input string: ");
        return scanner.nextLine();
    }

    private static String readTypeOperation(Scanner scanner) {
        String operation = scanner.nextLine();
        if (operation.isBlank() || !operation.equalsIgnoreCase(ENCODE_MENU_ITEM) && !operation.equalsIgnoreCase(DECODE_MENU_ITEM)
                && !operation.equalsIgnoreCase(EXIT_MENU_ITEM)) {
            String msg = String.format("There is no '%s' operation", operation);
            throw new IllegalArgumentException(msg);
        }
        return operation;
    }



}