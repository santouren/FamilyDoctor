package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
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
 * 专家坐诊数据详情
 */
public class SpecialistDetailActivity extends BaseActivity {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.pdfView)
    PDFView pdfView;

    private String noticeid;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_specialist_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        noticeid = intent.getStringExtra("noticeid");
        title = intent.getStringExtra("title");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("");
    }

    private void getData() {
        showLoadingPage("数据加载中...", R.drawable.ic_loading);
        ApiRequestManager.getSpecialistDetail(noticeid, new RequestCallBack<ResponseBody>() {
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
        titleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
