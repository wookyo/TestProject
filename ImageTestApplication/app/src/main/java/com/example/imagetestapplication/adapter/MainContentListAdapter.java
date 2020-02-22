package com.example.imagetestapplication.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.imagetestapplication.R;
import com.example.imagetestapplication.constants.Constants;
import com.example.imagetestapplication.fragments.BaseListFragment;
import com.example.imagetestapplication.network.data.MainContentListData.MainListItemData;
import com.example.imagetestapplication.preferences.SharedPreferencesManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainContentListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG =  MainContentListAdapter.class.getSimpleName();
    private Context mContext;
    private BaseListFragment.onEventChangeListener mCallback;
    private ArrayList<MainListItemData> mItems;


    public MainContentListAdapter(Context context,
                                  ArrayList<MainListItemData> items,
                                  BaseListFragment.onEventChangeListener callback) {
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
        final MainListItemData data = mItems.get(position);
        final int itemId = data.getId();
        final String itemName = data.getName();
        final String itemImg = data.getThumbnail();
        final String itemRate = data.getRate();
        final boolean itemIsAdd = data.getIsFavoriteAdd();

        final ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;

        Glide.clear(itemViewHolder.mItemImage);
        Glide.with(mContext)
                .load(itemImg)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .into(itemViewHolder.mItemImage);

        itemViewHolder.mItemTitle.setText(itemName);
        itemViewHolder.mItemRate.setText(mContext.getString(R.string.main_item_rate,itemRate));

        itemViewHolder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null){
                    mCallback.onItemClicked(data);
                }
            }
        });

        itemViewHolder.mItemAddItem.setSelected(itemIsAdd);
        itemViewHolder.mItemAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null){
                    // 현재 상태에 따라 data 변경
                    if(itemIsAdd){
                        SharedPreferencesManager.deleteObjectPreferences(mContext,
                                Constants.Preference.PREFERENCE_FAVORITE_ADD,
                                data);
                    }else{
                        SharedPreferencesManager.setObjectPreferences(mContext,
                                Constants.Preference.PREFERENCE_FAVORITE_ADD,
                                data);
                    }
                    if(mCallback != null){
                        mCallback.onAddFavoriteItem(false);
                    }
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
        private TextView mItemRate;
        private TextView mItemRateTime;
        private ImageView mItemImage;
        private ImageButton mItemAddItem;
        private Object data;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mContainer = (LinearLayout) itemView.findViewById(R.id.main_list_item_layout);
            mItemImage = (ImageView) itemView.findViewById(R.id.main_list_item_img);
            mItemTitle = (TextView) itemView.findViewById(R.id.main_list_item_title);
            mItemRate = (TextView) itemView.findViewById(R.id.main_list_item_score);
            mItemRateTime = (TextView) itemView.findViewById(R.id.main_list_item_score_time);
            mItemAddItem = (ImageButton) itemView.findViewById(R.id.main_list_item_btn_add_favorite);
        }
    }
}