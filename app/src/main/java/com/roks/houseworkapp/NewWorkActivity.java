package com.roks.houseworkapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewWorkActivity extends AppCompatActivity {

    public static final String WORK_NAME = "workName";
    public static final String WORK_SCORE = "workScore";

    private EditText editWorkName;
    private EditText editWorkScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_work);

        editWorkName = findViewById(R.id.edit_work);
        editWorkScore = findViewById(R.id.edit_score);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editWorkName.getText()) || TextUtils.isEmpty(editWorkScore.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String work = editWorkName.getText().toString();
                Integer score = Integer.parseInt(editWorkScore.getText().toString());
                replyIntent.putExtra(WORK_NAME, work);
                replyIntent.putExtra(WORK_SCORE, score);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
