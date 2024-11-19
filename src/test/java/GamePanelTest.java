
import org.frank.main.GamePanel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class GamePanelTest {

    @Test
    public void testGameThreadIsRunning() throws InterruptedException {
        GamePanel gamePanel = Mockito.spy(GamePanel.class);
        gamePanel.startGameThread();

        Thread.sleep(10);
        verify(gamePanel, atLeastOnce()).run();

        gamePanel.gameThread = null;
    }
}
