/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.spaceapps.liftoffgame.LiftOffGame;
import com.spaceapps.liftoffgame.actors.ButtonActor;

/**
 *
 * @author craw
 */
public class Game {
  
  public static final long DEFAULT_COUNTDOWN = 154800;
  public static final long COUNTDOWN_STEP = 1;
  public static final long COUNTDOWN_RATE = 100;
  
  public ButtonActor rocket;
  public ButtonActor platform;
  private final Stage stage;
  
  private long countdown;
  private boolean countdownRunning = true;

  public Game(Stage stage) {
    this.stage = stage;
    
    countdown = DEFAULT_COUNTDOWN;
    
    rocket = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("rocket"));
    rocket.setPosition(600, 80);
    
    platform = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("platform"));
    platform.setPosition(400, 80);
    
    stage.addActor(rocket);
    stage.addActor(platform);
  }
  
  public void draw(SpriteBatch batch) {
    
  }
  
  public void act(float delta) {
    if (countdownRunning) {
      countdown -= COUNTDOWN_STEP * COUNTDOWN_RATE;      
    }
      
  }
  
  
}
