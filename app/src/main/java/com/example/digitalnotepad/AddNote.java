package com.example.digitalnotepad;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNote extends AppCompatActivity
{
    private static final String TAG = "AddNote";

    private RadioGroup priorityGroup;
    private RadioGroup colorGroup;
    private EditText titleText;
    private EditText noteText;
    private int color;
    private int priority;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Log.d(TAG, "onCreate: started.");

        init();
    }

    public void init()
    {
        Log.d(TAG, "init: initialized.");

        titleText = findViewById(R.id.title_text);
        noteText = findViewById(R.id.note_text);
        priorityGroup = findViewById(R.id.priority_group);
        colorGroup = findViewById(R.id.color_group);
    }

    public int getRadioPriority()
    {
        Log.d(TAG, "getRadioPriority: getting.");

        int priorityId = priorityGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(priorityId);

        if (radioButton != null)
        {
            if (radioButton.getText().toString().compareTo("High") == 0)
            {
                return 0;
            }
            else if (radioButton.getText().toString().compareTo("Medium") == 0)
            {
                return 1;
            }
            else if (radioButton.getText().toString().compareTo("Low") == 0)
            {
                return 2;
            }
        }
        
        return -1;
    }

    public int getRadioColor()
    {
        Log.d(TAG, "getRadioColor: getting.");

        int colorId = colorGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(colorId);

        if (radioButton != null)
        {
            if (radioButton.getText().toString().compareTo("Red") == 0)
            {
                return ContextCompat.getColor(this, R.color.myRed);
            }
            else if (radioButton.getText().toString().compareTo("Lime") == 0)
            {
                return ContextCompat.getColor(this, R.color.myLime);
            }
            else if (radioButton.getText().toString().compareTo("Yellow") == 0)
            {
                return ContextCompat.getColor(this, R.color.myYellow);
            }
            else if (radioButton.getText().toString().compareTo("Aqua") == 0)
            {
                return ContextCompat.getColor(this, R.color.myAqua);
            }
            else if (radioButton.getText().toString().compareTo("Blue") == 0)
            {
                return ContextCompat.getColor(this, R.color.myBlue);
            }
            else if (radioButton.getText().toString().compareTo("Fuchsia") == 0)
            {
                return ContextCompat.getColor(this, R.color.myFuchsia);
            }
        }

        return -1;
    }
    
    public boolean inputControl()
    {
        Log.d(TAG, "inputControl: checked.");
        
        color = getRadioColor();
        priority = getRadioPriority();

        if (TextUtils.isEmpty(titleText.getText()))
        {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(noteText.getText()))
        {
            Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (priority == -1)
        {
            Toast.makeText(this, "Please choose a priority", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (color == -1)
        {
            Toast.makeText(this, "Please choose a color", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG, "onCreateOptionsMenu: created.");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_note, menu);
        return true;
    }

    public void save(MenuItem item)
    {
        if (inputControl())
        {
            Log.d(TAG, "save: saved.");

            String title = titleText.getText().toString();
            String note = noteText.getText().toString();

            Note n = new Note(title, note, color, priority);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("note", n);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
