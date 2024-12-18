package org.example;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Juegos implements Serializable,Comparable<Juegos>{
    private String game;
    private String platform;
    private String developer;
    private String genre;
    private String numberPlayers;
    private String rating;
    private String releaseDate;
    private int positiveCritics;
    private int neutralCritics;
    private int negativeCritics;
    private int positiveUsers;
    private int neutralUsers;
    private int negativeUsers;
    private double metascore;
    private double userScore;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor
    public Juegos(String game, String platform, String developer, String genre, String numberPlayers, String rating,
                  String releaseDate, int positiveCritics, int neutralCritics, int negativeCritics,
                  int positiveUsers, int neutralUsers, int negativeUsers, double metascore, double userScore) {
        this.game = game;
        this.platform = platform;
        this.developer = developer;
        this.genre = genre;
        this.numberPlayers = numberPlayers;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.positiveCritics = positiveCritics;
        this.neutralCritics = neutralCritics;
        this.negativeCritics = negativeCritics;
        this.positiveUsers = positiveUsers;
        this.neutralUsers = neutralUsers;
        this.negativeUsers = negativeUsers;
        this.metascore = metascore;
        this.userScore = userScore;
    }

    @Override
    public String toString() {
        return "Juegos{" +
                "game='" + game + '\'' +
                ", platform='" + platform + '\'' +
                ", developer='" + developer + '\'' +
                ", genre='" + genre + '\'' +
                ", numberPlayers='" + numberPlayers + '\'' +
                ", rating='" + rating + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", positiveCritics=" + positiveCritics +
                ", neutralCritics=" + neutralCritics +
                ", negativeCritics=" + negativeCritics +
                ", positiveUsers=" + positiveUsers +
                ", neutralUsers=" + neutralUsers +
                ", negativeUsers=" + negativeUsers +
                ", metascore=" + metascore +
                ", userScore=" + userScore +
                '}';
    }

    // Getters y Setters (Opcional)
    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(String numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPositiveCritics() {
        return positiveCritics;
    }

    public void setPositiveCritics(int positiveCritics) {
        this.positiveCritics = positiveCritics;
    }

    public int getNeutralCritics() {
        return neutralCritics;
    }

    public void setNeutralCritics(int neutralCritics) {
        this.neutralCritics = neutralCritics;
    }

    public int getNegativeCritics() {
        return negativeCritics;
    }

    public void setNegativeCritics(int negativeCritics) {
        this.negativeCritics = negativeCritics;
    }

    public int getPositiveUsers() {
        return positiveUsers;
    }

    public void setPositiveUsers(int positiveUsers) {
        this.positiveUsers = positiveUsers;
    }

    public int getNeutralUsers() {
        return neutralUsers;
    }

    public void setNeutralUsers(int neutralUsers) {
        this.neutralUsers = neutralUsers;
    }

    public int getNegativeUsers() {
        return negativeUsers;
    }

    public void setNegativeUsers(int negativeUsers) {
        this.negativeUsers = negativeUsers;
    }

    public double getMetascore() {
        return metascore;
    }

    public void setMetascore(double metascore) {
        this.metascore = metascore;
    }

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

    @Override
    public int compareTo(Juegos o) {
        try {
            Date thisDate = inputFormat.parse(this.releaseDate);
            Date otherDate = inputFormat.parse(o.releaseDate);
            return thisDate.compareTo(otherDate);
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha: " + e.getMessage());
        }
    }
}

