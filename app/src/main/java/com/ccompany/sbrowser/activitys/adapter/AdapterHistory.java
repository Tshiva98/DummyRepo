package com.ccompany.sbrowser.activitys.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ccompany.sbrowser.R;
import com.ccompany.sbrowser.activitys.database.HistoryEntity;
import com.ccompany.sbrowser.activitys.pojoclass.PojoHistory;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {

    private List<PojoHistory> pojoHistory;
    private List<HistoryEntity> historyEntities;
    private Context context;

    public AdapterHistory(List<HistoryEntity> historyEntities, Context context) {
        this.historyEntities = historyEntities;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View omen = LayoutInflater.from(context).inflate(R.layout.history_single_item,parent,false);
        return new ViewHolder(omen);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.ViewHolder holder, int position) {
        final PojoHistory temp = pojoHistory.get(position);
        final HistoryEntity temprary = historyEntities.get(position);

        Intent intent = new Intent();
        intent.putExtra("id",temprary.getId());


        holder.history_url.setText(historyEntities.get(position).getHistoryURL());
        holder.url_time.setText(temprary.getTime());
        holder.url_date.setText(temprary.getDate());


    }

    @Override
    public int getItemCount() {
        return historyEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView url_time, url_date, history_url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            url_time = itemView.findViewById(R.id.url_time);
            url_date = itemView.findViewById(R.id.url_date);
            history_url = itemView.findViewById(R.id.history_url);
        }
    }
}
