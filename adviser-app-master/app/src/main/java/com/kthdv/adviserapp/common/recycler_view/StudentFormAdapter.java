package com.kthdv.adviserapp.common.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.recycler_view.base.RecyclerViewAdapter;
import com.kthdv.adviserapp.common.utils.Utils;
import com.kthdv.adviserapp.models.StudentForm;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentFormAdapter extends RecyclerViewAdapter {
    public StudentFormAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View itemView = getInflater().inflate(R.layout.item_student_form, parent, false);
        return new ItemStudentFormViewHolder(itemView);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        StudentForm studentForm = getItem(position, StudentForm.class);

        ItemStudentFormViewHolder itemStudentFormViewHolder = (ItemStudentFormViewHolder) holder;
        itemStudentFormViewHolder.txtIndex.setText((position + 1) + "");
        itemStudentFormViewHolder.txtStudentName.setText(studentForm.getStudentName());
        itemStudentFormViewHolder.txtStudentID.setText("Student ID: " + studentForm.getStudentUsername());
        itemStudentFormViewHolder.txtCreatedDate.setText(Utils.getDateFromMilliseconds(studentForm.getLastModified()));
    }

    class ItemStudentFormViewHolder extends NormalViewHolder {
        @BindView(R.id.txt_index)
        TextView txtIndex;
        @BindView(R.id.txt_student_name)
        TextView txtStudentName;
        @BindView(R.id.txt_student_id)
        TextView txtStudentID;
        @BindView(R.id.txt_created_date)
        TextView txtCreatedDate;

        ItemStudentFormViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
