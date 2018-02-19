package Mathematical_Expression;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no of identifiers (n):");
        int n = scanner.nextInt();
        HashMap<String, Integer> identifiers = new HashMap<>();
        System.out.println("Enter identifiers with value: (ex: a = 1)");
        String regEx = "\\w*\\s=\\s\\d{1,}";      //to ensure "identifier(space)=(space)int_Value"
        for (int i = 0; i < n; )
        {
            String s = scanner.nextLine();
            s = s.trim();
            if (!s.equals("") && s.matches(regEx))
            {
                StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                String id = stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                int val = Integer.parseInt(stringTokenizer.nextToken());
                identifiers.put(id, val);
                i++;
            } else
            {
                System.out.println("invalid expression. please check and input again:");
            }
        }
        System.out.println("Enter no of expressions (m):");
        int m = scanner.nextInt();
        String[] expressions = new String[m];
        System.out.println("Enter expressions: (ex: a x b + a x c + b x c)");
        for (int i = 0; i < m; )
        {
            String s = scanner.nextLine();
            if (!s.equals(""))
            {
                expressions[i] = s;
                i++;
            }
        }

        for (String key : identifiers.keySet())
        {
            System.out.println(identifiers.get(key));
        }

    }

}
