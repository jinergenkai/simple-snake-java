package com.example.snake;

public class Food extends Point {
    private int score;

    public Food(int x, int y, int score) {
      super(x, y);
      setScore(score);
    }

    public int getScore() {
      return score;
    }

    public void setScore(int score) {
      this.score = score;
    }
}