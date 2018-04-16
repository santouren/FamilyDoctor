package com.cpinfo.familydoctor.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjz on 9/3/16.
 * 临时假数据（医生版我的病人）
 */
public class TempPatientData {
    private String index;
    private String name;

    public TempPatientData(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public static List<TempPatientData> getChineseContacts() {
        List<TempPatientData> patients = new ArrayList<>();
        patients.add(new TempPatientData("A", "阿宝"));
        patients.add(new TempPatientData("A", "安心发"));
        patients.add(new TempPatientData("A", "阿里巴巴"));
        patients.add(new TempPatientData("A", "安飞飞"));
        patients.add(new TempPatientData("B", "包良建"));
        patients.add(new TempPatientData("B", "班发梅"));
        patients.add(new TempPatientData("B", "鲍薇薇"));
        patients.add(new TempPatientData("B", "白妮发"));
        patients.add(new TempPatientData("C", "蔡文文"));
        patients.add(new TempPatientData("C", "蔡依林"));
        patients.add(new TempPatientData("C", "蔡鑫伟"));
        patients.add(new TempPatientData("C", "Charles"));
        patients.add(new TempPatientData("C", "Christina"));
        patients.add(new TempPatientData("C", "常羲"));
        patients.add(new TempPatientData("C", "嫦娥"));
        patients.add(new TempPatientData("D", "David"));
        patients.add(new TempPatientData("D", "Daniel"));
        patients.add(new TempPatientData("E", "Elizabeth"));
        patients.add(new TempPatientData("E", "Eric"));
        patients.add(new TempPatientData("E", "Eva"));
        patients.add(new TempPatientData("E", "二郎神"));
        patients.add(new TempPatientData("F", "伏羲"));
        patients.add(new TempPatientData("G", "观世音"));
        patients.add(new TempPatientData("J", "精卫"));
        patients.add(new TempPatientData("K", "夸父"));
        patients.add(new TempPatientData("N", "女娲"));
        patients.add(new TempPatientData("N", "哪吒"));
        patients.add(new TempPatientData("P", "盘古"));
        patients.add(new TempPatientData("Q", "青龙"));
        patients.add(new TempPatientData("R", "如来"));
        patients.add(new TempPatientData("S", "孙凯"));
        patients.add(new TempPatientData("S", "孙慧慧"));
        patients.add(new TempPatientData("S", "孙晓红"));
        patients.add(new TempPatientData("T", "邰鑫磊"));
        patients.add(new TempPatientData("T", "邰磊磊"));
        patients.add(new TempPatientData("X", "荀菲菲"));
        patients.add(new TempPatientData("X", "徐发发"));
        patients.add(new TempPatientData("Z", "茱莉亚"));
        patients.add(new TempPatientData("Z", "朱开发"));
        patients.add(new TempPatientData("Z", "钟丽丽"));
        return patients;
    }

}
