package game.tanki.emitters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import game.tanki.TankGame;
import game.tanki.units.BotTank;

public class BotEmitter {
    private BotTank[] bots;

    public BotTank[] getBots() {
        return bots;
    }

    public static final int MAX_BOTS_COUNT = 200;

    public BotEmitter(TankGame game, TextureAtlas atlas) {
        this.bots = new BotTank[MAX_BOTS_COUNT];
        for (int i = 0; i < bots.length; i++) {
            this.bots[i] = new BotTank(game, atlas);
        }
    }

    public void activate(float x, float y) {
        for (int i = 0; i < bots.length; i++) {
            if (!bots[i].isActive()) {
                bots[i].activate(x, y);
                break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < bots.length; i++) {
            if (bots[i].isActive()) {
                bots[i].render(batch);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < bots.length; i++) {
            if (bots[i].isActive()) {
                bots[i].update(dt);
            }
        }
    }
}
