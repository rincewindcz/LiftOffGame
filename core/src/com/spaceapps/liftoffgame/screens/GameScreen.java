/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.spaceapps.liftoffgame.LiftOffGame;
import com.spaceapps.liftoffgame.actors.ButtonActor;
import com.spaceapps.liftoffgame.actors.ProgressBar;
import com.spaceapps.liftoffgame.game.Game;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author craw
 */
public class GameScreen extends ScreenAdapter {

  private final Stage stage;
  private final SpriteBatch spriteBatch;
  private final Game game;
  private final Texture backgroundTexture;
  private float bgY = 0;
  private float bgColor = 1;
  
  private ButtonActor engineButton;
  private ButtonActor cargoButton;
  private ButtonActor fuelButton;
  private ButtonActor crewButton;
  private ButtonActor radarButton;
  private ButtonActor platformButton;
  private ButtonActor goButton;
  private ButtonActor nogoButton;
  
  private final ArrayList<ProgressBar> progressBars = new ArrayList<ProgressBar>(8);
  
  public GameScreen() {
    spriteBatch = LiftOffGame.getInstance().getSpriteBatch();
    stage = new Stage(new StretchViewport(1024, 600), spriteBatch);
    backgroundTexture = LiftOffGame.getInstance().resources.backgroundTexture;

    Gdx.input.setInputProcessor(stage);
    
    createButtons();
    
    game = new Game(stage);
  }
  
  public void createButtons() {
    engineButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    engineButton.setBounds(0, 0, 150, 100);
    engineButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("engine"));
    
    engineButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        addProgressBar("engine", 10, "Starting engines", ProgressBar.ActionEvent.EngineOn);
        engineButton.setBlendColor(Color.BLUE);
        LiftOffGame.getInstance().resources.playSound(1);
      }});
    
    stage.addActor(engineButton);
    
    cargoButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    cargoButton.setBounds(0, 100,150, 100);
    cargoButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("cargo"));
    
    cargoButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        addProgressBar("cargo", 6, "Loading cargo", ProgressBar.ActionEvent.None);
        cargoButton.setBlendColor(Color.RED);
        LiftOffGame.getInstance().resources.playSound(1);
        game.loadCargo();
      }});
    
    stage.addActor(cargoButton);
    
    fuelButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    fuelButton.setBounds(0, 200, 150, 100);
    fuelButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("fuel"));
    
    fuelButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        addProgressBar("fuel", 15f, "Fueling rocket", ProgressBar.ActionEvent.None);
        rocketTankFullAnimation();
        fuelButton.setBlendColor(Color.YELLOW);
        LiftOffGame.getInstance().resources.playSound(1);
        game.tankFuel();
      }});
    
    stage.addActor(fuelButton);
    
    crewButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    crewButton.setBounds(0, 300, 150, 100);
    crewButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("astronaut"));
    
    crewButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        addProgressBar("astronaut", 12, "Boarding of crew", ProgressBar.ActionEvent.CrewIn);
        crewButton.setBlendColor(Color.ORANGE);
        LiftOffGame.getInstance().resources.playSound(1);
        game.loadCrew();
      }});
    
    stage.addActor(crewButton);
    
    radarButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    radarButton.setBounds(0, 400, 150, 100);
    radarButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("radar"));
    
    radarButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {

        addProgressBar("radar", 2, "Radar check in progress", ProgressBar.ActionEvent.None);
        radarButton.setBlendColor(Color.GREEN);
        LiftOffGame.getInstance().resources.playSound(1);
        
      }});
    
    stage.addActor(radarButton);
    
    platformButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    platformButton.setBounds(0, 500, 150, 100);
    platformButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("platform_on"));
    
    platformButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        addProgressBar("platform_on", 10, "Platform", ProgressBar.ActionEvent.PlatformOff);
        platformButton.setBlendColor(Color.PURPLE);
        LiftOffGame.getInstance().resources.playSound(1);
      }});
    
    stage.addActor(platformButton);
    
    Sprite buttonSprite = LiftOffGame.getInstance().resources.getNewSprite("button");
    buttonSprite.flip(true, false);
    goButton = new ButtonActor(buttonSprite);
    goButton.setBounds(874, 300, 150, 100);
    goButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("gotext"));
    
    goButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        LiftOffGame.getInstance().resources.playSound(3);
        LiftOffGame.getInstance().resources.playSound(4);
        
        Game.EndGameStory s = game.checkConditionsEndGame();
        if (s != Game.EndGameStory.None) {
          System.out.println("Failure: " + s.toString());
          rocketCrashAnimation();
        }
        else
          game.launchRocket();
      }});
    
    stage.addActor(goButton);
    
    buttonSprite = LiftOffGame.getInstance().resources.getNewSprite("button");
    buttonSprite.flip(true, false);
    nogoButton = new ButtonActor(buttonSprite);
    nogoButton.setBounds(874, 200, 150, 100);
    nogoButton.setIcon(LiftOffGame.getInstance().resources.getNewSprite("nogotext"));
    
    nogoButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        LiftOffGame.getInstance().resources.playSound(5);
        game.postponeStart(30);

      }});
    
    stage.addActor(nogoButton);
  }
  
  private void checkProgressBars() {
    for (Iterator<ProgressBar> it = progressBars.iterator(); it.hasNext();) {
      ProgressBar o = it.next();
      if (o.isFinished()) {

        switch(o.actionEvent) {
          case CrewIn:
            game.loadCrew();
            break;
          case EngineOn:
            game.startEngines();
            rocketEngineAnimation();
            break;
          case PlatformOff:
            game.platformOff();
            rocketPlatformOnAnimation();
            break;
          case None:
            default:
              break;
        }

        it.remove();
        o.remove();
      }
    }
    
    for (int i = 0; i < progressBars.size(); i++) {
      progressBars.get(i).setPosition(400, 110 * i + 25);
    }
  }
  
  public void addProgressBar(String icon, float time, String label, ProgressBar.ActionEvent aevent) {
    ProgressBar progressBar = new ProgressBar(LiftOffGame.getInstance().resources.getNewSprite(icon), time, label);
    progressBar.start();
    progressBar.setPosition(400, 110 * (progressBars.size()) + 25);
    progressBar.actionEvent = aevent;
    stage.addActor(progressBar);
    progressBars.add(progressBar);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(bgColor, bgColor, bgColor, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    game.act(delta);
    stage.act(delta);
    checkProgressBars();
    
    spriteBatch.begin();
    flyingAnimation(delta);
    spriteBatch.draw(backgroundTexture, 0, bgY);
    game.draw(spriteBatch);
    
    spriteBatch.end();
    
    stage.draw();
    
  }
  
  public void rocketEngineAnimation(){
      MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(game.rocket.getX(), 60);
        action.setInterpolation(Interpolation.swingIn);
        action.setDuration(0.8f);
        game.rocket.addAction(action);   
  }
  
  public void rocketCrashAnimation() {
    MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(420, 0);
        action.setDuration(1f);
        game.rocket.addAction(action);
        
        RotateToAction action2 = Actions.action(RotateToAction.class);
        action2.setRotation(-180f);
        action2.setDuration(1f);
        game.rocket.addAction(action2);
  }
  
  public void rocketTankFullAnimation(){
      
  }
  
  public void rocketPlatformOnAnimation(){
      MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(-100, 100);
        action.setDuration(1f);
        game.platform.addAction(action);
        
        AlphaAction action6 = Actions.action(AlphaAction.class);
       // action6.setRotation(90f);
        action6.setColor(Color.CLEAR);
        action6.setDuration(1f);
        game.platform.addAction(action6);
  }
  
  public void flyingAnimation(float delta) {
    if (game.isInAir()) {
      
      if (bgY < -400f) {
        if (bgColor > 0f)
          bgColor -= delta*0.5f;
      }
      else {
        if (bgY < -1f)
          bgY -= -delta*bgY*3;
        else
          bgY -= delta*10;
      }
    }
  }
}
