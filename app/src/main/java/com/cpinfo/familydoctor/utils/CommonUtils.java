package com.cpinfo.familydoctor.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.activity.LoginActivity;

/**
 * Created by CPInfo on 2017/4/20.
 * 通用工具
 */

public class CommonUtils {

//    /**
//     * 通用的输入dialog（直接修改textView）
//     */
//    public static void commonInputDialog(final Context context, String title, final TextView textView) {
//        new MaterialDialog.Builder(context)
//                .title(title)
//                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
//                .input(title, null, false, new MaterialDialog.InputCallback() {
//                    @Override
//                    public void onInput(MaterialDialog dialog, CharSequence input) {
//                        textView.setText(input);
//                    }
//                }).show();
//    }

    /**
     * 通用的状态提示视图
     */
    public static View getStatusView(Context context, int resId, String description) {
        View statusView = LayoutInflater.from(context).inflate(R.layout.common_status_view, null);
        if (resId != 0) {
            ImageView statusImg = (ImageView) statusView.findViewById(R.id.status_img);
            statusImg.setImageResource(resId);
        }
        if (description != null) {
            TextView statusDescription = (TextView) statusView.findViewById(R.id.status_description);
            statusDescription.setText(description);
        }
        return statusView;
    }

    /**
     * 通用的缓冲提示
     */
    public static MaterialDialog LoadingDialog(Context context) {
        MaterialDialog loadingDialog = new MaterialDialog.Builder(context)
                .content("信息处理中...")
                .progress(true, 0)
                .show();
        loadingDialog.setCanceledOnTouchOutside(false);
        return loadingDialog;
    }

    public static void commonDialog(Context context, String content, String okText) {
        new MaterialDialog.Builder(context)
                .title("提示")
                .content(content)
                .positiveText(okText)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void commonLoginDialog(final Context context) {
        new MaterialDialog.Builder(context)
                .title("提示")
                .content("您尚未登录，请登录后再次尝试！")
                .positiveText("立即登录")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }
                })
                .negativeText("暂不登录").negativeColor(Color.GRAY).show();
    }

    /**
     * 根据uri获取真实路径
     */
    public static String getRealFilePath(Context context, Uri uri) {

        if (null == uri) return null;

        final String scheme = uri.getScheme();
        String path = null;

        if (scheme == null)
            path = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            path = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(ImageColumns.DATA);
                    if (index > -1) {
                        path = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return path;
    }

}
