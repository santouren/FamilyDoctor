package com.cpinfo.familydoctor.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;

import java.util.List;

/**
 * Created by Juna on 2017/9/29.
 * 描述：
 */

public class XGalleryAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> listData;

    public XGalleryAdapter(Context context, List<String> data) {
        listData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_grallerry_member, null);
        TextView name = (TextView) view.findViewById(R.id.member_name);
        name.setText(listData.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object != null && object instanceof View) {
            container.removeView((View) object);
        }
    }
}
