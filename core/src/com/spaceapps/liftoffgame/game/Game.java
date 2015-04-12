/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.spaceapps.liftoff.weather.Weather;
import com.spaceapps.liftoffgame.LiftOffGame;
import com.spaceapps.liftoffgame.actors.ButtonActor;
import java.util.Random;

/**
 *
 * @author craw
 */
public class Game {

  public static final long DEFAULT_COUNTDOWN = 154800;
  public static final long COUNTDOWN_RATE = 100;
  
  public static final int ROCKET_X = 420;
  public static final int ROCKET_Y = 60;

  public ButtonActor rocket;
  public ButtonActor platform;
  private final Stage stage;
  private final Random random;

  protected final Weather weather;
  
  public final Sprite windySprite;
  public final Sprite stormySprite;
  public final Sprite sunnySprite;

  private long countdown;
  private boolean countdownRunning = true;
  private float timer = 0f;
  public String timeLabel;

  public Game(Stage stage) {
    this.stage = stage;
    random = new Random();

    countdown = DEFAULT_COUNTDOWN;

    weather = new Weather();

    rocket = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("rocket"));
    rocket.setPosition(ROCKET_X, ROCKET_Y-60);

    platform = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("platform"));
    platform.setPosition(ROCKET_X - 80, ROCKET_Y);
    
    windySprite = LiftOffGame.getInstance().resources.getNewSprite("windy");
    windySprite.setPosition(900, 500);
    stormySprite = LiftOffGame.getInstance().resources.getNewSprite("stormy");
    stormySprite.setPosition(900, 500);
    sunnySprite = LiftOffGame.getInstance().resources.getNewSprite("sunny");
    sunnySprite.setPosition(900, 500);

    stage.addActor(rocket);
    stage.addActor(platform);
  }

  public void draw(SpriteBatch batch) {
    switch (weather.getState()) {
      case Normal:
        sunnySprite.draw(batch);
        break;
      case Storm:
        stormySprite.draw(batch);
        break;
      case Wind:
        windySprite.draw(batch);
        break;
    }
  }

  public void act(float delta) {
    if (countdownRunning) {
      timer += delta;
      if (timer >= 1f) {
        countdown -= COUNTDOWN_RATE;
        timer -= 1f;
        updateTimeLabel();
      }
    }

  }

  private void updateTimeLabel() {
    timeLabel = "T- " + countdown; // TODO
  }

}
