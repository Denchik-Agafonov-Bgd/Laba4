package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к HTML файлу: ");
        String filePath = scanner.nextLine();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
        {
            FileWriter writer = new FileWriter("output.txt");

            Pattern patternLink = Pattern.compile("<a\\s*href\\s*=\\s*['\\\"]([^'\\\"]*)['\\\"].*?>(.*?)<\\/a>", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Pattern pattern = Pattern.compile("(https?:\\/\\/)?([\\w-]{1,}\\.[\\w-]{1,})[^\\s]*$", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

            Matcher matcher;

            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                matcher = patternLink.matcher(line);
                if (matcher.find()) {

                    result += matcher.group(1) + "  " + matcher.group(2) + "\n";
                    //System.out.println(result);
                } else {
                    matcher = pattern.matcher(line);

                    if (matcher.find()) {
                        result += matcher.group(1) + matcher.group(2) + "\n";
                        //System.out.println(result);

                    }
                }
            }
            System.out.println(result);
            writer.write(result);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
