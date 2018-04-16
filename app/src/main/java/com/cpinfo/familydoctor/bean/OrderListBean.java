package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by Juna on 2017/10/26.
 * 描述：
 */

public class OrderListBean {

    /**
     * data : [{"quxiao_id":"app","sex":"男","createtime1":"2017-09-20","yizhoupbid":"802","hosnum":null,"guahaolb":"1","createtime":{"time":1505884121000,"minutes":8,"seconds":41,"hours":13,"month":8,"timezoneOffset":-480,"year":117,"day":3,"date":20},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"08:00-16:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":"app","doorplate":null,"quxiao_time":{"time":1506495101000,"minutes":51,"seconds":41,"hours":14,"month":8,"timezoneOffset":-480,"year":117,"day":3,"date":27},"zz_reason":null,"dangtianpbid":"160572","status":"取消","guahaobc":"1","quhaomm":"467360","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"普通","createname":"app","uuid":"297e969a5e9d8a05015e9db02a0c0166","yy_riqi":"2017-09-21","dept_address":null,"guahaoxh":"1","yb_card":"E11345284"},{"quxiao_id":"app","sex":"男","createtime1":"2017-09-21","yizhoupbid":"112","hosnum":null,"guahaolb":"2","createtime":{"time":1505972907000,"minutes":48,"seconds":27,"hours":13,"month":8,"timezoneOffset":-480,"year":117,"day":4,"date":21},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"301","feiyongmx":null,"jiuzhensj":"06:00-12:40","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":"app","doorplate":null,"quxiao_time":{"time":1508917633000,"minutes":47,"seconds":13,"hours":15,"month":9,"timezoneOffset":-480,"year":117,"day":3,"date":25},"zz_reason":null,"dangtianpbid":"160582","status":"取消","guahaobc":"1","quhaomm":"467919","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"急诊科","yy_doctorname":"普通","createname":"app","uuid":"297e969a5e9d8a05015ea2faee4f2fd2","yy_riqi":"2017-09-22","dept_address":null,"guahaoxh":"1","yb_card":"E11345284"},{"quxiao_id":"app","sex":"男","createtime1":"2017-10-24","yizhoupbid":"315","hosnum":null,"guahaolb":"1","createtime":{"time":1508813996000,"minutes":59,"seconds":56,"hours":10,"month":9,"timezoneOffset":-480,"year":117,"day":2,"date":24},"yishengdm":"*","mobilephone":"13957731814","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"07:30-11:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":"app","doorplate":null,"quxiao_time":{"time":1508917635000,"minutes":47,"seconds":15,"hours":15,"month":9,"timezoneOffset":-480,"year":117,"day":3,"date":25},"zz_reason":null,"dangtianpbid":"162858","status":"取消","guahaobc":"1","quhaomm":"484899","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"普通医生","createname":"app","uuid":"297e969a5f48c398015f4c5281f6080a","yy_riqi":"2017-10-25","dept_address":null,"guahaoxh":"2","yb_card":"E11345284"},{"quxiao_id":null,"sex":"男","createtime1":"2017-10-24","yizhoupbid":"315","hosnum":null,"guahaolb":"1","createtime":{"time":1508813950000,"minutes":59,"seconds":10,"hours":10,"month":9,"timezoneOffset":-480,"year":117,"day":2,"date":24},"yishengdm":"*","mobilephone":"13957731814","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"07:30-11:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":null,"doorplate":null,"zz_reason":null,"dangtianpbid":"162858","status":"正常","guahaobc":"1","quhaomm":"484898","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"普通医生","createname":"app","uuid":"297e969a5f48c398015f4c51cc600806","yy_riqi":"2017-10-25","dept_address":null,"guahaoxh":"1","yb_card":"E11345284"},{"quxiao_id":null,"sex":"男","createtime1":"2017-09-19","yizhoupbid":"315","hosnum":null,"guahaolb":"4","createtime":{"time":1505802659000,"minutes":30,"seconds":59,"hours":14,"month":8,"timezoneOffset":-480,"year":117,"day":2,"date":19},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"07:30-11:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":null,"doorplate":null,"zz_reason":null,"dangtianpbid":"160476","status":"正常","guahaobc":"1","quhaomm":"466928","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"专家","createname":"app","uuid":"297e969a5e81b005015e98d5274013e2","yy_riqi":"2017-09-20","dept_address":null,"guahaoxh":"1","yb_card":"E11345284"},{"quxiao_id":null,"sex":"男","createtime1":"2017-09-19","yizhoupbid":"315","hosnum":null,"guahaolb":"4","createtime":{"time":1505803370000,"minutes":42,"seconds":50,"hours":14,"month":8,"timezoneOffset":-480,"year":117,"day":2,"date":19},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"07:30-11:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":null,"doorplate":null,"zz_reason":null,"dangtianpbid":"160476","status":"正常","guahaobc":"1","quhaomm":"466940","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"专家","createname":"app","uuid":"297e969a5e81b005015e98dfffd1146d","yy_riqi":"2017-09-20","dept_address":null,"guahaoxh":"2","yb_card":"E11345284"},{"quxiao_id":"app","sex":"男","createtime1":"2017-09-19","yizhoupbid":"315","hosnum":null,"guahaolb":"5","createtime":{"time":1505807122000,"minutes":45,"seconds":22,"hours":15,"month":8,"timezoneOffset":-480,"year":117,"day":2,"date":19},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"07:30-11:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":"app","doorplate":null,"quxiao_time":{"time":1508737902000,"minutes":51,"seconds":42,"hours":13,"month":9,"timezoneOffset":-480,"year":117,"day":1,"date":23},"zz_reason":null,"dangtianpbid":"160476","status":"取消","guahaobc":"1","quhaomm":"466987","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"专家","createname":"app","uuid":"297e969a5e81b005015e991941bb17cf","yy_riqi":"2017-09-20","dept_address":null,"guahaoxh":"3","yb_card":"E11345284"},{"quxiao_id":null,"sex":"男","createtime1":"2017-10-23","yizhoupbid":"176","hosnum":null,"guahaolb":"1","createtime":{"time":1508759107000,"minutes":45,"seconds":7,"hours":19,"month":9,"timezoneOffset":-480,"year":117,"day":1,"date":23},"yishengdm":"*","mobilephone":"15757821642","name":"方光宇","keshidm":"318","feiyongmx":null,"jiuzhensj":"00:01-12:00","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":null,"doorplate":null,"zz_reason":null,"dangtianpbid":"162846","status":"正常","guahaobc":"1","quhaomm":"484409","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"发热门诊","yy_doctorname":"普通医生","createname":"app","uuid":"297e969a5f48c398015f490cf63f0073","yy_riqi":"2017-10-24","dept_address":null,"guahaoxh":"4","yb_card":"E11345284"},{"quxiao_id":null,"sex":"男","createtime1":"2017-09-19","yizhoupbid":"315","hosnum":null,"guahaolb":"5","createtime":{"time":1505809235000,"minutes":20,"seconds":35,"hours":16,"month":8,"timezoneOffset":-480,"year":117,"day":2,"date":19},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"07:30-11:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":null,"doorplate":null,"zz_reason":null,"dangtianpbid":"160476","status":"正常","guahaobc":"1","quhaomm":"466998","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"专家","createname":"app","uuid":"297e969a5e81b005015e99397e9d1a98","yy_riqi":"2017-09-20","dept_address":null,"guahaoxh":"4","yb_card":"E11345284"},{"quxiao_id":null,"sex":"男","createtime1":"2017-09-20","yizhoupbid":"802","hosnum":null,"guahaolb":"1","createtime":{"time":1505884143000,"minutes":9,"seconds":3,"hours":13,"month":8,"timezoneOffset":-480,"year":117,"day":3,"date":20},"yishengdm":"*","mobilephone":"13322161736","name":"方光宇","keshidm":"311","feiyongmx":null,"jiuzhensj":"08:00-16:30","yy_hosname":"淳安县第一人民医院","nodecode":null,"quxiao_name":null,"doorplate":null,"zz_reason":null,"dangtianpbid":"160572","status":"正常","guahaobc":"1","quhaomm":"467361","idcard":"330127194403153918","createphone":null,"yuyuely":"0","createid":"app","yy_dept":"内分泌科","yy_doctorname":"普通","createname":"app","uuid":"297e969a5e9d8a05015e9db07fda0175","yy_riqi":"2017-09-21","dept_address":null,"guahaoxh":"2","yb_card":"E11345284"}]
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
         * quxiao_id : app
         * sex : 男
         * createtime1 : 2017-09-20
         * yizhoupbid : 802
         * hosnum : null
         * guahaolb : 1
         * createtime : {"time":1505884121000,"minutes":8,"seconds":41,"hours":13,"month":8,"timezoneOffset":-480,"year":117,"day":3,"date":20}
         * yishengdm : *
         * mobilephone : 13322161736
         * name : 方光宇
         * keshidm : 311
         * feiyongmx : null
         * jiuzhensj : 08:00-16:30
         * yy_hosname : 淳安县第一人民医院
         * nodecode : null
         * quxiao_name : app
         * doorplate : null
         * quxiao_time : {"time":1506495101000,"minutes":51,"seconds":41,"hours":14,"month":8,"timezoneOffset":-480,"year":117,"day":3,"date":27}
         * zz_reason : null
         * dangtianpbid : 160572
         * status : 取消
         * guahaobc : 1
         * quhaomm : 467360
         * idcard : 330127194403153918
         * createphone : null
         * yuyuely : 0
         * createid : app
         * yy_dept : 内分泌科
         * yy_doctorname : 普通
         * createname : app
         * uuid : 297e969a5e9d8a05015e9db02a0c0166
         * yy_riqi : 2017-09-21
         * dept_address : null
         * guahaoxh : 1
         * yb_card : E11345284
         */

