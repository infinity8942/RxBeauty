package bj.codesolution.rxbeauty.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.badoo.mobile.util.WeakHandler;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public abstract class BaseActivity<Bind extends ViewDataBinding> extends SwipeBackActivity {

    private Bind binding;
    private WeakHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new WeakHandler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                BaseActivity.this.handleMessage(msg);
                return false;
            }
        });
        if (layoutId() != 0) {
            binding = DataBindingUtil.setContentView(this, layoutId());
        }
    }

    protected abstract int layoutId();

    public Bind getBinding() {
        return binding;
    }

    protected WeakHandler getHandler() {
        return mHandler;
    }

    protected void handleMessage(Message msg) {
    }
}