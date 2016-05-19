package bj.codesolution.rxbeauty.ui.adapter;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bj.codesolution.rxbeauty.R;
import bj.codesolution.rxbeauty.model.entity.Beauty;
import bj.codesolution.rxbeauty.util.Image.ImageLoader;
import bj.codesolution.rxbeauty.util.Image.ImageLoaderUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.BeautyHolder> {

    private List<Beauty> mBeauties;
    private Context mContext;

    public BeautyAdapter(Context context, List<Beauty> beautyList) {
        this.mContext = context;
        this.mBeauties = beautyList;
    }

    @Override
    public BeautyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beauty, parent, false);
        return new BeautyHolder(view);
    }

    @Override
    public void onBindViewHolder(BeautyHolder holder, int position) {
        Beauty beauty = mBeauties.get(position);
        if (beauty == null) {
            return;
        }

        ImageLoader imageLoader = new ImageLoader.Builder().url(beauty.getPicUrl()).mImageView(holder.mPoster).build();
        ImageLoaderUtil.getInstance().loadImage(mContext, imageLoader);

        holder.mTitle.setText(beauty.getTitle());
    }

    @Override
    public void onViewRecycled(BeautyHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mBeauties.size();
    }

    class BeautyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.poster)
        SimpleDraweeView mPoster;

        @BindView(R.id.title)
        TextView mTitle;

        public BeautyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card)
        void onPosterClick() {

        }
    }
}