package com.example.snake;

public class Point {
  int x; 
  int y; 
  
  public Point(int x, int y) {
      this.x = x;
      this.y = y;
  }
  
  public boolean equals(Point other) {
      return this.x == other.x && this.y == other.y;
  }
  
  public String toString() {
      return "(" + x + ", " + y + ")";
  }
}