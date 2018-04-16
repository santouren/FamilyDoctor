package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
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
 * 健康体检详情页面
 */
public class PhysicalDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.pdfView)
    PDFView pdfView;

    private String pexamId;
    private String pexamJgdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_physical_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        pexamId = intent.getStringExtra("pexamId");
        pexamJgdm = intent.getStringExtra("pexamJgdm");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("体检报告");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
    }

    private void getData() {
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        ApiRequestManager.getPhysicalDetail(pexamId, pexamJgdm, new RequestCallBack<ResponseBody>() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
        }
    }
}
