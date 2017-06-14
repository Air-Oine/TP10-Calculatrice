package com.example.admin.tp10_calculatrice;

import android.icu.util.ValueIterator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculette extends AppCompatActivity {

    public static final int NUMBER_ROW = 4;

    public static final String MULTIPLY = "MULTIPLY";
    public static final String DIVIDE = "DIVIDE";
    public static final String MINUS = "MINUS";
    public static final String ADD = "ADD";

    private Stack<Float> pile = new Stack<>();

    TextView saisie;
    List<TextView> numbersDisplayed = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculette);

        saisie = (TextView) findViewById(R.id.saisie);

        numbersDisplayed.add((TextView) findViewById(R.id.number1));
        numbersDisplayed.add((TextView) findViewById(R.id.number2));
        numbersDisplayed.add((TextView) findViewById(R.id.number3));
        numbersDisplayed.add((TextView) findViewById(R.id.number4));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //LISTENERS

    //Nombres
    public void one(View view) {
        type("1");
    }

    public void two(View view) {
        type("2");
    }

    public void three(View view) {
        type("3");
    }

    public void four(View view) {
        type("4");
    }

    public void five(View view) {
        type("5");
    }

    public void six(View view) {
        type("6");
    }

    public void seven(View view) {
        type("7");
    }

    public void eight(View view) {
        type("8");
    }

    public void nine(View view) {
        type("9");
    }

    public void zero(View view) {
        type("0");
    }

    public void dot(View view) {
        type(".");
    }

    //Contrôles

    public void enter(View view) {
        validate();
    }

    public void clear(View view) {
        pile.removeAllElements();

        refreshNumbers();

        resetTypeField();
    }

    public void back(View view) {
        String valueString = saisie.getText().toString();

        if(!valueString.isEmpty()) {
            valueString = valueString.substring(0, valueString.length()-1);

            saisie.setText(valueString);
        }
    }

    public void pop(View view) {
        if(pile.size() > 0) {
            popStack();
        }
    }

    public void swap(View view) {
        if(pile.size() > 1) {
            Float value1 = pile.pop();
            Float value2 = pile.pop();

            pile.push(value1);
            pile.push(value2);

            refreshNumbers();
        }
    }

    //Opérations

    public void add(View view) {
        calculate(ADD);
    }

    public void minus(View view) {
        calculate(MINUS);
    }

    public void divide(View view) {
        calculate(DIVIDE);
    }

    public void multiply(View view) {
        calculate(MULTIPLY);
    }

    //FONCTIONS PRIVEES

    private void calculate(String operation) {
        //On ajoute la valeur saisie à la pile
        validate();

        if(pile.size() > 1) {
            Float value1 = pile.pop();
            Float value2 = pile.pop();

            Float result = 0f;

            switch (operation) {
                case MULTIPLY:
                    result = value2 * value1;
                    break;
                case DIVIDE:
                    result = value2 / value1;
                    break;
                case MINUS:
                    result = value2 - value1;
                    break;
                case ADD:
                    result = value2 + value1;
                    break;
            }

            pile.push(result);

            refreshNumbers();
        }
    }

    private void validate() {
        String valueString = saisie.getText().toString();

        if(!valueString.isEmpty()) {
            Float value = Float.parseFloat(valueString);

            putStack(value);

            resetTypeField();
        }
    }

    /**
     * Appui sur un chifffre : saisie du chiffre correspondant dans le champ
     */
    private void type(String chiffre) {
        saisie.setText(saisie.getText() + chiffre);
    }

    /**
     * Ajoute sur la stack de nombres une valeur
     *
     * @param value
     */
    private void putStack(Float value) {
        //On ajoute la valeur sur la pile
        pile.push(value);

        refreshNumbers();
    }

    /**
     * Retire de la stack de nombres une valeur
     *
     * @return
     */
    private Float popStack() {
        Float value = pile.pop();

        refreshNumbers();

        return value;
    }

    /**
     * Actualise l'affichage des 4 lignes de nombres
     */
    private void refreshNumbers() {
        String valueRow;

        for (TextView number : numbersDisplayed) {
            int indexOfNumber = numbersDisplayed.indexOf(number);

            if (pile.size() < NUMBER_ROW - indexOfNumber) {
                valueRow = "";
            } else {
                valueRow = String.valueOf(pile.get(pile.size() - (NUMBER_ROW - indexOfNumber)));
            }
            number.setText(valueRow);
        }
    }

    /**
     * Remet à zéro le champ de saisie
     */
    private void resetTypeField() {
        TextView saisie = (TextView) findViewById(R.id.saisie);

        saisie.setText("");
    }
}
