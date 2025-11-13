package com.example.snake;
import java.util.Random;

public class Board {
    private int width;
    private int height;

    private Snake snake;
    private Food food;
    private int score;
    private boolean gameOver;
    private int speed;

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
            food = new Food(rand.nextInt(width), rand.nextInt(height), 10);
        } while (snake.containsPoint(food));
    }

    public void update() {
        if (gameOver) return;

        snake.move();

        if (snake.getHead().equals(food)) {
            snake.grow();
            score += food.getScore();
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getSpeed() {
        return speed;
    }
}