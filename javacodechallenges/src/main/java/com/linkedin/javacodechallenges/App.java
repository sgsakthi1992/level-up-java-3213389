package com.linkedin.javacodechallenges;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class App 
{
    public static void redactTextFile(String fileName,
                                      String[] redactedWordsArray) {
        if (!fileName.endsWith(".txt")) {
            System.out.println("This is not a text file.");
            return;
        }
        try{
            var file = Files.readString(Path.of(fileName));
            AtomicReference<String> redactedFileContent = new AtomicReference<>(file);
            for(String word : redactedWordsArray) {          
                if(file.contains(word)) {
                    redactedFileContent.set(redactedFileContent.get().replaceAll(word, "REDACTED"));
                }
            };
            Files.write(Path.of("redacted-"+fileName), redactedFileContent.get().getBytes(), StandardOpenOption.CREATE);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What file would you like to " +
                "redact information from?");
        String fileName = scanner.nextLine();

        System.out.println("What words would you like to redact? " +
                "Separate each word or phrase with a comma. " +
                "If you phrase includes punctuation, include " +
                "that in your input.");
        String redactedWords = scanner.nextLine();
        String[] redactedWordsList = redactedWords.split(",");

        redactTextFile(fileName, redactedWordsList);

        scanner.close();
    }
}
