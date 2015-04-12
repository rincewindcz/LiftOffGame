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

/**
 *
 * @author craw
 */
public class ButtonActor extends Actor {

  private Sprite image;
  private Sprite icon;
  private Color blendColor;

  public ButtonActor(Sprite image) {
    this.image = image;
    blendColor = Color.WHITE;
  }

  @Override
  public void draw(Batch batch, float alpha) {
    image.setColor(blendColor);
    image.draw(batch);
    if (icon != null) {
      icon.draw(batch);
    }
      
  }

  public void setBlendColor(Color blendColor) {
    this.blendColor = blendColor;
  }

  public void setImage(Sprite image) {
    this.image = image;
  }
  
  public void setIcon(Sprite image) {
    this.icon = image;
    icon.setPosition(getX() + getWidth()/2 - icon.getWidth()/2, getY() + getHeight()/2 - icon.getHeight()/2);
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

  @Override
  public void act(float delta) {
    super.act(delta);
    image.setPosition(getX(), getY());
    image.setRotation(getRotation());
  }

}
