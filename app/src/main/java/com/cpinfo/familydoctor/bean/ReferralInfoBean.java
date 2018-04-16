package com.cpinfo.familydoctor.bean;

import java.util.List;

/**
 * Created by CPInfo on 2017/11/24.
 */

public class ReferralInfoBean {

    /**
     * data : [{"centerid":"297e969a5c85d918015c86af53ff05cc","phone":"13456787019","applydeptname":"神经内科（康复二）","applytime1":"20170608","type":"县外转诊","ghxh":"8","applydoc":"014","admissiontime":null,"brzs_b5":null,"grounds":"医疗技术因素","deptname":"神经外科","turnname":null,"patname":"何桂林","starttime1":"20170609","starttime":{"nanos":0,"time":1496937600000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":117,"day":5,"date":9},"doctorid":null,"status":"已申请","applydocname":"方诚","preregid":"10599716","applyhosnum":"330110026","purposehos":"浙江大学医学院附属第二医院","idnum":"330127194809102715","outland":null,"medicareidamt":null,"endtime1":"20170609","turncode":null,"icd10":"I67.110","accountamt":null,"endtime":{"nanos":0,"time":1496937600000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":117,"day":5,"date":9},"brtz_b5":null,"regcode":null,"doctorname":"普通门诊","approvalstatus":"已申请","sex":"1","docphone":"13456787019","upordown":"上转","jzsj":"08:21-08:42","bqms_b5":null,"cardid":"E11252045","diagname":null,"medicareid":null,"totalamt":null,"deptcode":"1030500","istemp":null,"applydeptid":"220","outtime":null,"applyhosname":"淳安县中医院","cbzd_b5":"I67.110","localhostid":"1","jzrq":"2017-06-09","inorout":"县外","message":"申请成功","jcjy_b5":null,"address":"瑶山乡新光村","applytime":{"time":1496908256000,"minutes":50,"seconds":56,"hours":15,"month":5,"timezoneOffset":-480,"year":117,"day":4,"date":8}},{"centerid":"297e969a5c80b194015c8580f69b1804","phone":"64810890","applydeptname":"神经内科（康复二）","applytime1":"20170608","type":"县外转诊","ghxh":"8","applydoc":"645","admissiontime":null,"brzs_b5":null,"grounds":"医疗技术因素","deptname":"神经外科","turnname":null,"patname":"何桂林","starttime1":"20170609","starttime":{"nanos":0,"time":1496937600000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":117,"day":5,"date":9},"doctorid":null,"status":"已取消","applydocname":"肖丽娟","preregid":"10595606","applyhosnum":"330110026","purposehos":"浙江大学医学院附属第二医院","idnum":"330127194809102715","outland":null,"medicareidamt":null,"endtime1":"20170609","turncode":null,"icd10":"I72.000","accountamt":null,"endtime":{"nanos":0,"time":1496937600000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":117,"day":5,"date":9},"brtz_b5":null,"regcode":null,"doctorname":"普通门诊","approvalstatus":"已申请","sex":"1","docphone":"64810890","upordown":"上转","jzsj":"14:33-14:42","bqms_b5":null,"cardid":"E11252045","diagname":null,"medicareid":"18129","totalamt":null,"deptcode":"1030500","istemp":null,"applydeptid":"220","outtime":null,"applyhosname":"淳安县中医院","cbzd_b5":"I72.000","localhostid":"1","jzrq":"2017-06-09","inorout":"县外","message":"申请成功","jcjy_b5":null,"address":"瑶山乡新光村","applytime":{"time":1496888440000,"minutes":20,"seconds":40,"hours":10,"month":5,"timezoneOffset":-480,"year":117,"day":4,"date":8}}]
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
         * centerid : 297e969a5c85d918015c86af53ff05cc
         * phone : 13456787019
         * applydeptname : 神经内科（康复二）
         * applytime1 : 20170608
         * type : 县外转诊
         * ghxh : 8
         * applydoc : 014
         * admissiontime : null
         * brzs_b5 : null
         * grounds : 医疗技术因素
         * deptname : 神经外科
         * turnname : null
         * patname : 何桂林
         * starttime1 : 20170609
         * starttime : {"nanos":0,"time":1496937600000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":117,"day":5,"date":9}
         * doctorid : null
         * status : 已申请
         * applydocname : 方诚
         * preregid : 10599716
         * applyhosnum : 330110026
         * purposehos : 浙江大学医学院附属第二医院
         * idnum : 330127194809102715
         * outland : null
         * medicareidamt : null
         * endtime1 : 20170609
         * turncode : null
         * icd10 : I67.110
         * accountamt : null
         * endtime : {"nanos":0,"time":1496937600000,"minutes":0,"seconds":0,"hours":0,"month":5,"timezoneOffset":-480,"year":117,"day":5,"date":9}
         * brtz_b5 : null
         * regcode : null
         * doctorname : 普通门诊
         * approvalstatus : 已申请
         * sex : 1
         * docphone : 13456787019
         * upordown : 上转
         * jzsj : 08:21-08:42
         * bqms_b5 : null
         * cardid : E11252045
         * diagname : null
         * medicareid : null
         * totalamt : null
         * deptcode : 1030500
         * istemp : null
         * applydeptid : 220
         * outtime : null
         * applyhosname : 淳安县中医院
         * cbzd_b5 : I67.110
         * localhostid : 1
         * jzrq : 2017-06-09
         * inorout : 县外
         * message : 申请成功
         * jcjy_b5 : null
         * address : 瑶山乡新光村
         * applytime : {"time":1496908256000,"minutes":50,"seconds":56,"hours":15,"month":5,"timezoneOffset":-480,"year":117,"day":4,"date":8}
         */

