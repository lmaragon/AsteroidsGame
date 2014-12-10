SpaceShip battleStarGalactica = new SpaceShip();
LaserSight lazer = new LaserSight();
Star [] galaxy;
ArrayList <Asteroid> armageddon = new ArrayList <Asteroid>();
ArrayList <Bullet> fullMetalJacket = new ArrayList <Bullet>();
//Bullet fullMetalJacket = new Bullet(battleStarGalactica);
boolean keyz[] = new boolean [4];


int score = 0;
boolean gameOver = false;

public void setup() 
{
  size(1000,800);
  galaxy = new Star[1000];

  //ArrayList <Asteroid> planet = new ArrayList <Asteroid>();
  for (int k=0; k<10; k++)
  {
    armageddon.add(new Asteroid());
  }

  for (int i=0; i<galaxy.length; i++)
  {
    galaxy[i] = new Star();
  }


}

public void draw() 
{
  background(0);
  if (gameOver == true)
  {
    textSize(50);
    fill(0,222,255);
    textAlign(CENTER);
    text("GAME OVER", 500, 200);
    text("Score:"+score, 500, 400);
    text("Press N to start", 500, 600);
    battleStarGalactica = new SpaceShip();
    lazer = new LaserSight();
  }
  if (gameOver == false)
  {
    
  

  for (int i=0; i<galaxy.length; i++)
  {
    galaxy[i].starShow();
  }

  for (int j=0; j<armageddon.size(); j++)
  {
    armageddon.get(j).show();
    armageddon.get(j).move();
    double dis = dist(battleStarGalactica.getX(),battleStarGalactica.getY(),armageddon.get(j).getX(),armageddon.get(j).getY()); // asteroid collision w ship
    if (dis < 35)
    {
      armageddon.remove(j);
      armageddon.add(new Asteroid());
      gameOver = true;
    }

    for (int m=0; m<fullMetalJacket.size(); m++)
    {
      double dis2 = dist(fullMetalJacket.get(m).getX(),fullMetalJacket.get(m).getY(),armageddon.get(j).getX(),armageddon.get(j).getY()); // asteroid collision w bullet
      if (dis2 < 35)
      {
        armageddon.remove(j);
        fullMetalJacket.remove(m);
        armageddon.add(new Asteroid());
        score++;
        if (score % 10 == 0)
        {
          for (int h = 0; h<armageddon.size(); h++)
          {
            armageddon.get(h).setDirectionX(armageddon.get(h).getDirectionX()*2);
            armageddon.get(h).setDirectionY(armageddon.get(h).getDirectionY()*2);
          }
        }
        /*if (score == 10 || score == 20 || score == 30 || score == 40 || score == 50)
        {
          //armageddon.add(new Asteroid());
            for (int h=0; h<armageddon.size(); h++)
            {
              Asteroid faster = armageddon.get(h);
              faster.setDirectionX(faster.getDirectionX()*2);
              faster.setDirectionY(faster.getDirectionY()*2);
              if (gameOver == true)
              {
                faster.setDirectionX((int)(Math.random()*5)-1);
                faster.setDirectionY((int)(Math.random()*5)-1);
              }
            }
        }*/
        /*if (score > 0 && score < 500)
        {
          textSize(50);
          //text("Shotgun", 50,50);
          fullMetalJacket.add(new Bullet(battleStarGalactica));
          fullMetalJacket.add(new Bullet(battleStarGalactica));
          fullMetalJacket.add(new Bullet(battleStarGalactica));
          fullMetalJacket.add(new Bullet(battleStarGalactica));
          fullMetalJacket.add(new Bullet(battleStarGalactica));
          fullMetalJacket.add(new Bullet(battleStarGalactica));
        }   */
      }
    }
    
    System.out.println(dis);
    
  }

  for (int n=0; n<fullMetalJacket.size(); n++)
  {
    fullMetalJacket.get(n).show();
    fullMetalJacket.get(n).move();
    if (fullMetalJacket.get(n).getX() > 995 || fullMetalJacket.get(n).getX() < 5 || fullMetalJacket.get(n).getY() > 795 || fullMetalJacket.get(n).getY() < 5)
    {
      fullMetalJacket.remove(n);
    }
  }

  lazer.show();
  lazer.move();
  battleStarGalactica.show();
  battleStarGalactica.move();
  keyShipMove();
  textSize(50);
  fill(0,255,0);
  text(score,500,750);
}
}

