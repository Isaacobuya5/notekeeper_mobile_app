package com.example.notekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

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
        // dummy note to add
//        sDataManager = DataManager.getInstance();
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the test note body";

        // create a new note placeholder and return its index
        int noteIndex = sDataManager.createNewNote();
        // getting that particular note
        NoteInfo note = sDataManager.getNotes().get(noteIndex);
        // setting the note fields
        note.setCourse(course);
        note.setTitle(noteTitle);
        note.setText(noteText);

        // get note at that particular position
        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        // check if the fields of the note matches the fields we set
        assertEquals(compareNote.getCourse(), course);
        assertEquals(compareNote.getTitle(), noteTitle);
        assertEquals(compareNote.getText(), noteText);
    }

    @Test
    public void findSimilarNotes() {

        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText1 = "This is the body text of my test note";
        final String noteText2  = "This is the body of my second test note";

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

    @Test
    public void createNewNoteOneStopCreation() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body of my test note";
        int noteIndex = sDataManager.createNewNote(course, noteTitle, noteText);
        // get a note at that location
        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertEquals(course, compareNote.getCourse());
        assertEquals(noteText, compareNote.getText());
    }

}