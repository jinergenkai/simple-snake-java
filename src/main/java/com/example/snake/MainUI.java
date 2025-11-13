package com.example.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainUI extends JPanel {
    private Board board;
    private static final int CELL_SIZE = 20;

    // Biến ảnh
    private BufferedImage imgFood, imgBody, imgHeadUp, imgHeadDown, imgHeadLeft, imgHeadRight;

    private Runnable onRestart;

    public MainUI(Board initBoard, Runnable onRestart) {
        this.board = initBoard;
        this.onRestart = onRestart;
        setPreferredSize(new Dimension(initBoard.width * CELL_SIZE, initBoard.height * CELL_SIZE));
        setBackground(Color.WHITE);

        // Load ảnh
        try {
            imgFood = ImageIO.read(new File("assets/apple.png"));
            imgBody = ImageIO.read(new File("assets/body.png"));
            imgHeadUp = ImageIO.read(new File("assets/head_up.png"));
            imgHeadDown = ImageIO.read(new File("assets/head_down.png"));
            imgHeadLeft = ImageIO.read(new File("assets/head_left.png"));
            imgHeadRight = ImageIO.read(new File("assets/head_right.png"));
        } catch (IOException e) {
            System.err.println("Không thể load ảnh assets: " + e.getMessage());
        }

        setFocusable(true);
        requestFocusInWindow();

        // Lắng nghe phím điều khiển rắn
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_UP:
                        initBoard.snake.changeDirection("UP");
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        initBoard.snake.changeDirection("DOWN");
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        initBoard.snake.changeDirection("LEFT");
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        initBoard.snake.changeDirection("RIGHT");
                        break;
                    case java.awt.event.KeyEvent.VK_ENTER:
                        if (initBoard.gameOver && onRestart != null) {
                            onRestart.run();
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCheckerBoard(g);
        drawStatus(g);
        drawSnake(g);
        drawFood(g);
        drawBorder(g);
    }

    private void drawStatus(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        String status = board.gameOver ? "Game Over" : "Playing";
        g.drawString("Status: " + status + "  -  Score: " + board.score, 10, 20);
    }

    private void drawSnake(Graphics g) {
        java.util.List<Point> bodyList = board.snake.body;
        // Vẽ thân (bỏ đầu)
        if (imgBody != null) {
            for (int i = 1; i < bodyList.size(); i++) {
                Point p = bodyList.get(i);
                g.drawImage(imgBody, p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            }
        }
        // Vẽ đầu
        Point head = board.snake.getHead();
        BufferedImage headImg = imgHeadRight;
        String dir = board.snake.direction;
        if (dir.equals("UP")) headImg = imgHeadUp;
        else if (dir.equals("DOWN")) headImg = imgHeadDown;
        else if (dir.equals("LEFT")) headImg = imgHeadLeft;
        else if (dir.equals("RIGHT")) headImg = imgHeadRight;
        if (headImg != null) {
            g.drawImage(headImg, head.x * CELL_SIZE, head.y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
        }
    }

    private void drawFood(Graphics g) {
        if (imgFood != null) {
            g.drawImage(imgFood, board.food.x * CELL_SIZE - (int)(0.25 * CELL_SIZE), board.food.y * CELL_SIZE  - (int)(0.25 * CELL_SIZE), (int)(CELL_SIZE * 1.5), (int)(CELL_SIZE * 1.5), null);
        }
    }

    // Vẽ caro xanh cho background
    private void drawCheckerBoard(Graphics g) {
        Color color1 = new Color(0xa6d03c); // xanh nhạt
        Color color2 = new Color(0xabd543); // xanh đậm
        for (int y = 0; y < board.height; y++) {
            for (int x = 0; x < board.width; x++) {
                if ((x + y) % 2 == 0) g.setColor(color1);
                else g.setColor(color2);
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, board.width * CELL_SIZE - 1, board.height * CELL_SIZE - 1);
    }
}