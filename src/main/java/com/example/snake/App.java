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
                int tick = 0;

                void start() {
                    board = new Board(30, 20);
                    panel = new MainUI(board, this::restart, () -> tick);
                    frame.setContentPane(panel);
                    frame.pack();
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    panel.requestFocusInWindow();
                    timer = new Timer(board.getSpeed(), e -> {
                        tick++;
                        if (!board.isGameOver()) {
                            board.update();
                            panel.repaint();
                            timer.setDelay(board.getSpeed());
                        }
                    });
                    timer.start();
                }

                void restart() {
                    if (timer != null) timer.stop();
                    tick = 0;
                    start();
                }
            }

            new Game().start();
        });
    }
}
