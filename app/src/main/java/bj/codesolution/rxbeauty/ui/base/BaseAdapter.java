package bj.codesolution.rxbeauty.ui.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<Data, Binding extends ViewDataBinding> extends RecyclerView.Adapter<BaseHolder<Data, Binding>> {

    private List<Data> list;

    private OnItemClickListener<Data, Binding> itemClickListener;

    BaseAdapter() {
    }

    @Override
    public BaseHolder<Data, Binding> onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseHolder<Data, Binding> holder = new BaseHolder<>(LayoutInflater.from(parent.getContext()).inflate(holderLayout(viewType), parent, false));
        if (itemClickListener != null) {
            holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(holder);
                }
            });
        }
        initHolder(holder, viewType);
        return holder;
    }

    protected void initHolder(BaseHolder<Data, Binding> holder, int viewType) {
    }

    public void setItemClickListener(OnItemClickListener<Data, Binding> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(Data data) {
        if (this.list == null) this.list = new ArrayList<>();
        this.list.add(data);
        notifyItemInserted(this.list.size() - 1);
    }

    public void addItem(Data data, int position) {
        if (this.list == null) this.list = new ArrayList<>();
        this.list.add(position, data);
        notifyItemInserted(position);
    }

    public void addItems(List<Data> datas) {
        if (datas == null || datas.isEmpty()) return;
        if (this.list == null || this.list.isEmpty()) {
            setList(datas);
            return;
        }
        this.list.addAll(datas);
        notifyItemRangeChanged(this.list.size() - datas.size(), datas.size());
    }

    public void addItems(List<Data> datas, int position) {
        if (datas == null || datas.isEmpty()) return;
        if (this.list == null || this.list.isEmpty()) {
            setList(datas);
            return;
        }
        if (this.list.size() - 1 < position) {
            addItems(datas);
            return;
        }
        this.list.addAll(position, datas);
        notifyItemRangeChanged(position, datas.size());
    }

    public void remove(int position) {
        if (getItemCount() == 0 || position < 0 || position > getItemCount() - 1) return;
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(Data data) {
        if (getItemCount() == 0) return;
        remove(this.list.indexOf(data));
    }

    public void removeAll() {
        if (getItemCount() == 0) return;
        this.list.clear();
        notifyDataSetChanged();
    }

    public abstract int holderLayout(int viewType);

    public static class SimpleAdapter<Data, Binding extends ViewDataBinding> extends BaseAdapter<Data, Binding> {
        int holderLayout;
        int variableId;

        public SimpleAdapter(int variableId, int holderLayout) {
            this.variableId = variableId;
            this.holderLayout = holderLayout;
        }

        @Override
        public int holderLayout(int viewType) {
            return holderLayout;
        }

        @Override
        public void onBindViewHolder(BaseHolder<Data, Binding> holder, int position) {
            if (getList() == null || getList().isEmpty()) return;
            holder.fill(variableId, getList().get(position));
        }

        @Override
        public void onBindViewHolder(BaseHolder<Data, Binding> holder, int position, List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    public interface OnItemClickListener<Data, Binding extends ViewDataBinding> {
        void onItemClick(BaseHolder<Data, Binding> holder);
    }

    public Data getData(int position) {
        if (list != null && list.size() > position) {
            return list.get(position);
        } else return null;
    }
}