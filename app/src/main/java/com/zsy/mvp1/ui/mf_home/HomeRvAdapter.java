package com.zsy.mvp1.ui.mf_home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zsy.mvp1.R;

import java.util.List;

/**
 * Created by 24275 on 2016/10/20.
 */

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.HomeRvVH> {


    private List<String> mData;

    public HomeRvAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public HomeRvVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_rv, parent, false);
        return new HomeRvVH(view);
    }

    @Override
    public void onBindViewHolder(HomeRvVH holder, int position) {
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class HomeRvVH extends RecyclerView.ViewHolder {

        TextView tv;

        public HomeRvVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
