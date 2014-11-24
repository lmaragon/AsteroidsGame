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
ArrayList <Asteroid> planet = new ArrayList <Asteroid>();
boolean keyz[] = new boolean [5];

public void setup() 
{
  size(1000,800);
  galaxy = new Star[1000];

  //ArrayList <Asteroid> planet = new ArrayList <Asteroid>();
  for (int k=0; k<10; k++)
  {
    planet.add(new Asteroid());
  }

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

  for (int j=0; j<planet.size(); j++)
  {
    planet.get(j).show();
    planet.get(j).move();
    double dis = dist(battleStarGalactica.getX(),battleStarGalactica.getY(),planet.get(j).getX(),planet.get(j).getY());
    System.out.println(dis);
    if (dis < 40)
    {
      planet.remove(j);
      planet.add(new Asteroid());
    }
    /*if (planet.get(j).myDirectionX == 0 && planet.get(j).myDirectionY == 0)
    {
      planet.get(j).myDirectionX = (int)(Math.random()*3)-1;
      planet.get(j).myDirectionX = (int)(Math.random()*3)-1;
    }*/
  }

  battleStarGalactica.show();
  battleStarGalactica.move();
  keyShipMove();

}

public void keyPressed()
{
  if (keyCode == LEFT)  keyz[0] = true;
  if (keyCode == RIGHT)  keyz[1] = true;
  if (keyCode == UP)  keyz[2] = true;
  if (keyCode == DOWN)  keyz[3] = true;
  if (key == ' ') keyz[4] = true;
}
 
public void keyReleased()
{
  if (keyCode == LEFT)  keyz[0] = false;
  if (keyCode == RIGHT)  keyz[1] = false;
  if (keyCode == UP)  keyz[2] = false;
  if (keyCode == DOWN)  keyz[3] = false;
  if (key == ' ') keyz[4] = false;
}

public void keyShipMove()
{
  for (int i = 0; i < keyz.length; i++)
  {
    //System.out.println(keyz[i]);
    if (keyz[i])
    {
      if (keyz[0] == true)
      {
        battleStarGalactica.rotate(-8);
      }
      if (keyz[1] == true)
      {
        battleStarGalactica.rotate(8);
      }
      if (keyz[2] == true)
      {
        battleStarGalactica.accelerate(.2f);
      }
      if (keyz[3] == true)
      {
        battleStarGalactica.accelerate(-.2f);
      }
      if (keyz[4] == true)
      {
        battleStarGalactica.setX((int)random(1000));
        battleStarGalactica.setY((int)random(800));
        battleStarGalactica.setDirectionX(0);
        battleStarGalactica.setDirectionY(0);
        noFill();
        strokeWeight(5);
        stroke(255,0,0);
        rect(battleStarGalactica.getX()-25, battleStarGalactica.getY()-25, 50,50);
    }
  }
}
}

class Star
{
  private int starX, starY, starSize;
  Star()
  {
    starX = (int)(Math.random()*1000)+1;
    starY = (int)(Math.random()*800)+1;
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
  public SpaceShip()
  {
    corners = 9;
    xCorners = new int [corners];
    yCorners = new int [corners];
    myColor = color(218, 53, 53);
    myCenterX = 500;
    myCenterY = 400;
    myPointDirection = 0;
    myDirectionX = 0;
    myDirectionY = 0;
    xCorners[0] = 5; yCorners[0] = -10;
    xCorners[1] = 10; yCorners[1] = -5;
    xCorners[2] = 15; yCorners[2] = 0;
    xCorners[3] = 10; yCorners[3] = 5;
    xCorners[4] = 5; yCorners[4] = 10;
    xCorners[5] = -10; yCorners[5] = 10;
    xCorners[6] = -5; yCorners[6] = 5;
    xCorners[7] = -5; yCorners[7] = -5;
    xCorners[8] = -10; yCorners[8] = -10;
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

class Asteroid extends Floater
{
  private int myRotation;
  public Asteroid()
  {
    corners = 8;
    xCorners = new int [corners];
    yCorners = new int [corners];
    myColor = 100;
    myCenterX = (int)(Math.random()*1000);
    myCenterY = (int)(Math.random()*800);
    myPointDirection = Math.random()*360;
    myDirectionX = (int)(Math.random()*5)-1;
    myDirectionY = (int)(Math.random()*5)-1;
    xCorners[0] = 15; yCorners[0] = -25;
    xCorners[1] = 25; yCorners[1] = -15;
    xCorners[2] = 25; yCorners[2] = 15;
    xCorners[3] = 15; yCorners[3] = 25;
    xCorners[4] = -15; yCorners[4] = 25;
    xCorners[5] = -25; yCorners[5] = 15;
    xCorners[6] = -25; yCorners[6] = -15;
    xCorners[7] = -15; yCorners[7] = -25;
    myRotation = (int)(Math.random()*4)-2;
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

  public void move ()
  {      
    rotate(myRotation);
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
