/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spaceapps.liftoff.weather;
import java.util.Random;

/**
 *
 * @author Ornela
 */
public class Weather {
    public enum State {
      Normal, Wind, Storm
  };
    private State state;
    private Random random;

    public Weather() {
        random = new Random();
    }
        
    public void update (){
      if (state==State.Normal){
          if (percentageHit(0.5f))
              state = State.Wind;
      }
      if (state==State.Wind){
          if (percentageHit(0.33f))
              state = State.Normal;
          else if (percentageHit(0.33f))
              state = State.Storm;
          else 
              state = State.Wind;
      }
          if (state==State.Storm){
          if (percentageHit(0.5f))
              state = State.Wind;
          else if (percentageHit(0.4f))
              state = State.Normal;
          else 
              state = State.Storm;
      }          
    }
        public boolean percentageHit(float probability) {
        return random.nextFloat() <= probability;
    }   
}
