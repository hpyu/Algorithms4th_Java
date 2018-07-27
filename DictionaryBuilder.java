import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * This {@code DictionaryBuilder} class build words frequency dictionary from
 * a text file, store words, count of each word, the sentences
 *
 * It support raw text file or clean sentences file what generated from raw text
 *
 * Author: Haipeng Yu
 * Email:  haipengyu.yu@gmail.com
 */
public class DictionaryBuilder {
    HashMap<String, Integer> wordCountMap  = new HashMap<>();
    HashMap<String, String> wordContexMap = new HashMap<>();

    public DictionaryBuilder(In rawIn, Out wordsOut, String wordsFileName)
    {
        while(!rawIn.isEmpty()) {
            String rawString = rawIn.readLine().trim();

            // only keep lines with more than 10 words as meaningful context
            if (rawString.split("\\s+").length < 10) continue;

            //1. fix word connection "- ", replace with ""
            String s1 = rawString.replaceAll("- ", "");

            String s2 = s1.replaceAll("^[^A-Za-z ]+", "");
            String s3 = s2.replaceAll("\\. ", "\n");
            //3. extract sentence and save in wordsFile
            //StdOut.println(s3);

            wordsOut.println(s3);
        }

        rawIn.close();
        wordsOut.close();

        In wordsIn = new In(wordsFileName);
        buildDictionary(wordsIn);
    }

    public DictionaryBuilder(In wordsIn)
    {
        buildDictionary(wordsIn);
    }

    public void buildDictionary(In wordsIn)
    {
        while(!wordsIn.isEmpty())
        {
            String wordsContex = wordsIn.readLine().trim();

            // only keep lines with more than 10 words as meaningful context
            String[] words = wordsContex.split("\\s+");
            if (words.length < 8) continue;

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (word.length() < 4 || word.length() > 10) continue;
                word = word.toLowerCase().replaceAll("[^a-z]", "");

                StdOut.println(word);
                hashWord(word, wordsContex);
            }
            StdOut.println(wordsContex);

        }
    }
    private void hashWord(String word, String wordsContex)
    {
        if (wordCountMap.get(word) == null) {
            wordCountMap.put(word, 1);
        } else {
            wordCountMap.put(word, wordCountMap.get(word) + 1);
        }

        wordContexMap.put(word, wordsContex);
    }


    public void dumpWordsAndCounter(int maxCount)
    {
        Stream<Map.Entry<String, Integer>> sorted =
                wordCountMap.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        wordCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);

    }

    public void showWordContext()
    {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            StdOut.println("Input words to lookup context:");
            String word = scanner.nextLine();
            if (word.equals("q")) return;

            String ctx = wordContexMap.get(word);
            ctx = ctx.replace(word, "["+word+"]");
            StdOut.println(ctx);
        }
    }

    public static void main(String[] args)
    {
        DictionaryBuilder db = null;
        try {
            if (args.length == 2) {
                In rawIn = new In(args[0]);
                Out wordsOut = new Out(args[1]);
                db = new DictionaryBuilder(rawIn, wordsOut, args[1]);
            } else if (args.length == 1) {
                In wordsIn = new In(args[0]);
                db = new DictionaryBuilder(wordsIn);
            }

        }  catch (IllegalArgumentException e) {
            StdOut.println(e.toString());
        }
        if (db != null) {
            db.dumpWordsAndCounter(0);
            db.showWordContext();
        }
    }
}


