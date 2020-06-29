package com.abedkiloo.note;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class NoteActivityViewModel extends ViewModel {
    public String OriginalCourseId, OriginalCourseTitle, OriginalCourseText,
            ORIGINAL_NOTE_COURSE_ID = "com.abedkiloo.note.NOTE_COURSE_ID",
            ORIGINAL_NOTE_TITLE = "com.abedkiloo.note.NOTE_TITLE",
            ORIGINAL_NOTE_TEXT = "com.abedkiloo.note.NOTE_TEXT";

    public boolean isNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putString(ORIGINAL_NOTE_COURSE_ID, OriginalCourseId);
        outState.putString(ORIGINAL_NOTE_TEXT, OriginalCourseText);
        outState.putString(ORIGINAL_NOTE_TITLE, OriginalCourseTitle);
    }

    public void restoreState(Bundle inState) {
        OriginalCourseId = inState.getString(ORIGINAL_NOTE_COURSE_ID);
        OriginalCourseText = inState.getString(ORIGINAL_NOTE_TEXT);
        OriginalCourseTitle = inState.getString(ORIGINAL_NOTE_TITLE);
    }
}
