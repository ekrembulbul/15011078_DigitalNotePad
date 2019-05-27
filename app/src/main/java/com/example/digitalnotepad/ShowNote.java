package com.example.digitalnotepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class ShowNote extends AppCompatActivity
{
    private static final String TAG = "ShowNote";

    Note note;
    int index;
    TextView noteView;
    TextView noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        init();
    }

    public void init()
    {
        Log.d(TAG, "init: initialized.");
        noteView = findViewById(R.id.note_view);
        noteTitle = findViewById(R.id.note_title);
        Intent intent = getIntent();
        note = intent.getParcelableExtra("showNote");
        index = intent.getIntExtra("noteIndex", -1);
        setTitle(note.getTitle());
        displayNote();
    }

    public void displayNote()
    {
        Log.d(TAG, "displayNote: displayed.");
        noteView.setText(note.getNote());
        noteTitle.setText(note.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG, "onCreateOptionsMenu: created.");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_show_note, menu);
        return true;
    }

    public void deleteNote(MenuItem item)
    {
        Log.d(TAG, "deleteNote: deleted.");
        Intent intent = new Intent();
        intent.putExtra("flag", 100);
        intent.putExtra("index", index);
        intent.putExtra("note", note);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void editNote(MenuItem item)
    {
        Log.d(TAG, "editNote: edited.");
        Intent intent = new Intent();
        intent.putExtra("flag", 101);
        intent.putExtra("index", index);
        intent.putExtra("note", note);
        setResult(RESULT_OK, intent);
        finish();
    }
}
