package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class ArtGallery {

    private InputReader inputReader;
    private int cornersCount;
    private ArrayList<Coordinates> cornerCoordinates;

    public void run() {
        inputReader = new InputReader("02_envoltoria_convexa\\ex_art_gallery\\inputs\\example.txt");
        cornerCoordinates = new ArrayList<>();
        readItems();
    }

    private void readItems() {
        String line = "";
        int cornersLeft = -1;

        while (inputReader.hasNext()) {
            line = inputReader.readLine();

            String[] values = line.split(" ");

            if (cornersLeft == 0) {
                boolean hasCriticalPoint = hasCriticalPoint();
                if (hasCriticalPoint) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
                cornerCoordinates.clear();
            }

            if (values.length > 1) {
                Coordinates coordinates = new Coordinates(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                cornerCoordinates.add(coordinates);
                cornersLeft--;
            } else {
                cornersCount = Integer.parseInt(values[0]);
                cornersLeft = cornersCount;
            }
        }
    }

    private boolean hasCriticalPoint() {
        Stack<Coordinates> upperPoints = new Stack<>();
        Stack<Coordinates> lowerPoints = new Stack<>();

        upperPoints = createSequence(0, cornersCount);
        lowerPoints = createSequence(cornersCount - 1, -1);

        upperPoints.pop();
        lowerPoints.pop();

        return upperPoints.size() + lowerPoints.size() != cornersCount;
    }

    private Stack<Coordinates> createSequence(int initial, int end) {
        Collections.sort(cornerCoordinates);
        Stack<Coordinates> outerPoints = new Stack<>();
        boolean reverse = false;
        if (initial > end) {
            reverse = true;
        }

        int i = initial;
        while (i != end) {
            outerPoints.push(cornerCoordinates.get(i));

            while (outerPoints.size() > 2) {
                Coordinates first = outerPoints.get(outerPoints.size() - 1);
                Coordinates second = outerPoints.get(outerPoints.size() - 2);
                Coordinates third = outerPoints.get(outerPoints.size() - 3);
                boolean ccw = CCW(first, second, third);

                if (ccw) {
                    outerPoints.remove(outerPoints.size()-2);
                } else {
                    break;
                }
            }

            if (reverse) {
                i--;
            } else {
                i++;
            }   
        }

        return outerPoints;
    }

    private boolean CCW(Coordinates p1, Coordinates p2, Coordinates p3) {
        double value = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) -
                (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
        if (value < 0.000001)
            return true;
        else
            return false;
    }
}
