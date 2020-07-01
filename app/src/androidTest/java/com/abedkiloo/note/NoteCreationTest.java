package com.abedkiloo.note;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    @Rule
    public ActivityTestRule<NoteListActivity> noteListActivityActivityTestRule = new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote() {
//        ViewInteraction fabnewNote=onView(withId(R.id.edit_note_floating_action_bar));
//        fabnewNote.perform(click());
        onView(withId(R.id.fab)).perform(click());

        /* create note*/
        onView(withId(R.id.text_note_title)).perform(typeText("Test Note Title"));
        onView(withId(R.id.text_note_text)).perform(typeText("This is te body of out note"), closeSoftKeyboard());

    }

}