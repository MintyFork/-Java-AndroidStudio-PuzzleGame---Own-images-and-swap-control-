package com.example.minty.photopuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static com.example.minty.photopuzzle.GameActivity.diff;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
    }

    public void clickD(View view) {
        switch(view.getId()){
            case R.id.button2:
                diff = 50;
                break;
            case R.id.button3:
                diff = 200;
                break;
            case R.id.button4:
                diff = 500;
                break;
        }
        Intent intent = new Intent(DifficultyActivity.this, GameActivity.class);
        startActivity(intent);
    }
}