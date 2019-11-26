package util;

import model.Labyrinthe;
import model.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class AStar {

    private static LinkedList<Node> next(Labyrinthe game, Node u, LinkedList<Node> closeList, Position goal) {
        LinkedList<Node> res = new LinkedList<>();

        if (game.isFree(u.getX() - 1, u.getY())) {
            Node next = new Node(u.getX() - 1, u.getY(), u.getCout()+1, (goal.getY() - u.getY()) * (goal.getY() - u.getY()) + (goal.getX() - (u.getX()-1)) * (goal.getX() - (u.getX()-1)), u);
            if (!closeList.contains(next)) {
                res.addFirst(next);
            }
        }
        if (game.isFree(u.getX() + 1, u.getY())) {
            Node next = new Node(u.getX() + 1, u.getY(), u.getCout()+1, (goal.getY() - u.getY()) * (goal.getY() - u.getY()) + (goal.getX() - (u.getX()+1)) * (goal.getX() - (u.getX()+1)), u);
            if (!closeList.contains(next)) {
                if (res.size() == 0 || res.get(0).getHeuristic() < next.getHeuristic()) {
                    res.addLast(next);
                }
                else {
                    res.addFirst(next);
                }
            }
        }
        if (game.isFree(u.getX(), u.getY() - 1)) {
            Node next = new Node(u.getX(), u.getY() - 1, u.getCout()+1, (goal.getY() - (u.getY()-1)) * (goal.getY() - (u.getY()-1)) + (goal.getX() - u.getX()) * (goal.getX() - u.getX()), u);
            if (!closeList.contains(next)) {
                if (res.size() == 0 || res.get(0).getHeuristic() < next.getHeuristic()) {
                    res.addLast(next);
                }
                else {
                    res.addFirst(next);
                }
            }
        }
        if (game.isFree(u.getX(), u.getY() + 1)) {
            Node next = new Node(u.getX(), u.getY() + 1, u.getCout()+1, (goal.getY() - (u.getY()+1)) * (goal.getY() - (u.getY()+1)) + (goal.getX() - u.getX()) * (goal.getX() - u.getX()), u);
            if (!closeList.contains(next)) {
                if (res.size() == 0 || res.get(0).getHeuristic() < next.getHeuristic()) {
                    res.addLast(next);
                }
                else {
                    res.addFirst(next);
                }
            }
        }
        return res;
    }

    public static ArrayList<Position> getPath(Labyrinthe game, Position goal, Position start) {
        LinkedList<Node> openList = new LinkedList<>();
        LinkedList<Node> closeList = new LinkedList<>();

        int cmpt = 0;
        openList.add(new Node(start.getX(), start.getY(), 0, 0, null));

        while (!openList.isEmpty() && cmpt < 200){
            cmpt++;
            Node u = openList.pop();
            closeList.add(u);


            if (u.getX() == goal.getX() && goal.getY() == u.getY()) {
                return reconstructPath(u);
            }

            LinkedList<Node> tmp = next(game, u, closeList, goal);

            for (int i = tmp.size()-1; i >= 0; i--) {
                openList.addFirst(tmp.get(i));
            }
        }
        return null;
    }

    public static ArrayList<Position> reconstructPath(Node end) {
        Node current = end;
        ArrayList<Position> res = new ArrayList<>();
        while (current.getCameFrom() != null) {
            res.add(new Position(current.getX(), current.getY()));
            current = current.getCameFrom();
        }
        return res;
    }

}