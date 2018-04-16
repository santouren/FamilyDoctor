package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/3.
 * 首页=》医院指南列表的adapter
 */

public class HospitalGuideListAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private Context mContext;

    public HospitalGuideListAdapter(Context context, List<Integer> data) {
        super(R.layout.item_hospital_guide_list, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), item);
        helper.setImageBitmap(R.id.hospital_cover, bitmap);
    }
}
