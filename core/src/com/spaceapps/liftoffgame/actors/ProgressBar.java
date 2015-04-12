/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.spaceapps.liftoffgame.LiftOffGame;

/**
 *
 * @author craw
 */
public class ProgressBar extends Actor {
  private final Sprite bar;
  private final Sprite icon;
  private final Sprite rectangle;
  private float duration = 10f;
  private float value = 0f;
  private boolean enabled = false;
  private final int defaultWidth;
  private final String text;
  public enum ActionEvent {PlatformOff, EngineOn, None, CrewIn};
  public ActionEvent actionEvent = ActionEvent.None;


  public ProgressBar(Sprite icon, float duration, String text) {
    bar = LiftOffGame.getInstance().resources.getNewSprite("progressbar");
    bar.setColor(Color.GREEN);
    bar.setAlpha(0.75f);
    
    rectangle = LiftOffGame.getInstance().resources.getNewSprite("rectangle");
    rectangle.setColor(Color.BLACK);
    rectangle.setAlpha(0.33f);
    rectangle.setSize(520f, 100f);
    
    this.duration = duration;
    defaultWidth = bar.getRegionWidth();
    this.icon = icon;
    
    this.text = text;
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    bar.setPosition(getX(), getY());
    int w = (int) (defaultWidth*getPercentage());
    bar.setRegionWidth(w);
    bar.setSize(w, bar.getHeight());
    
    icon.setPosition(getX() - (icon.getWidth() + 10f), getY());
    
    rectangle.setPosition(getX() - 120, getY() - 10);
    
    if(enabled) {
      if (value < duration)
        value += delta;
      else
        enabled = false;
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    rectangle.draw(batch);
    bar.draw(batch);
    icon.draw(batch);
    LiftOffGame.getInstance().resources.fontStandard.draw(batch, text, bar.getX(), bar.getY() + 100);
  }

  public float getDuration() {
    return duration;
  }

  public void setDuration(float duration) {
    this.duration = duration;
  }
    
  public float getPercentage() {
    return (value/duration);
  }
  
  public boolean isFinished() {
    return value >= duration;
  }
  
  public void resetProgress() {
    value = 0f;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void start() {
    enabled = true;
  }
  
  public void stop() {
    enabled = false;
  }

}
