package com.cpinfo.familydoctor.fragment.doctor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseFragment;

/**
 * Created by Juna on 2017/3/23.
 * 类描述：
 */

public class DoctorKPIFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_doctor_kpi);
        return getContentView();
    }

}
