package com.example.perry.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Questions and concerns:
 * 1) I need the user's search results to append to the end of the URL
 * 2) This is a Fragment that does business logic. Prepare a day to separate the view and the logic
 * 3) I may have to encode the user input if Japanese language is inputted.
 * 4) Make better names for the JSON variables
 * 5) Fully understand how retrieving from the internet
 * 6) Look into a better way for retrieving JSON data. Maybe JSON Parser??
 * 7) How to get the user's input into this fragment??
 * 8) When I get the data organized into an object, how should I move it to another Fragment?
 */

public class SearchFragment extends Fragment {
    private ProgressBar progressBar;
    private SearchResultsInfo searchResultsInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.spinnerProgressBar);
        progressBar.setVisibility(view.GONE);
        return view;
    }

    // what format should I do for AsyncTask? <Void, Void, Void>??
    protected class SearchTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(getView().VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(getView().GONE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {
            //int totalResults, String searchTerm, double searchTime, String linkTitles[], String linkURL[]
            String userSearch = "hello";
            StringBuilder url = new StringBuilder("https://www.googleapis.com/customsearch/v1?key=AIzaSyD2Sdv-o5x99pf22pFEs2grw9n76QEl-6I&cx=000522273819204147574:y98ebz7tpde&q=");
            url.append(userSearch);
            int totalResults = 0;
            String searchTerms = "";
            double searchTime = 0.0;
            ArrayList<String> titles = new ArrayList<String>();
            ArrayList<String> links = new ArrayList<String>();

            try {
                InputStream is = new URL(url.toString()).openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                // HMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
                // I should learn exactly how this works before I used this....
                String jsonText = readAll(reader);

                /**
                 * The JSON data we are interested in is:
                 *       queries.request.totalResults
                 *       queries.request.searchTerms
                 *       searchInformation.searchTime
                 *       items[n].kind.title
                 *       items[n].kind.link
                 */

                JSONObject jsonAll = new JSONObject(jsonText); // Holds all of the JSON data
                JSONObject jsonSearchInfo = jsonAll.getJSONObject("searchInformation");
                JSONArray jsonArrayItems = jsonAll.getJSONArray("items");
                if(jsonArrayItems != null) {
                    // If the result is 0???

                    JSONObject jsonArrayBro;

                    for (int x = 0; x < jsonArrayItems.length(); x++) {
                        jsonArrayBro = jsonArrayItems.getJSONObject(x);
                        titles.add(jsonArrayBro.getString("title"));
                        links.add(jsonArrayBro.getString("link"));
                    }
                }
                JSONObject jsonQueries = jsonAll.getJSONObject("queries");
                JSONArray jsonArrayRequest = jsonQueries.getJSONArray("request");
                JSONObject jsonRequest = jsonArrayRequest.getJSONObject(0);

                searchTerms = jsonRequest.getString("searchTerms");
                totalResults = jsonRequest.getInt("totalResults");
                searchTime = jsonSearchInfo.getDouble("searchTime");
                is.close();
            } catch (IOException e) {
                System.out.println("IOExeption");
            } catch (JSONException error) {
                System.out.println("JSONExeption");
            }

            searchResultsInfo = new SearchResultsInfo(totalResults, searchTerms, searchTime, titles, links);

            return null;
        }

        }
    private String readAll(Reader rd) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int count;
        while ((count = rd.read()) != -1) {
            stringBuilder.append((char) count);
        }
        return stringBuilder.toString();
    }
    }


