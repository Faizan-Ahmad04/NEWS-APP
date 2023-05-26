package com.example.allnewshub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursesRecyclerViewAdapter
        extends
        RecyclerView.Adapter<CoursesRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    LayoutInflater mLayoutInflater;

    ItemClickListenerInterface mItemClickListenerInterface;

    CoursesRecyclerViewAdapter(Context context, List<String> data,
                               ItemClickListenerInterface itemClickListenerInterface){
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(this.context);
        this.data = data;
        mItemClickListenerInterface = itemClickListenerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String courses = data.get(position);
        holder.courseTextView.setText(courses);
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public String getItem(int position) {
        return data.get(position);
    }


    // View Holder class
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView courseTextView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseTextView = itemView.findViewById(R.id.myNewsRow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mItemClickListenerInterface != null) {
                mItemClickListenerInterface.onItemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListenerInterface {
        void onItemClicked(View view, int position);
    }

    // Animations on news titles
    private  void setAnimation(View view, int position) {

        Animation titleSlideIn = AnimationUtils.loadAnimation(context, R.anim.news_title_animation);
        view.startAnimation(titleSlideIn);

    }
}
