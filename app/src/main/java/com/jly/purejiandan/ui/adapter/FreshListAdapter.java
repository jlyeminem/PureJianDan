package com.jly.purejiandan.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.FreshNews;
import com.jly.purejiandan.db.ReadFreshNews;
import com.jly.purejiandan.ui.activity.FreshDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jly on 2016/6/5.
 */
public class FreshListAdapter extends RecyclerView.Adapter<FreshListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<FreshNews> mFreshNewses;
    private Context mContext;

    public FreshListAdapter(Context context, List<FreshNews> freshNewses) {
        mFreshNewses = freshNewses;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fresh_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final FreshNews freshNews = mFreshNewses.get(position);
        if (freshNews != null) {
            List<String> images = freshNews.getCustom_fields().getThumb_c();
            if (images != null && images.size() > 0) {
                Glide.with(mContext).load(images.get(0)).placeholder(R.drawable.ic_loading_small).centerCrop().into(holder.mImg);
            }
            holder.mTvTitle.setText(freshNews.getTitle());
            String strSource = freshNews.getAuthor().getName() + "@" + freshNews.getTags().get(0).getTitle();
            holder.mTvInfo.setText(strSource);
            if (freshNews.isRead()) {
                holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.textColorSecond_Day));
                holder.mTvInfo.setTextColor(ContextCompat.getColor(mContext, R.color.textColorSecond_Day));
            } else {
                holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.textColorFirst_Day));
                holder.mTvInfo.setTextColor(ContextCompat.getColor(mContext, R.color.textColorFirst_Day));
            }
            holder.mCvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!freshNews.isRead()) {
                        freshNews.setRead(true);
                        holder.mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.textColorSecond_Day));
                        holder.mTvInfo.setTextColor(ContextCompat.getColor(mContext, R.color.textColorSecond_Day));
                        //操作数据库是耗时操作，需要开启线程操作
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ReadFreshNews.getInstance(mContext).addReadId(freshNews.getId());
                            }
                        }).start();
                    }
                    FreshDetailActivity.start(mContext, freshNews);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mFreshNewses == null ? 0 : mFreshNewses.size();
    }

    public List<FreshNews> getFreshNewses() {
        return mFreshNewses;
    }

    public void changeData(List<FreshNews> freshNewses) {
        mFreshNewses = freshNewses;
        notifyDataSetChanged();
    }

    public void addData(List<FreshNews> freshNewses) {
        if (mFreshNewses != null) {
            mFreshNewses.addAll(freshNewses);
            notifyDataSetChanged();
        } else {
            changeData(freshNewses);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_img)
        ImageView mImg;
        @Bind(R.id.tv_title)
        TextView mTvTitle;
        @Bind(R.id.tv_info)
        TextView mTvInfo;
        @Bind(R.id.cv_item)
        CardView mCvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
