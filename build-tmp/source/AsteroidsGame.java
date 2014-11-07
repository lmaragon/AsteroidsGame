import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip battleStarGalactica = new SpaceShip();
Star [] galaxy;

public void setup() 
{
  size(800,500);
  galaxy = new Star[300];
  for (int i=0; i<galaxy.length; i++)
  {
    galaxy[i] = new Star();
  }
}

public void draw() 
{
  background(0);
  for (int i=0; i<galaxy.length; i++)
  {
    galaxy[i].starShow();
  }
  battleStarGalactica.show();
  battleStarGalactica.move();
}

public void keyPressed()
{
  if (keyCode == LEFT)
  {
    battleStarGalactica.rotate(-15);
  }
  if (keyCode == RIGHT)
  {
    battleStarGalactica.rotate(15);
  }
  if (keyCode == UP)
  {
    battleStarGalactica.accelerate(.2f);
  }
  if (keyCode == DOWN)
  {
    battleStarGalactica.accelerate(-.2f);
  }
  if (key == ' ')
  {
    battleStarGalactica.setX((int)random(800));
    battleStarGalactica.setY((int)random(500));
    battleStarGalactica.setDirectionX(0);
    battleStarGalactica.setDirectionY(0);
  }
}

class Star
{
  private int starX, starY, starSize;
  Star()
  {
    starX = (int)(Math.random()*800)+1;
    starY = (int)(Math.random()*500)+1;
    starSize = (int)(Math.random()*4)+1;
  }
  public void starShow()
  {
    fill(255, 213, 0);
    noStroke();
    ellipse(starX, starY, starSize, starSize);
  }
}

class SpaceShip extends Floater  
{   
    SpaceShip()
    {
      corners = 3;
      xCorners = new int [corners];
      yCorners = new int [corners];
      myColor = 255;
      myCenterX = 400;
      myCenterY = 250;
      myPointDirection = 0;
      myDirectionX = 0;
      myDirectionY = 0;
      xCorners[0] = 15; yCorners[0] = 0;
      xCorners[1] = -10; yCorners[1] = 8;
      xCorners[2] = -10; yCorners[2] = -8;
    }
    public void setX(int x){myCenterX = x;}
    public int getX(){return (int)myCenterX;}
    public void setY(int y){myCenterY = y;}
    public int getY(){return (int)myCenterY;}
    public void setDirectionX(double x){myDirectionX = x;}
    public double getDirectionX(){return myDirectionX;}
    public void setDirectionY(double y){myDirectionY = y;}
    public double getDirectionY(){return myDirectionY;}
    public void setPointDirection(int degrees){myPointDirection = degrees;}
    public double getPointDirection(){return myPointDirection;}
    
}
abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   
  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(int x);  
  abstract public int getX();   
  abstract public void setY(int y);   
  abstract public int getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}