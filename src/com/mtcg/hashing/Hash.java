package com.mtcg.hashing;

import org.mindrot.jbcrypt.BCrypt;

public class Hash {

    private static Hash instance = null;
    private String generatedPasswordHash;
    static {
        Hash.instance = new Hash();
    }
    private Hash() {
        Hash.instance = getInstance();
    }

    public static Hash getInstance() {
        return instance;
    }

    public String generatePasswordHash(String password) {
        generatedPasswordHash = BCrypt.hashpw(password,BCrypt.gensalt(12));
        return generatedPasswordHash;
    }

    public boolean checkPassword(String hashPassword, String password) {
        return BCrypt.checkpw(password, hashPassword);
    }

}
