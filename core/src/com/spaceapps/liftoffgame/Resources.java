/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoffgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import java.util.HashMap;

/**
 *
 * @author craw
 */
public class Resources {
  
  public final static String GAME_ATLAS = "atlas/atlas.atlas";
  public final static String STANDARD_FONT = "font/ribeye.fnt";
  public final static String MUSIC = "sound/music.ogg";
  public final static String BACKGROUND = "background.png";
  public final static float RATIO = 1.0f;
  
  public TextureAtlas atlas;
  public AssetManager assetManager = new AssetManager();
  public BitmapFont fontStandard;
  private final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>(64);
  private Sprite emptySprite;
  public Sound[] sounds;
  public Music   music;
  public Texture backgroundTexture;

  public Resources() {
    loadAssets();
  }

  public void playSound(int n) {
      sounds[n].play();
  }
  
  public void loadFont() {
    fontStandard = new BitmapFont(Gdx.files.internal(STANDARD_FONT));
  }
  
  public Sprite getSprite(String name) {
    Sprite sprite = sprites.get(name);
    return (sprite != null) ? sprite : emptySprite;
  }
  
  public Sprite getNewSprite(String name) {
    Sprite sprite = sprites.get(name);
    return new Sprite((sprite != null) ? sprite : emptySprite);
  }
  
  private void createSprites(float ratio) {
    Array<TextureAtlas.AtlasRegion> regions = atlas.getRegions();
    for (int i = 0; i < regions.size; i++) {
      
      Sprite sprite = new Sprite(regions.get(i));
      
      sprite.setSize(sprite.getWidth()*ratio, sprite.getHeight()*ratio);
      sprite.setOrigin(sprite.getWidth()/2f, sprite.getHeight()/2f);
      
      sprites.put(regions.get(i).name, sprite);
      
      if (i == 0) {
        emptySprite = sprite;
      }
    }
    LiftOffGame.logInfo(regions.size + " sprites loaded and created.");
  }
  
  private void loadSounds(int number) {
    sounds = new Sound[number];
    for (int i =0; i < number; i++) {
      sounds[i] = Gdx.audio.newSound(Gdx.files.internal("sound/sound" + i + ".ogg"));
    }
  }
  
  private void loadAssets() {
    assetManager.load(GAME_ATLAS, TextureAtlas.class);
    assetManager.load(BACKGROUND, Texture.class);
    assetManager.finishLoading();
    
    atlas = assetManager.get(GAME_ATLAS);
    backgroundTexture = assetManager.get(BACKGROUND);
    music = Gdx.audio.newMusic(Gdx.files.internal(MUSIC));
    loadFont();

    createSprites(RATIO);
    
    loadSounds(6);

    LiftOffGame.logInfo("Loading assets done!");
  }
}
