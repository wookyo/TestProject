package com.example.imagetestapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.imagetestapplication.constants.Constants;
import com.example.imagetestapplication.network.data.MainContentListData;

public class MainItemInfoActivity extends AppCompatActivity {
    private String TAG = MainItemInfoActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_item_info);

        MainContentListData.MainListItemData item = (MainContentListData.MainListItemData) getIntent().getSerializableExtra(Constants.Intent.KEY_MAIN_LIST_ITEM);
        initView(item);
    }

    private void initView(final MainContentListData.MainListItemData data) {
        final String name = data.getName();
        final String thumbnailPath = data.getThumbnail();
        final String imagePath = data.getDescription().getImagePath();

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
