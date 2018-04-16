package com.cpinfo.familydoctor.activity.patient;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.DensityUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 已支付=》结算信息页面
 */
public class PayInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.drug_list)
    RecyclerView drugList;
    @BindView(R.id.unfold_drug_list)
    TextView unfoldDrugList;
    private DrugListAdapter drugListAdapter;
    private boolean isOpen;//药品列表是否展开的判断标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay_info;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("结算告知单");
    }

    private void initView() {
        ArrayList<String> drugDatas = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            drugDatas.add("复方咳嗽药剂" + i);
        }

        drugList.setLayoutManager(new LinearLayoutManager(this));
        drugListAdapter = new DrugListAdapter(drugDatas);
        drugListAdapter.openLoadAnimation();
        drugList.setAdapter(drugListAdapter);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        unfoldDrugList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.unfold_drug_list:
                if (isOpen) {
                    int h = DensityUtils.dp2px(mContext, 66);
                    ViewGroup.LayoutParams params = drugList.getLayoutParams();
                    params.height = h;
                    drugList.setLayoutParams(params);
                    isOpen = false;
                    unfoldDrugList.setText("点击展开");
                    unfoldDrugList.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pull_down, 0);
                } else {
                    ViewGroup.LayoutParams params = drugList.getLayoutParams();
                    params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
                    drugList.setLayoutParams(params);
                    isOpen = true;
                    unfoldDrugList.setText("点击收起");
                    unfoldDrugList.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pull_up, 0);
                }
                break;
        }
    }

    private class DrugListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public DrugListAdapter(ArrayList<String> data) {
            super(R.layout.item_drug_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.drug_name, item);
        }
    }
}
