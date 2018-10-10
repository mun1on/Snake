import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener
{
    private ImageIcon titleImage;

    private int[] snakeXLength = new int[750];
    private int[] snakeYLength = new int[750];

    private int[] enemyXPosition = new int[] {25,50,75,100,125,150,175,200,225,250,
                                              275,300,325,350,375,400,425,450,475,
                                              500,525,550,575,600,625,650,675,700,
                                              725,750,775,800,825,850};
    private int[] enemyYPosition = new int[] {75,100,125,150,175,200,225,250,
                                              275,300,325,350,375,400,425,450,475,
                                              500,525,550,575,600,625};

    private ImageIcon enemyImage;
    private Random random = new Random();
    private int randomXPos = random.nextInt(34);
    private int randomYPos = random.nextInt(23);

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private boolean vkLeft = true;
    private boolean vkRight = true;
    private boolean vkUp = true;
    private boolean vkDown = true;

    private int lengthOfSnake = 3;
    private int score = 0;
    private int moves = 0;

    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;
    private ImageIcon snakeBody;

    private Timer timer;
    private int delay = 100;



    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        if (moves == 0) {
            snakeXLength[2] = 50;
            snakeXLength[1] = 75;
            snakeXLength[0] = 100;

            snakeYLength[2] = 100;
            snakeYLength[1] = 100;
            snakeYLength[0] = 100;
        }

        // draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        // draw the title image
        titleImage = new ImageIcon("src/assets/titleSnake.png");
        titleImage.paintIcon(this, g, 25, 11);

        // draw border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        // draw background for the gameplay
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        // draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores: " + score, 780, 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: " + lengthOfSnake, 780, 50);

        // draw the body of snake start position
        if (moves == 0) {
            rightMouth = new ImageIcon("src/assets/moveRight.png");
            rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
        }


        for (int i = 0; i < lengthOfSnake; i++) {
            if (i == 0 && left) {
                leftMouth = new ImageIcon("src/assets/moveLeft.png");
                leftMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i == 0 && right) {
                rightMouth = new ImageIcon("src/assets/moveRight.png");
                rightMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i == 0 && up) {
                upMouth = new ImageIcon("src/assets/moveUp.png");
                upMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
            if (i == 0 && down) {
                downMouth = new ImageIcon("src/assets/moveDown.png");
                downMouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }

            if (i != 0) {
                snakeBody = new ImageIcon("src/assets/body.png");
                snakeBody.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
            }
        }

        enemyImage = new ImageIcon("src/assets/enemy.png");

        if (enemyXPosition[randomXPos] == snakeXLength[0] &&
                enemyYPosition[randomYPos] == snakeYLength[0])
        {
            lengthOfSnake++;
            if(score == 0)
            {
                score++;
            }
            else
            {
                score+=lengthOfSnake;
            }
            randomXPos = random.nextInt(34);
            randomYPos = random.nextInt(23);
        }

        // Score for users

        enemyImage.paintIcon(this, g, enemyXPosition[randomXPos], enemyYPosition[randomYPos]);

        for (int b = 1; b < lengthOfSnake; b++)
        {
            if (snakeXLength[b] == snakeXLength[0] && snakeYLength[b] == snakeYLength[0])
            {
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Press SPACE to RESTART", 310, 340);

                vkLeft = false;
                vkRight = false;
                vkUp = false;
                vkDown = false;
            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(right)
        {
            for(int i=lengthOfSnake-1; i>=0; i--)
            {
                snakeYLength[i+1]=snakeYLength[i];
            }
            for(int i=lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeXLength[i] = snakeXLength[i] + 25;
                }
                else
                {
                    snakeXLength[i] = snakeXLength[i-1];
                }
                if(snakeXLength[i] > 850)
                {
                    snakeXLength[i] = 25;
                }

                repaint();
            }
        }
        if(left)
        {
            for(int i=lengthOfSnake-1; i>=0; i--)
            {
                snakeYLength[i+1]=snakeYLength[i];
            }
            for(int i=lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeXLength[i] = snakeXLength[i] - 25;
                }
                else
                {
                    snakeXLength[i] = snakeXLength[i-1];
                }
                if(snakeXLength[i] < 25)
                {
                    snakeXLength[i] = 850;
                }

                repaint();
            }
        }
        if(up)
        {
            for(int i=lengthOfSnake-1; i>=0; i--)
            {
                snakeXLength[i+1]=snakeXLength[i];
            }
            for(int i=lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeYLength[i] = snakeYLength[i] - 25;
                }
                else
                {
                    snakeYLength[i] = snakeYLength[i-1];
                }
                if(snakeYLength[i] < 75)
                {
                    snakeYLength[i] = 625;
                }

                repaint();
            }
        }
        if(down)
        {
            for(int i=lengthOfSnake-1; i>=0; i--)
            {
                snakeXLength[i+1]=snakeXLength[i];
            }
            for(int i=lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeYLength[i] = snakeYLength[i] + 25;
                }
                else
                {
                    snakeYLength[i] = snakeYLength[i-1];
                }
                if(snakeYLength[i] > 625)
                {
                    snakeYLength[i] = 75;
                }

                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            vkLeft = true;
            vkRight = true;
            vkUp = true;
            vkDown = true;

            moves=0;
            score=0;
            lengthOfSnake=3;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && vkRight)
        {
            moves++;
            right = true;
            if(!left)
            {
                right = true;
            }
            else
            {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT && vkLeft)
        {
            moves++;
            left = true;
            if(!right)
            {
                left = true;
            }
            else
            {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP && vkUp)
        {
            moves++;
            up = true;
            if(!down)
            {
                up = true;
            }
            else
            {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN && vkDown)
        {
            moves++;
            down = true;
            if(!up)
            {
                down = true;
            }
            else
            {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