        private String quxiao_id;
        private String sex;
        private String createtime1;
        private String yizhoupbid;
        private Object hosnum;
        private String guahaolb;
        private CreatetimeBean createtime;
        private String yishengdm;
        private String mobilephone;
        private String name;
        private String keshidm;
        private Object feiyongmx;
        private String jiuzhensj;
        private String yy_hosname;
        private Object nodecode;
        private String quxiao_name;
        private Object doorplate;
        private QuxiaoTimeBean quxiao_time;
        private Object zz_reason;
        private String dangtianpbid;
        private String status;
        private String guahaobc;
        private String quhaomm;
        private String idcard;
        private Object createphone;
        private String yuyuely;
        private String createid;
        private String yy_dept;
        private String yy_doctorname;
        private String createname;
        private String uuid;
        private String yy_riqi;
        private Object dept_address;
        private String guahaoxh;
        private String yb_card;

        public String getQuxiao_id() {
            return quxiao_id;
        }

        public void setQuxiao_id(String quxiao_id) {
            this.quxiao_id = quxiao_id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCreatetime1() {
            return createtime1;
        }

        public void setCreatetime1(String createtime1) {
            this.createtime1 = createtime1;
        }

        public String getYizhoupbid() {
            return yizhoupbid;
        }

        public void setYizhoupbid(String yizhoupbid) {
            this.yizhoupbid = yizhoupbid;
        }

        public Object getHosnum() {
            return hosnum;
        }

        public void setHosnum(Object hosnum) {
            this.hosnum = hosnum;
        }

        public String getGuahaolb() {
            return guahaolb;
        }

        public void setGuahaolb(String guahaolb) {
            this.guahaolb = guahaolb;
        }

        public CreatetimeBean getCreatetime() {
            return createtime;
        }

        public void setCreatetime(CreatetimeBean createtime) {
            this.createtime = createtime;
        }

        public String getYishengdm() {
            return yishengdm;
        }

        public void setYishengdm(String yishengdm) {
            this.yishengdm = yishengdm;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKeshidm() {
            return keshidm;
        }

        public void setKeshidm(String keshidm) {
            this.keshidm = keshidm;
        }

        public Object getFeiyongmx() {
            return feiyongmx;
        }

        public void setFeiyongmx(Object feiyongmx) {
            this.feiyongmx = feiyongmx;
        }

        public String getJiuzhensj() {
            return jiuzhensj;
        }

        public void setJiuzhensj(String jiuzhensj) {
            this.jiuzhensj = jiuzhensj;
        }

        public String getYy_hosname() {
            return yy_hosname;
        }

        public void setYy_hosname(String yy_hosname) {
            this.yy_hosname = yy_hosname;
        }

        public Object getNodecode() {
            return nodecode;
        }

        public void setNodecode(Object nodecode) {
            this.nodecode = nodecode;
        }

        public String getQuxiao_name() {
            return quxiao_name;
        }

        public void setQuxiao_name(String quxiao_name) {
            this.quxiao_name = quxiao_name;
        }

        public Object getDoorplate() {
            return doorplate;
        }

        public void setDoorplate(Object doorplate) {
            this.doorplate = doorplate;
        }

        public QuxiaoTimeBean getQuxiao_time() {
            return quxiao_time;
        }

        public void setQuxiao_time(QuxiaoTimeBean quxiao_time) {
            this.quxiao_time = quxiao_time;
        }

        public Object getZz_reason() {
            return zz_reason;
        }

        public void setZz_reason(Object zz_reason) {
            this.zz_reason = zz_reason;
        }

        public String getDangtianpbid() {
            return dangtianpbid;
        }

        public void setDangtianpbid(String dangtianpbid) {
            this.dangtianpbid = dangtianpbid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGuahaobc() {
            return guahaobc;
        }

        public void setGuahaobc(String guahaobc) {
            this.guahaobc = guahaobc;
        }

        public String getQuhaomm() {
            return quhaomm;
        }

        public void setQuhaomm(String quhaomm) {
            this.quhaomm = quhaomm;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public Object getCreatephone() {
            return createphone;
        }

        public void setCreatephone(Object createphone) {
            this.createphone = createphone;
        }

        public String getYuyuely() {
            return yuyuely;
        }

        public void setYuyuely(String yuyuely) {
            this.yuyuely = yuyuely;
        }

        public String getCreateid() {
            return createid;
        }

        public void setCreateid(String createid) {
            this.createid = createid;
        }

        public String getYy_dept() {
            return yy_dept;
        }

        public void setYy_dept(String yy_dept) {
            this.yy_dept = yy_dept;
        }

        public String getYy_doctorname() {
            return yy_doctorname;
        }

        public void setYy_doctorname(String yy_doctorname) {
            this.yy_doctorname = yy_doctorname;
        }

        public String getCreatename() {
            return createname;
        }

        public void setCreatename(String createname) {
            this.createname = createname;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getYy_riqi() {
            return yy_riqi;
        }

        public void setYy_riqi(String yy_riqi) {
            this.yy_riqi = yy_riqi;
        }

        public Object getDept_address() {
            return dept_address;
        }

        public void setDept_address(Object dept_address) {
            this.dept_address = dept_address;
        }

        public String getGuahaoxh() {
            return guahaoxh;
        }

        public void setGuahaoxh(String guahaoxh) {
            this.guahaoxh = guahaoxh;
        }

        public String getYb_card() {
            return yb_card;
        }

        public void setYb_card(String yb_card) {
            this.yb_card = yb_card;
        }

        public static class CreatetimeBean {
            /**
             * time : 1505884121000
             * minutes : 8
             * seconds : 41
             * hours : 13
             * month : 8
             * timezoneOffset : -480
             * year : 117
             * day : 3
             * date : 20
             */

            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
            private int day;
            private int date;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }
        }

        public static class QuxiaoTimeBean {
            /**
             * time : 1506495101000
             * minutes : 51
             * seconds : 41
             * hours : 14
             * month : 8
             * timezoneOffset : -480
             * year : 117
             * day : 3
             * date : 27
             */

            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
            private int day;
            private int date;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }
        }
    }
}
