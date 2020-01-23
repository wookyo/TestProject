package com.example.inovationtest.fragments;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.inovationtest.MainContract;
import com.example.inovationtest.MainItemInfoActivity;
import com.example.inovationtest.MainPresenter;
import com.example.inovationtest.R;
import com.example.inovationtest.constants.Constants;
import com.example.inovationtest.network.data.MainContentListData;

public class MainListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, MainContract.View {
    private String TAG = MainListFragment.class.getSimpleName();

    protected RecyclerView mMainListView;
    protected EditText mMainEditText;
    protected RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    protected MainPresenter mMainPresenter;

    public onEventChangeListener mOnEventChangeListener = null;

    @Override
    public void responseContentData(boolean isSuccess, MainContentListData data) {
    }

    public interface onEventChangeListener {
        public void onScrollStateChanged(boolean isRefresh);
        public void onItemClicked(Object obj);
    }

    public MainListFragment() {
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

    public void setOnEventChangeListener(onEventChangeListener listener) {
        mOnEventChangeListener = listener;
    }


    // view init
    private void initView(View view) {
        mMainPresenter = new MainPresenter(this);

        mMainEditText = (EditText) view.findViewById(R.id.main_edit);
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
