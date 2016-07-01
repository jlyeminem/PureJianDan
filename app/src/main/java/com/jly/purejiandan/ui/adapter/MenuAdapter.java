package com.jly.purejiandan.ui.adapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jly.purejiandan.R;
import com.jly.purejiandan.ui.FragmentMenuItem;
import com.jly.purejiandan.ui.activity.MainActivity;
import com.jly.purejiandan.ui.fragment.FreshListFragment;
import com.jly.purejiandan.ui.fragment.JokeFragment;
import com.jly.purejiandan.ui.fragment.OXPictureFragment;
import com.jly.purejiandan.ui.fragment.PictureFragment;
import com.jly.purejiandan.ui.fragment.VideoFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * Created by jly on 2016/6/2.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<FragmentMenuItem> mFragmentMenuItems;
    private MainActivity mMainActivity;

    public MenuAdapter(Context context) {
        mFragmentMenuItems = new ArrayList<>();
        mMainActivity = (MainActivity) context;
        mInflater = LayoutInflater.from(context);
        initMenu();
    }

    private void initMenu() {
        mFragmentMenuItems.clear();
        mFragmentMenuItems.add(new FragmentMenuItem("新鲜事", R.drawable.ic_explore_white_24dp, FragmentMenuItem.FragmentType.FreshNews,
                FreshListFragment.class));
        mFragmentMenuItems.add(new FragmentMenuItem("无聊图", R.drawable.ic_mood_white_24dp, FragmentMenuItem.FragmentType.BoringPicture,
                PictureFragment.class));
        mFragmentMenuItems.add(new FragmentMenuItem("妹子图", R.drawable.ic_local_florist_white_24dp, FragmentMenuItem.FragmentType.Sister,
                OXPictureFragment.class));
        mFragmentMenuItems.add(new FragmentMenuItem("段子", R.drawable.ic_chat_white_24dp, FragmentMenuItem.FragmentType.Joke, JokeFragment
                .class));
        mFragmentMenuItems.add(new FragmentMenuItem("小电影", R.drawable.ic_movie_white_24dp, FragmentMenuItem.FragmentType.Video,
                VideoFragment.class));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FragmentMenuItem fragmentMenuItem = mFragmentMenuItems.get(position);
        holder.mTvTitle.setText(fragmentMenuItem.getTitle());
        holder.mImgMenu.setImageResource(fragmentMenuItem.getResourceId());
        holder.mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentMenuItem.getFragment()!=null){
                    try {
                        Fragment fragment = (Fragment) Class.forName(fragmentMenuItem.getFragment().getName()).newInstance();
                        mMainActivity.setFragment(R.id.content_container,fragment);
                        mMainActivity.closeDrawers();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mFragmentMenuItems !=null) {
            return mFragmentMenuItems.size();
        }else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_menu)
        ImageView mImgMenu;
        @Bind(R.id.tv_title)
        TextView mTvTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
