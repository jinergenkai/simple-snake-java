package com.example.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<Point> body;
    String direction;

    public Snake(int startX, int startY) {
        body = new ArrayList<>();
        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        body.add(new Point(startX - 2, startY));
        direction = "RIGHT";
    }

    public Point getHead() {
        return body.get(0);
    }
  
    public void move() {
        Point head = getHead();
        Point newHead = switch (direction) {
            case "UP" -> new Point(head.x, head.y - 1);
            case "DOWN" -> new Point(head.x, head.y + 1);
            case "LEFT" -> new Point(head.x - 1, head.y);
            case "RIGHT" -> new Point(head.x + 1, head.y);
            default -> new Point(head.x, head.y);
        };
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
    }

    public void changeDirection(String newDirection) {
        if (direction.equals("UP") && newDirection.equals("DOWN")) return;
        if (direction.equals("DOWN") && newDirection.equals("UP")) return;
        if (direction.equals("LEFT") && newDirection.equals("RIGHT")) return;
        if (direction.equals("RIGHT") && newDirection.equals("LEFT")) return;
        direction = newDirection;
    }

    public boolean checkSelfCollision() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPoint(Point p) {
        for (Point point : body) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }
}