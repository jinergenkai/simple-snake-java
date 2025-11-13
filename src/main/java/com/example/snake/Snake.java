package com.example.snake;

public class Snake {
    private int MAX_LENGTH = 100;
    private Point[] body;
    private int length;
    private Direction direction;

    public Snake(int startX, int startY) {
        body = new Point[MAX_LENGTH];
        body[0] = new Point(startX, startY);
        body[1] = new Point(startX - 1, startY);
        body[2] = new Point(startX - 2, startY);
        length = 3;
        direction = Direction.RIGHT;
    }

    public void move() {
        Point head = getHead();
        Point newHead = switch (direction) {
            case UP -> new Point(head.x, head.y - 1);
            case DOWN -> new Point(head.x, head.y + 1);
            case LEFT -> new Point(head.x - 1, head.y);
            case RIGHT -> new Point(head.x + 1, head.y);
            default -> new Point(head.x, head.y);
        };
        for (int i = length; i > 0; i--) {
            body[i] = body[i - 1];
        }
        body[0] = newHead;
    }

    public void grow() {
        Point tail = body[length - 1];
        body[length] = new Point(tail.x, tail.y);
        length++;
    }

    public void changeDirection(Direction newDirection) {
        direction = newDirection;
    }

    public boolean checkSelfCollision() {
        Point head = getHead();
        for (int i = 1; i < length; i++) {
            if (head.equals(body[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPoint(Point p) {
        for (int i = 0; i < length; i++) {
            if (body[i].equals(p)) {
                return true;
            }
        }
        return false;
    }

    public Point getHead() {
        return body[0];
    }

    public Point[] getBody() {
        return body;
    }

    public int getLength() {
        return length;
    }

    public Direction getDirection() {
        return direction;
    }
}