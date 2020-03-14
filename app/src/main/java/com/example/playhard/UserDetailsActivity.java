package com.example.playhard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class UserDetailsActivity extends AppCompatActivity {

    TextView knowYouId;
    MaterialButton takeMeHomeButton;
    EditText firstNameEditText , lastNameEditText , levelEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        knowYouId = findViewById(R.id.knowYouId);
        takeMeHomeButton = findViewById(R.id.takeMeHomeButton);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        levelEditText = findViewById(R.id.levelEditText);

        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String level = levelEditText.getText().toString().trim();

        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("FIRSTNAME",firstName);
        editor.putString("LASTNAME",lastName);
        editor.putString("LEVEL",level);

        editor.apply();

        SpannableString spannableString = new SpannableString("Let's get \nto know you");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.homeColor));
        spannableString.setSpan(foregroundColorSpan,18,22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        knowYouId.setText(spannableString);

        takeMeHomeButton.setOnClickListener(view ->
                startActivity(new Intent(UserDetailsActivity.this,HomeActivity.class)));
    }
}
