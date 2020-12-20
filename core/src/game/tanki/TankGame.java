package game.tanki;

import clientUI.ClientApplicationJDX;
import clientUI.RoomInfo;
import clientUI.controllers.NamesConstants;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import game.tanki.emitters.BotEmitter;
import game.tanki.emitters.BulletEmitter;
import game.tanki.units.BotTank;
import game.tanki.units.PlayerTank;
import game.tanki.units.Tank;

import java.util.Map;
import java.util.Set;


public class TankGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private BitmapFont font;
    private PlayerTank player;
    private BulletEmitter bulletEmitter;
    private BotEmitter botEmitter;
    private float gameTimer;
    private ClientApplicationJDX application;
    private RoomInfo roomInfo;
    private static final boolean FRIENDLY_FIRE = false;
    private Music sound;

    public TankGame() {
    }


    public TankGame(ClientApplicationJDX application, RoomInfo roomInfo) {
        this.application = application;
        this.roomInfo = roomInfo;
    }

    public PlayerTank getPlayer() {
        return player;
    }

    public BulletEmitter getBulletEmitter() {
        return bulletEmitter;
    }

    @Override
    public void create() {
        TextureAtlas atlas = new TextureAtlas(NamesConstants.TEXTURE_GAME.getName());
        font = new BitmapFont(Gdx.files.internal(NamesConstants.BITMAP_GAME.getName()));
        batch = new SpriteBatch();
        player = new PlayerTank(this, atlas);
        bulletEmitter = new BulletEmitter(atlas);
        botEmitter = new BotEmitter(this, atlas);
        botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
        sound = Gdx.audio.newMusic(Gdx.files.internal(NamesConstants.MUSIC_GAME.getName()));
        sound.play();
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0.6f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        renderHUD(batch, font, roomInfo);
        player.render(batch);
        botEmitter.render(batch);
        bulletEmitter.render(batch);
        batch.end();
    }

    public void setScore(Integer id, Integer killsCount) {
        // TODO: 19.12.2020 прийти должен RoomInfo. Изменить. 
        roomInfo.getMapUsers().put(id, killsCount);
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font, RoomInfo roomInfo) {
        int forY = 700;
        Set<Map.Entry<Integer, Integer>> playerSet = roomInfo.getMapUsers().entrySet();
        for (Map.Entry<Integer, Integer> player : playerSet) {
            font.draw(batch, "User:" + player.getKey() + "   score:  " + player.getValue(), 20, forY);
            forY -= 30;
        }
    }

    public void addScore() {
        application.addKill(roomInfo.getMapUsers().get(application.getID()));
    }

    public void update(float dt) {
        gameTimer += dt;
        if (gameTimer > 5.0f) {
            gameTimer = 0.0f;
            botEmitter.activate(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
        }
        player.update(dt);
        botEmitter.update(dt);
        bulletEmitter.update(dt);
        checkCollisions();
        closeGame();
    }

    public void checkCollisions() {
        for (int i = 0; i < bulletEmitter.getBullets().length; i++) {
            Bullet bullet = bulletEmitter.getBullets()[i];
            if (bullet.isActive()) {
                for (int j = 0; j < botEmitter.getBots().length; j++) {
                    BotTank bot = botEmitter.getBots()[j];
                    if (bot.isActive()) {
                        if (checkBulletAndTank(bot, bullet) && bot.getHitBox().contains(bullet.getPosition())) {
                            bullet.deactivate();
                            bot.takeDamage(bullet.getDamage());
                            if (!bot.isActive()) {
                                addScore();
                            }
                            break;
                        }
                    }
                }
                if (checkBulletAndTank(player, bullet) && player.getHitBox().contains(bullet.getPosition())) {
                    bullet.deactivate();
                    player.takeDamage(bullet.getDamage());
                }
            }
        }
    }

    public boolean checkBulletAndTank(Tank tank, Bullet bullet) {
        if (!FRIENDLY_FIRE) {
            return tank.getOwnerType() != bullet.getOwner().getOwnerType();
        } else {
            return tank != bullet.getOwner();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }


    public void closeGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            sound.stop();
            application.closeGame(roomInfo);
            Gdx.app.exit();
        }
    }

    public void setRoomInfo(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
    }
}