package dk.cphbusiness.koa.contract;


import org.mindrot.jbcrypt.BCrypt;

public class Program {
    public static void main(String[] args) {
        String hashed = BCrypt.hashpw("Secret", BCrypt.gensalt());
        System.out.println(hashed);
        boolean good = BCrypt.checkpw("Secret", hashed);
        System.out.println(good);
    }

}
