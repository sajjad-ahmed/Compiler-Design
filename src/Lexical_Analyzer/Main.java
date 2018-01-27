package Lexical_Analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ------------------------------------------------------------------------------------------------*
 * File name: Main.java
 * Project name: Compiler Design
 * Author: SAJJAD AHMED NILOY
 * Created on: 11:57 AM 22-Jan-18
 * ------------------------------------------------------------------------------------------------*
 **/

//Keywords: int, float, if, else
//        Identifiers: a, b, c, d, e
//        Math Operators: +, -, =
//        Logical Operators: >
//        Numerical Values: 5, 6, 2.0, 6.0
//        Others: , ; ( ) { }


//                    if (alphabateflag)
//                            {
//                            if (isKeyword(word))
//                            keywordList.add(word);
//                            else
//                            identifierList.add(word);
//                            word = "";
//                            alphabateflag = false;
//                            } else if (operatorFlag)
//                            {
//                            System.out.println("-----here");
//                            if (isMatOperator(word))
//                            arithmeticOperatorList.add(word);
//                            else if (isAssignmentOperator(word))
//                            assignmentOperatorList.add(word);
//                            else if (isLogical(word))
//                            logicalOperatorList.add(word);
//                            else if (isLogical(word))
//                            relationalOperatorList.add(word);
//                            else
//                            errorList.add(word);
//                            word = "";
//                            operatorFlag = false;
//                            } else if (numberFlag)
//                            {
//                            numericValuesList.add(word);
//                            word = "";
//                            numberFlag = false;
//                            }

public class Main
{

    private static ArrayList<String> keywordList = new ArrayList<>();
    private static ArrayList<String> identifierList = new ArrayList<>();

    private static ArrayList<String> arithmeticOperatorList = new ArrayList<>(); //arithmatic
    private static ArrayList<String> assignmentOperatorList = new ArrayList<>();
    private static ArrayList<String> logicalOperatorList = new ArrayList<>();
    private static ArrayList<String> relationalOperatorList = new ArrayList<>();

    private static ArrayList<String> numericValuesList = new ArrayList<>();

    private static ArrayList<String> otherValuesList = new ArrayList<>();
    private static ArrayList<String> allTokensArrayList = new ArrayList<>();

    private static ArrayList<String> errorList = new ArrayList<>();


    private static String[] keywordArray = {"abstract", "assert", "boolean", "break", "byte", "case",
            "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"};

    private static String[] commonOperatorArray = {"+", "-", "*", "/", "%", "=", "<", ">", "!", "&", "|", "."};

    private static String[] arithmeticOperatorArray = {"+", "-", "*", "/", "%", "++", "--"};
    private static String[] assignmentOperatorArray = {"=", "+=", "-=", "*=", "/=", "%=", "<<=", ">>=", "&=", "^=", "|="};
    private static String[] logicalOperatorArray = {"!", "&&", "||"};
    private static String[] relationalOperatorArray = {"==", "!=", ">", "<", ">=", "<="};

    private static String[] symbolValuesArray = {".", ",", "\"", "{", "}", "(", ")", "[", "]", ";"};


