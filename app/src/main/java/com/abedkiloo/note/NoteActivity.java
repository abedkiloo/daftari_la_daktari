package com.abedkiloo.note;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_INFO_POSITION = "com.abedkiloo.note.NOTE_INFO_POSITION";
    private NoteActivityViewModel noteActivityViewModel;
    private NoteInfo noteInfo;
    private boolean isNewNote;
    private int defaultValueBoolean;
    private EditText textNoteText;
    private EditText textNoteTitle;
    private List<CourseInfo> courses;
    private Spinner spinnerCourses;
    private int notePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activty);
        spinnerCourses = findViewById(R.id.spinner_course);

        courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> courseInfoArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        spinnerCourses.setAdapter(courseInfoArrayAdapter);



        /**
         * view model class
         */
        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));

        noteActivityViewModel = viewModelProvider.get(NoteActivityViewModel.class);

        if (savedInstanceState != null && noteActivityViewModel.isNewlyCreated)
            noteActivityViewModel.restoreState(savedInstanceState);

        noteActivityViewModel.isNewlyCreated = false;


        readDisplayStateValues();
        saveCurrentNoteDetails();

        textNoteTitle = findViewById(R.id.text_note_title);
        textNoteText = findViewById(R.id.text_note_text);
        if (!isNewNote)
            displayNote(spinnerCourses, textNoteTitle, textNoteText);
    }

    private void saveCurrentNoteDetails() {
        if (isNewNote)
            return;
        noteActivityViewModel.OriginalCourseId = noteInfo.getCourse().getCourseId();
        noteActivityViewModel.OriginalCourseTitle = noteInfo.getCourse().getCourseId();
        noteActivityViewModel.OriginalCourseText = noteInfo.getCourse().getCourseId();
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> course = DataManager.getInstance().getCourses();
        int courseIndex = course.indexOf(noteInfo.getCourse());
        spinnerCourses.setSelection(courseIndex);
        textNoteText.setText(noteInfo.getText());
        textNoteTitle.setText(noteInfo.getTitle());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
//        noteInfo = intent.getParcelableExtra(NOTE_INFO_POSITION);
        defaultValueBoolean = -1;
        int position = intent.getIntExtra(NOTE_INFO_POSITION, defaultValueBoolean);
        isNewNote = position == defaultValueBoolean;
        if (isNewNote)
            createNewNote();
        else
            noteInfo = DataManager.getInstance().getNotes().get(position);


    }

    private void createNewNote() {
        DataManager dataManager = DataManager.getInstance();
        notePosition = dataManager.createNewNote();
        noteInfo = dataManager.getNotes().get(notePosition);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        noteActivityViewModel.saveState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNote();
    }

    private void saveNote() {
        noteInfo.setCourse((CourseInfo) spinnerCourses.getSelectedItem());
        noteInfo.setTitle(textNoteTitle.getText().toString());
        noteInfo.setText(textNoteText.getText().toString());
    }

}

