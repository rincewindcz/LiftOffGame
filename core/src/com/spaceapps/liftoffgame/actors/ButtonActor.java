/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author craw
 */
public class ButtonActor extends Actor {

  private Sprite image;

  public ButtonActor(Sprite image) {
    this.image = image;
  }

  @Override
  public void draw(Batch batch, float alpha) {
    image.draw(batch);
  }
  
  public void setImage(Sprite image) {
    this.image = image;
  }
  
  @Override
  public void setBounds(float x, float y, float width, float height) {
    super.setBounds(x, y, width, height);
    image.setBounds(x, y, width, height);
  }

  @Override
  public void setSize(float width, float height) {
    super.setSize(width, height);
    image.setSize(width, height);
  }

  @Override
  public void setPosition(float x, float y) {
    super.setPosition(x, y);
    super.setSize(image.getWidth(), image.getHeight());
    image.setPosition(x, y);
  }
  
  
}
