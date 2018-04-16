package com.cpinfo.familydoctor.fragment.doctor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Juna on 2017/3/23.
 * 类描述：问答模块
 */

public class DoctorAnswersFragment extends BaseFragment {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_doctor_answers);
        initTitleBar();
        setListeners();
        return getContentView();
    }

    private void initTitleBar() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleMiddle.setText("问答");
    }

    private void setListeners() {
    }

}
