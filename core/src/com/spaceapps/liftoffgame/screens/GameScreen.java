/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.spaceapps.liftoffgame.LiftOffGame;
import com.spaceapps.liftoffgame.actors.ButtonActor;
import com.spaceapps.liftoffgame.game.Game;

/**
 *
 * @author craw
 */
public class GameScreen extends ScreenAdapter {

  private final Stage stage;
  private final SpriteBatch spriteBatch;
  private final Game game;
  private final Texture backgroundTexture;
  
  private ButtonActor engineButton;
  private ButtonActor cargoButton;
  private ButtonActor fuelButton;
  private ButtonActor crewButton;
  private ButtonActor radarButton;
  private ButtonActor platformButton;
  private ButtonActor goButton;
  private ButtonActor nogoButton;
  
  public GameScreen() {
    spriteBatch = LiftOffGame.getInstance().getSpriteBatch();
    stage = new Stage(new StretchViewport(1024, 600), spriteBatch);
    backgroundTexture = LiftOffGame.getInstance().resources.backgroundTexture;

    Gdx.input.setInputProcessor(stage);
    
    createButtons();
    
    game = new Game(stage);
  }
  
  public void createButtons() {
    engineButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("engine"));
    engineButton.setBounds(0, 0, 150, 100);
    
    engineButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("What the hell!");
      }});
    
    stage.addActor(engineButton);
    
    cargoButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("cargo"));
    cargoButton.setBounds(0, 100,150, 100);
    
    cargoButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("Hello!");
      }});
    
    stage.addActor(cargoButton);
    
    fuelButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("fuel"));
    fuelButton.setBounds(0, 200, 150, 100);
    
    fuelButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("What's up!");
      }});
    
    stage.addActor(fuelButton);
    
    crewButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("astronaut"));
    crewButton.setBounds(0, 300, 150, 100);
    
    crewButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("Hi!");
      }});
    
    stage.addActor(crewButton);
    
    radarButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("radar"));
    radarButton.setBounds(0, 400, 150, 100);
    
    radarButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("Hey!");
      }});
    
    stage.addActor(radarButton);
    
    platformButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("platform_on"));
    platformButton.setBounds(0, 500, 150, 100);
    
    platformButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("Hei!");
      }});
    
    stage.addActor(platformButton);
    
    goButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("rock2"));
    goButton.setBounds(874, 300, 150, 100);
    
    goButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("GO!");
      }});
    
    stage.addActor(goButton);
    
    nogoButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("rock2"));
    nogoButton.setBounds(874, 200, 150, 100);
    
    nogoButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("NO GO!");
      }});
    
    stage.addActor(nogoButton);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    game.act(delta);
    stage.act(delta);
    
    spriteBatch.begin();
    
    spriteBatch.draw(backgroundTexture, 0, 0);
    game.draw(spriteBatch);
    
    spriteBatch.end();
    
    stage.draw();
    
  }
  
}