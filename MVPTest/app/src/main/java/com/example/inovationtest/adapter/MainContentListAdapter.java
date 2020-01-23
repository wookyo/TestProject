package com.example.inovationtest.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inovationtest.R;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.fragments.MainListFragment.onEventChangeListener;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import com.example.inovationtest.preferences.SharedPreferencesManager;

import java.util.ArrayList;

public class MainContentListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG =  MainContentListAdapter.class.getSimpleName();
    private Context mContext;
    private onEventChangeListener mCallback;
    private ArrayList<MainListItemData> mItems;


    public MainContentListAdapter(Context context,
                           ArrayList<MainListItemData> items,
                           onEventChangeListener callback) {
        mContext = context;
        mItems = items;
        mCallback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
       Log.d(TAG, "[onBindViewHolder]");
        final MainListItemData data = mItems.get(position);
        String itemName = data.getDisplaySitename();
        final String itemImg = data.getThumbnailUrl();

        if (TextUtils.isEmpty(itemName)) {
            itemName = data.getCollection();
        }
        final ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        Glide.clear(itemViewHolder.mItemImage);
        Glide.with(mContext)
                .load(itemImg)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .into(itemViewHolder.mItemImage);

        itemViewHolder.mItemTitle.setText(itemName);

        itemViewHolder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null){
                    mCallback.onItemClicked(data);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mContainer;
        private TextView mItemTitle;
        private ImageView mItemImage;

        private Object data;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mContainer = (LinearLayout) itemView.findViewById(R.id.main_list_item_layout);
            mItemImage = (ImageView) itemView.findViewById(R.id.main_list_item_img);
            mItemTitle = (TextView) itemView.findViewById(R.id.main_list_item_title);


        }
    }
}
