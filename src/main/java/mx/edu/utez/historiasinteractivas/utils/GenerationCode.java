package mx.edu.utez.historiasinteractivas.utils;

import java.security.SecureRandom;

public class GenerationCode {
    public static String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(1000000);
        return String.format("%06d", num);
    }
}
