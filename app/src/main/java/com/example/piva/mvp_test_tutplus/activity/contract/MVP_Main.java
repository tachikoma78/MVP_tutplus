package com.example.piva.mvp_test_tutplus.activity.contract;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.piva.mvp_test_tutplus.activity.view.recycler.NotesViewHolder;
import com.example.piva.mvp_test_tutplus.models.Note;

/**
 * Holder interface that contains all interfaces
 * responsible to maintain communication between
 * Model View Presenter layers.
 * Each layer implements its respective interface:
 *      View implements RequiredViewOps
 *      Presenter implements ProvidedPresenterOps, RequiredPresenterOps
 *      Model implements ProvidedModelOps
 *      */

public interface MVP_Main {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     *      Presenter to View
     */
    interface RequiredViewOps{
        // view operations permitted to the presenter
        Context getAppContext();
        Context getActivityContext();
        void showToast(Toast toast);
        void showProgress();
        void hideProgress();
        void showAlert(AlertDialog dialog);
        void notifyItemRemoved(int position);
        void notifyDataSetChanged();
        void notifyItemInserted(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
        void clearEditText();
    }

    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     *      View to Presenter
     */
    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(RequiredViewOps view);
        NotesViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(NotesViewHolder holder, int position);
        int getNotesCount();
        void clickNewNote(EditText editText);
        void clickDeleteNote(Note note, int adapterPos, int layoutPos);
    }

    /**
     * Required Presenter methods available to Model.
     *
     */
    interface RequiredPresenterOps{
        Context getAppContext();
        Context getActivityContext();
    }

    /**
     * Operations offered to Model to communicate with Presenter
     * Handles all data business logic.
     *
     */

    interface ProvidedModelsOps{
        void onDestroy(boolean isChangingConfiguration);
        int insertNote(Note note);
        boolean loadData();
        Note getNote(int position);
        boolean deleteNote(Note note, int adapterPos);
        int getNotesCount();
    }


}