    public static void main(String[] _args)
    {
        File file = new File("C:\\Users\\Gigabyte\\Desktop\\spring 18\\Compiler Design\\src\\Lexical_Analyzer\\input.txt");


        ArrayList<Character> characterArrayList = new ArrayList<>();
        try
        {
            InputStream in = new FileInputStream(file);
            Reader reader = new InputStreamReader(in, Charset.defaultCharset());
            Reader buffer = new BufferedReader(reader);
            characterArrayList = handleCharacters(buffer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        HashMap<Integer, String> keywordsMap = new HashMap<>();
        int beginning = 0;
        boolean alphabateflag = false;
        boolean numberFlag = false;
        boolean operatorFlag = false;
        String word = "";

        for (char eachCharacter : characterArrayList)
        {
//            System.out.println("*:" + ch);
//--------------------------------------------------------------------------------------------------
            if ((int) eachCharacter == 32 && !alphabateflag)
            {
                alphabateflag = false;
            }
//--------------------------------------------------------------------------------------------------
//            else if ((int) eachCharacter == 32 && !numberFlag)
//            {
//                numberFlag = false;
//            }
//////--------------------------------------------------------------------------------------------------
//            else if ((int) eachCharacter == 32 && !operatorFlag)
//            {
//                operatorFlag = false;
//            }
//--------------------------------------------------------------------------------------------------
            else if ((int) eachCharacter == 32)
            {

                allTokensArrayList.add(word);
                if (isKeyword(word))
                    keywordList.add(word);
                else
                    identifierList.add(word);
                word = "";
                alphabateflag = false;
            }
//--------------------------------------------------------------------------------------------------
            else if (isASymbol(eachCharacter))
            {
                if (alphabateflag)
                {
                    allTokensArrayList.add(word);
                    if (isKeyword(word))
                        keywordList.add(word);
                    else
                        identifierList.add(word);
                    word = "";
                }
                allTokensArrayList.add(eachCharacter + "");
                otherValuesList.add(eachCharacter + "");


            }
//--------------------------------------------------------------------------------------------------
            else if (isAlphabate(eachCharacter))
            {
                System.out.println("be====" + word);

                word = word.concat("" + eachCharacter);
                System.out.println("af====" + word);
                if (operatorFlag)
                {
                    allTokensArrayList.add(word);
                    relationalOperatorList.add(word);
                }

                alphabateflag = true;
                numberFlag = false;
                operatorFlag = false;
            }
//--------------------------------------------------------------------------------------------------
            else if (isNumber(eachCharacter))
            {
                if (operatorFlag)
                {
                    allTokensArrayList.add(word);
                    logicalOperatorList.add(word);
                }
                word = word.concat("" + eachCharacter);
                numberFlag = true;
                alphabateflag = false;
                operatorFlag = false;
            }
//--------------------------------------------------------------------------------------------------
            else if (isCommonOperator(eachCharacter))
            {

                System.out.println("-----hiii");
                if (alphabateflag)
                {
                    allTokensArrayList.add(word);
                    if (isKeyword(word))
                        keywordList.add(word);
                    else
                        identifierList.add(word);
                    word = "";
                    alphabateflag = false;
                }

                word = word.concat("" + eachCharacter);
                operatorFlag = true;
            }
//--------------------------------------------------------------------------------------------------
        }
        printArrayList(allTokensArrayList, "All Tokens:");

        printArrayList(keywordList, "keyword list");
        printArrayList(otherValuesList, "Other");
        printArrayList(identifierList, "identifier list");

        printArrayList(arithmeticOperatorList, "Arithmetic");
        printArrayList(assignmentOperatorList, "Assignment");
        printArrayList(logicalOperatorList, "Logical");
        printArrayList(relationalOperatorList, "");

        printArrayList(numericValuesList, "Numeric");

        printArrayList(errorList, "Error");


    }

    private static boolean isAlphabate(char ch)
    {
        return ((int) ch >= 65 && (int) ch <= 90) ||
                ((int) ch >= 97 && (int) ch <= 122);
    }

    private static boolean isNumber(char ch)
    {
        return (int) ch >= 48 && (int) ch <= 57;
    }

    public static boolean isMatOperator(String w)
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

    private static boolean isCommonOperator(char ch)
    {
        int flag = 5;
        for (String string : commonOperatorArray)
        {

            if (string.equals("" + ch))
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

    private static boolean isASymbol(char ch)
    {
        int flag = 5;
        for (String string : symbolValuesArray)
        {
            if (string.equals("" + ch))
            {
                flag++;
                break;
            }
        }
        return flag != 5;
    }

    public static boolean isAssignmentOperator(String w)
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

    public static boolean isRelationalOperator(String w)
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

    public static boolean isLogical(String w)
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

    private static ArrayList<Character> handleCharacters(Reader reader) throws IOException
    {
        ArrayList<Character> arrayList = new ArrayList<>();
        int r;
        while ((r = reader.read()) != -1)
        {
            char ch = (char) r;
            arrayList.add(ch);
        }
        return arrayList;
    }


}