package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class MovieRatingCollector {

    public static class RatingCollectorImpl implements RatingCollector {

        final static Map<String, Double> moviesFeedAvg = new ConcurrentHashMap<>();
        final static Map<String, Double> moviesFeedToalRating = new ConcurrentHashMap<>();
        final static Map<String, Integer> moviesCount = new ConcurrentHashMap<>();

        @Override
        public void putNewRating(String movie, double rating) {
            if(!moviesCount.containsKey(movie) ){
                synchronized (this){
                    moviesCount.put(movie, 1);
                    moviesFeedAvg.put(movie, rating);
                    moviesFeedToalRating.put(movie, rating);
                }
            }else{
                synchronized (this){
                    int count = moviesCount.get(movie);
                    int totalCount = count+1;
                    moviesCount.put(movie, totalCount);
                    double total = moviesFeedToalRating.get(movie) + rating;
                    moviesFeedToalRating.put(movie,total);
                    double avg = total / totalCount;
                    moviesFeedAvg.put(movie,avg);
                }
            }
        }

        @Override
        public double getAverageRating(String movie) {
            return moviesFeedAvg.get(movie);
        }

        @Override
        public int getRatingCount(String movie) {
            return moviesCount.get(movie);
        }
    }

    ////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

    public interface RatingCollector {
        // This is an input. Make note of this rating.  Rating can be a double number between 0 and 100.
        void putNewRating(String movie, double rating);

        // Get the average rating
        double getAverageRating(String movie);

        // Get the total number of ratings received for the movie
        int getRatingCount(String movie);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLines = Integer.parseInt(scanner.nextLine());
        int currentLine = 0;
        while (currentLine++ < numLines) {
            final RatingCollector stats = new RatingCollectorImpl();
            final Set<String> movies = new TreeSet<>();

            String line = scanner.nextLine();
            String[] inputs = line.split(",");
            for (int i = 0; i < inputs.length; ++i) {
                String[] tokens = inputs[i].split(" ");
                final String symbol = tokens[0];
                movies.add(symbol);
                final double price = Double.parseDouble(tokens[1]);

                stats.putNewRating(symbol, price);

            }

            for (String movie : movies) {
                System.out.println(
                        String.format("%s %.4f %d", movie, stats.getAverageRating(movie), stats.getRatingCount(movie)));
            }

        }
        scanner.close();

    }
}



/*
Input
        1
        I_am_legend 22.22,A_Beutiful_Mind 33.33,I_am_legend 44.44,A_Beutiful_Mind 55.55,I_am_legend 66.66
        Your Code's Output
        I_am_legend
        Expected Correct Output
        A_Beutiful_Mind 44.4400 2
        I_am_legend 44.4400 3*/
