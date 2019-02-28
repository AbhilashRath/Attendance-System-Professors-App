package com.halo.AttenGoTeacher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    ArrayList<Student> Students;

    public StudentAdapter(Context c , ArrayList<Student> p)
    {
        context = c;
        Students = p;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.student_info,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.Name.setText(Students.get(position).getName());
        holder.RollNo.setText(Students.get(position).getRollNo());
        holder.AttendanceCount.setText(Students.get(position).getChemistry().toString());

    }

    @Override
    public int getItemCount() {
        return Students.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,RollNo,AttendanceCount;

        public StudentViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.text_view_name);
            RollNo = (TextView) itemView.findViewById(R.id.text_view_rollno);
            AttendanceCount = (TextView) itemView.findViewById(R.id.text_attendance_count);
        }

    }
}