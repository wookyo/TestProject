package com.example.inovationtest.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inovationtest.MainContract;
import com.example.inovationtest.MainItemInfoActivity;
import com.example.inovationtest.MainPresenter;
import com.example.inovationtest.R;
import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.network.data.MainContentListData;

import java.util.ArrayList;

public class MainListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MainContract.View {
    private String TAG = MainListFragment.class.getSimpleName();
    private ArrayList<MainContentListData.MainListItemData> mConentDataList = new ArrayList<MainContentListData.MainListItemData>();
    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mMainListView;
    protected RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

    protected LinearLayout mFavoriteSortTapContainer = null;
    protected TextView mFavoriteSortTapMain = null;
    protected TextView mFavoriteSortTapSub = null;

    protected MainPresenter mMainPresenter;

    onEventChangeListener mOnEventChangeListener = null;

    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {

    }

    @Override
    public void responseCheckFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {

    }

    @Override
    public void responseFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {

    }

    @Override
    public void reponseSortFavoriteData(ArrayList<MainContentListData.MainListItemData> list) {

    }

    public interface onEventChangeListener {
        public void onScrollStateChanged(boolean isRefresh);
        public void onItemClicked(Object obj);
        public void onAddFavoriteItem(boolean isAdd);
    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(mOnEventChangeListener!= null){
                mOnEventChangeListener.onAddFavoriteItem(false);
            }
        }
    };

    public MainListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        initView(view);

        registerBroadCast();
        return view;
    }

    @Override
    public void onDestroyView() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroyView();
    }

    public void setOnEventChangeListener(onEventChangeListener listener) {
        mOnEventChangeListener = listener;
    }

    private void registerBroadCast(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.Intent.ACTION_BROADCAST_FAVORITE_DATA_CHANGE);
        getActivity().registerReceiver(mReceiver, filter);
    }

    // view init
    private void initView(View view) {
        mMainPresenter = new MainPresenter(this);

        mFavoriteSortTapContainer = (LinearLayout) view.findViewById(R.id.tap_favorite_sort_container);
        mFavoriteSortTapMain = (TextView) view.findViewById(R.id.tap_favorite_sort_main);
        mFavoriteSortTapSub = (TextView) view.findViewById(R.id.tap_favorite_sort_sub);

        mMainListView = (RecyclerView) view.findViewById(R.id.main_list_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mMainListView.setLayoutManager(mLayoutManager);
        mMainListView.scrollToPosition(0);
        mMainListView.setAdapter(mAdapter);

        mMainListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //마지막 체크
                if (!mMainListView.canScrollVertically(1)) {
                    if(mOnEventChangeListener != null){
                        mOnEventChangeListener.onScrollStateChanged(false);
                    }
                }
            }
        });

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.main_list_swipe);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        if(mOnEventChangeListener!= null){
            mOnEventChangeListener.onScrollStateChanged(true);
        }
    }

    // Item 상세 보기 화면으로 이동
    protected void moveMainItemActivity(Object obj) {
        Intent intent = new Intent(getActivity(), MainItemInfoActivity.class);
        intent.putExtra(Constants.Intent.KEY_MAIN_LIST_ITEM, (MainContentListData.MainListItemData) obj);
        getActivity().startActivity(intent);
    }
}
