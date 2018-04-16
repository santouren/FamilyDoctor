package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/12/2.
 */

public class CountyInReferralBean {

    /**
     * data : [{"quxiao_id":null,"sex":"女","createtime1":"20171021","yizhoupbid":"3","hosnum":"0000","guahaolb":"1","createtime":{"time":1508576278000,"minutes":57,"seconds":58,"hours":16,"month":9,"timezoneOffset":-480,"year":117,"day":6,"date":21},"yishengdm":"*","mobilephone":"18379191720","name":"应倩","keshidm":"103","feiyongmx":null,"jiuzhensj":"08:00-11:30","yy_hosname":"淳安县中医院","nodecode":"0000","quxiao_name":null,"doorplate":"123","zz_reason":"医疗技术因素","dangtianpbid":"118799","yishengzc":"急诊","status":"正常","guahaobc":"1","quhaomm":"498715","idcard":"330721198702111921","createphone":"18868831950","yuyuely":"0","zhenliaof":null,"createid":"000655","yy_dept":"骨科","yy_doctorname":"普通门诊","createname":"信息中心","uuid":"8a8a8a0a5f3e0497015f3e27395a000f","yy_riqi":"2017-10-23","dept_address":"佑安楼三楼","guahaoxh":"1","yb_card":"E14851190"},{"quxiao_id":"000655","sex":"女","createtime1":"20171021","yizhoupbid":"861","hosnum":"0000","guahaolb":"10","createtime":{"time":1508574206000,"minutes":23,"seconds":26,"hours":16,"month":9,"timezoneOffset":-480,"year":117,"day":6,"date":21},"yishengdm":"022","mobilephone":"18379191720","name":"应倩","keshidm":"103","feiyongmx":null,"jiuzhensj":"13:30-17:00","yy_hosname":"淳安县中医院","nodecode":"0000","quxiao_name":"信息中心","doorplate":"123","quxiao_time":{"time":1508574428000,"minutes":27,"seconds":8,"hours":16,"month":9,"timezoneOffset":-480,"year":117,"day":6,"date":21},"zz_reason":"医疗技术因素","dangtianpbid":"118779","yishengzc":"急诊","status":"取消","guahaobc":"2","quhaomm":"498711","idcard":"330721198702111921","createphone":"18868831950","yuyuely":"0","zhenliaof":null,"createid":"000655","yy_dept":"骨科","yy_doctorname":"王玉忠","createname":"信息中心","uuid":"8a8a8a0a5f3e0497015f3e079b9e000d","yy_riqi":"2017-10-23","dept_address":"佑安楼三楼","guahaoxh":"1","yb_card":"E14851190"},{"quxiao_id":"000655","sex":"女","createtime1":"20171020","yizhoupbid":"3","hosnum":null,"guahaolb":"1","createtime":{"time":1508471244000,"minutes":47,"seconds":24,"hours":11,"month":9,"timezoneOffset":-480,"year":117,"day":5,"date":20},"yishengdm":"*","mobilephone":"18379191720","name":"应倩","keshidm":"103","feiyongmx":null,"jiuzhensj":"08:00-11:30","yy_hosname":"淳安县中医院","nodecode":null,"quxiao_name":"信息中心","doorplate":"123","quxiao_time":{"time":1508471357000,"minutes":49,"seconds":17,"hours":11,"month":9,"timezoneOffset":-480,"year":117,"day":5,"date":20},"zz_reason":"医疗技术因素","dangtianpbid":"118799","yishengzc":"普通","status":"取消","guahaobc":"1","quhaomm":"498300","idcard":"330721198702111921","createphone":null,"yuyuely":"0","zhenliaof":null,"createid":"000655","yy_dept":"骨科","yy_doctorname":"普通门诊","createname":"信息中心","uuid":"8a8a8a215f37884b015f37e4881b0031","yy_riqi":"2017-10-23","dept_address":"佑安楼三楼","guahaoxh":"1","yb_card":"E14851190"}]
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
         * quxiao_id : null
         * sex : 女
         * createtime1 : 20171021
         * yizhoupbid : 3
         * hosnum : 0000
         * guahaolb : 1
         * createtime : {"time":1508576278000,"minutes":57,"seconds":58,"hours":16,"month":9,"timezoneOffset":-480,"year":117,"day":6,"date":21}
         * yishengdm : *
         * mobilephone : 18379191720
         * name : 应倩
         * keshidm : 103
         * feiyongmx : null
         * jiuzhensj : 08:00-11:30
         * yy_hosname : 淳安县中医院
         * nodecode : 0000
         * quxiao_name : null
         * doorplate : 123
         * zz_reason : 医疗技术因素
         * dangtianpbid : 118799
         * yishengzc : 急诊
         * status : 正常
         * guahaobc : 1
         * quhaomm : 498715
         * idcard : 330721198702111921
         * createphone : 18868831950
         * yuyuely : 0
         * zhenliaof : null
         * createid : 000655
         * yy_dept : 骨科
         * yy_doctorname : 普通门诊
         * createname : 信息中心
         * uuid : 8a8a8a0a5f3e0497015f3e27395a000f
         * yy_riqi : 2017-10-23
         * dept_address : 佑安楼三楼
         * guahaoxh : 1
         * yb_card : E14851190
         * quxiao_time : {"time":1508574428000,"minutes":27,"seconds":8,"hours":16,"month":9,"timezoneOffset":-480,"year":117,"day":6,"date":21}
         */

