package Regular_Expression;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ------------------------------------------------------------------------------------------------*
 * File name: Main
 * Project name: Compiler Design
 * Author: SAJJAD AHMED NILOY
 * ID: 15301095
 * Created on: 6:36 PM
 * License:
 * ------------------------------------------------------------------------------------------------*
 **/

public class Main
{
    public static void main(String[] args)
    {
        File inputFile = new File("C:\\Users\\Gigabyte\\Desktop\\spring 18\\Compiler Design\\src\\Regular_Expression\\input.txt");
        File outputFile = new File("C:\\Users\\Gigabyte\\Desktop\\spring 18\\Compiler Design\\src\\Regular_Expression\\output.txt");
        Scanner scanner;
        ArrayList<String> allLinesArrayList = new ArrayList<>();
        try
        {
            scanner = new Scanner(inputFile);
            while (scanner.hasNext())
            {
                String line = scanner.nextLine().trim();
                if (!line.equals(""))
                    allLinesArrayList.add(line);
            }
        } catch (Exception e)
        {
            System.out.println("File not found!...Please insert the correct path!");
        }

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < allLinesArrayList.size(); i++)
        {
            int c = 0;
            if (star(allLinesArrayList.get(i)))
                c++;
            if (plus(allLinesArrayList.get(i)))
                c++;
            if (question(allLinesArrayList.get(i)))
                c++;
            if (range(allLinesArrayList.get(i)))
                c++;
            if (n_times(allLinesArrayList.get(i)))
                c++;
            if (negation(allLinesArrayList.get(i)))
                c++;
            if (c > 0)
                stringBuilder.append("YES, ").append(c).append("\n");
            else stringBuilder.append("NO, 0\n");
        }
        try
        {
            System.out.println(stringBuilder.toString());
            byte data[] = stringBuilder.toString().getBytes();
            FileOutputStream out = new FileOutputStream(outputFile);
            out.write(data);
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean n_times(String exp)
    {
        int c = 0;
        char[] charArray = exp.toCharArray();
        if (charArray.length != 6)
            return false;
        else
            for (int i = 0; i < charArray.length; i++)
            {
                if (charArray[i] == 'a' || charArray[i] == 'e' || charArray[i] == 'i' || charArray[i] == 'o' || charArray[i] == 'u')
                    c++;
            }
        if (c == 0)
            return true;
        else return false;
    }

    private static boolean negation(String exp)
    {

        int c = 0;
        char[] charArray = exp.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            if (charArray[i] == 'a' || charArray[i] == 'e' || charArray[i] == 'i' || charArray[i] == 'o' || charArray[i] == 'u')
                c++;
        }
        if (c == 0)
            return true;
        else return false;

    }

    private static boolean star(String exp)
    {
        if (!exp.startsWith("a") && !exp.endsWith("de"))
            return false;
        else
        {
            exp = exp.substring(1, exp.length() - 2);
            if (exp.equals(""))
                return true;
            else
            {
                int c = 0;
                char[] charArray = exp.toCharArray();
                c = validateBC(c, charArray);
                if (c == charArray.length && charArray.length % 2 == 0)
                    return true;
                else return false;
            }
        }
    }

    private static int validateBC(int c, char[] charArray)
    {
        for (int i = 0; i < charArray.length; i++)
        {
            if (i % 2 == 0 && charArray[i] == 'b')
                c++;
            else if (charArray[i] == 'c')
                c++;
        }
        return c;
    }

    private static boolean plus(String exp)
    {
        if (!exp.startsWith("a") && !exp.endsWith("de"))
            return false;
        else
        {
            exp = exp.substring(1, exp.length() - 2);
            if (exp.equals(""))
                return false;
            else
            {
                int c = 0;
                char[] charArray = exp.toCharArray();
                c = validateBC(c, charArray);
                if (c == charArray.length && charArray.length % 2 == 0)
                    return true;
                else return false;
            }
        }
    }

    private static boolean question(String exp)
    {
        if (!exp.startsWith("a"))
            return false;
        else if (!exp.endsWith("de"))
            return false;
        else
        {
            exp = exp.substring(1, exp.length() - 2);
            System.out.println(exp);
            if (exp.equals(""))
                return true;
            else
            {
                int c = 0;
                char[] charArray = exp.toCharArray();
                c = validateBC(c, charArray);
                if (c == charArray.length && charArray.length <= 2)
                    return true;
                else return false;
            }
        }
    }

    private static boolean range(String exp)
    {
        int c = 0;
        char[] charArray = exp.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            if (charArray[i] <= 109 && charArray[i] >= 97)
                c++;
        }
        if (c == charArray.length)
            return true;
        else return false;

    }
}
