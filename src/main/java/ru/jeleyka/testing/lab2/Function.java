package ru.jeleyka.testing.lab2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Function {

    public abstract double apply(double x);

    public void save(File file, double startX, double endX, double stepX) throws IOException {
        PrintWriter printWriter = new PrintWriter(file);
        for (double currentX = startX; currentX <= endX; currentX += stepX) {
            String value;
            try {
                value = String.valueOf(apply(currentX));
            } catch (IllegalArgumentException ignored) {
                value = "NaN";
            }
            printWriter.println(currentX + ", " + value);
        }
        printWriter.close();
    }

}
