package com.cpinfo.familydoctor.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Juna on 2017/3/22.
 * 类描述：用户登录bean
 */

public class UserLoginBean {

    /**
     * data : [{"dactorName":"","sex":"男","docChatID":"","dactor_idcard":"","identityId":"330182199306142114","docPortrait":"","citizen_card":"E987654321","doctorPhone":"","password":"123","nation":"蒙古族","sign_endtime":"","home_address":"333333","isSign":"未签约","rotxid":"e453909544db3b9c491eec9d0af12f88","sign_hosname":"","sign_starttime":"","age":"25","patPhone":"15757821642","userName":"游振强","uuid":" ","chatId":"roid"},{"dactorName":"","sex":"女","docChatID":"","dactor_idcard":"","identityId":"330182197808281342","docPortrait":"","citizen_card":"","doctorPhone":"","password":"123","nation":"","sign_endtime":"","home_address":"","isSign":"未签约","rotxid":"e453909544db3b9c491eec9d0af12f88","sign_hosname":"","sign_starttime":"","age":"40","patPhone":"15757821642","userName":"姜安华","uuid":" ","chatId":"roid"},{"dactorName":"","sex":"男","docChatID":"","dactor_idcard":"","identityId":"330127192306133617","docPortrait":"","citizen_card":"","doctorPhone":"","password":"123","nation":"","sign_endtime":"","home_address":"","isSign":"未签约","rotxid":"e453909544db3b9c491eec9d0af12f88","sign_hosname":"","sign_starttime":"","age":"95","patPhone":"15757821642","userName":"朱坤木","uuid":" ","chatId":"roid"},{"dactorName":"刘早春","sex":"女","docChatID":"7cb11a4c23db241f1d28d86b8ba33427","dactor_idcard":"330127196203290634","identityId":"330127196610310620","docPortrait":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/刘早春330127196203290634.jpg","citizen_card":"","doctorPhone":"13003658678","password":"123","nation":"","sign_endtime":"2017-07-27","home_address":"","isSign":"已签约","sign_hosname":"文昌镇卫生院","sign_starttime":"2018 ","age":"52","patPhone":"15757821642","userName":"陈平英","uuid":"297e969a5d7f09a8015d83fe85a22091","chatId":"roid"},{"dactorName":"刘早春","sex":"女","docChatID":"7cb11a4c23db241f1d28d86b8ba33427","dactor_idcard":"330127196203290634","identityId":"330721198702111921","docPortrait":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/刘早春330127196203290634.jpg","citizen_card":"123456","doctorPhone":"13003658678","password":"123","nation":"蒙古族","sign_endtime":"2017-07-20","home_address":"老K路凌乱","isSign":"不可续约","sign_hosname":"文昌镇卫生院","sign_starttime":"2018 ","age":"31","patPhone":"15757821642","userName":"应倩","uuid":"297e969a5d160f5e015d5f2d923c52cf","chatId":"roid"},{"dactorName":"江更生","sex":"男","docChatID":"edb9651e3857bd77e3aba362eee66e0e","dactor_idcard":"330127196502062914","identityId":"330127194809102715","docPortrait":"http://fds.qdhws.gov.cn:8089/fds/doctorphoto/江更生330127196502062914.jpg","citizen_card":"","doctorPhone":"13758199948","password":"123","nation":"","sign_endtime":"2017-08-12","home_address":"","isSign":"不可续约","sign_hosname":"瑶山卫生院","sign_starttime":"2018 ","age":"70","patPhone":"15757821642","userName":"何桂林","uuid":"297e969a5dcb9de0015dd364e0e41db8","chatId":"roid"},{"dactorName":"","sex":"男","docChatID":"","dactor_idcard":"","identityId":"620600198407048773","docPortrait":"","citizen_card":"","doctorPhone":"","password":"123","nation":"汉族","sign_endtime":"","home_address":"大家都","isSign":"未签约","rotxid":"e453909544db3b9c491eec9d0af12f88","sign_hosname":"","sign_starttime":"","age":"34","patPhone":"15757821642","userName":"阿尔是","uuid":" ","chatId":"roid"},{"dactorName":"","sex":"男","docChatID":"","dactor_idcard":"","identityId":"330127194605100718","docPortrait":"","citizen_card":"","doctorPhone":"","password":"123","nation":"","sign_endtime":"","home_address":"","isSign":"未签约","rotxid":"e453909544db3b9c491eec9d0af12f88","sign_hosname":"","sign_starttime":"","age":"72","patPhone":"15757821642","userName":"宋柏青","uuid":" ","chatId":"roid"}]
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

    public static class DataBean  implements Serializable {
        /**
         * dactorName :
         * sex : 男
         * docChatID :
         * dactor_idcard :
         * identityId : 330182199306142114
         * docPortrait :
         * citizen_card : E987654321
         * doctorPhone :
         * password : 123
         * nation : 蒙古族
         * sign_endtime :
         * home_address : 333333
         * isSign : 未签约
         * rotxid : e453909544db3b9c491eec9d0af12f88
         * sign_hosname :
         * sign_starttime :
         * age : 25
         * patPhone : 15757821642
         * userName : 游振强
         * uuid :
         * chatId : roid
         */

