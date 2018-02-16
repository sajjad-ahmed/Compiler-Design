package Lexical_Analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * ------------------------------------------------------------------------------------------------*
 * File name: M2
 * Project name: Compiler Design
 * Author: SAJJAD AHMED NILOY
 * Created on: 11:20 PM
 * License:
 * ------------------------------------------------------------------------------------------------*
 **/


public class M2
{

    private static ArrayList<String> allTokensArrayList = new ArrayList<>();
    private static ArrayList<String> keywordList = new ArrayList<>();
    private static ArrayList<String> identifierList = new ArrayList<>();
    private static ArrayList<String> numericValuesList = new ArrayList<>();
    private static ArrayList<String> operatorList = new ArrayList<>();
    private static ArrayList<String> symbolList = new ArrayList<>();

    private static ArrayList<String> arithmeticOperatorList = new ArrayList<>();
    private static ArrayList<String> assignmentOperatorList = new ArrayList<>();
    private static ArrayList<String> logicalOperatorList = new ArrayList<>();
    private static ArrayList<String> relationalOperatorList = new ArrayList<>();


    private static ArrayList<String> otherList = new ArrayList<>();


    private static String[] keywordArray = {"abstract", "assert", "boolean", "break", "byte", "case",
            "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"};

    private static String[] allOperatorArray = {"+", "-", "*", "/", "%", "++", "--", "=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "^=", "|=", "!", "&&", "||", "==", "!=", ">", "<", ">=", "<=", ":"};

    private static String[] arithmeticOperatorArray = {"+", "-", "*", "/", "%", "++", "--"};
    private static String[] assignmentOperatorArray = {"=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "^=", "|="};
    private static String[] logicalOperatorArray = {"!", "&&", "||"};
    private static String[] relationalOperatorArray = {"==", "!=", ">", "<", ">=", "<="};

    private static String[] symbolArray = {".", ",", "\"", "{", "}", "(", ")", "[", "]", ";"};


    public static void main(String[] _args)
    {
        File file = new File("C:\\Users\\Gigabyte\\Desktop\\spring 18\\Compiler Design\\src\\Lexical_Analyzer\\input2.txt");


        ArrayList<String> stringArrayList = new ArrayList<>();
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                stringArrayList.add(scanner.nextLine());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        if (stringArrayList.size() == 0)
        {
            System.out.println("Empty file");
            return;
        }

        for (String eachLine : stringArrayList)
        {
            StringTokenizer stringTokenizer = new StringTokenizer(eachLine, " ");
            while (stringTokenizer.hasMoreTokens())
            {
                String token = stringTokenizer.nextToken().trim();
                if (isASymbol(token))
                {
                    if (!symbolList.contains(token))
                        symbolList.add(token);

                } else if (isOperator(token))
                {
                    if (isArithmeticOperator(token) && !arithmeticOperatorList.contains(token))
                        arithmeticOperatorList.add(token);
                    else if (isAssignmentOperator(token) && !assignmentOperatorList.contains(token))
                        assignmentOperatorList.add(token);
                    else if (isLogical(token) && !logicalOperatorList.contains(token))
                        logicalOperatorList.add(token);
                    else if (isRelationalOperator(token) && !relationalOperatorList.contains(token))
                        relationalOperatorList.add(token);


                } else if (isANumber(token))
                {
                    if (!numericValuesList.contains(token))
                        numericValuesList.add(token);

                } else if (isAWord(token))
                {
                    if (isKeyword(token))
                    {
                        if (!keywordList.contains(token))
                            keywordList.add(token);
                    } else
                    {
                        if (!identifierList.contains(token))
                            identifierList.add(token);
                    }
                } else
                {
                    otherList.add(token);
                }

            }
        }

        // ---------printing
//        printArrayList(allTokensArrayList, "All Tokens:");

        printArrayList(keywordList, "keyword list");
        printArrayList(identifierList, "identifier list");
        printArrayList(numericValuesList, "Numeric");
        printArrayList(symbolList, "Symbols");
//        printArrayList(operatorList, "Operators");

        printArrayList(arithmeticOperatorList, "Arithmetic");
        printArrayList(assignmentOperatorList, "Assignment");
        printArrayList(logicalOperatorList, "Logical");
        printArrayList(relationalOperatorList, "Relational");


        printArrayList(otherList, "Other");


        System.out.println();
        System.out.print("Error: ");
        for (String s : identifierList)
        {
            int flag = 0;
            for (char ch : s.toCharArray())
            {
                if ((((int) ch >= 48 && (int) ch <= 57) || (int) ch == 46) && flag == 0)
                {
                    System.out.print(s + " ");
                }
            }
        }
    }
//-------------------------------------------------------------------------------------------


    private static boolean isANumber(String s)
    {
        int flag = 5;
        for (char ch : s.toCharArray())
        {
            if (((int) ch >= 48 && (int) ch <= 57) || (int) ch == 46)
            {
                flag = 5;
            } else
            {
                flag++;
                break;
            }
        }
        return flag == 5;

    }

    private static boolean isAWord(String s)
    {
        int flag = 5;
        for (char ch : s.toCharArray())
        {
            if (((int) ch >= 65 && (int) ch <= 90) ||
                    ((int) ch >= 97 && (int) ch <= 122))
            {
                flag = 5;
            } else
            {
                flag++;
                break;
            }
        }
        return flag == 5;

    }

    private static boolean isOperator(String s)
    {
        int flag = 5;
        for (String string : allOperatorArray)
        {

            if (string.equals(s))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

    private static boolean isKeyword(String s)
    {
        int flag = 5;
        for (String string : keywordArray)
        {
            if (string.equals("" + s))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

    private static boolean isASymbol(String s)
    {
        int flag = 5;
        for (String string : symbolArray)
        {
            if (string.equals("" + s))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

//-------------------------------------------------------------------------------------------


    private static boolean isArithmeticOperator(String w)
    {
        int flag = 5;
        for (String string : arithmeticOperatorArray)
        {

            if (string.equals(w))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }


    private static boolean isAssignmentOperator(String w)
    {
        int flag = 5;
        for (String string : assignmentOperatorArray)
        {
            if (string.equals(w))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

    private static boolean isRelationalOperator(String w)
    {
        int flag = 5;
        for (String string : relationalOperatorArray)
        {
            if (string.equals(w))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

    private static boolean isLogical(String w)
    {
        int flag = 5;
        for (String string : logicalOperatorArray)
        {
            if (string.equals(w))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

    private static void printArrayList(ArrayList<String> simpleList, String name)
    {
        System.out.println();
        System.out.print(name + ": ");
        for (String s : simpleList)
            System.out.print(s + " ");
    }


}