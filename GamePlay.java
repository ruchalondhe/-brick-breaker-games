/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author londh
 */
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt. Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class GamePlay extends JPanel implements KeyListener,ActionListener{
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer timer;
    private int delay = 8;
    private int playerX = 350;
    private int ballposX = 120;
    private int ballposY = 350;
    private int balldirX = -1;
    private int balldirY = -2;
    private Mapgenerator map;
    public GamePlay()
    {
         map = new Mapgenerator(3,7);
         addKeyListener(this);
         setFocusable(true);
         timer =new Timer(delay, this);
         setFocusTraversalKeysEnabled(false);
         timer.start();
    }
    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D) g);
        
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(685, 0, 3, 592);
        
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);
        
        g.setColor(Color.green);
        g.fillOval(ballposX, ballposY, 20, 20);
        if(ballposY>570)
        {
            play=false;
            balldirX = 0;
            balldirY =0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString(" Game Over Score: "+score, 190, 300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        if(totalbricks==0)
        {
            play=false;
            balldirY =-2;
            balldirX =-1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over:"+score, 190, 300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(play)
        {
          if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
              balldirY=-balldirY;
          }
          A:
          for(int i=0;i<map.map.length;i++)
          {
              for(int j=0;j<map.map.length;j++)
              {
                  if(map.map[i][j]>0)
                  {
                      int brickX = j*map.brickWidth+80;
                      int brickY = i*map.brickHeight+50;
                      int brickWidth = map.brickWidth;
                      int brickHeight = map.brickHeight;
                Rectangle rect = new Rectangle(brickX ,brickY,brickWidth,brickHeight);
                Rectangle ballrect =new Rectangle(ballposX,ballposY,20,20);
                Rectangle brickrect = rect;
                if(ballrect.intersects(brickrect)){
                    map.setBricksValue(0,i,j);
                    totalbricks-- ;
                    score+=5;
                    if(ballposX + 19<=brickrect.x||ballposX+1>=brickrect.x+brickWidth)
                    {
                        balldirX=-balldirX;
                    }
                    else{
                        balldirY=-balldirY;
                    }
                    break A;
                }
                  }
              }
          }
          ballposX+=balldirX;
          ballposY+=balldirY;
          if(ballposX<0)
          {
              balldirX=-balldirX;
          }
          if(ballposY<0)
          {
              balldirY=-balldirY;
          }
          if(ballposX>670)
          {
             balldirX=-balldirY; 
          }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT)
        {
        if(playerX>=600)
        {
            playerX = 600;
        }
        else{
            MoveRight();
        }
    }
        if(e.getKeyCode()== KeyEvent.VK_LEFT)
        {
            if(playerX<10){
               playerX=10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode()== KeyEvent.VK_ENTER)
        {
            if(!play){
                ballposX=120;
                ballposY=350;
                balldirX=-1;
                balldirY=-2;
                score=0;
                playerX=310;
                totalbricks = 21;
                map=new Mapgenerator(3,7);
                repaint();
            }
        }
    }
    public void MoveRight(){
        play=true;
        playerX+=20;
    }
    public void moveLeft()
    {
        play=true;
        playerX-=20;
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
            
   
    
    
    
    
    

