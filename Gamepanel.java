import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Gamepanel extends JPanel implements Runnable, KeyListener
{
    
     private static final long serialVersionUID = 1L;
        
     public static final int WIDTH = 500, HEIGHT = 500;
     
     private Thread thread;

     private Apple apple;
     private ArrayList<Apple> apples;

     private Random r;

     private  BodyPart b;
     private ArrayList<BodyPart> snake;
     
     private int xCoor = 10, yCoor = 10, size = 5;
     
     private boolean running;
     
     private boolean right = true, left = false, up = false, down = false;
     private int ticks = 0;
        
     public Gamepanel()
        {
            setFocusable(true);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            addKeyListener(this);
            
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            
            snake = new ArrayList<BodyPart>();
            apples = new ArrayList<Apple>();

            r = new Random();
            
            start();
        }
     public void start()
     {
         running = true;
         thread = new Thread(this);
         thread.start();
     }
     public void stop()
     {
         running = false;
         try
         {
             thread.join();
            }
         catch(InterruptedException e)
         {
             e.printStackTrace();
            }
     }
     public void tick()
     {
         if(snake.size() == 0)
         {
             b = new BodyPart(xCoor, yCoor, 10);
             snake.add(b);
         }
         ticks++;
         if(ticks > 250000)
         {
             if(right) xCoor++;
             if(left) xCoor--;
             if(up) yCoor--;
             if(down)yCoor++;
             
             ticks = 0;
             
             b = new BodyPart(xCoor, yCoor, 10);
             snake.add(b);
             
             if(snake.size() > size)
             {
                 snake.remove(0);
                }
         }
         if(apples.size() == 0)
         {
             int xCoor = r.nextInt(49);
             int yCoor = r.nextInt(49);

             apple = new Apple(xCoor, yCoor, 10);
             apples.add(apple);
         }
        }
     public void paint(Graphics g)
     {
         g.clearRect(0, 0, WIDTH, HEIGHT);
         
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, WIDTH, HEIGHT);
         
         for (int i = 0;  i< WIDTH/10; i++)
         {
             g.drawLine(i*10, 0, i*10, HEIGHT);
         }
         for (int i = 0;  i< HEIGHT/10; i++)
         {
             g.drawLine(i*10, 0, i*10, HEIGHT);
         }
         for(int i = 0; i < snake.size(); i++)
         {
             snake.get(i).draw(g);
         }
         for(int i = 0; i < apples.size(); i++)
         {
             apples.get(i).draw(g);
         }
     }
     public void run()
     {
         while(running)
         {
             tick();
             repaint();
         }
    }   
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left)
        {
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_LEFT && !right)
        {
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_UP && !down)
        {
            up = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_RIGHT && !up)
        {
            right = true;
            up = false;
            down = false;
        }
}
    public void keyReleased(KeyEvent arg0)
    {
    }
    public void keyTyped(KeyEvent arg0)
    {
    }
}
