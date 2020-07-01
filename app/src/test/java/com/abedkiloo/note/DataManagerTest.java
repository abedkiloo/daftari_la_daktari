package com.abedkiloo.note;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertSame;


public class DataManagerTest {

    static DataManager sDataManager;

    @BeforeClass
    public static void classSetUp() {
        sDataManager = DataManager.getInstance();
    }


    @Before
    public void setUp() {
        sDataManager.getNotes().clear();
        sDataManager.initializeExampleNotes();
    }


    @Test
    public void createNewNote() {
        sDataManager = DataManager.getInstance();
        final CourseInfo courseInfo = sDataManager.getCourse("android_sync");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body of my note test";
        int noteIndex = sDataManager.createNewNote();
        NoteInfo newNoteInfo = sDataManager.getNotes().get(noteIndex);
        newNoteInfo.setCourse(courseInfo);
        newNoteInfo.setText(noteText);
        newNoteInfo.setTitle(noteTitle);




        /* Testing note  first parameter is the expected value the second is the what you are testing against*/
        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertSame(courseInfo, compareNote.getCourse());
        assertSame(noteTitle, compareNote.getTitle());
        assertSame(noteText, compareNote.getText());
    }


    @Test
    public void findSimilarNotes() {

        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText1 = "This is the body text of my test note";
        final String noteText2 = "This is the body of my second test note";

        int noteIndex1 = sDataManager.createNewNote();
        NoteInfo newNote1 = sDataManager.getNotes().get(noteIndex1);
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        int noteIndex2 = sDataManager.createNewNote();
        NoteInfo newNote2 = sDataManager.getNotes().get(noteIndex2);
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);

        int foundIndex1 = sDataManager.findNote(newNote1);
        assertEquals(noteIndex1, foundIndex1);

        int foundIndex2 = sDataManager.findNote(newNote2);
        assertEquals(noteIndex2, foundIndex2);
    }
}