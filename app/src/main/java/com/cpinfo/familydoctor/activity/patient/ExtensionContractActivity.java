package com.cpinfo.familydoctor.activity.patient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class ExtensionContractActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.extension)
    TextView extension;
    @BindView(R.id.extension_for_family)
    TextView extensionForFamily;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.extension_year)
    TextView extensionYear;
    @BindView(R.id.item_extension_year)
    LinearLayout itemExtensionYear;
    @BindView(R.id.extension_patient)
    TextView extensionPatient;
    @BindView(R.id.item_extension_patient)
    LinearLayout itemExtensionPatient;
    @BindView(R.id.family_member_list)
    RecyclerView familyMemberList;
    private boolean isExtension;//是否已选择续约的标志
    private boolean isExtensionForFamily;//是否已选择为家人续约的标志
    private FamilyMemberListAdapter familyMemberListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_extension_contract;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("续约");
        titleRight.setText("我的续约");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
        extension.setOnClickListener(this);
        extensionForFamily.setOnClickListener(this);
        extensionYear.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right://我的续约
                CommonUtils.commonDialog(mContext, "功能建设中，敬请期待！", "期待一下");
                break;
            case R.id.extension://续约
                selectExtension();
                break;
            case R.id.extension_for_family://为家人续约
                selectFamilyExtension();
                break;
            case R.id.extension_year://续约年份
                new MaterialDialog.Builder(this)
                        .title("请选择性别")
                        .items("2018", "2019", "2020", "2021", "2022", "2023")
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                extensionYear.setText(text);
                                dialog.dismiss();
                                return true;
                            }
                        })
                        .positiveText("确定")
                        .show();
                break;
            case R.id.submit://提交
                if (isExtension || isExtensionForFamily) {
                    new MaterialDialog.Builder(mContext)
                            .content("提交成功！")
                            .positiveText("确定")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                }
                            })
                            .show();
                } else {
                    CommonUtils.commonDialog(mContext, "您尚未选择续约服务！", "知道了");
                }
                break;
        }
    }

    /**
     * 为家人选择续约操作
     */
    private void selectFamilyExtension() {
        if (isExtensionForFamily) {//不为家人续约
            isExtensionForFamily = false;
            extensionForFamily.setText("否");
            familyMemberList.setVisibility(View.GONE);
        } else {//为家人续约
            isExtensionForFamily = true;
            extensionForFamily.setText("是");
            familyMemberList.setVisibility(View.VISIBLE);
            showFamilyMemberList();
        }
    }

    /**
     * 显示家庭成员列表
     */
    private void showFamilyMemberList() {
        ArrayList<String> items = new ArrayList<>();
        items.add("蔡少芬");
        items.add("闵慧国");
        items.add("蔡明明");

        familyMemberList.setLayoutManager(new LinearLayoutManager(this));
        familyMemberListAdapter = new FamilyMemberListAdapter(items);
        familyMemberListAdapter.openLoadAnimation();
        familyMemberListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImageView label = (ImageView) view.findViewById(R.id.selected);
                int visibility = label.getVisibility();
                if (visibility == View.VISIBLE) {
                    label.setVisibility(View.GONE);
                } else if (visibility == View.GONE) {
                    label.setVisibility(View.VISIBLE);
                }
            }
        });
        familyMemberList.setAdapter(familyMemberListAdapter);
    }

    /**
     * 选择续约操作
     */
    private void selectExtension() {
        if (isExtension) {//不续约
            isExtension = false;
            extension.setText("否");
            itemExtensionYear.setVisibility(View.GONE);
            itemExtensionPatient.setVisibility(View.GONE);
        } else {
            isExtension = true;
            extension.setText("是");
            itemExtensionYear.setVisibility(View.VISIBLE);
            itemExtensionPatient.setVisibility(View.VISIBLE);
        }
    }

    private class FamilyMemberListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public FamilyMemberListAdapter(ArrayList<String> items) {
            super(R.layout.item_family_member_list, items);

        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.member_name, item);
        }
    }
}
