package com.cpinfo.familydoctor.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.cpinfo.familydoctor.AppConstants;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;
import com.cpinfo.familydoctor.utils.SPUtils;
import com.yanzhenjie.album.Album;

import java.util.List;

import butterknife.BindView;

/**
 * 医生身份认证页面
 */
public class IdentityAuthenticationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.identity_front)
    ImageView identityFront;
    @BindView(R.id.identity_contrary)
    ImageView identityContrary;
    @BindView(R.id.doctor_certificate)
    ImageView doctorCertificate;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.page1)
    ScrollView page1;
    @BindView(R.id.page2)
    LinearLayout page2;
    @BindView(R.id.data_state)
    TextView dataState;
    @BindView(R.id.state_declare)
    TextView stateDeclare;
    @BindView(R.id.ok)
    Button ok;

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int ALBUM_REQUEST_CODE = 2;
    // 拍照照片保存路径
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath() + "/FamilyDoctor/camera/";
    private String cameraPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_identity_authentication;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("身份认证");
    }

    private void initView() {
        if (SPUtils.getBoolean(mContext, AppConstants.IS_IDENTIFICATION, false)) {
            page1.setVisibility(View.GONE);
            page2.setVisibility(View.VISIBLE);
        } else {
            page1.setVisibility(View.VISIBLE);
            page2.setVisibility(View.GONE);
            new MaterialDialog.Builder(mContext).title("提示")
                    .content("您尚未进行身份认证，提交身份认证并审核通过后才可进入应用！")
                    .positiveText("立即认证").show();
        }
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        identityFront.setOnClickListener(this);
        identityContrary.setOnClickListener(this);
        doctorCertificate.setOnClickListener(this);
        submit.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.identity_front:
                getPic(1);
//                new GetPicDialog(mContext).show();
                break;
            case R.id.identity_contrary:
                getPic(2);
                break;
            case R.id.doctor_certificate:
                getPic(3);
                break;
            case R.id.submit://提交认证信息资料
                SPUtils.put(mContext, AppConstants.IS_IDENTIFICATION, true);
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                break;
            case R.id.ok://修改个人资料（或者确定进入主页）
                if (SPUtils.getBoolean(mContext, AppConstants.IS_IDENTIFICATION, false)) {
                    startActivity(new Intent(mContext, DoctorInfoActivity.class));
                } else {
                    startActivity(new Intent(mContext, DoctorMainActivity.class));
                }
                finish();
                break;
        }
    }

    private void getPic(int reqCode) {
        Album.album(this)
                .requestCode(reqCode) // 请求码，返回时onActivityResult()的第一个参数。
                .toolBarColor(mContext.getResources().getColor(R.color.light_blueA400)) // Toolbar 颜色，默认蓝色。
                .statusBarColor(mContext.getResources().getColor(R.color.light_blueA400)) // StatusBar 颜色，默认蓝色。
//                        .navigationBarColor() // NavigationBar 颜色，默认黑色，建议使用默认。
                .title("图库") // 配置title。
                .selectCount(1) // 最多选择几张图片。
                .columnCount(2) // 相册展示列数，默认是2列。
                .camera(true) // 是否有拍照功能。
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Successfully.
                List<String> pathList = Album.parseResult(data); // Parse path.
                Glide.with(mContext).load(pathList.get(0)).error(R.drawable.default_portrait).into(identityFront);
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了操作。
//                Snackbar.make(changePortrait, "暂时不改了", Snackbar.LENGTH_SHORT).show();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {// Successfully.
                List<String> pathList = Album.parseResult(data); // Parse path.
                Glide.with(mContext).load(pathList.get(0)).error(R.drawable.default_portrait).into(identityContrary);
            } else if (resultCode == RESULT_CANCELED) {// 用户取消了操作。
            }
        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {// Successfully.
                List<String> pathList = Album.parseResult(data); // Parse path.
                Glide.with(mContext).load(pathList.get(0)).error(R.drawable.default_portrait).into(doctorCertificate);
            } else if (resultCode == RESULT_CANCELED) {// 用户取消了操作。
            }
        }
    }
//    /**
//     * 选择获取照片的方式（底部弹窗）
//     */
//    private class GetPicDialog extends Dialog {
//
//        GetPicDialog(@NonNull Context context) {
//            //重写dialog默认的主题
//            this(context, R.style.full_screen_dialog);
//        }
//
//        GetPicDialog(@NonNull Context context, @StyleRes int themeResId) {
//            super(context, themeResId);
//            View view = LayoutInflater.from(context).inflate(R.layout.photo_dialog, null);
//            view.findViewById(R.id.camera).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dismiss();
//                    openCamera();
//                }
//            });
//            view.findViewById(R.id.album).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dismiss();
//                    openAlbum();
//                }
//            });
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            setContentView(view);
//        }
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            getWindow().setGravity(Gravity.BOTTOM);
//
//            WindowManager.LayoutParams params = getWindow().getAttributes();
//            params.width = WindowManager.LayoutParams.MATCH_PARENT;
//            params.height = (int) (ScreenUtils.getScreenHeight(mContext) * 0.2);
//            getWindow().setAttributes(params);
//        }
//    }
//
//    /**
//     * 打开相册选择图片
//     */
//    private void openAlbum() {
//        Intent intent = new Intent(Intent.ACTION_PICK, null);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, ALBUM_REQUEST_CODE);
//    }
//
//    /**
//     * 打开相机拍摄
//     */
//    private void openCamera() {
//        // 指定相机拍摄照片保存地址
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".png";
//            Intent intent = new Intent();
//            // 指定开启系统相机的Action
//            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//            String out_file_path = SAVED_IMAGE_DIR_PATH;
//            File dir = new File(out_file_path);
//            if (!dir.exists()) {//不存在则创建目录
//                dir.mkdirs();
//            }
//            // 把文件地址转换成Uri格式
//            Uri uri = Uri.fromFile(new File(cameraPath));
//            // 设置系统相机拍摄照片完成后图片文件的存放地址
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            startActivityForResult(intent, CAMERA_REQUEST_CODE);
//        } else {
//            Snackbar.make(identityFront, "请确认已经插入SD卡！", Snackbar.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            try {
//                if (requestCode == CAMERA_REQUEST_CODE) {
//                    Logger.e("path=" + CommonUtils.getRealFilePath(mContext, data.getData()));
//                }
//                if (requestCode == ALBUM_REQUEST_CODE) {
//                    String absolutePath = CommonUtils.getRealFilePath(mContext, data.getData());
//                    Glide.with(mContext).load(absolutePath).into(identityFront);
//                    Logger.e("path=" + absolutePath);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
