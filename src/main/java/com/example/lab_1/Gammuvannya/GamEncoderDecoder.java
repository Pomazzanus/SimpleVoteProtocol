package com.example.lab_1.Gammuvannya;

import java.util.Arrays;

public class GamEncoderDecoder {

    public int[] encode(String message, char[] gam) {
        char[] binm = message.toCharArray();
        int[] xores = new int[binm.length];
        for (int i = 0; i < binm.length; i++) {
            int messageChar = binm[i];
            int gamChar = gam[i % gam.length];
            xores[i] = messageChar ^ gamChar;
            System.out.println(xores[i]);
        }
        return xores;
    }

    public String decode(int[] message, char[] gam) {
        char[] decodedChars = new char[message.length];
        for (int i = 0; i < message.length; i++) {
            int gamChar = gam[i % gam.length];
            char originalChar = (char)(message[i] ^ gamChar);
            decodedChars[i] = originalChar;
        }
        return new String(decodedChars);
    }



    public String[] toBinary(String message){
        String[] binaryArray = new String[message.length()];

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            String binaryRepresentation = Integer.toBinaryString(c);

            // Дополните строку нулями слева, чтобы обеспечить фиксированную длину (например, 8 бит)
            int paddingLength = 8 - binaryRepresentation.length();
            if (paddingLength > 0) {
                String padding = "0".repeat(paddingLength);
                binaryRepresentation = padding + binaryRepresentation;
            }

            binaryArray[i] = binaryRepresentation;
            System.out.println(binaryArray[i]);
        }

        return binaryArray;
    }
}
