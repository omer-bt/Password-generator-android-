package com.example.passwordgenerator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private TextView lengthCounterText;
    private int lengthCounter = 6;
    private ImageButton addToLength;
    private ImageButton subtractFromLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         lengthCounterText = (TextView) findViewById(R.id.lengthCounterText);
         addToLength  = findViewById(R.id.addToLength);
         subtractFromLength = findViewById(R.id.subtractFromLength);
        lengthCounterText.setText("" + lengthCounter);

        addToLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lengthCounterText.setText("" + (lengthCounter+=1));
            }
        });

        subtractFromLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lengthCounter > 6) {
                    lengthCounterText.setText("" + (lengthCounter -= 1));
                }else{
                    Toast.makeText(MainActivity.this, "6 characters is the min", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button = findViewById(R.id.generatePass);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView = findViewById(R.id.thePassword);
                textView.setText(generator());

            }
        });


    }

    public String generator(){
        StringBuilder generatorString = new StringBuilder();
        CheckBox lowerCheck = findViewById(R.id.lowerCaseCheck);
        if(lowerCheck.isChecked()){
            generatorString.append(lowerCase());
        }
        CheckBox upperCheck = findViewById(R.id.upperCaseCheck);
        if(upperCheck.isChecked()){
            generatorString.append(upperCase());
        }
        CheckBox numberCheck = findViewById(R.id.numbersCheck);
        if(numberCheck.isChecked()){
            generatorString.append(numbers());
        }
        CheckBox symbolsCheck = findViewById(R.id.symbolsCheck);
        EditText SymbolsET = findViewById(R.id.SymbolsET);
        if(symbolsCheck.isChecked()){
            generatorString.append(SymbolsET.getText().toString());
        }


        String generatedPass = generatingPass(generatorString.toString());
        return generatedPass;


    }

    public String generatingPass(String characters){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        try {
            for (int i = 0; i < Integer.parseInt(lengthCounterText.getText().toString()); i++) {
                sb.append(characters.charAt(random.nextInt(characters
                        .length())));
            }
        }catch (Exception e){

            return "Please pick options";

        }
        CheckBox similarCheck = findViewById(R.id.similarCheck);
        if(!similarCheck.isChecked()){
            for(int i = sb.length() - 1; i > 0; i--) {
                for(int j = i-1; j > 0; j--){
                    if(sb.charAt(i) == sb.charAt(j)){
                        sb.deleteCharAt(j);
                        ;
                        sb.append(randomCharacter());
                    }
                }

            }
        }

        return sb.toString();
    }

    public String lowerCase(){
        String lower = "qwertyuiopasdfghjklzxcvbnm";
        return lower;
    }

    public String upperCase(){
        String upper = "QWERTYUIOPASDFGHJKLZXCVBNM";
        return upper;
    }

    public String numbers(){
        String numbers = "1234567890";
        return numbers;
    }

    public char randomCharacter() {
        StringBuilder allCharacters = new StringBuilder();
        String myString = (lowerCase() + upperCase() + numbers());
        allCharacters.append(myString);
        char randomizedCharacter = (char) allCharacters.charAt((int) (Math.random() * (myString.length() - 0 + 1)) + 0);
        System.out.println("\n"+randomizedCharacter+"\n");
        return randomizedCharacter;
    }



}