        private String centerid;
        private String phone;
        private String applydeptname;
        private String applytime1;
        private String type;
        private String ghxh;
        private String applydoc;
        private Object admissiontime;
        private Object brzs_b5;
        private String grounds;
        private String deptname;
        private Object turnname;
        private String patname;
        private String starttime1;
        private StarttimeBean starttime;
        private Object doctorid;
        private String status;
        private String applydocname;
        private String preregid;
        private String applyhosnum;
        private String purposehos;
        private String idnum;
        private Object outland;
        private Object medicareidamt;
        private String endtime1;
        private Object turncode;
        private String icd10;
        private Object accountamt;
        private EndtimeBean endtime;
        private Object brtz_b5;
        private Object regcode;
        private String doctorname;
        private String approvalstatus;
        private String sex;
        private String docphone;
        private String upordown;
        private String jzsj;
        private Object bqms_b5;
        private String cardid;
        private Object diagname;
        private Object medicareid;
        private Object totalamt;
        private String deptcode;
        private Object istemp;
        private String applydeptid;
        private Object outtime;
        private String applyhosname;
        private String cbzd_b5;
        private String localhostid;
        private String jzrq;
        private String inorout;
        private String message;
        private Object jcjy_b5;
        private String address;
        private ApplytimeBean applytime;

        public String getCenterid() {
            return centerid;
        }

