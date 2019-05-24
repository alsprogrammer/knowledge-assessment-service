package amanagment.data.generators;

import amanagment.data.models.Image;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@UtilityClass
public class IdGenerator {
    private final String algorithmName = "MD5";

    public String generateId(Object entity) {
        if (entity instanceof Image)
            return calculateImageHash(((Image) entity).getId());

        return generateUUIDId();
    }

    private String calculateImageHash(String image) {
        BigInteger i;

        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            byte[] data = image.getBytes();
            m.update(data,0,data.length);
            i = new BigInteger(1,m.digest());
            return String.format("%1$032X", i);
        } catch (NoSuchAlgorithmException e) {
            return generateUUIDId();
        }
    }

    private String generateUUIDId() {
        return UUID.randomUUID().toString();
    }
}
