package com.abedkiloo.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> noteInfoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });
        intializeDisplayContent();
    }

    private void intializeDisplayContent() {
        final ListView listNote = findViewById(R.id.note_list_view);
        List<NoteInfo> noteInfos = DataManager.getInstance().getNotes();
        noteInfoArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, noteInfos
        );
        listNote.setAdapter(noteInfoArrayAdapter);
        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);

//                NoteInfo noteInfo = (NoteInfo) listNote.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_INFO_POSITION, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteInfoArrayAdapter.notifyDataSetChanged();
    }
}
