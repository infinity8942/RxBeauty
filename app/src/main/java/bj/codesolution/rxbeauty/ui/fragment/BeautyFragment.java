package bj.codesolution.rxbeauty.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import bj.codesolution.rxbeauty.R;
import bj.codesolution.rxbeauty.model.entity.Beauty;
import bj.codesolution.rxbeauty.presenter.BeautyPresenter;
import bj.codesolution.rxbeauty.ui.adapter.BeautyAdapter;
import bj.codesolution.rxbeauty.ui.view.BeautyView;
import bj.codesolution.rxbeauty.ui.widget.AutoLoadRecylerView;
import bj.codesolution.rxbeauty.util.TipUtil;
import butterknife.BindView;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BeautyFragment extends BaseFragment<BeautyPresenter> implements BeautyView
        , SwipeRefreshLayout.OnRefreshListener, AutoLoadRecylerView.loadMoreListener {

    @BindView(R.id.recycler_view)
    AutoLoadRecylerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String TYPE = "type";
    private static final int NUM = 20;
    private String type;
    private BeautyAdapter mBeautyAdapter;
    private List<Beauty> beautyList;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean canLoading = true;

    public static BeautyFragment newInstance(String type) {
        BeautyFragment fragment = new BeautyFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    public BeautyFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_beauty;
    }

    @Override
    protected void initPresenter(Bundle savedInstanceState) {
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }

        presenter = new BeautyPresenter(getContext(), this);
        presenter.init();
    }

    @Override
    public void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        beautyList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLoadMoreListener(this);
        mBeautyAdapter = new BeautyAdapter(getContext(), beautyList);
        mRecyclerView.setAdapter(mBeautyAdapter);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                presenter.getBeauty(page, NUM);
            }
        });
    }

    @Override
    public void loadData(List<Beauty> list) {
        canLoading = true;
        page++;
        if (isRefresh) {
            beautyList.clear();
            beautyList.addAll(list);
            mBeautyAdapter.notifyDataSetChanged();
            isRefresh = false;
        } else {
            beautyList.addAll(list);
            mBeautyAdapter.notifyDataSetChanged();
            mRecyclerView.setLoading(false);
        }
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        presenter.getBeauty(page, NUM);
    }

    @Override
    public void onLoadMore() {
        if (canLoading) {
            presenter.getBeauty(page, NUM);
            canLoading = false;
        }
    }

    @Override
    public void showProgressBar() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgressBar() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showErrorView() {
        canLoading = true;
        mRecyclerView.setLoading(false);
        TipUtil.showTipWithAction(mRecyclerView, getString(R.string.load_failed), getString(R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getBeauty(page, NUM);
            }
        });
    }

    @Override
    public void showNoMoreData() {
        canLoading = false;
        TipUtil.showSnackTip(mRecyclerView, getString(R.string.no_more_data));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.release();
    }

}