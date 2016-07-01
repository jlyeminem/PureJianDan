package com.jly.purejiandan.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.Joke;
import com.jly.purejiandan.utils.FileUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jly on 2016/6/5.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Joke> mJokes;
    private Context mContext;

    public JokeAdapter(Context context, List<Joke> jokes) {
        mJokes = jokes;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_joke, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Joke joke = mJokes.get(position);
        if (joke != null) {
            holder.mTvAuthor.setText(joke.getComment_author());
            holder.mTvContent.setText(joke.getText_content());
            holder.mTvLike.setText(joke.getVote_positive());
            holder.mTvUnlike.setText(joke.getVote_negative());
            holder.mTvTime.setText(joke.getComment_date());
        }
        holder.mImgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(mContext)
                        .items(R.array.joke_dialog)
                        .backgroundColor(mContext.getResources().getColor(R.color.colorPrimary))
                        .contentColor(Color.WHITE)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                switch (which) {
                                    //分享
                                    case 0:
                                        FileUtil.shareText((Activity) mContext, joke.getComment_content().trim());
                                        break;
                                    //复制
                                    case 1:
                                        FileUtil.copyText((Activity) mContext, joke.getComment_content());
                                        break;
                                }

                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mJokes == null ? 0 : mJokes.size();
    }

    public List<Joke> getJokeList() {
        return mJokes;
    }

    public void changeData(List<Joke> jokes) {
        mJokes = jokes;
        notifyDataSetChanged();
    }

    public void addData(List<Joke> jokes) {
        if (mJokes != null) {
            mJokes.addAll(jokes);
            notifyDataSetChanged();
        } else {
            changeData(jokes);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_share)
        ImageView mImgShare;
        @Bind(R.id.tv_author)
        TextView mTvAuthor;
        @Bind(R.id.tv_time)
        TextView mTvTime;
        @Bind(R.id.tv_content)
        TextView mTvContent;
        @Bind(R.id.tv_like)
        TextView mTvLike;
        @Bind(R.id.tv_unlike)
        TextView mTvUnlike;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
