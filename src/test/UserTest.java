import com.google.gson.Gson;
import com.mtcg.repository.user.UserMediator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserMediatorName() {
        Gson gson = new Gson();
        String jsonString = "{\"Name\": \"Kienboeck\",  \"Bio\": \"me playin...\", \"Image\": \":-)\"}";
        UserMediator userMediator = gson.fromJson(jsonString,UserMediator.class);
        assertEquals("Kienboeck",userMediator.getName());
    }

    @Test
    public void testUserMediatorBio() {
        Gson gson = new Gson();
        String jsonString = "{\"Name\": \"Kienboeck\",  \"Bio\": \"me playin...\", \"Image\": \":-)\"}";
        UserMediator userMediator = gson.fromJson(jsonString,UserMediator.class);
        assertEquals("me playin...",userMediator.getBio());
    }

    @Test
    public void testUserMediatorImage() {
        Gson gson = new Gson();
        String jsonString = "{\"Name\": \"Kienboeck\",  \"Bio\": \"me playin...\", \"Image\": \":-)\"}";
        UserMediator userMediator = gson.fromJson(jsonString,UserMediator.class);
        assertEquals(":-)",userMediator.getImage());
    }


}