        public void setCenterid(String centerid) {
            this.centerid = centerid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getApplydeptname() {
            return applydeptname;
        }

        public void setApplydeptname(String applydeptname) {
            this.applydeptname = applydeptname;
        }

        public String getApplytime1() {
            return applytime1;
        }

        public void setApplytime1(String applytime1) {
            this.applytime1 = applytime1;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGhxh() {
            return ghxh;
        }

        public void setGhxh(String ghxh) {
            this.ghxh = ghxh;
        }

        public String getApplydoc() {
            return applydoc;
        }

        public void setApplydoc(String applydoc) {
            this.applydoc = applydoc;
        }

        public Object getAdmissiontime() {
            return admissiontime;
        }

        public void setAdmissiontime(Object admissiontime) {
            this.admissiontime = admissiontime;
        }

        public Object getBrzs_b5() {
            return brzs_b5;
        }

        public void setBrzs_b5(Object brzs_b5) {
            this.brzs_b5 = brzs_b5;
        }

        public String getGrounds() {
            return grounds;
        }

        public void setGrounds(String grounds) {
            this.grounds = grounds;
        }

        public String getDeptname() {
            return deptname;
        }

        public void setDeptname(String deptname) {
            this.deptname = deptname;
        }

        public Object getTurnname() {
            return turnname;
        }

        public void setTurnname(Object turnname) {
            this.turnname = turnname;
        }

        public String getPatname() {
            return patname;
        }

        public void setPatname(String patname) {
            this.patname = patname;
        }

        public String getStarttime1() {
            return starttime1;
        }

        public void setStarttime1(String starttime1) {
            this.starttime1 = starttime1;
        }

        public StarttimeBean getStarttime() {
            return starttime;
        }

        public void setStarttime(StarttimeBean starttime) {
            this.starttime = starttime;
        }

        public Object getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(Object doctorid) {
            this.doctorid = doctorid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getApplydocname() {
            return applydocname;
        }

        public void setApplydocname(String applydocname) {
            this.applydocname = applydocname;
        }

        public String getPreregid() {
            return preregid;
        }

        public void setPreregid(String preregid) {
            this.preregid = preregid;
        }

        public String getApplyhosnum() {
            return applyhosnum;
        }

        public void setApplyhosnum(String applyhosnum) {
            this.applyhosnum = applyhosnum;
        }

        public String getPurposehos() {
            return purposehos;
        }

        public void setPurposehos(String purposehos) {
            this.purposehos = purposehos;
        }

        public String getIdnum() {
            return idnum;
        }

        public void setIdnum(String idnum) {
            this.idnum = idnum;
        }

        public Object getOutland() {
            return outland;
        }

        public void setOutland(Object outland) {
            this.outland = outland;
        }

        public Object getMedicareidamt() {
            return medicareidamt;
        }

        public void setMedicareidamt(Object medicareidamt) {
            this.medicareidamt = medicareidamt;
        }

        public String getEndtime1() {
            return endtime1;
        }

        public void setEndtime1(String endtime1) {
            this.endtime1 = endtime1;
        }

        public Object getTurncode() {
            return turncode;
        }

        public void setTurncode(Object turncode) {
            this.turncode = turncode;
        }

        public String getIcd10() {
            return icd10;
        }

        public void setIcd10(String icd10) {
            this.icd10 = icd10;
        }

        public Object getAccountamt() {
            return accountamt;
        }

        public void setAccountamt(Object accountamt) {
            this.accountamt = accountamt;
        }

        public EndtimeBean getEndtime() {
            return endtime;
        }

        public void setEndtime(EndtimeBean endtime) {
            this.endtime = endtime;
        }

        public Object getBrtz_b5() {
            return brtz_b5;
        }

        public void setBrtz_b5(Object brtz_b5) {
            this.brtz_b5 = brtz_b5;
        }

        public Object getRegcode() {
            return regcode;
        }

        public void setRegcode(Object regcode) {
            this.regcode = regcode;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public String getApprovalstatus() {
            return approvalstatus;
        }

        public void setApprovalstatus(String approvalstatus) {
            this.approvalstatus = approvalstatus;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getDocphone() {
            return docphone;
        }

        public void setDocphone(String docphone) {
            this.docphone = docphone;
        }

        public String getUpordown() {
            return upordown;
        }

        public void setUpordown(String upordown) {
            this.upordown = upordown;
        }

        public String getJzsj() {
            return jzsj;
        }

        public void setJzsj(String jzsj) {
            this.jzsj = jzsj;
        }

        public Object getBqms_b5() {
            return bqms_b5;
        }

        public void setBqms_b5(Object bqms_b5) {
            this.bqms_b5 = bqms_b5;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public Object getDiagname() {
            return diagname;
        }

        public void setDiagname(Object diagname) {
            this.diagname = diagname;
        }

        public Object getMedicareid() {
            return medicareid;
        }

        public void setMedicareid(Object medicareid) {
            this.medicareid = medicareid;
        }

        public Object getTotalamt() {
            return totalamt;
        }

        public void setTotalamt(Object totalamt) {
            this.totalamt = totalamt;
        }

        public String getDeptcode() {
            return deptcode;
        }

        public void setDeptcode(String deptcode) {
            this.deptcode = deptcode;
        }

        public Object getIstemp() {
            return istemp;
        }

        public void setIstemp(Object istemp) {
            this.istemp = istemp;
        }

        public String getApplydeptid() {
            return applydeptid;
        }

        public void setApplydeptid(String applydeptid) {
            this.applydeptid = applydeptid;
        }

        public Object getOuttime() {
            return outtime;
        }

        public void setOuttime(Object outtime) {
            this.outtime = outtime;
        }

        public String getApplyhosname() {
            return applyhosname;
        }

        public void setApplyhosname(String applyhosname) {
            this.applyhosname = applyhosname;
        }

        public String getCbzd_b5() {
            return cbzd_b5;
        }

        public void setCbzd_b5(String cbzd_b5) {
            this.cbzd_b5 = cbzd_b5;
        }

        public String getLocalhostid() {
            return localhostid;
        }

        public void setLocalhostid(String localhostid) {
            this.localhostid = localhostid;
        }

        public String getJzrq() {
            return jzrq;
        }

        public void setJzrq(String jzrq) {
            this.jzrq = jzrq;
        }

        public String getInorout() {
            return inorout;
        }

        public void setInorout(String inorout) {
            this.inorout = inorout;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getJcjy_b5() {
            return jcjy_b5;
        }

        public void setJcjy_b5(Object jcjy_b5) {
            this.jcjy_b5 = jcjy_b5;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public ApplytimeBean getApplytime() {
            return applytime;
        }

        public void setApplytime(ApplytimeBean applytime) {
            this.applytime = applytime;
        }

        public static class StarttimeBean {
            /**
             * nanos : 0
             * time : 1496937600000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 5
             * timezoneOffset : -480
             * year : 117
             * day : 5
             * date : 9
             */

            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
            private int day;
            private int date;

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

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

        public static class EndtimeBean {
            /**
             * nanos : 0
             * time : 1496937600000
             * minutes : 0
             * seconds : 0
             * hours : 0
             * month : 5
             * timezoneOffset : -480
             * year : 117
             * day : 5
             * date : 9
             */

            private int nanos;
            private long time;
            private int minutes;
            private int seconds;
            private int hours;
            private int month;
            private int timezoneOffset;
            private int year;
            private int day;
            private int date;

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

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

        public static class ApplytimeBean {
            /**
             * time : 1496908256000
             * minutes : 50
             * seconds : 56
             * hours : 15
             * month : 5
             * timezoneOffset : -480
             * year : 117
             * day : 4
             * date : 8
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
