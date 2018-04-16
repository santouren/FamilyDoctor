package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.adapter.OrderHospitalListAdapter;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.bean.OrderHospitalsBean;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 预约挂号=》选择医院，医院列表
 */
public class OrderHospitalListActivity extends BaseActivity implements View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.hospital_list)
    RecyclerView hospitalList;
    @BindView(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;
    private OrderHospitalListAdapter orderHospitalListAdapter;
    private int startPage = 1;
    private int endPage = 10;
    private int maxCounter = 0;
    private int mCurrentCounter = 0;
    private boolean isTop = true;
    private boolean isRefresh = false;
    private List<OrderHospitalsBean.DataBean.InfoBean> hospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        getData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_hospital_list;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("选择医院");
    }

    private void getData() {
        ApiRequestManager.getOrderHospitals(startPage, endPage, new RequestCallBack<OrderHospitalsBean>() {
            @Override
            public void onSuccess(OrderHospitalsBean data) {
                maxCounter = data.getData().getCount();
                hospitals = data.getData().getInfo();
                if (isTop) {
                    isTop = false;
                    initView();
                } else {
                    orderHospitalListAdapter.addData(hospitals);
                    mCurrentCounter = orderHospitalListAdapter.getData().size();
                    orderHospitalListAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void initView() {
        if (isRefresh) {
            isRefresh = false;
            orderHospitalListAdapter.setNewData(hospitals);
            mCurrentCounter = orderHospitalListAdapter.getData().size();
            ptrFrameLayout.refreshComplete();
//            orderHospitalListAdapter.setEnableLoadMore(true);
        } else {
            hospitalList.setLayoutManager(new LinearLayoutManager(this));
            orderHospitalListAdapter = new OrderHospitalListAdapter(mContext, hospitals);
            orderHospitalListAdapter.setOnLoadMoreListener(this, hospitalList);
            orderHospitalListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            hospitalList.setAdapter(orderHospitalListAdapter);
            mCurrentCounter = orderHospitalListAdapter.getData().size();

            hospitalList.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    OrderHospitalsBean.DataBean.InfoBean hospital = (OrderHospitalsBean.DataBean.InfoBean) adapter.getData().get(position);
                    Intent intent = new Intent();
                    intent.putExtra("hospital", hospital.getHosname());
                    intent.putExtra("hosNum", hospital.getHosnum());
                    intent.putExtra("nodeCode", hospital.getNodecode());
                    intent.putExtra("hospitalImg", hospital.getPic());
                    intent.putExtra("hospitalGrade", hospital.getGrade());
                    intent.putExtra("hospitalAddress", hospital.getAddress());
                    setResult(1, intent);
                    finish();
                }
            });
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(mContext);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isTop = true;
                isRefresh = true;
                startPage = 1;
                endPage = 10;
                mCurrentCounter = 0;
                getData();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
//        Logger.e(mCurrentCounter + "mCurrentCounter");
//        Logger.e(maxCounter + "maxCounter");
        if (mCurrentCounter < maxCounter) {
            startPage += 10;
            endPage += 10;
            getData();
        } else {
            orderHospitalListAdapter.loadMoreEnd(false);
        }
    }
}
