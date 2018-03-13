package bj.codesolution.rxbeauty.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseHolder<Data, Binding extends ViewDataBinding> extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Binding binding;
    private OnClickListener<Data, Binding> clickListener;

    public BaseHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public Binding getBinding() {
        return binding;
    }

    public void fill(int variableId, Data data) {
        binding.setVariable(variableId, data);
    }

    public void setClickListener(OnClickListener<Data, Binding> clickListener) {
        this.clickListener = clickListener;
    }

    public void setClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null)
            clickListener.onClick(this, v);
    }

    public interface OnClickListener<Data, Binding extends ViewDataBinding> {
        void onClick(BaseHolder<Data, Binding> holder, View v);
    }
}