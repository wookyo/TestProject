package com.example.inovationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.network.data.MainContentListData.MainListItemData;

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
        final String name = data.getDisplaySitename();
        final String thumbnailPath = data.getThumbnailUrl();
        final String imagePath = data.getImageUrl();

        ImageView itemThumbnailView = (ImageView) findViewById(R.id.main_item_thumbnail);
        ImageView itemImgView = (ImageView) findViewById(R.id.main_item_img);
        TextView itemNameView = (TextView) findViewById(R.id.main_item_name);

        itemNameView.setText(name);

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

    }
}
