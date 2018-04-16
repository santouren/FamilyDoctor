package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by Juna on 2017/10/9.
 * 描述：
 */

public class DepartmentsBean {

    /**
     * data : [{"KESHIDM":"301","JIUZHENDD":"急诊一楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"急诊科"},{"KESHIDM":"311","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"内分泌科"},{"KESHIDM":"312","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肿瘤内科"},{"KESHIDM":"314","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"消化内科"},{"KESHIDM":"315","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"神经内科"},{"KESHIDM":"316","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"神经外科"},{"KESHIDM":"318","JIUZHENDD":"3号楼1楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"发热门诊"},{"KESHIDM":"319","JIUZHENDD":"3号楼1楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肠道门诊"},{"KESHIDM":"320","JIUZHENDD":"3号楼1楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"感染门诊"},{"KESHIDM":"324","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"儿科"},{"KESHIDM":"326","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"外科门诊"},{"KESHIDM":"327","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"普外科"},{"KESHIDM":"328","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肛肠外科"},{"KESHIDM":"329","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"胸外科"},{"KESHIDM":"331","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"骨科"},{"KESHIDM":"332","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"骨科门诊"},{"KESHIDM":"333","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"妇科"},{"KESHIDM":"334","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"产科"},{"KESHIDM":"337","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"口腔科"},{"KESHIDM":"338","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"耳鼻喉科"},{"KESHIDM":"339","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"眼科"},{"KESHIDM":"340","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"皮肤科"},{"KESHIDM":"342","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"精神科"},{"KESHIDM":"344","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"中医科门诊"},{"KESHIDM":"345","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"推拿科"},{"KESHIDM":"346","JIUZHENDD":"门诊三楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"针灸科"},{"KESHIDM":"347","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"康复医学科"},{"KESHIDM":"358","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"妇产科专科门诊"},{"KESHIDM":"379","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肿瘤外科门诊"},{"KESHIDM":"380","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"微创外科门诊"},{"KESHIDM":"381","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肝胆胰门诊"},{"KESHIDM":"382","JIUZHENDD":"门诊二楼","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"血液病门诊"},{"KESHIDM":"384","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"心内科"},{"KESHIDM":"389","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"糖尿病专科门诊"},{"KESHIDM":"391","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肺结核门诊"},{"KESHIDM":"393","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"呼吸内科"},{"KESHIDM":"396","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"泌尿外科"},{"KESHIDM":"398","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"肾内科"},{"KESHIDM":"400","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"营养科"},{"KESHIDM":"403","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"风湿免疫专科门诊"},{"KESHIDM":"409","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"看守所卫生所"},{"KESHIDM":"411","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"戒烟门诊"},{"KESHIDM":"412","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"母乳喂养门诊"},{"KESHIDM":"413","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"高血压咨询"},{"KESHIDM":"417","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"风湿免疫科"},{"KESHIDM":"418","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"全科医学科"},{"KESHIDM":"442","JIUZHENDD":"门诊四楼名医馆","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"胃肠外科(名医馆)"},{"KESHIDM":"448","JIUZHENDD":"","SHENGYUHYSL":"0","KESHIJS":"","KESHIMC":"内镜中心"}]
     * stateCode : 0
     * errorMsg :
     */

    private int stateCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * KESHIDM : 301
         * JIUZHENDD : 急诊一楼
         * SHENGYUHYSL : 0
         * KESHIJS :
         * KESHIMC : 急诊科
         */

        private String KESHIDM;
        private String JIUZHENDD;
        private String SHENGYUHYSL;
        private String KESHIJS;
        private String KESHIMC;

        public String getKESHIDM() {
            return KESHIDM;
        }

        public void setKESHIDM(String KESHIDM) {
            this.KESHIDM = KESHIDM;
        }

        public String getJIUZHENDD() {
            return JIUZHENDD;
        }

        public void setJIUZHENDD(String JIUZHENDD) {
            this.JIUZHENDD = JIUZHENDD;
        }

        public String getSHENGYUHYSL() {
            return SHENGYUHYSL;
        }

        public void setSHENGYUHYSL(String SHENGYUHYSL) {
            this.SHENGYUHYSL = SHENGYUHYSL;
        }

        public String getKESHIJS() {
            return KESHIJS;
        }

        public void setKESHIJS(String KESHIJS) {
            this.KESHIJS = KESHIJS;
        }

        public String getKESHIMC() {
            return KESHIMC;
        }

        public void setKESHIMC(String KESHIMC) {
            this.KESHIMC = KESHIMC;
        }
    }
}
