package com.cpinfo.familydoctor.fragment.patient;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.patient.AddInfoActivity;
import com.cpinfo.familydoctor.activity.patient.ExamineListActivity;
import com.cpinfo.familydoctor.activity.patient.FamilyRecordActivity;
import com.cpinfo.familydoctor.activity.patient.HospitalizedListActivity;
import com.cpinfo.familydoctor.activity.patient.InspectionListActivity;
import com.cpinfo.familydoctor.activity.patient.OutpatientListActivity;
import com.cpinfo.familydoctor.activity.patient.PatientHistoryListActivity;
import com.cpinfo.familydoctor.activity.patient.PhysicalListActivity;
import com.cpinfo.familydoctor.base.BaseFragment;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.eventbus.MessageChars;
import com.cpinfo.familydoctor.eventbus.MessageEvent;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Juna on 2017/3/4.
 * 类描述：档案模块页面
 */

public class RecordFragment extends BaseFragment {

    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.member_tab)
    TabLayout memberTab;

    private List<Map<String, Object>> listDatas;
    private List<FamilyMemberBean.DataBean> memberData;
    private String selectID;
    private Boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_record);
        initTitleBar();
        initView();
        getData();
        setListeners();
        EventBus.getDefault().register(this);
        return getContentView();
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = SPUtils.getBoolean(mActivity, AppConstants.IS_LOGIN, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initTitleBar() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleMiddle.setText("健康档案");
    }

    protected void getData() {
        String identityId = SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, "");
        ApiRequestManager.getFamilyInfo(identityId, new RequestCallBack<FamilyMemberBean>() {
            @Override
            public void onSuccess(FamilyMemberBean data) {
                memberData = data.getData();
                if (memberData != null) {
                    memberTab.removeAllTabs();
                    for (int i = 0; i < memberData.size(); i++) {
                        memberTab.addTab(memberTab.newTab().setText(memberData.get(i).getName()));
                    }
                }
            }

            @Override
            public void onFail(String errorMessage) {
                Toast.makeText(mActivity, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initView() {
        String[] itemName = new String[]{"门诊", "住院", "检验", "检查", "健康体检", "家庭档案"};
        int[] itemIcon = new int[]{R.drawable.record_outpatient, R.drawable.record_hospital,
                R.drawable.record_examine, R.drawable.record_inspection,
                R.drawable.health_service, R.drawable.family_archives};

        listDatas = new ArrayList<>();
        for (int i = 0; i < itemName.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", itemName[i]);
            item.put("icon", itemIcon[i]);
            listDatas.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(mActivity, listDatas,
                R.layout.record_items, new String[]{"name", "icon"}, new int[]{R.id.item_name, R.id.item_icon});
        gridview.setAdapter(simpleAdapter);
    }

    protected void setListeners() {
        memberTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectID = memberData.get(tab.getPosition()).getIdcard();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isLogin) {
                    CommonUtils.commonLoginDialog(mActivity);
                    return;
                }
                if (TextUtils.isEmpty(SPUtils.getString(mActivity, AppConstants.IDENTITY_ID, ""))) {
                    new MaterialDialog.Builder(mActivity)
                            .title("提示")
                            .content("您的信息尚未完善，是否立即完善信息？")
                            .positiveText("马上完善")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(mActivity, AddInfoActivity.class);
                                    intent.putExtra("phone", SPUtils.getString(mActivity, AppConstants.PATIENT_PHONE, ""));
                                    startActivity(intent);
                                }
                            })
                            .negativeText("暂不完善")
                            .negativeColor(Color.GRAY)
                            .show()
                            .setCanceledOnTouchOutside(false);
                    return;
                }

                switch (position) {
                    case 0://门诊
                        Intent intent1 = new Intent(mActivity, OutpatientListActivity.class);
                        intent1.putExtra("identityID", selectID);
                        startActivity(intent1);
                        break;
                    case 1://住院
                        Intent intent2 = new Intent(mActivity, HospitalizedListActivity.class);
                        intent2.putExtra("identityID", selectID);
                        startActivity(intent2);
                        break;
                    case 2://检验
                        Intent intent3 = new Intent(mActivity, ExamineListActivity.class);
                        intent3.putExtra("identityID", selectID);
                        startActivity(intent3);
                        break;
                    case 3://检查
                        Intent intent4 = new Intent(mActivity, InspectionListActivity.class);
                        intent4.putExtra("identityID", selectID);
                        startActivity(intent4);
                        break;
//                    case 4://电子病历
//                        startActivity(new Intent(mActivity, PatientHistoryListActivity.class));
//                        break;
                    case 4://健康体检
                        Intent intent5 = new Intent(mActivity, PhysicalListActivity.class);
                        intent5.putExtra("identityID", selectID);
                        startActivity(intent5);
                        break;
                    case 5://家庭档案
//                        startActivity(new Intent(mActivity, FamilyListActivity.class));
                        startActivity(new Intent(mActivity, FamilyRecordActivity.class));
                        break;
                }
            }
        });
    }

    public void identityId(){

    }

    /**
     * EventBus事件处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.e("test", event.getEvent());
        switch (event.getEvent()) {
            case MessageChars.LOGOUT:
                Log.e("test", "LOGOUT");
                memberTab.setVisibility(View.INVISIBLE);
                break;
            case MessageChars.LOGIN_SUCCESS:
                Log.e("test", "LOGIN_SUCCESS");
                getData();
                Log.e("test", "getData");
                memberTab.setVisibility(View.VISIBLE);
                Log.e("test", "VISIBLE");
                break;
        }
    }
}
