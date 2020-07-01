package com.abedkiloo.note;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    static DataManager dataManager;
    @Rule
    public ActivityTestRule<NoteListActivity> noteListActivityActivityTestRule = new ActivityTestRule<>(NoteListActivity.class);

    @BeforeClass
    public static void classSetup() {
        dataManager = DataManager.getInstance();
    }

    @Test
    public void createNewNote() {


        CourseInfo courseInfo = dataManager.getCourse("java_lang");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body of our test note";
//        ViewInteraction fabnewNote=onView(withId(R.id.edit_note_floating_action_bar));
//        fabnewNote.perform(click());
        onView(withId(R.id.fab)).perform(click());


        onView(withId(R.id.spinner_course)).perform(click());
        onData(allOf(instanceOf(CourseInfo.class), equalTo(courseInfo))).perform(click());
        /* create note*/
        onView(withId(R.id.text_note_title)).perform(typeText(noteTitle));
        onView(withId(R.id.text_note_text)).perform(typeText(noteText), closeSoftKeyboard());


        /*view interaction for the spinner*/
        onView(withId(R.id.spinner_course)).check(matches(withSpinnerText(
                containsString(courseInfo.getTitle())
        )));

        /* test whether the correct data is type(just for fun verification)*/
        onView(withId(R.id.text_note_text)).check(matches(withText(containsString(noteTitle))));
        onView(withId(R.id.text_note_title)).check(matches(withText(containsString(noteText))));

        pressBack();

        /* validate create of the note*/
        int lastIndexOfNote = dataManager.getNotes().size() - 1;
        NoteInfo createdNote=dataManager.getNotes().get(lastIndexOfNote);
        assertEquals(courseInfo,createdNote.getCourse());
        assertEquals(noteText,createdNote.getText());
        assertEquals(noteTitle,createdNote.getTitle());

    }

}