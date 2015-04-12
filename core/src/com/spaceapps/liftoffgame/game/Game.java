/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
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
  public ButtonActor engineFire;
  public ButtonActor platform;
  private final Stage stage;
  private final Random random;

  protected final Weather weather;
  
  public final Sprite windySprite;
  public final Sprite stormySprite;
  public final Sprite sunnySprite;
  
  public final Sprite weatherwarn;
  public final Sprite cargowarn;
  public final Sprite crewwarn;
  public final Sprite fuelwarn;


  private long countdown;
  private boolean countdownRunning = true;
  private float timer = 0f;
  public String timeLabel = "";

  public void startEngines() {
    engineFire.setVisible(true);
  }
  
  public enum EndGameStory {NoFuel, TooMuchFuel, CrewBored, AirplaneCrash, AirplaneOk, Weather, Platform,  None}
  
  long fuelInTank = 0;
  float crewInside = -1f;
  float radarCheck = -1f;
  boolean cargoLoaded = false;
  boolean platformOn = true;

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
    
    weatherwarn = LiftOffGame.getInstance().resources.getNewSprite("weatherwarn");
    weatherwarn.setPosition(200, 450);
    crewwarn = LiftOffGame.getInstance().resources.getNewSprite("crewwarn");
    crewwarn.setPosition(200, 300);
    fuelwarn = LiftOffGame.getInstance().resources.getNewSprite("fuelwarn");
    fuelwarn.setPosition(200, 150);
    cargowarn = LiftOffGame.getInstance().resources.getNewSprite("cargowarn");
    cargowarn.setPosition(200, 10);
    
    engineFire = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("fire"));
    engineFire.setPosition(rocket.getX()+95, rocket.getY()-50);
    engineFire.setVisible(false);

    stage.addActor(engineFire);
    stage.addActor(rocket);
    stage.addActor(platform);
  }

  public void draw(SpriteBatch batch) {
    switch (weather.getState()) {
      case Normal:
        sunnySprite.draw(batch);
        break;
      case Storm:
        weatherwarn.setAlpha(1f);
        weatherwarn.draw(batch);
        stormySprite.draw(batch);
        break;
      case Wind:
        windySprite.draw(batch);
        weatherwarn.setAlpha(0.7f);
        weatherwarn.draw(batch);
        break;
    }
      drawTimeLabel(batch);

      cargowarn.draw(batch);
      crewwarn.draw(batch);
      fuelwarn.draw(batch);
  }

  public void act(float delta) {
    if (countdownRunning) {
      timer += delta;
      if (timer >= 1f) {
        countdown -= COUNTDOWN_RATE;
        timer -= 1f;
        updateTimeLabel();
      }
      
      if (crewInside >= 0f)
        crewInside += delta;
      
      if (radarCheck >= 0f)
        radarCheck -= delta;
    }
    
    engineFire.setPosition(rocket.getX()+95, rocket.getY()+10);
  }

  private void updateTimeLabel() {
    timeLabel = "T- " + countdown; // TODO
  }
  
  public void drawTimeLabel(SpriteBatch batch) {
      LiftOffGame.getInstance().resources.fontStandard.draw(batch, timeLabel, 720, 580);
  }

  public void postponeStart(long i) {
    countdown += i;
    updateTimeLabel();
  }
  
  public void loadCargo() {
    cargoLoaded = true;
  }
  
  public void loadCrew() {
    if (crewInside < 0f)
      crewInside = 0f;
  }
  
  public void checkRadar() {
    radarCheck = 40f;
  }
  
  public void tankFuel() {
    fuelInTank++;
  }
  
  public void platformOff() {
    platformOn = false;
  }

  
  public EndGameStory checkConditionsInGame () {
    
    if (crewInside > 120f) {
      return EndGameStory.CrewBored;
    }
    
    return EndGameStory.None;
  }
  
  public EndGameStory checkConditionsEndGame () {
    
    if (platformOn) {
      return EndGameStory.Platform;
    }
    
    if (fuelInTank == 0) {
      return EndGameStory.NoFuel;
    } 
    
    if (weather.getState() == Weather.State.Storm) {
      return EndGameStory.Weather;
    }
    
    if (weather.getState() == Weather.State.Wind) {
      if (percentageHit(33))
        return EndGameStory.Weather;
    }
    
    if (fuelInTank > 3) {
      return EndGameStory.TooMuchFuel;
    }
    
    if (radarCheck < 0f) {
      if (percentageHit(60))
        return EndGameStory.AirplaneOk;
      else 
        return EndGameStory.AirplaneCrash;
    }
    
    return EndGameStory.None;
  }
  
  private boolean percentageHit(float probability) {
    return random.nextFloat() <= probability;
  }
}
