package com.example.digitalnotepad;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    public ArrayList<Note> notes;
    private DatabaseHalper myDb;

    private int editIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        init();
    }

    public void init()
    {
        Log.d(TAG, "init: initialized.");
        notes = new ArrayList<>();
        myDb = new DatabaseHalper(this);
        getDatas();
        initRecyclerView();
    }

    public void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: initialized.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdaptor adaptor = new RecyclerViewAdaptor(this, notes);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addData(Note n)
    {
        boolean isInserted = myDb.insertData(n.getTitle(), n.getNote(), n.getDate(), n.getHour(), n.getAdress(), n.getColor(), n.getPriority());
        if (isInserted)
        {
            Log.d(TAG, "addData: inserted.");
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
            int id = myDb.getDbId();
            System.out.println(id);
            n._setId(id);
        }
    }

    public void getDatas()
    {
        Cursor res = myDb.getData();

        if (res.getCount() == 0)
            return;

        while (res.moveToNext())
        {
            int id = res.getInt(0);
            String title = res.getString(1);
            String note = res.getString(2);
            String date = res.getString(3);
            String hour = res.getString(4);
            String adress = res.getString(5);
            int color = res.getInt(6);
            int priority = res.getInt(7);
            Note n = new Note(title, note, date, hour, adress, color, priority);
            n._setId(id);
            notes.add(n);
        }
    }

    public void updateData(Note n, int id)
    {
        myDb.updateData(Integer.toString(id), n.getTitle(), n.getNote(), n.getDate(), n.getHour(), n.getAdress(), n.getColor(), n.getPriority());
    }

    public void deleteData(int id)
    {
        Integer deletedRows = myDb.deleteData(Integer.toString(id));
        System.out.println(deletedRows);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG, "onCreateOptionsMenu: created.");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addClick(MenuItem item)
    {
        Log.d(TAG, "addClick: clicked.");
        Intent intent = new Intent(this, AddNote.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            Log.d(TAG, "onActivityResult: resulted.");

            Note note = data.getParcelableExtra("note");
            notes.add(note);
            addData(note);

            initRecyclerView();
        }

        if (requestCode == 2 && resultCode == RESULT_OK)
        {
            Log.d(TAG, "onActivityResult: resulted.");

            int flag = data.getIntExtra("flag", -1);
            Log.d(TAG, "onActivityResult: indexed.");
            editIndex = data.getIntExtra("index", -1);
            Note note = data.getParcelableExtra("note");

            if (flag == 100)
            {
                Log.d(TAG, "onActivityResult: deleted.");
                deleteData(note._getId());
                notes.remove(editIndex);
            }
            else if (flag == 101)
            {
                Log.d(TAG, "onActivityResult: updated.");
                Intent intent = new Intent(this, EditNote.class);
                intent.putExtra("note", note);
                startActivityForResult(intent, 3);
            }

            initRecyclerView();
        }

        if (requestCode == 3 && resultCode == RESULT_OK)
        {
            Log.d(TAG, "onActivityResult: resulted edit.");

            Note note = data.getParcelableExtra("note");
            updateData(note, note._getId());
            notes.remove(editIndex);
            notes.add(editIndex, note);

            initRecyclerView();
        }
    }

    public void sortClick(MenuItem item)
    {

    }
}
