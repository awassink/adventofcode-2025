package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));
        InputStream inputStream = Main.class.getResourceAsStream("/codes.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int position = 50;
            int count = 0;
            String previousDirection = "";
            while ((line = br.readLine()) != null) {
                var direction = line.substring(0,1);
                var amount = Integer.parseInt(line.substring(1));
                if  (direction.equals("L")) {
                    boolean previousPositionZero = (position == 0);
                    position -= amount;
                    while (position < 0) {
                        position += 100;
                        if (previousPositionZero && previousDirection.equals("R")) {
                            previousPositionZero = false;
                        } else {
                            count++;
                        }
                    }
                } else if (direction.equals("R")) {
                    if  (position == 0 && previousDirection.equals("L")) {
                        count++;
                    }
                    position += amount;
                    while (position > 99) {
                        position -= 100;
                        count++;
                    }
                } else {
                    IO.println("Invalid direction");
                }
                previousDirection = direction;
                IO.println(String.format("d=%s a=%d p=%d c=%d", direction, amount, position, count));
            }
            IO.println(String.format("c = %d", count));
        }
    }
}
