package com.zsy.mvp1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zsy.sum.utils.depend.Lg;

/**
 * Created by 24275 on 2016/10/26.
 */

public class LoadRecyclerView extends RecyclerView {


    private boolean isAutoLoad = true;
    private LoadAdapter loadAdapter;

    public LoadRecyclerView(Context context) {
        this(context, null);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public LoadAdapter getLoadAdapter() {
        return loadAdapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (isAutoLoad) {
            loadAdapter = new LoadAdapter(adapter);
        }
        super.swapAdapter(adapter, true);
    }

    public static class LoadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        //        private final int LoadType = 0x10;
        private final int FooterType = 0x20;

        private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
        private LoadViewHolder loadViewHolder;

        public LoadAdapter(RecyclerView.Adapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Lg.e("ViewType:" + viewType + "; FooterType:" + FooterType);
            if (viewType == FooterType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_footer, parent, false);
                loadViewHolder = new LoadViewHolder(view);
                return loadViewHolder;
            }
            return mAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (getItemViewType(position) != FooterType) {
                Lg.i("bindLoadHolder");
                mAdapter.onBindViewHolder(holder, position);
            } else {
                Lg.i("bindFooterHolder");
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == mAdapter.getItemCount()) {
                return FooterType;
            }
            return mAdapter.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            Lg.e("count:" + mAdapter.getItemCount());
            return mAdapter.getItemCount() + 1;
        }
    }

    public static class LoadViewHolder extends RecyclerView.ViewHolder {

        ProgressBar rvFooterPb;
        TextView rvFooterTv;

        public LoadViewHolder(View itemView) {
            super(itemView);
            rvFooterPb = (ProgressBar) itemView.findViewById(R.id.pb_rv_footer);
            rvFooterTv = (TextView) itemView.findViewById(R.id.tv_rv_footer);
        }
    }

//    set


}
