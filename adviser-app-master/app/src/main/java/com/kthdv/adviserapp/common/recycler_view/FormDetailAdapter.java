package com.kthdv.adviserapp.common.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kthdv.adviserapp.R;
import com.kthdv.adviserapp.common.recycler_view.base.RecyclerViewAdapter;
import com.kthdv.adviserapp.models.Score;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormDetailAdapter extends RecyclerViewAdapter {
    public FormDetailAdapter(Context context) {
        super(context, false);
    }

    public void setData(Map<String, Integer> data) {
        getListWrapperModels().clear();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            addModel(new Score(entry.getKey(), entry.getValue()), false);
        }
        notifyDataSetChanged();
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View itemView = getInflater().inflate(R.layout.item_score, parent, false);
        return new FormDetailViewHolder(itemView);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        Score score = getItem(position, Score.class);

        FormDetailViewHolder formDetailViewHolder = (FormDetailViewHolder) holder;
        formDetailViewHolder.txtName.setText(score.getName());
        formDetailViewHolder.txtScore.setText(score.getScore() + "");
    }

    class FormDetailViewHolder extends NormalViewHolder {
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_score)
        TextView txtScore;

        FormDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
