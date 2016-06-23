package com.jly.purejiandan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.Video;
import com.jly.purejiandan.bean.VideoC;
import com.jly.purejiandan.ui.activity.VideoDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jly on 2016/6/8.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Video> mVideos;
    private List<VideoC> mVideoCs;
    private Context mContext;

    public VideoAdapter(Context context, List<VideoC> videoCs) {
        mVideoCs = videoCs;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VideoC videoC = mVideoCs.get(position);
        if (videoC != null) {
            holder.mTvAuthor.setText(videoC.getComment_author());
            holder.mTvLike.setText(videoC.getVote_positive());
            holder.mTvUnlike.setText(videoC.getVote_negative());
            holder.mTvTime.setText(videoC.getComment_date());
            mVideos = videoC.getVideos();
            if (mVideos.size() > 0 && mVideos.get(0).getThumbnail_v2() != null) {
                Glide.with(mContext).load(mVideos.get(0).getThumbnail_v2()).into(holder.mIvContent);
            }
            holder.mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VideoDetailActivity.class);
                    intent.putExtra("url",videoC.getComment_content());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mVideoCs == null ? 0 : mVideoCs.size();
    }

    public List<VideoC> getVideoCList() {
        return mVideoCs;
    }

    public void changeData(List<VideoC> videoCs) {
        mVideoCs = videoCs;
        notifyDataSetChanged();
    }

    public void addData(List<VideoC> videoCs) {
        if (mVideoCs != null) {
            mVideoCs.addAll(videoCs);
            notifyDataSetChanged();
        } else {
            changeData(videoCs);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_share)
        ImageView mImgShare;
        @Bind(R.id.tv_author)
        TextView mTvAuthor;
        @Bind(R.id.tv_time)
        TextView mTvTime;
        @Bind(R.id.iv_content)
        ImageView mIvContent;
        @Bind(R.id.tv_like)
        TextView mTvLike;
        @Bind(R.id.tv_unlike)
        TextView mTvUnlike;
        @Bind(R.id.card)
        CardView mCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
