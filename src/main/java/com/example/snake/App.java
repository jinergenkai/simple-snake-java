package com.example.snake;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game - Java Basic Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            class Game {
                Board board;
                MainUI panel;
                Timer timer;

                void start() {
                    board = new Board(30, 20);
                    panel = new MainUI(board, this::restart);
                    frame.setContentPane(panel);
                    frame.pack();
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    panel.requestFocusInWindow();
                    timer = new Timer(board.speed, e -> {
                        if (!board.gameOver) {
                            board.update();
                            panel.repaint();
                            timer.setDelay(board.speed);
                        }
                    });
                    timer.start();
                }

                void restart() {
                    if (timer != null) timer.stop();
                    start();
                }
            }

            new Game().start();
        });
    }
}
