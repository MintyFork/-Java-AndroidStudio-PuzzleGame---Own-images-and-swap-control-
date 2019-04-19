package com.example.minty.photopuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.example.minty.photopuzzle.GameActivity.score;

public class GameOverActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        textView = (TextView) findViewById(R.id.scoreid);
        textView.setText(Integer.toString(score-1));
    }

    public void clickGO(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.restart:
                intent = new Intent(GameOverActivity.this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.changediff:
                intent = new Intent(GameOverActivity.this, DifficultyActivity.class);
                startActivity(intent);
                break;
        }
    }

}
