import com.mtcg.hashing.Hash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class HashTest {

    @Test
    public void testInstance() {
        var hashInstance =  Hash.getInstance();
        var hashInstanceCompare = Hash.getInstance();
        assertEquals(hashInstance,hashInstanceCompare);
    }

    @Test
    public void testPasswordHash1() {
        var hashInstance =  Hash.getInstance();
        String hashedPassword = hashInstance.generatePasswordHash("password");
        assertTrue(hashInstance.checkPassword(hashedPassword, "password"));
    }

    @Test
    public void testPasswordHash2() {
        var hashInstance =  Hash.getInstance();
        String hashedPassword = hashInstance.generatePasswordHash("admin");
        assertTrue(hashInstance.checkPassword(hashedPassword, "admin"));
    }

    @Test
    public void testPasswordHash3() {
        var hashInstance =  Hash.getInstance();
        String hashedPassword = hashInstance.generatePasswordHash("sample");
        assertTrue(hashInstance.checkPassword(hashedPassword, "sample"));
    }

}
