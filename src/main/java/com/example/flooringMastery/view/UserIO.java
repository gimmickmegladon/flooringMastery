package com.example.flooringMastery.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserIO implements UserIOInterface{

    // A UserIO class implementation that was lifted and adapted from the one available at :
    // https://academy.engagelms.com/mod/book/view.php?id=142019&chapterid=39137

    final private Scanner scanner = new Scanner(System.in);

    // Print message to console
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    // print prompt, and
    // read in next user input line as String
    @Override
    public String readString(String msgPrompt) {
        System.out.print(msgPrompt);
        return scanner.nextLine();
    }

    // print prompt, and
    // read in next user input line as int
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }
}
