package com.leveluptasks.tools;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {

    public static String hashSHA512(String input) throws NoSuchAlgorithmException {
        // getInstance récupère le singleton MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        // méthode pour réinitialiser le contenu du messageDigest s'il a été utilisé
        // auparavant
        md.reset();

        // application de l'algorithme de hachage à la chaine en entrée
        byte[] messageDigestArray = md.digest(input.getBytes(StandardCharsets.UTF_8));

        // conversion du messageDigestArray en une réprésentation numérique signée
        BigInteger bi = new BigInteger(1, messageDigestArray);

        return String.format("%0128x", bi);
    }

}
