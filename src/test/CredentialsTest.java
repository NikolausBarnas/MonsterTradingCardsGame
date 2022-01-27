import com.google.gson.Gson;
import com.mtcg.repository.credentials.Credentials;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CredentialsTest {

    @Test
    public void testUsername() {
        Gson gson = new Gson();
        String json = "{\"Username\":\"kienboec\", \"Password\":\"different\"}";
        var credentials = gson.fromJson(json,Credentials.class);
        assertEquals("kienboec",credentials.getUsername());
    }

    @Test
    public void testPassword() {
        Gson gson = new Gson();
        String json = "{\"Username\":\"kienboec\", \"Password\":\"different\"}";
        var credentials = gson.fromJson(json,Credentials.class);
        assertEquals("different",credentials.getPassword());
    }

    @Test
    public void testSetHashPassword() {
        var credentials = new Credentials("username", "password");
        credentials.setHashPassword("hashPW");
        assertEquals("hashPW",credentials.getHashPassword());
    }
}
