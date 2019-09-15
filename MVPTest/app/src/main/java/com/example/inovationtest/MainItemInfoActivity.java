package com.example.inovationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.network.data.MainContentListData;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;
import com.example.inovationtest.preferences.SharedPreferencesManager;

public class MainItemInfoActivity extends AppCompatActivity {
    private String TAG = MainItemInfoActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "[onCreate] ");
        setContentView(R.layout.activity_main_item_info);

        MainListItemData item = (MainListItemData) getIntent().getSerializableExtra(Constants.Intent.KEY_MAIN_LIST_ITEM);

        initView(item);
    }

    private void initView(final MainListItemData data) {
        final String name = data.getName();
        final String thumbnailPath = data.getThumbnail();
        final String rate = data.getRate();
        final int id = data.getId();

        MainContentListData.MainListItemDescription itemDescription = data.getDescription();
        final String imagePath = itemDescription.getImagePath();
        final String subject = itemDescription.getSubject();
        final int price = itemDescription.getPrice();
        final boolean itemIsAdd = data.getIsFavoriteAdd();

        ImageView itemThumbnailView = (ImageView) findViewById(R.id.main_item_thumbnail);
        ImageView itemImgView = (ImageView) findViewById(R.id.main_item_img);

        TextView itemSubjectView = (TextView) findViewById(R.id.main_item_subject);
        TextView itemRateView = (TextView) findViewById(R.id.main_item_rate);
        TextView itemPriceView = (TextView) findViewById(R.id.main_item_price);
        TextView itemIdView = (TextView) findViewById(R.id.main_item_id);
        TextView itemNameView = (TextView) findViewById(R.id.main_item_name);
        final ToggleButton itemAddFavorite = (ToggleButton) findViewById(R.id.btn_favorite_add);

        itemNameView.setText(name);
        itemSubjectView.setText(subject);
        itemRateView.setText(getString(R.string.main_item_rate,rate));
        itemPriceView.setText(getString(R.string.main_item_price,String.valueOf(price)) );
        itemIdView.setText(getString(R.string.main_item_id,String.valueOf(id)));

        Glide.clear(itemImgView);
        Glide.with(this)
                .load(imagePath)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .into(itemImgView);

        Glide.clear(itemThumbnailView);
        Glide.with(this)
                .load(thumbnailPath)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(true)
                .into(itemThumbnailView);

        itemAddFavorite.setSelected(itemIsAdd);

        itemAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAddFavorite.setSelected( !itemIsAdd);
                data.setFavoriteAdd(!itemIsAdd);

                // 현재 상태에 따라 data 변경
                if(itemIsAdd){
                    SharedPreferencesManager.deleteObjectPreferences(MainItemInfoActivity.this,
                            Constants.Preference.PREFERENCE_FAVORITE_ADD,
                            data);
                }else{
                    SharedPreferencesManager.setObjectPreferences(MainItemInfoActivity.this,
                            Constants.Preference.PREFERENCE_FAVORITE_ADD,
                            data);
                }
                Intent intent = new Intent();
                intent.setAction(Constants.Intent.ACTION_BROADCAST_FAVORITE_DATA_CHANGE);
                sendBroadcast(intent);
            }
        });

    }
}
