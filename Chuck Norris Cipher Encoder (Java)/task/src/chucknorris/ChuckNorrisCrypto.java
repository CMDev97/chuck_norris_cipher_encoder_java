package chucknorris;

public class ChuckNorrisCrypto {


    public String encode(String decodeString) {
        String binaries = decodedToBinary(decodeString);
        return encodeBinary(binaries);
    }

    public String decode(String encodeString) {
        String binary = encodeToBinary(encodeString);
        return binaryToString(binary);
    }



    private String encodeToBinary(String encode) {
        String[] splitData = encode.split(" ");

        boolean isOdd = splitData.length % 2 == 1;
        if (isOdd) {
            throw new IllegalStateException("Encoded string is not valid.");
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < splitData.length/2; i++) {
            if (!splitData[i*2].equals("0") && !splitData[i*2].equals("00")) {
                throw new IllegalStateException("Encoded string is not valid.");
            }
            String typeChar = (splitData[i*2].equals("0")) ? "1" : "0";
            String repeatChar = typeChar.repeat(splitData[i*2+1].length());
            result.append(repeatChar);
        }

        int validateLength = result.length() % 7;
        if (validateLength != 0) {
            throw new IllegalStateException("Encoded string is not valid.");
        }
        return result.toString();
    }

    private String binaryToString(String binary) {
        StringBuilder result = new StringBuilder();
        int chunk = binary.length() / 7;
        for (int i = 0; i < chunk; i++) {
            String chunkBinary = binary.substring(i*7, i*7+7);
            result.append((char) Integer.parseInt(chunkBinary, 2));
        }
        return result.toString();
    }

    private static String decodedToBinary(String phrase) {
        StringBuilder binaryConcat = new StringBuilder();
        for (Character c : splitToChar(phrase)) {
            binaryConcat.append(toBinaryString(c));
        }
        return binaryConcat.toString();
    }

    private static String encodeBinary(String bytes) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        char[] chars = splitToChar(bytes);

        for (int i = 0; i < chars.length; i++) {
            if (i+1 < chars.length && chars[i] == chars[i+1]) {
                count++;
            } else {
                result.append("0");
                if (chars[i] == '0') result.append("0");
                result.append(" ");
                result.append("0".repeat(count)).append(" ");
                count = 1;
            }
        }
        return result.toString();
    }

    private static char[] splitToChar(String phrase) {
        return phrase.toCharArray();
    }

    private static String toBinaryString(char c) {
        String value = Integer.toBinaryString(c);
        return String.format("%7s", value).replace(' ', '0');
    }

}
