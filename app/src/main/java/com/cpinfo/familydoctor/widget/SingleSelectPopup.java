package com.cpinfo.familydoctor.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by Juna on 2017/6/29.
 * 描述：单选弹窗
 */

public class SingleSelectPopup extends PopupWindow {

    private RecyclerView conentView;
    private TextView targetView;

    public SingleSelectPopup(Context context, final TextView targetView, final List<String> data) {
        conentView = new RecyclerView(context);
        this.targetView = targetView;
        int h = ViewGroup.LayoutParams.WRAP_CONTENT;
        int w = ViewGroup.LayoutParams.MATCH_PARENT;
        this.setContentView(conentView);
        this.setWidth(w);
        this.setHeight(h);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);

        conentView.setLayoutManager(new LinearLayoutManager(context));
        WindowListAdapter windowListAdapter = new WindowListAdapter(data);
        windowListAdapter.openLoadAnimation();
        windowListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                targetView.setText(data.get(position));
                dismiss();
            }
        });
        conentView.setAdapter(windowListAdapter);
    }

    private class WindowListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public WindowListAdapter(List<String> data) {
            super(R.layout.item_window_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_name, item);
        }
    }

    /**
     * 显示popupWindow
     */
    public void showPopupWindow() {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(targetView);
        } else {
            this.dismiss();
        }
    }
}
