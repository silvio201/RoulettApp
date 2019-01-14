package com.example.fanzengruber15.roulettapp;

import android.os.AsyncTask;

public class FewResult extends AsyncTask<Void, Void, Void> {
    MainActivity mainActivity;

    public FewResult(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mainActivity.findViewById(R.id.buttonGuess).setEnabled(false);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress();
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        mainActivity.resetImages();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mainActivity.findViewById(R.id.buttonGuess).setEnabled(true);
    }
}
