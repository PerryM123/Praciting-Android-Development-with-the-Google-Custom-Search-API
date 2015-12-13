package com.example.perry.myapplication;

import java.util.ArrayList;

/**
 * Created by Perry on 12/13/2015.
 *
 * Questions and Conerns:
 * 1) Should I set everything through the constructor or use a bunch of setters? Should I construct it then
 *    have a method for returning the object???
 *
 *
 *
 *
 */
public class SearchResultsInfo {
    private int totalResults;
    private String searchTerm; // I can get from user input also...
    private double searchTime;
    private ArrayList linkTitles;
    private ArrayList linkURLs;

    public SearchResultsInfo(int totalResults, String searchTerm, double searchTime, ArrayList<String> linkTitles, ArrayList<String> linkURLs) {
        this.totalResults = totalResults;
        this.searchTerm = searchTerm;
        this.searchTime = searchTime;
        this.linkTitles = linkTitles;
        this.linkURLs = linkURLs;
    }

    // ????????????????
    public SearchResultsInfo getSearchResultsInfo() {
        return this;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public double getSearchTime() {
        return searchTime;
    }

    public ArrayList<String> getLinkTitles() {
        return linkTitles;
    }

    public ArrayList<String> getLinkURLs() {
        return linkURLs;
    }
}
