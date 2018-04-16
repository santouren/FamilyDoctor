package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.common.EncodeUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.orhanobut.logger.Logger;

import java.io.InputStream;

import butterknife.BindView;
import okhttp3.ResponseBody;

/**
 * 居民版=》档案=》检查报告详情单
 */
public class InspectionDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.pdfView)
    PDFView pdfView;

    private String jclsh;
    private String jcbh;
    private String region;
    private String cloudType;
    private String cloudAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        getData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_inspection_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        jclsh = intent.getStringExtra("applyid");// 病人id
        jcbh = intent.getStringExtra("jcbh");
        region = intent.getStringExtra("region");
        cloudType = intent.getStringExtra("cloudType");
        if ("云心电".equals(cloudType)) {
            cloudAddress = intent.getStringExtra("cloudAddress");
        }
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("检查报告");
        titleRight.setText(cloudType);
        if (TextUtils.isEmpty(cloudType)) {
            titleRight.setEnabled(false);
        }
        titleRight.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        titleRight.setVisibility(View.VISIBLE);
    }

    private void getData() {
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        ApiRequestManager.getInspectionDetail(jclsh, new RequestCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody response) {
                InputStream inputStream = response.byteStream();
                showPDF(inputStream);
            }

            @Override
            public void onFail(String errorMessage) {
                showErrorView(errorMessage, R.drawable.ic_error);
            }
        });
    }

    private void showPDF(InputStream inputStream) {
        pdfView.fromStream(inputStream)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                        Logger.d("onLayerDrawn");
                    }
                }) // allows to draw something on a provided canvas, above the current page
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Logger.d("loadComplete");
                    }
                }) // called after document is loaded and starts to be rendered
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        Logger.d("onPageChanged");
                    }
                })
                .onPageScroll(new OnPageScrollListener() {
                    @Override
                    public void onPageScrolled(int page, float positionOffset) {
                        Logger.d("onPageScrolled");
                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError：" + t.getMessage());
                    }
                })
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                        Logger.d("onInitiallyRendered");
                    }
                }) // called after document is rendered for the first time
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .load();
        showContentView();
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right://打开云影像
                if ("云影像".equals(cloudType)) {
                    if (TextUtils.isEmpty(jcbh) || TextUtils.isEmpty(region)) {
                        CommonUtils.commonDialog(mContext, "您没有云影像！", "知道了");
                    } else {
                        String urlBase = "http://image.qdhws.gov.cn:9000/WebViewer.aspx?";
                        String urlParameter = "cnt=1&jcbh=" + jcbh + "&querymode=localhidden&region=" + region;
                        String encodeUrl = new String(EncodeUtils.base64Encode(urlParameter));
                        Intent intent = new Intent(mContext, NewsWebActivity.class);
                        intent.putExtra("title", "云影像");
                        intent.putExtra("link", urlBase + encodeUrl);
                        startActivity(intent);
                    }
                } else if ("云心电".equals(cloudType)) {
                    if (TextUtils.isEmpty(cloudAddress)) {
                        CommonUtils.commonDialog(mContext, "您没有云心电！", "知道了");
                    } else {
                        Intent intent = new Intent(mContext, NewsWebActivity.class);
                        intent.putExtra("title", "云心电");
                        intent.putExtra("link", cloudAddress);
                        startActivity(intent);
                    }
                }
                break;
        }
    }
}
