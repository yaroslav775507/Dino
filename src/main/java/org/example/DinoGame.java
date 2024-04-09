package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class DinoGame extends JFrame implements KeyListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final int GROUND_HEIGHT = 50;
    private static final int DINO_WIDTH = 20;
    private static final int DINO_HEIGHT = 40;
    private static final int CACTUS_WIDTH = 20;
    private static final int CACTUS_HEIGHT = 40;
    private static final int CACTUS_SPEED = 10;
    private static final int JUMP_SPEED = 15;
    private static final int SCORE_INCREMENT = 1000;
    private static final int SPEED_INCREMENT = 1;

    private boolean isJumping = false;
    private boolean isGameOver = false;
    private int dinoY = HEIGHT - GROUND_HEIGHT - DINO_HEIGHT;
    private int dinoSpeedY = 0;
    private int cactusSpeed = CACTUS_SPEED;
    private int score = 0;
    private ArrayList<Rectangle2D> cacti = new ArrayList<>();
    private Random random = new Random();
    private JPanel gamePanel;
    private JLabel scoreLabel;

    public DinoGame() {
        setSize(WIDTH, HEIGHT);
        setTitle("Dino Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(this);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                render(g);
            }
        };
        contentPane.add(gamePanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("Score: 0");
        contentPane.add(scoreLabel, BorderLayout.NORTH);

        createCactus();

        Timer timer = new Timer(20, e -> {
            update();
            gamePanel.repaint();
        });
        timer.start();
    }

    private void createCactus() {
        cacti.clear();
        Rectangle2D cactus = new Rectangle(WIDTH, dinoY, CACTUS_WIDTH, CACTUS_HEIGHT);
        cacti.add(cactus);
    }

    private void update() {
        if (!isGameOver) {
            if (isJumping) {
                dinoSpeedY -= 1;
                dinoY -= dinoSpeedY;
                if (dinoY >= HEIGHT - GROUND_HEIGHT - DINO_HEIGHT) {
                    dinoY = HEIGHT - GROUND_HEIGHT - DINO_HEIGHT;
                    dinoSpeedY = 0;
                    isJumping = false;
                }
            }
            for (Rectangle2D cactus : cacti) {
                cactus.setRect(cactus.getX() - cactusSpeed, HEIGHT - GROUND_HEIGHT - CACTUS_HEIGHT, cactus.getWidth(), cactus.getHeight());
                if (cactus.intersects(new Rectangle(0, dinoY, DINO_WIDTH, DINO_HEIGHT))) {
                    isGameOver = true;
                }
            }

            if (cacti.get(0).getX() + CACTUS_WIDTH < 0) {
                cacti.remove(0);
                //int height = random.nextInt(HEIGHT / 4);
                Rectangle2D cactus = new Rectangle(WIDTH, HEIGHT - GROUND_HEIGHT - CACTUS_HEIGHT, CACTUS_WIDTH, CACTUS_HEIGHT);
                cacti.add(cactus);


                increaseScoreAndSpeed();
            }
        }
    }


    private void increaseScoreAndSpeed() {
        score++;
        scoreLabel.setText("Score: " + score);
        if (score % SCORE_INCREMENT == 0) {
            cactusSpeed += SPEED_INCREMENT;
        }
    }

    private void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GRAY);
        g.fillRect(0, HEIGHT - GROUND_HEIGHT, WIDTH, GROUND_HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(0, dinoY, DINO_WIDTH, DINO_HEIGHT);
        g.setColor(Color.RED);
        for (Rectangle2D cactus : cacti) {
            g.fillRect((int) cactus.getX(), (int) cactus.getY(), (int) cactus.getWidth(), (int) cactus.getHeight());
        }

        if (isGameOver) {
            g.setColor(Color.BLACK);
            g.drawString("Game Over!", WIDTH / 2 - 30, HEIGHT / 2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping) {
            isJumping = true;
            dinoSpeedY = JUMP_SPEED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        DinoGame dinoGame = new DinoGame();
        dinoGame.setVisible(true);
    }
}
