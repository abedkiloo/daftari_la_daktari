package com.abedkiloo.note;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<NoteInfo> noteInfos;

    public NoteRecyclerAdapter(Context context, List<NoteInfo> noteInfos) {
        this.mContext = context;
        this.noteInfos = noteInfos;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.note_list_item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteInfo noteInfo = noteInfos.get(position);
        holder.textCourse.setText(noteInfo.getText());
        holder.titleCourse.setText(noteInfo.getCourse().getTitle());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return noteInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textCourse;
        public final TextView titleCourse;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textCourse = itemView.findViewById(R.id.design_item_course_text);
            titleCourse = itemView.findViewById(R.id.design_item_course_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NoteActivity.class);
                    intent.putExtra(NoteActivity.NOTE_INFO_POSITION, mCurrentPosition);
                    mContext.startActivity(intent);

                }
            });
        }
    }
}
