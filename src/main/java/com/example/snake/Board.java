package com.example.snake;
import java.util.Random;

public class Board {
    int width;
    int height;
    Snake snake;
    Point food;
    int score;
    boolean gameOver;
    int speed;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new Snake(width / 2, height / 2);
        this.score = 0;
        this.gameOver = false;
        this.speed = 150;
        spawnFood();
    }

    public void spawnFood() {
        Random rand = new Random();
        do {
            food = new Point(rand.nextInt(width), rand.nextInt(height));
        } while (snake.containsPoint(food));
    }

    public void update() {
        if (gameOver) return;

        snake.move();

        if (snake.getHead().equals(food)) {
            snake.grow();
            score += 10;
            spawnFood();

            speed = Math.max(50, speed - 5);
        }

        Point head = snake.getHead();
        if (head.x < 0 || head.x >= width || head.y < 0 || head.y >= height) {
            gameOver = true;
        }

        if (snake.checkSelfCollision()) {
            gameOver = true;
        }
    }
}