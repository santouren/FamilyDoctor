package com.cpinfo.familydoctor.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by CPInfo on 2017/4/19.
 * 解决scrollview嵌套listview滑动冲突
 */

public class ScrollListView extends ListView {
    public ScrollListView(Context context) {
        super(context);
    }

    public ScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //这句话的作用 告诉父view，我的单击事件我自行处理，不要阻碍我。
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
