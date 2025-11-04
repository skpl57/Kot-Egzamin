package com.example.galeria_sk;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int numerZdjecia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<Integer> obrazy = new ArrayList<>();

        obrazy.add(R.drawable.szczur);
        obrazy.add(R.drawable.kamien);
        obrazy.add(R.drawable.kowadlo);
        obrazy.add(R.drawable.szop);

        ImageView zdjecie = findViewById(R.id.idImg);
        EditText inputText = findViewById(R.id.idNumer);
        Switch przelacznik = findViewById(R.id.idTlo);
        Button next = findViewById(R.id.idNext);
        Button prev = findViewById(R.id.idPrev);


        if(savedInstanceState != null){
            numerZdjecia = savedInstanceState.getInt("NUMER");
            zdjecie.setImageResource(obrazy.get(numerZdjecia));
        }

        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(numerZdjecia < obrazy.size() - 1){
                            numerZdjecia++;
                        }
                        else{
                            numerZdjecia = 0;
                        }
                        zdjecie.setImageResource(obrazy.get(numerZdjecia));
                    }
                }
        );
        prev.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(numerZdjecia > 0){
                            numerZdjecia--;
                        }
                        else{
                            numerZdjecia = obrazy.size() - 1;
                        }
                        zdjecie.setImageResource(obrazy.get(numerZdjecia));
                    }
                }
        );
        inputText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {

                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if(!String.valueOf(inputText.getText()).equals("")){
                            if(Integer.parseInt(String.valueOf(inputText.getText())) < obrazy.size()){
                                numerZdjecia = Integer.parseInt(String.valueOf(inputText.getText()));
                                zdjecie.setImageResource(obrazy.get(numerZdjecia));
                            }
                        }
                    }
                }
        );

        przelacznik.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                        if(b){
                            findViewById(R.id.main).setBackgroundColor(getColor(R.color.blue));
                        }
                        else{
                            findViewById(R.id.main).setBackgroundColor(getColor(R.color.green));

                        }
                    }
                }
        );

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("NUMER", numerZdjecia);
    }
}