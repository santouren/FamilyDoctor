package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

/**
 * 人体导诊
 */
public class BodyGuideDiagnosisActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_middle)
    TextView titleMiddle;
    @BindView(R.id.body_type)
    ImageView bodyType;
    @BindView(R.id.rotate_body)
    ImageView rotateBody;
    @BindView(R.id.type_man)
    TextView typeMan;
    @BindView(R.id.type_woman)
    TextView typeWoman;
    @BindView(R.id.body_head)
    View bodyHead;
    @BindView(R.id.body_bosom)
    View bodyBosom;
    @BindView(R.id.body_abdomen)
    View bodyAbdomen;
    @BindView(R.id.body_leg1)
    View bodyLeg1;
    @BindView(R.id.body_leg2)
    View bodyLeg2;
    @BindView(R.id.body_arm1)
    View bodyArm1;
    @BindView(R.id.body_arm2)
    View bodyArm2;

    private boolean isFront;//是否正面标志
    private boolean isMan = true;//是否男人的标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_body_guide_diagnosis;
    }

    @Override
    protected void initTitleBar() {
        titleMiddle.setText("人体导诊");
    }

    private void setListeners() {
        titleLeft.setOnClickListener(this);
        rotateBody.setOnClickListener(this);
        typeMan.setOnClickListener(this);
        typeWoman.setOnClickListener(this);
        bodyHead.setOnClickListener(this);
        bodyBosom.setOnClickListener(this);
        bodyAbdomen.setOnClickListener(this);
        bodyLeg1.setOnClickListener(this);
        bodyLeg2.setOnClickListener(this);
        bodyArm1.setOnClickListener(this);
        bodyArm2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.rotate_body://旋转身体
                if (isFront) {
                    if (isMan) {
                        bodyType.setImageResource(R.drawable.img_man_back);
                    } else {
                        bodyType.setImageResource(R.drawable.img_woman_back);
                    }
                    isFront = false;
                } else {
                    if (isMan) {
                        bodyType.setImageResource(R.drawable.img_man_front);
                    } else {
                        bodyType.setImageResource(R.drawable.img_woman_front);
                    }
                    isFront = true;
                }
                break;
            case R.id.type_man://选择男
                typeMan.setBackgroundResource(R.color.blue_100);
                typeWoman.setBackgroundResource(R.color.grey_54);
                bodyType.setImageResource(R.drawable.img_man_front);
                isMan = true;
                isFront = true;
                break;
            case R.id.type_woman://选择女
                typeMan.setBackgroundResource(R.color.grey_54);
                typeWoman.setBackgroundResource(R.color.blue_100);
                bodyType.setImageResource(R.drawable.img_woman_front);
                isMan = false;
                isFront = true;
                break;
            case R.id.body_head:
                Intent intent1 = new Intent(mContext, IllnessListActivity.class);
                intent1.putExtra("part", "头部");
                startActivity(intent1);
                break;
            case R.id.body_bosom:
                Intent intent2 = new Intent(mContext, IllnessListActivity.class);
                intent2.putExtra("part", "胸部");
                startActivity(intent2);
                break;
            case R.id.body_abdomen:
                Intent intent3 = new Intent(mContext, IllnessListActivity.class);
                intent3.putExtra("part", "腹部");
                startActivity(intent3);
                break;
            case R.id.body_leg1:
            case R.id.body_leg2:
                Intent intent4 = new Intent(mContext, IllnessListActivity.class);
                intent4.putExtra("part", "腿部");
                startActivity(intent4);
                break;
            case R.id.body_arm1:
            case R.id.body_arm2:
                Intent intent5 = new Intent(mContext, IllnessListActivity.class);
                intent5.putExtra("part", "手臂");
                startActivity(intent5);
                break;
        }
    }
}
