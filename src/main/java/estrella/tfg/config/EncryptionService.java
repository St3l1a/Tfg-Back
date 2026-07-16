package estrella.tfg.config;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    private final String SECRET_KEY =
            System.getenv("ENCRYPTION_KEY");


    public String encrypt(String data) {

        try {

            SecretKeySpec key =
                    new SecretKeySpec(
                            SECRET_KEY.getBytes(),
                            "AES"
                    );


            Cipher cipher =
                    Cipher.getInstance("AES");

            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    key
            );


            byte[] encrypted =
                    cipher.doFinal(data.getBytes());


            return Base64
                    .getEncoder()
                    .encodeToString(encrypted);


        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }



    public String decrypt(String encrypted){

        try {

            SecretKeySpec key =
                    new SecretKeySpec(
                            SECRET_KEY.getBytes(),
                            "AES"
                    );


            Cipher cipher =
                    Cipher.getInstance("AES");

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    key
            );


            byte[] decoded =
                    Base64.getDecoder()
                            .decode(encrypted);


            return new String(
                    cipher.doFinal(decoded)
            );


        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}