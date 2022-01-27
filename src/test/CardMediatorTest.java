import com.google.gson.Gson;
import com.mtcg.repository.card.CardMediator;
import com.mtcg.repository.card.ElementType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CardMediatorTest {

    @Test
    public void testCardMediatorName() {
        Gson gson = new Gson();
        String json = "{\"Id\":\"845f0dc7-37d0-426e-994e-43fc3ac83c08\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}";
        var cardMediator = gson.fromJson(json, CardMediator.class);
        assertEquals("WaterGoblin", cardMediator.getName());
    }

    @Test
    public void testCardMediatorId() {
        Gson gson = new Gson();
        String json = "{\"Id\":\"845f0dc7-37d0-426e-994e-43fc3ac83c08\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}";
        var cardMediator = gson.fromJson(json, CardMediator.class);
        assertEquals("845f0dc7-37d0-426e-994e-43fc3ac83c08", cardMediator.getId());
    }

    @Test
    public void testCardMediatorDamage() {
        Gson gson = new Gson();
        String json = "{\"Id\":\"845f0dc7-37d0-426e-994e-43fc3ac83c08\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}";
        var cardMediator = gson.fromJson(json, CardMediator.class);
        assertEquals(10.0, cardMediator.getDamage());
    }

    @Test
    public void testCardMediatorElementType() {
        Gson gson = new Gson();
        String json = "{\"Id\":\"845f0dc7-37d0-426e-994e-43fc3ac83c08\", \"Name\":\"WaterGoblin\", \"Damage\": 10.0}";
        var cardMediator = gson.fromJson(json, CardMediator.class);
        assertEquals(ElementType.WATER, cardMediator.getElementType());
    }
}
