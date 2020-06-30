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

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<CourseInfo> courseInfos;

    public CourseRecyclerAdapter(Context context, List<CourseInfo> courseInfo) {
        this.mContext = context;
        this.courseInfos = courseInfo;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.note_list_course_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseInfo courseInfo = courseInfos.get(position);
        holder.titleCourse.setText(courseInfo.getTitle());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return courseInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleCourse;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleCourse = itemView.findViewById(R.id.course_design_item_course_title);

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
