package com.example.imagetestapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.imagetestapplication.MainItemInfoActivity;
import com.example.imagetestapplication.R;
import com.example.imagetestapplication.constants.Constants;
import com.example.imagetestapplication.network.data.MainContentListData;


/**
 *  각 fragment 의 공통적이 이벤트 및 UI 초기화 상위 class
 *
 *  기본적으로 MVP 패턴을 기본으로 사용하며 *
 *  Presenter 을 통해  data request / response
 *  작업이 이루어 진다.
 *
 * */
public class BaseListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private String TAG = BaseListFragment.class.getSimpleName();

    protected SwipeRefreshLayout mRefreshLayout;
    protected RecyclerView mMainListView;
    protected RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    protected LinearLayout mFavoriteSortTapContainer = null;
    protected TextView mFavoriteSortTapMain = null;
    protected TextView mFavoriteSortTapSub = null;
    protected onEventChangeListener mOnEventChangeListener = null;

    /**
     * App 안의 이벤트 관련 Callback
     * */
    public interface onEventChangeListener {
        // 스크롤 변경에 따른 Data refresh
        public void onScrollStateChanged(boolean isRefresh);

        // LIST 에서 ITEM 클릭시
        public void onItemClicked(Object obj);

        // 즐겨 찾기 버튼 클릭시
        public void onAddFavoriteItem(boolean isAdd);
    }

    public BaseListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_list, container, false);

        initView(view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * callback 리스너 객체 등록
     * */
    public void setOnEventChangeListener(onEventChangeListener listener) {
        mOnEventChangeListener = listener;
    }

    /**
     * 공통 UI 초기화
     * */
    private void initView(View view) {
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
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //스크콜 마지막 체크
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

    /**
     * SwipeRefreshLayout Refresh 이벤트
     * */
    @Override
    public void onRefresh() {
        if(mOnEventChangeListener!= null){
            mOnEventChangeListener.onScrollStateChanged(true);
        }
    }

    /**
     * Item 상세 보기 화면으로 이동
     * */
    protected void moveMainItemActivity(Object obj) {
        Intent intent = new Intent(getActivity(), MainItemInfoActivity.class);
        intent.putExtra(Constants.Intent.KEY_MAIN_LIST_ITEM, (MainContentListData.MainListItemData) obj);
        getActivity().startActivity(intent);
    }

}