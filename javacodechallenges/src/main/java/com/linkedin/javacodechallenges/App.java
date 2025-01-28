package com.linkedin.javacodechallenges;

import java.time.LocalDate;

public class App 
{
    // Create function to calculate the date that's 
    // 100 days from now

    public static void main( String[] args )
    {     
        System.out.println("100 days from now is... " 
            + LocalDate.now().plusDays(100));
    }
}
