package com.example.piva.mvp_test_tutplus;


import android.content.Context;

import com.example.piva.mvp_test_tutplus.activity.model.MainModel;
import com.example.piva.mvp_test_tutplus.activity.presenter.MainPresenter;
import com.example.piva.mvp_test_tutplus.data.DAO;
import com.example.piva.mvp_test_tutplus.models.Note;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 24, manifest = "/src/main/AndroidManifest.xml")
public class MainModelTest {


    private MainModel mModel;
    private DAO mDAO;

    @Before
    public void setup() {
        // Using RuntimeEnvironment.application will permit
        // us to access a Context and create a real DAO
        // inserting data that will be saved temporarily
        Context context = RuntimeEnvironment.application;
        mDAO = new DAO(context);
        // Using a mock Presenter will permit to verify
        // if certain methods were called in Presenter
        MainPresenter mockPresenter = Mockito.mock(MainPresenter.class);
        // We create a Model instance using a construction that includes
        // a DAO. This constructor exists to facilitate tests
        mModel = new MainModel(mockPresenter, mDAO);
        // Subscribing mNotes is necessary for tests  methods
        // that depends on the arrayList
        mModel.mNotes = new ArrayList<>();
        // We’re reseting our mock Presenter to guarantee that our method verification remain consistent between the tests
        // Mockito.reset(mockPresenter);
        reset(mockPresenter);
    }

    private Note createNote(String text) {
        Note note = new Note();
        note.setText(text);
        note.setDate("some date");
        return note;
    }

    @Test
    public void loadData(){

        int notesSize = 10;
        for (int i =0; i<notesSize; i++){
            mDAO.insertNote(createNote("note_" + Integer.toString(i)));
        }

        mModel.loadData();
        assertEquals(mModel.mNotes.size(), notesSize);

    }

    @Test
    public void insertNote() {
        int pos = mModel.insertNote(createNote("noteText"));
        assertTrue(pos > -1);
    }

    @Test
    public void deleteNote() {
        Note note = createNote("testNote");
        Note insertedNote = mDAO.insertNote(note);
        mModel.mNotes = new ArrayList<>();
        mModel.mNotes.add(insertedNote);

        assertTrue(mModel.deleteNote(insertedNote, 0));

        Note fakeNote = createNote("fakeNote");
        assertFalse(mModel.deleteNote(fakeNote, 0));
    }







}
