package com.example.admin.tp10_calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Calculette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculette);
    }

    //LISTENERS

    //Nombres
    public void one(View view) {
        saisir("1");
    }

    public void two(View view) {
        saisir("2");
    }

    public void three(View view) {
        saisir("3");
    }

    public void four(View view) {
        saisir("4");
    }

    public void five(View view) {
        saisir("5");
    }

    public void six(View view) {
        saisir("6");
    }

    public void seven(View view) {
        saisir("7");
    }

    public void eight(View view) {
        saisir("8");
    }

    public void nine(View view) {
        saisir("9");
    }

    private void saisir(String chiffre) {
        TextView saisie = (TextView) findViewById(R.id.saisie);
        saisie.setText(saisie.getText()+chiffre);
    }
}
