package com.spaceapps.liftoffgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceapps.liftoffgame.screens.GameScreen;
import java.util.Random;

public class LiftOffGame extends Game {

  public final static String GAMETAG = "[i] Rogue";

  private static LiftOffGame instance = null;
  public final Random random = new Random();

  private SpriteBatch spriteBatch;
  public Resources resources;

  private LiftOffGame() {

  }
  
  public static void logInfo(String message) {
    Gdx.app.log(GAMETAG, message);
  }

  public static LiftOffGame getInstance() {
    if (instance == null) {
      instance = new LiftOffGame();
    }
    return instance;
  }

  @Override
  public void create() {
    resources = new Resources();
    spriteBatch = new SpriteBatch();
    setScreen(new GameScreen());
  }

  @Override
  public void render() {
    super.render();
  }

  public SpriteBatch getSpriteBatch() {
    return spriteBatch;
  }

}
