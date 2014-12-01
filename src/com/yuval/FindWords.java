package com.yuval;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;


///////////////////////////////////////////////////////////////////
//ALL RIGHTS RESERVED. YUVAL HERMELIN, SUNNYVALE - CALIFORNIA. 2014
///////////////////////////////////////////////////////////////////
public class FindWords
{

    // Creates a debugger object that will help me debug my code
    static Debugger d = new Debugger();
    static List myList;
    static String[] cordinatesFound;
    static boolean firstTime = true;
    static boolean secondFirstTime = false;

    // The idea here is to get a bunch of strings from the user. It can be a
    // large number of strings. Once they are done, they type the word stop.
    // Then we compare the INDIVIDUAL chars in the string in these ways:
    // Horizontal, vertical, diagonal (And all of these SHOULD work backwards
    // and forwards). To do this I would use basic math and if statements, all
    // in a loop because we would need to check every SINGLE char in the program.
    // I decided I want to have their word puzzle saved to a file. I will look for
    // the file at the beginning.    
    public static void main(String[] args) {

        int numberOfColums = 0;
        int numberOfRows = 0;
        JOptionPane.showMessageDialog(null, "ALL RIGHTS RESERVED. YUVAL HERMELIN, SUNNYVALE - CALIFORNIA. 2014");
        boolean currentWordFound = false;
        boolean stillChecking = true;
        String word = "";
        int i = 0;
        // This HashMap will contain the strings that the user has inputed.
        char[][] map = new char[100][100];
        // Will be used to parse user's input into other type
        String userInput;
        char[] TEST = new char[100000];





        ////////////////////////////////////////////////////////////////////////
        userInput = JOptionPane.showInputDialog("How many letters per line?");
        numberOfColums = Integer.parseInt(userInput);
        userInput = JOptionPane.showInputDialog("How many lines?");
        numberOfRows = Integer.parseInt(userInput);
        ////////////////////////////////////////////////////////////////////////





        //////////////////////////////////////////////
        readFile(map, numberOfColums, numberOfRows);
        //////////////////////////////////////////////





        ////////////////////////////////////////////////////////////////////////////////////////////
        while (firstTime)
        {

            for (int lines = 0; lines < numberOfRows; lines++)
            {
                userInput = JOptionPane.showInputDialog("Type in line " + (lines + 1));
                if (userInput.length() != numberOfColums)
                {
                    JOptionPane.showMessageDialog(null, "Sorry, you said you would only have " + numberOfColums + " letters per line!");
                    FindWords.main(args);
                }
                TEST = userInput.toCharArray();
                for (int col = 0; col < numberOfColums; col++)
                {
                    map[lines][col] = TEST[col];
                    d.log(map[lines][col]);
                }

                if (lines == numberOfRows - 1)
                {
                    firstTime = false;
                    secondFirstTime = true;
                }
            }

        }
        ////////////////////////////////////////////////////////////////////////////////////////////





        //////////////////////////////////////////////
        if (secondFirstTime)
        {
            writeFile(map, numberOfColums, numberOfRows);
        }
        secondFirstTime = false;
        //////////////////////////////////////////////





        ////////////////////////////////////////////////////////////////////////////////////////////
        myList = new List(map, numberOfRows, numberOfColums);
        word = JOptionPane.showInputDialog("What is the word you want to find?");
        myList.setWord(word);
        if (findWord())
        {
            JOptionPane.showMessageDialog(null, "Your word was found.");
            for (int check = 0; check < cordinatesFound.length; check++)
            {
                JOptionPane.showMessageDialog(null, "Letter: " + (check + 1) + " is found at " + cordinatesFound[check]);
            }
            myList.printMap();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your word was not found.");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
    }

    public static boolean findWord() {
        String word = myList.getWord();
        int numberOfRows, numberOfColums;
        numberOfRows = myList.getNumberOfRows();
        numberOfColums = myList.getNumberOfColums();
        for (int row = 0; row < numberOfRows; row++)
        {
            for (int col = 0; col < numberOfColums; col++)
            {
                for (int dir = 0; dir < 8; dir++)
                {
                    if (myList.checkDirections(word, row, col, dir))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void readFile(char[][] map, int numberOfColums, int numberOfRows) {

        try
        {

            String desktop = System.getProperty("user.home") + "/Desktop/";
            BufferedReader in = new BufferedReader(new FileReader(desktop + "WordSolver.txt"));
            String line;
            String lines = "";
            int line1 = 0;
            while ((line = in.readLine()) != null)
            {
                // Parse the data

                for (int col = 0; col < numberOfColums; col++)
                {

                    map[line1][col] = line.charAt(col);
                    d.log(map[line1][col]);

                }
                line1++;
            }

            firstTime = false;
            in.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void writeFile(char[][] map, int numberOfColums, int numberOfRows) {
        if (secondFirstTime)
        {
            try
            {
                String desktop = System.getProperty("user.home") + "/Desktop/";
                File myFile = new File(desktop + "WordSolver.txt");
                BufferedWriter out = new BufferedWriter(new FileWriter(myFile));
                for (int lines = 0; lines < numberOfRows; lines++)
                {
                    for (int col = 0; col < numberOfColums; col++)
                    {
                        out.append(map[lines][col]);
                    }
                    out.append("\n");
                }
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
