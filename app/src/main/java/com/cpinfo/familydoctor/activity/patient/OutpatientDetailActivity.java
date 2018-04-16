package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.http.ApiRequestManager;
import com.cpinfo.familydoctor.http.RequestCallBack;
import com.cpinfo.familydoctor.http.UrlConfig;
import com.cpinfo.familydoctor.utils.CommonUtils;
import com.cpinfo.familydoctor.utils.common.SnackbarUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.InputStream;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.ResponseBody;

import static com.cpinfo.familydoctor.R.id.title_left;

/**
 * 档案=》门诊详情（PDF文件形式显示）
 */
public class OutpatientDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.pdfView)
    PDFView pdfView;
    private String recipeNo;
    private String jgdm;
    private String identityId;
    private ResponseBody mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        getData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_outpatient_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        recipeNo = intent.getStringExtra("recipeNo");
        jgdm = intent.getStringExtra("jgdm");
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("门诊详情");
        titleRight.setText("保存");
        titleRight.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_download), null);
        titleRight.setVisibility(View.VISIBLE);
//        identityId = SPUtils.getString(mContext, AppConstants.IDENTITY_ID, "");
        identityId = "330127194403153918";//测试写死
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        titleRight.setOnClickListener(this);
    }

    private void getData() {
        showLoadingPage("玩命加载中...", R.drawable.ic_loading);
        ApiRequestManager.getOutpatientDetail(recipeNo, jgdm, new RequestCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody response) {
                mResponse = response;
                showPDF(response.byteStream());
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
            case R.id.title_right:
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FamilyDoctor/Records";
                String fileName = "门诊" + recipeNo + ".pdf";
                String url = UrlConfig.BASE_URL + "fds_bak/httpServer?requesttype=84&recipeNo=" + recipeNo;
                final MaterialDialog loadingDialog = CommonUtils.LoadingDialog(mContext);
                OkHttpUtils.get().url(url).build()
                        .execute(new FileCallBack(filePath, fileName) {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Logger.e("onError :" + e.getMessage());
                                loadingDialog.dismiss();
                                SnackbarUtils.showShort(titleRight, "保存失败！");
                            }

                            @Override
                            public void onResponse(File file, int id) {
                                Logger.e("onResponse :" + file.getAbsolutePath());
                                loadingDialog.dismiss();
                                SnackbarUtils.showShort(titleRight, "保存成功！");
                            }
                        });

                break;
        }
    }
}
