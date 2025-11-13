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

    // Hàm cung cấp tick
    private final java.util.function.IntSupplier tickSupplier;

    public MainUI(Board initBoard, Runnable onRestart, java.util.function.IntSupplier tickSupplier) {
        this.board = initBoard;
        this.tickSupplier = tickSupplier;
        setPreferredSize(new Dimension(initBoard.getWidth() * CELL_SIZE, initBoard.getHeight() * CELL_SIZE));
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
                        initBoard.getSnake().changeDirection(Direction.UP);
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        initBoard.getSnake().changeDirection(Direction.DOWN);
                        break;
                    case java.awt.event.KeyEvent.VK_LEFT:
                        initBoard.getSnake().changeDirection(Direction.LEFT);
                        break;
                    case java.awt.event.KeyEvent.VK_RIGHT:
                        initBoard.getSnake().changeDirection(Direction.RIGHT);
                        break;
                    case java.awt.event.KeyEvent.VK_ENTER:
                        if (initBoard.isGameOver() && onRestart != null) {
                            onRestart.run();
                        }
                        break;
                }
            }
        });
    }

    public MainUI(Board initBoard, Runnable onRestart) {
        this(initBoard, onRestart, () -> 0);
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
        String status = board.isGameOver() ? "Game Over" : "Playing";
        int tick = tickSupplier != null ? tickSupplier.getAsInt() : 0;
        g.drawString("Status: " + status + "  -  Score: " + board.getTotalScore() + "  -  Tick: " + tick, 10, 20);
    }

    private void drawSnake(Graphics g) {
        Point[] bodyList = board.getSnake().getBody();
        // Vẽ thân (bỏ đầu)
        if (imgBody != null && bodyList != null) {
            for (int i = 1; i < board.getSnake().getLength(); i++) {
                Point p = bodyList[i];
                g.drawImage(imgBody, p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
            }
        }
        // Vẽ đầu
        Point head = board.getSnake().getHead();
        BufferedImage headImg = imgHeadRight;
        Direction dir = board.getSnake().getDirection();
        if (dir == Direction.UP) headImg = imgHeadUp;
        else if (dir == Direction.DOWN) headImg = imgHeadDown;
        else if (dir == Direction.LEFT) headImg = imgHeadLeft;
        else if (dir == Direction.RIGHT) headImg = imgHeadRight;
        if (headImg != null) {
            g.drawImage(headImg, head.x * CELL_SIZE, head.y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
        }
    }

    private void drawFood(Graphics g) {
        if (imgFood != null && board.getFood() != null) {
            g.drawImage(imgFood, board.getFood().x * CELL_SIZE - (int)(0.25 * CELL_SIZE), board.getFood().y * CELL_SIZE  - (int)(0.25 * CELL_SIZE), (int)(CELL_SIZE * 1.5), (int)(CELL_SIZE * 1.5), null);
        }
    }

    // Vẽ caro xanh cho background
    private void drawCheckerBoard(Graphics g) {
        Color color1 = new Color(0xa6d03c); // xanh nhạt
        Color color2 = new Color(0xabd543); // xanh đậm
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if ((x + y) % 2 == 0) g.setColor(color1);
                else g.setColor(color2);
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, board.getWidth() * CELL_SIZE - 1, board.getHeight() * CELL_SIZE - 1);
    }
}