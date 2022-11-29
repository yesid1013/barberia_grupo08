package com.grupo08.barberia.Security;



//Algoritmo hash para encriptar
public class Hash {
    public static String getHash (String txt, String hashType){
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array =md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0X100)
                .substring(1,3));
                
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static String sha1(String txt) {
        return Hash.getHash(txt, "SHA1");
    }
}
