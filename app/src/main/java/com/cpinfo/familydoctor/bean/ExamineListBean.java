package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/5/31.
 * 检验列表实体类
 */

public class ExamineListBean {

    /**
     * data : [{"w":1,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 09:09:35","brbslb":"2","bblbmc":"血液","jybgdh":"20170908XXJ014","sqysxm":"王冬英","jybgdjg":"临检室","jgdm":"330110023","jybgdmc":"血型（含不规则抗体）"},{"w":2,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:54:37","brbslb":"2","bblbmc":"血浆","jybgdh":"20170908XNF058","sqysxm":"王冬英","jybgdjg":"临检室","jgdm":"330110023","jybgdmc":"凝血功能常规(住院)"},{"w":3,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:50:49","brbslb":"2","bblbmc":"全血","jybgdh":"20170908JZH014","sqysxm":"王冬英","jybgdjg":"急诊科化验","jgdm":"330110023","jybgdmc":"B型纳尿肽定量测定（急诊科）+肌钙蛋白I(急诊室)"},{"w":4,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:26:29","brbslb":"2","bblbmc":"粪便","jybgdh":"20170908FCG009","sqysxm":"王冬英","jybgdjg":"临检室","jgdm":"330110023","jybgdmc":"粪便隐血+粪便常规"},{"w":5,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:24:06","brbslb":"2","bblbmc":"痰","jybgdh":"20170908VIT010","sqysxm":"王冬英","jybgdjg":"细菌室","jgdm":"330110023","jybgdmc":"痰培养"},{"w":6,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:18:47","brbslb":"2","bblbmc":"血液","jybgdh":"20170908ESR015","sqysxm":"王冬英","jybgdjg":"临检室","jgdm":"330110023","jybgdmc":"血沉"},{"w":7,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:13:20","brbslb":"2","bblbmc":"尿液","jybgdh":"20170908NCG025","sqysxm":"王冬英","jybgdjg":"临检室","jgdm":"330110023","jybgdmc":"尿常规（住院）"},{"w":8,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:04:24","brbslb":"2","bblbmc":"血液","jybgdh":"20170908SYS016","sqysxm":"王冬英","jybgdjg":"临检室","jgdm":"330110023","jybgdmc":"血常规+CRP(病)"},{"w":9,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:03:48","brbslb":"2","bblbmc":"全血","jybgdh":"20170908SAA002","sqysxm":"王冬英","jybgdjg":"免疫室","jgdm":"330110023","jybgdmc":"血清淀粉样蛋白A测定"},{"w":10,"hosname":"淳安县第一人民医院","jyrq":"2017-09-08 08:03:41","brbslb":"2","bblbmc":"血浆","jybgdh":"20170908BAU173","sqysxm":"王冬英","jybgdjg":"生化室","jgdm":"330110023","jybgdmc":"D-二聚体（化验室）"}]
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
         * w : 1
         * hosname : 淳安县第一人民医院
         * jyrq : 2017-09-08 09:09:35
         * brbslb : 2
         * bblbmc : 血液
         * jybgdh : 20170908XXJ014
         * sqysxm : 王冬英
         * jybgdjg : 临检室
         * jgdm : 330110023
         * jybgdmc : 血型（含不规则抗体）
         */

        private int w;
        private String hosname;
        private String jyrq;
        private String brbslb;
        private String bblbmc;
        private String jybgdh;
        private String sqysxm;
        private String jybgdjg;
        private String jgdm;
        private String jybgdmc;

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public String getHosname() {
            return hosname;
        }

        public void setHosname(String hosname) {
            this.hosname = hosname;
        }

        public String getJyrq() {
            return jyrq;
        }

        public void setJyrq(String jyrq) {
            this.jyrq = jyrq;
        }

        public String getBrbslb() {
            return brbslb;
        }

        public void setBrbslb(String brbslb) {
            this.brbslb = brbslb;
        }

        public String getBblbmc() {
            return bblbmc;
        }

        public void setBblbmc(String bblbmc) {
            this.bblbmc = bblbmc;
        }

        public String getJybgdh() {
            return jybgdh;
        }

        public void setJybgdh(String jybgdh) {
            this.jybgdh = jybgdh;
        }

        public String getSqysxm() {
            return sqysxm;
        }

        public void setSqysxm(String sqysxm) {
            this.sqysxm = sqysxm;
        }

        public String getJybgdjg() {
            return jybgdjg;
        }

        public void setJybgdjg(String jybgdjg) {
            this.jybgdjg = jybgdjg;
        }

        public String getJgdm() {
            return jgdm;
        }

        public void setJgdm(String jgdm) {
            this.jgdm = jgdm;
        }

        public String getJybgdmc() {
            return jybgdmc;
        }

        public void setJybgdmc(String jybgdmc) {
            this.jybgdmc = jybgdmc;
        }
    }
}
