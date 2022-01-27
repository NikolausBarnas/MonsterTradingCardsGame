import com.google.gson.Gson;
import com.mtcg.repository.game.BattleLog;
import com.mtcg.repository.game.RealDamage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void realDamageTest1() {
        var realDamageInstance = new RealDamage(1.0,2.0);
        assertEquals(1.0,realDamageInstance.getPlayer1RealDamage());
    }

    @Test
    public void realDamageTest2() {
        var realDamageInstance = new RealDamage(1.0,2.0);
        assertEquals(2.0,realDamageInstance.getPlayer2RealDamage());
    }

    @Test
    public void BattleLogTestPlayer1() {
        BattleLog battleLog = new BattleLog("altenhof", "kienboec", "draw",null);
        assertEquals(battleLog.getPlayer1(),"altenhof");
    }

    @Test
    public void BattleLogTestPlayer2() {
        BattleLog battleLog = new BattleLog("altenhof", "kienboec", "draw",null);
        assertEquals(battleLog.getPlayer2(),"kienboec");
    }

    @Test
    public void BattleLogTestWinner() {
        BattleLog battleLog = new BattleLog("altenhof", "kienboec", "draw",null);
        assertEquals(battleLog.getWinner(),"draw");
    }

}