public void keyPressed()
{
  if (keyCode == LEFT)  keyz[0] = true;
  if (keyCode == RIGHT)  keyz[1] = true;
  if (keyCode == UP)  keyz[2] = true;
  if (keyCode == DOWN)  keyz[3] = true;
  //if (key == 'b') keyz[4] = true;
  if (key == 'n') {gameOver = false; score = 0;}
}
 
public void keyReleased()
{
  if (keyCode == LEFT)  keyz[0] = false;
  if (keyCode == RIGHT)  keyz[1] = false;
  if (keyCode == UP)  keyz[2] = false;
  if (keyCode == DOWN)  keyz[3] = false;
  if (key == 'b')
  {
    battleStarGalactica.setX((int)random(1000));
    battleStarGalactica.setY((int)random(800));
    battleStarGalactica.setDirectionX(0);
    battleStarGalactica.setDirectionY(0);
    lazer.setX(battleStarGalactica.getX());
    lazer.setY(battleStarGalactica.getY());
    lazer.setDirectionX(0);
    lazer.setDirectionY(0);
  }
  if (key == ' ')
  {
    fullMetalJacket.add(new Bullet(battleStarGalactica));
  }
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
        lazer.rotate(-8);
      }
      if (keyz[1] == true)
      {
        battleStarGalactica.rotate(8);
        lazer.rotate(8);
      }
      if (keyz[2] == true)
      {
        battleStarGalactica.accelerate(.2);
        lazer.accelerate(.2);
      }
      if (keyz[3] == true)
      {
        battleStarGalactica.accelerate(-.2);
        lazer.accelerate(-.2);
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
    myColor = color(0, 255, 255);
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

class LaserSight extends SpaceShip  
{   
  public LaserSight()
  {
    corners = 11;
    xCorners = new int [corners];
    yCorners = new int [corners];
    myColor = color(255, 0, 0);
    myCenterX = 500;
    myCenterY = 400;
    myPointDirection = battleStarGalactica.getPointDirection();
    myDirectionX = battleStarGalactica.getDirectionX();
    myDirectionY = battleStarGalactica.getDirectionY();
    xCorners[0] = 5; yCorners[0] = -10;
    xCorners[1] = 10; yCorners[1] = -5;
    xCorners[2] = 15; yCorners[2] = 0;
    xCorners[3] = 1000; yCorners[3] = 0;
    xCorners[4] = 15; yCorners[4] = 0;
    xCorners[5] = 10; yCorners[5] = 5;
    xCorners[6] = 5; yCorners[6] = 10;
    xCorners[7] = -10; yCorners[7] = 10;
    xCorners[8] = -5; yCorners[8] = 5;
    xCorners[9] = -5; yCorners[9] = -5;
    xCorners[10] = -10; yCorners[10] = -10;
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
    myColor = (int)(Math.random()*200)+50;
    myCenterX = (int)(Math.random()*1000);
    myCenterY = (int)(Math.random()*800);
    myPointDirection = Math.random()*360;
    myDirectionX = (double)(Math.random()*5)-1;
    myDirectionY = (double)(Math.random()*5)-1;
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

class Bullet extends Floater
{
  public Bullet(SpaceShip xWing)
  {
    myCenterX = xWing.getX();
    myCenterY = xWing.getY();
    myPointDirection = xWing.getPointDirection() + (Math.random()*25-12);
    double dRadians = myPointDirection*(Math.PI/180);
    myDirectionX = 10 * Math.cos(dRadians) + xWing.getDirectionX();
    myDirectionY = 10 * Math.sin(dRadians) + xWing.getDirectionY();
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
  
  public void show()
  {
    fill(0,255,0);
    noStroke();
    ellipse((int)myCenterX, (int)myCenterY, 5, 5);
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