        private String dactorName;
        private String sex;
        private String docChatID;
        private String dactor_idcard;
        private String identityId;
        private String docPortrait;
        private String citizen_card;
        private String doctorPhone;
        private String password;
        private String nation;
        private String sign_endtime;
        private String home_address;
        private String isSign;
        private String rotxid;
        private String sign_hosname;
        private String sign_starttime;
        private String age;
        private String patPhone;
        private String userName;
        private String uuid;
        private String chatId;

        public String getDactorName() {
            return dactorName;
        }

        public void setDactorName(String dactorName) {
            this.dactorName = dactorName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getDocChatID() {
            return docChatID;
        }

        public void setDocChatID(String docChatID) {
            this.docChatID = docChatID;
        }

        public String getDactor_idcard() {
            return dactor_idcard;
        }

        public void setDactor_idcard(String dactor_idcard) {
            this.dactor_idcard = dactor_idcard;
        }

        public String getIdentityId() {
            return identityId;
        }

        public void setIdentityId(String identityId) {
            this.identityId = identityId;
        }

        public String getDocPortrait() {
            return docPortrait;
        }

        public void setDocPortrait(String docPortrait) {
            this.docPortrait = docPortrait;
        }

        public String getCitizen_card() {
            return citizen_card;
        }

        public void setCitizen_card(String citizen_card) {
            this.citizen_card = citizen_card;
        }

        public String getDoctorPhone() {
            return doctorPhone;
        }

        public void setDoctorPhone(String doctorPhone) {
            this.doctorPhone = doctorPhone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getSign_endtime() {
            return sign_endtime;
        }

        public void setSign_endtime(String sign_endtime) {
            this.sign_endtime = sign_endtime;
        }

        public String getHome_address() {
            return home_address;
        }

        public void setHome_address(String home_address) {
            this.home_address = home_address;
        }

        public String getIsSign() {
            return isSign;
        }

        public void setIsSign(String isSign) {
            this.isSign = isSign;
        }

        public String getRotxid() {
            return rotxid;
        }

        public void setRotxid(String rotxid) {
            this.rotxid = rotxid;
        }

        public String getSign_hosname() {
            return sign_hosname;
        }

        public void setSign_hosname(String sign_hosname) {
            this.sign_hosname = sign_hosname;
        }

        public String getSign_starttime() {
            return sign_starttime;
        }

        public void setSign_starttime(String sign_starttime) {
            this.sign_starttime = sign_starttime;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getPatPhone() {
            return patPhone;
        }

        public void setPatPhone(String patPhone) {
            this.patPhone = patPhone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }
    }
}
