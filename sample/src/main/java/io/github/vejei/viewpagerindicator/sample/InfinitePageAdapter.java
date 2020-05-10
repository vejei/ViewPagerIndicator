package io.github.vejei.viewpagerindicator.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfinitePageAdapter extends RecyclerView.Adapter<InfinitePageAdapter.ViewHolder> {
    private List<String> data = new ArrayList<>();

    void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_page, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position % data.size()));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contentTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            contentTextView = itemView.findViewById(R.id.text_view_content);
        }

        void bind(String item) {
            contentTextView.setText(item);
        }
    }
}
