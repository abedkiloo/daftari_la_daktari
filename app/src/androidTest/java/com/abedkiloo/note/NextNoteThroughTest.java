package com.abedkiloo.note;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;




public class NextNoteThroughTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void NextThroughNotes() {
        /* select notes*/
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes));


        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        /*
        verify data in the selected item
         */
        List<NoteInfo> noteInfos = DataManager.getInstance().getNotes();
        int index = 0;
        NoteInfo noteInfo = noteInfos.get(index);


        onView(withId(R.id.spinner_course)).check(
                matches(withSpinnerText(noteInfo.getCourse().getTitle()))
        );
        onView(withId(R.id.text_note_title)).check(
                matches(withText(noteInfo.getTitle()))
        );
        onView(withId(R.id.text_note_text)).check(
                matches(withText(noteInfo.getText()))
        );

    }

}