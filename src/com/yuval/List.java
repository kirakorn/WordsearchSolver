/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuval;

import javax.swing.JOptionPane;

/**

 @author yuvalhermelin
 */
public class List
{

    char[][] map = new char[100][100];
    int numberOfRows;
    int numberOfColums;
    String word;
    int[] xCord, yCord;

    List(char[][] map, int numberOfRows, int numberOfColums) {
        this.map = map;
        this.numberOfRows = numberOfRows;
        this.numberOfColums = numberOfColums;
    }

    public boolean checkDirections(String word, int row, int col, int dir) {

        /////////////////////////////////////////////////////
        FindWords.cordinatesFound = new String[word.length()];
        yCord = new int[word.length()];
        xCord = new int[word.length()];
        int[] rowDirection =
        {
            -1, 1, 0, 0, -1, 1, 1, -1
        };
        int[] colDirection =
        {
            0, 0, 1, -1, 1, -1, 1, -1
        };
        /////////////////////////////////////////////////////




        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < word.length(); i++)
        {

            /////////////////////////////////////////////////////
            // If out of bounds
            if (row + i * rowDirection[dir] < 0 || row + i * rowDirection[dir] >= numberOfRows)
            {
                return false;
            }
            if (col + i * colDirection[dir] < 0 || col + i * colDirection[dir] >= numberOfColums)
            {
                return false;
            }
            /////////////////////////////////////////////////////


            char currentChar;
            currentChar = map[row + i * rowDirection[dir]][col + i * colDirection[dir]];
            if (currentChar != word.charAt(i))
            {
                return false;
            }
            else
            {
                FindWords.cordinatesFound[i] = "Line: " + (row + i * rowDirection[dir]) + " Col: " + (col + i * colDirection[dir]);
                yCord[i] = (row + i * rowDirection[dir]);
                xCord[i] = (col + i * colDirection[dir]);
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        return true;
    }
    
    public void printMap()
    {

        for(int i = 0; i < xCord.length; i++)
        {
            map[yCord[i]][xCord[i]] = '*';
        }
        System.out.println();
        for(int y = 0; y < numberOfRows; y++)
        {
            for (int x = 0; x < numberOfColums; x++)
            {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColums() {
        return numberOfColums;
    }

    public void setNumberOfColums(int numberOfColums) {
        this.numberOfColums = numberOfColums;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
