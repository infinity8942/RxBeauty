package bj.codesolution.rxbeauty.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;

import bj.codesolution.rxbeauty.BuildConfig;
import bj.codesolution.rxbeauty.R;
import bj.codesolution.rxbeauty.api.BeautyClient;
import bj.codesolution.rxbeauty.databinding.FragmentBeautyBinding;
import bj.codesolution.rxbeauty.databinding.ItemBeautyBinding;
import bj.codesolution.rxbeauty.model.BeautyData;
import bj.codesolution.rxbeauty.model.entity.Beauty;
import bj.codesolution.rxbeauty.model.entity.Size;
import bj.codesolution.rxbeauty.ui.base.BaseAdapter;
import bj.codesolution.rxbeauty.ui.base.BaseHolder;
import bj.codesolution.rxbeauty.ui.base.LazyLoadFragment;
import bj.codesolution.rxbeauty.ui.widget.AutoLoadRecylerView;
import bj.codesolution.rxbeauty.util.CommonUtil;
import bj.codesolution.rxbeauty.util.GlideApp;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BeautyFragment extends LazyLoadFragment<FragmentBeautyBinding> implements SwipeRefreshLayout.OnRefreshListener, AutoLoadRecylerView.loadMoreListener {

    private static final String TYPE = "type";
    private static final int NUM = 20;
    private String type;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean canLoading = true;

    private SparseArray<Size> array = new SparseArray<>();
    private int width;

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
    protected int layoutId() {
        return R.layout.fragment_beauty;
    }

    @Override
    public void initView() {
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }

        getBinding().swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        getBinding().swipeRefreshLayout.setOnRefreshListener(this);

        getBinding().recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        getBinding().recyclerView.setLoadMoreListener(this);

        this.width = CommonUtil.getScreenWidth();
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
        getBinding().swipeRefreshLayout.setRefreshing(true);
        getBeautyData(page);
    }

    private void getBeautyData(int currentPage) {
        BeautyClient.getBeautyRetrofitInstance().getBeauty(BuildConfig.BAIDU_APISTORE_PUBLIC_KEY, currentPage, NUM)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BeautyData>() {
                    @Override
                    public void call(BeautyData beautyData) {
                        if (beautyData.getNewslist().size() == 0) {
//                            iView.showNoMoreData();
                        } else {
                            canLoading = true;
                            page++;
                            if (isRefresh) {
                                array.clear();
                                isRefresh = false;
                            }
                            adapter.setList(beautyData.getNewslist());
                        }
                        getBinding().swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
//                        iView.hideProgressBar();
//                        iView.showErrorView();
                    }
                });
    }

    BaseAdapter.SimpleAdapter<Beauty, ItemBeautyBinding> adapter = new BaseAdapter.SimpleAdapter<Beauty, ItemBeautyBinding>(0, R.layout.item_beauty) {
        @Override
        public void onBindViewHolder(BaseHolder<Beauty, ItemBeautyBinding> holder, int position) {
            super.onBindViewHolder(holder, position);

            Beauty beauty = getData(position);
            if (beauty == null) {
                return;
            }

            ItemBeautyBinding binding = holder.getBinding();

            //设置图片的相对于屏幕的宽高比
            ViewGroup.LayoutParams params = binding.poster.getLayoutParams();
            if (array.get(holder.getAdapterPosition()) == null) {
                Size size = new Size(width / 2, (int) (400 + Math.random() * 400));
                array.put(holder.getAdapterPosition(), size);
                params.width = size.getWidth();
                params.height = size.getHeight();
            } else {
                params.width = array.get(holder.getAdapterPosition()).getWidth();
                params.height = array.get(holder.getAdapterPosition()).getHeight();
            }
            binding.poster.setLayoutParams(params);

            GlideApp.with(BeautyFragment.this).load(beauty.getPicUrl())
                    .format(DecodeFormat.PREFER_RGB_565)
                    .override(params.width, params.height)
                    .priority(Priority.NORMAL)
                    .centerCrop()
                    .thumbnail(0.1f)
                    .transition(withCrossFade()).into(binding.poster);

            binding.title.setText(beauty.getTitle());
        }
    };

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        getBeautyData(page);
    }

    @Override
    public void onLoadMore() {
        if (canLoading) {
            getBeautyData(page);
            canLoading = false;
        }
    }

//    @Override
//    public void showProgressBar() {
//        if (!mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(true);
//        }
//    }
//
//    @Override
//    public void hideProgressBar() {
//        if (mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
//    }
//
//    @Override
//    public void showErrorView() {
//        canLoading = true;
//        mRecyclerView.setLoading(false);
//        TipUtil.showTipWithAction(mRecyclerView, getString(R.string.load_failed), getString(R.string.retry), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.getBeauty(page, NUM);
//            }
//        });
//    }
//
//    @Override
//    public void showNoMoreData() {
//        canLoading = false;
//        TipUtil.showSnackTip(mRecyclerView, getString(R.string.no_more_data));
//    }
}