package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String encode(String message)
    {
        byte[] uniqueKey = message.getBytes();
        byte[] hash = null;

        try
        {
            hash = MessageDigest.getInstance("SHA256").digest(uniqueKey);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Error("No MD5 support in this VM.");
        }

        StringBuilder hashString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(aHash);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else
                hashString.append(hex.substring(hex.length() - 2));
        }
        return hashString.toString();
    }
}
