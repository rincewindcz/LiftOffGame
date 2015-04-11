/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
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
  
  private ButtonActor pauseButton;
  
  public GameScreen() {
    spriteBatch = LiftOffGame.getInstance().getSpriteBatch();
    stage = new Stage(new StretchViewport(1024, 600), spriteBatch);

    Gdx.input.setInputProcessor(stage);
    
    createButtons();
    
    game = new Game(stage);
  }
  
  public void createButtons() {
    pauseButton = new ButtonActor(LiftOffGame.getInstance().resources.getNewSprite("button"));
    pauseButton.setBounds(0, 0, 100, 100);
    
    pauseButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("What the hell!");
      }});
    
    stage.addActor(pauseButton);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    stage.act(delta);
    stage.draw();
    game.act(delta);
    game.draw(spriteBatch);
  }
  
}
