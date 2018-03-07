package Regular_Expression;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ------------------------------------------------------------------------------------------------*
 * File name: Main
 * Project name: Compiler Design
 * Author: SAJJAD AHMED NILOY
 * Created on: 6:36 PM
 * License:
 * ------------------------------------------------------------------------------------------------*
 **/

public class Main
{


    public static void main(String[] args)
    {
        File file = new File("C:\\Users\\Gigabyte\\Desktop\\spring 18\\Compiler Design\\src\\Regular_Expression\\input.txt");

        Scanner scanner;
        ArrayList<String> allLinesArrayList = new ArrayList<>();
        try
        {
            scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                String line = scanner.nextLine();
                if (!line.equals(""))
                    allLinesArrayList.add(line);
            }
        } catch (Exception e)
        {
            System.out.println("File not found!...Please insert the correct path!");
        }

        for (int idx = 0; idx < allLinesArrayList.size(); )
        {
            int n = Integer.parseInt(allLinesArrayList.get(idx++));
            for (int i = 0; i < n; i++)
            {
                String s = allLinesArrayList.get(idx++).trim();
                System.out.println("Reg: " + s);

            }
            int m = Integer.parseInt(allLinesArrayList.get(idx++));
            String[] expressions = new String[m];
            for (int i = 0; i < m; i++)
            {
                String s = allLinesArrayList.get(idx++).trim();
                System.out.println("exp: " + s);
            }

        }
    }

}
