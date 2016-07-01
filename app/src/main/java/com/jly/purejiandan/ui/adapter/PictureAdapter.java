package com.jly.purejiandan.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.Picture;
import com.jly.purejiandan.ui.activity.PictureDetailActivity;
import com.jly.purejiandan.utils.FileUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jly on 2016/6/8.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Picture> mPictures;
    private Context mContext;

    public PictureAdapter(Context context, List<Picture> pictures) {
        mPictures = pictures;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Picture picture = mPictures.get(position);
        if (picture != null) {
            holder.mTvAuthor.setText(picture.getComment_author());
            holder.mTvLike.setText(picture.getVote_positive());
            holder.mTvUnlike.setText(picture.getVote_negative());
            holder.mTvTime.setText(picture.getComment_date());
            holder.mTvContent.setText(picture.getText_content());
            if (picture.getPics().get(0).endsWith("gif")) {
                Glide.with(mContext).load(picture.getPics().get(0)).asBitmap().fitCenter().into(holder.mIvContent);
                holder.mImgGif.setVisibility(View.VISIBLE);
            } else {
                Glide.with(mContext).load(picture.getPics().get(0)).fitCenter().into(holder.mIvContent);
                holder.mImgGif.setVisibility(View.GONE);
            }
            holder.mIvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PictureDetailActivity.class);
                    intent.putExtra(PictureDetailActivity.IMG_URL, picture.getPics().get(0));
                    mContext.startActivity(intent);
                }
            });
            holder.mImgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialDialog.Builder(mContext)
                            .items(R.array.picture_dialog)
                            .backgroundColor(mContext.getResources().getColor(R.color.colorPrimary))
                            .contentColor(Color.WHITE)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                    switch (which) {
                                        //分享
                                        case 0:
                                            FileUtil.sharePicture((Activity) mContext,picture.getPics().get(0));
                                            break;
                                        //保存
                                        case 1:
                                            FileUtil.savePicture((Activity) mContext,picture.getPics().get(0));
                                            break;
                                    }
                                }
                            })
                            .show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mPictures == null ? 0 : mPictures.size();
    }

    public List<Picture> getPictureList() {
        return mPictures;
    }

    public void changeData(List<Picture> pictures) {
        mPictures = pictures;
        notifyDataSetChanged();
    }

    public void addData(List<Picture> pictures) {
        if (mPictures != null) {
            mPictures.addAll(pictures);
            notifyDataSetChanged();
        } else {
            changeData(pictures);
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
        @Bind(R.id.tv_content)
        TextView mTvContent;
        @Bind(R.id.img_gif)
        ImageView mImgGif;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
