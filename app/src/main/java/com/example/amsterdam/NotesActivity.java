package com.example.amsterdam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amsterdam.R;

public class NotesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        initializeLayout();
    }

    private void initializeLayout(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        // Edittext
        EditText notesEdittext = findViewById(R.id.edittext_notes);

        String notesSaved = sharedPref.getString("notes", "");
        if(notesSaved != null)
            notesEdittext.setText(notesSaved);

        // Save button
        TextView saveButton = findViewById(R.id.button_notes_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("notes", notesEdittext.getText().toString());
                editor.apply();

                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }
}
