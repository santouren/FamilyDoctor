package com.cpinfo.familydoctor.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 档案=》待支付和已支付列表的adapter（共用）
 */

public class PayListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int currentTab;

    public PayListAdapter(int currentTab, List<String> data) {
        super(R.layout.item_pay_list, data);
        this.currentTab = currentTab;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.pay_item_name, item);
        if (currentTab == 1) {
            helper.setText(R.id.pay_sum, "待支付：¥92.12");
        } else if (currentTab == 2) {
            helper.setText(R.id.pay_sum, "已支付：¥52.12");
            helper.setTextColor(R.id.pay_sum, Color.GRAY);
        }
    }
}
