package com.example.bali;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bali.R;

public class NotesFragment extends Fragment {

    EditText notesEdittext;
    TextView saveButton;

    public NotesFragment(){
        super(R.layout.fragment_notes);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        notesEdittext = view.findViewById(R.id.edittext_notes);
        saveButton = view.findViewById(R.id.button_notes_save);

        initializeLayout();
    }

    private void initializeLayout(){
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String notesSaved = sharedPref.getString("notes", "");
        notesEdittext.setText(notesSaved);

        saveButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("notes", notesEdittext.getText().toString());
            editor.apply();

            Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();

            getActivity().getFragmentManager().popBackStack();
        });
    }
}
