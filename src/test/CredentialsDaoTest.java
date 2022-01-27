import com.mtcg.repository.credentials.CredentialsDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CredentialsDaoTest {
    @Test
    public void credentialsInstanceTest() {
        var instance = CredentialsDao.getInstance();
        var instanceComp = CredentialsDao.getInstance();
        assertEquals(instance, instanceComp);
    }
}
