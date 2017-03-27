package com.example.piva.mvp_test_tutplus.activity.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.piva.mvp_test_tutplus.R;

/**
 * Created by piva on 05/09/16.
 */
public class NotesViewHolder  extends RecyclerView.ViewHolder{

    public RelativeLayout container;
    public TextView text, date;
    public ImageButton btnDelete;

    public NotesViewHolder(View itemView) {
        super(itemView);

        setupViews(itemView);
    }

    private void setupViews(View view) {
        container = (RelativeLayout) view.findViewById(R.id.holder_container);
        text = (TextView) view.findViewById(R.id.note_text);
        date = (TextView) view.findViewById(R.id.note_date);
        btnDelete = (ImageButton) view.findViewById(R.id.btn_delete);
    }



}