        private Object quxiao_id;
        private String sex;
        private String createtime1;
        private String yizhoupbid;
        private String hosnum;
        private String guahaolb;
        private CreatetimeBean createtime;
        private String yishengdm;
        private String mobilephone;
        private String name;
        private String keshidm;
        private Object feiyongmx;
        private String jiuzhensj;
        private String yy_hosname;
        private String nodecode;
        private Object quxiao_name;
        private String doorplate;
        private String zz_reason;
        private String dangtianpbid;
        private String yishengzc;
        private String status;
        private String guahaobc;
        private String quhaomm;
        private String idcard;
        private String createphone;
        private String yuyuely;
        private Object zhenliaof;
        private String createid;
        private String yy_dept;
        private String yy_doctorname;
        private String createname;
        private String uuid;
        private String yy_riqi;
        private String dept_address;
        private String guahaoxh;
        private String yb_card;
        private QuxiaoTimeBean quxiao_time;

        public Object getQuxiao_id() {
            return quxiao_id;
        }

        public void setQuxiao_id(Object quxiao_id) {
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

        public String getHosnum() {
            return hosnum;
        }

        public void setHosnum(String hosnum) {
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

        public String getNodecode() {
            return nodecode;
        }

        public void setNodecode(String nodecode) {
            this.nodecode = nodecode;
        }

        public Object getQuxiao_name() {
            return quxiao_name;
        }

        public void setQuxiao_name(Object quxiao_name) {
            this.quxiao_name = quxiao_name;
        }

        public String getDoorplate() {
            return doorplate;
        }

        public void setDoorplate(String doorplate) {
            this.doorplate = doorplate;
        }

        public String getZz_reason() {
            return zz_reason;
        }

        public void setZz_reason(String zz_reason) {
            this.zz_reason = zz_reason;
        }

        public String getDangtianpbid() {
            return dangtianpbid;
        }

        public void setDangtianpbid(String dangtianpbid) {
            this.dangtianpbid = dangtianpbid;
        }

        public String getYishengzc() {
            return yishengzc;
        }

        public void setYishengzc(String yishengzc) {
            this.yishengzc = yishengzc;
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

        public String getCreatephone() {
            return createphone;
        }

        public void setCreatephone(String createphone) {
            this.createphone = createphone;
        }

        public String getYuyuely() {
            return yuyuely;
        }

        public void setYuyuely(String yuyuely) {
            this.yuyuely = yuyuely;
        }

        public Object getZhenliaof() {
            return zhenliaof;
        }

        public void setZhenliaof(Object zhenliaof) {
            this.zhenliaof = zhenliaof;
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

        public String getDept_address() {
            return dept_address;
        }

        public void setDept_address(String dept_address) {
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

        public QuxiaoTimeBean getQuxiao_time() {
            return quxiao_time;
        }

        public void setQuxiao_time(QuxiaoTimeBean quxiao_time) {
            this.quxiao_time = quxiao_time;
        }

        public static class CreatetimeBean {
            /**
             * time : 1508576278000
             * minutes : 57
             * seconds : 58
             * hours : 16
             * month : 9
             * timezoneOffset : -480
             * year : 117
             * day : 6
             * date : 21
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
             * time : 1508574428000
             * minutes : 27
             * seconds : 8
             * hours : 16
             * month : 9
             * timezoneOffset : -480
             * year : 117
             * day : 6
             * date : 21
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
