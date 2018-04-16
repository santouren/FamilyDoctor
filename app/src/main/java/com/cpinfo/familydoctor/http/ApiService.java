package com.cpinfo.familydoctor.http;

import com.cpinfo.familydoctor.bean.AddFamilyMemberBean;
import com.cpinfo.familydoctor.bean.AddInfoBean;
import com.cpinfo.familydoctor.bean.AdviceInfoBean;
import com.cpinfo.familydoctor.bean.BeforehandContractInfoBean;
import com.cpinfo.familydoctor.bean.CancleRegistrationBean;
import com.cpinfo.familydoctor.bean.CentralHospitalBean;
import com.cpinfo.familydoctor.bean.CityListBean;
import com.cpinfo.familydoctor.bean.ContractEvaluationBean;
import com.cpinfo.familydoctor.bean.ContractRecordBean;
import com.cpinfo.familydoctor.bean.CountyInReferralBean;
import com.cpinfo.familydoctor.bean.CountyOutReferralBean;
import com.cpinfo.familydoctor.bean.DepartmentQueueBean;
import com.cpinfo.familydoctor.bean.DepartmentsBean;
import com.cpinfo.familydoctor.bean.DoctorArrangeBean;
import com.cpinfo.familydoctor.bean.DoctorOrderNoBean;
import com.cpinfo.familydoctor.bean.DoctorTeamsBean;
import com.cpinfo.familydoctor.bean.EvaluationInfoBean;
import com.cpinfo.familydoctor.bean.ExamineListBean;
import com.cpinfo.familydoctor.bean.ExtensionContractBean;
import com.cpinfo.familydoctor.bean.ExtensionContractInfoBean;
import com.cpinfo.familydoctor.bean.FamilyMemberBean;
import com.cpinfo.familydoctor.bean.FamilyRelationBean;
import com.cpinfo.familydoctor.bean.GoRegistrationBean;
import com.cpinfo.familydoctor.bean.HealthInsuranceBean;
import com.cpinfo.familydoctor.bean.HomeNewsBean;
import com.cpinfo.familydoctor.bean.HospitalizedListBean;
import com.cpinfo.familydoctor.bean.InspectionListBean;
import com.cpinfo.familydoctor.bean.LunBoBean;
import com.cpinfo.familydoctor.bean.MedicalRecordBean;
import com.cpinfo.familydoctor.bean.ModifyInformationBean;
import com.cpinfo.familydoctor.bean.ModifyPasswordBean;
import com.cpinfo.familydoctor.bean.MyDoctorBean;
import com.cpinfo.familydoctor.bean.MyQueueBean;
import com.cpinfo.familydoctor.bean.MyRegistrationBean;
import com.cpinfo.familydoctor.bean.NotificationEvaluateBean;
import com.cpinfo.familydoctor.bean.NotificationEvaluationBean;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;
import com.cpinfo.familydoctor.bean.OrderDepartmentsBean;
import com.cpinfo.familydoctor.bean.OrderHospitalsBean;
import com.cpinfo.familydoctor.bean.OrderListBean;
import com.cpinfo.familydoctor.bean.OutpatientListBean;
import com.cpinfo.familydoctor.bean.PatientHistoryReportsBean;
import com.cpinfo.familydoctor.bean.PhysicalListBean;
import com.cpinfo.familydoctor.bean.PublicityInfoBean;
import com.cpinfo.familydoctor.bean.ReferralInfoBean;
import com.cpinfo.familydoctor.bean.RelationListBean;
import com.cpinfo.familydoctor.bean.RelieveContractBean;
import com.cpinfo.familydoctor.bean.ResetPasswordBean;
import com.cpinfo.familydoctor.bean.SearchHospitalBean;
import com.cpinfo.familydoctor.bean.SetPasswordBean;
import com.cpinfo.familydoctor.bean.SignTeamMembersBean;
import com.cpinfo.familydoctor.bean.SpecialistInfoBean;
import com.cpinfo.familydoctor.bean.SubmitSignInfoBean;
import com.cpinfo.familydoctor.bean.SubmitEvaluationInfoBean;
import com.cpinfo.familydoctor.bean.UserLoginBean;
import com.cpinfo.familydoctor.bean.UserRegisterBean;
import com.cpinfo.familydoctor.bean.VerificationCodeBean;
import com.cpinfo.familydoctor.bean.VerificationForPsdBean;
import com.cpinfo.familydoctor.bean.VersionBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Juna on 2017/3/20.
 * 类描述：联网请求接口
 */

public interface ApiService {
    //用户注册
    @POST("fds_bak/httpServer")
    Call<UserRegisterBean> toRegister(@Query("requesttype") String requestType,
                                      @Query("identityId") String identityId,
                                      @Query("userName") String userName,
                                      @Query("phone") String phone,
                                      @Query("password") String password,
                                      @Query("phoneCode") String phoneCode);

    //用户登录(通过手机号)
    @POST("fds_bak/httpServer")
    Call<UserLoginBean> loginByPhone(@Query("requesttype") String requestType,
                                     @Query("phone") String phone,
                                     @Query("password") String password);

    //手机登录=》设置密码
    @POST("fds_bak/httpServer")
    Call<SetPasswordBean> setPassword(@Query("requesttype") String requestType,
                                      @Query("phone") String phone,
                                      @Query("password") String password);

    //手机登录=》完善信息
    @POST("fds_bak/httpServer")
    Call<AddInfoBean> addInfo(@Query("requesttype") String requestType,
                              @Query("phone") String phone,
                              @Query("idnum") String cardID,
                              @Query("name") String name);

    //用户登录(通过身份证号)
    @POST("fds_bak/httpServer")
    Call<UserLoginBean> loginByIdentity(@Query("requesttype") String requestType,
                                        @Query("identityId") String identityId,
                                        @Query("password") String password);

    //获取（发送）验证码（注册）
    @POST("fds_bak/httpServer")
    Call<VerificationCodeBean> getVerificationCode(@Query("requesttype") String requestType,
                                                   @Query("phone") String phone,
                                                   @Query("idnum") String identityId);

    //获取（发送）验证码（找回密码）
    @POST("fds_bak/httpServer")
    Call<VerificationForPsdBean> getVerificationForPsd(@Query("requesttype") String requestType,
                                                       @Query("phone") String phone,
                                                       @Query("idnum") String identityId);

    //获取（发送）验证码（添加家人）
    @POST("fds_bak/httpServer")
    Call<VerificationCodeBean> getVerificationNum(@Query("requesttype") String requestType,
                                                  @Query("phonenum") String phone,
                                                  @Query("idcard") String idcard);

    //获取（发送）验证码（手机号登录）
    @POST("fds_bak/httpServer")
    Call<VerificationCodeBean> getVerificationCode(@Query("requesttype") String requestType,
                                                   @Query("phone") String phone);

    //用户我的页面（签约医生的信息）
    @GET("fds_bak/httpServer")
    Call<MyDoctorBean> getSignedDoctorInfo(@Query("requesttype") String requestType,
                                           @Query("identityId") String identityId);

    //居民版=>选择签约医院列表
    @GET("fds_bak/httpServer")
    Call<CentralHospitalBean> getCentralHospitals(@Query("requesttype") String requestType);

    //居民版=>选择医生团队列表
    @GET("fds_bak/httpServer")
    Call<DoctorTeamsBean> getDoctorTeams(@Query("requesttype") String requestType,
                                         @Query("hosNum") String hospitalNum);

    //居民版=>预约挂号=》选择医院（医院列表）
    @GET("fds_bak/httpServer")
    Call<OrderHospitalsBean> getOrderHospitals(@Query("requesttype") String requestType,
                                               @Query("startPage") int startPage,
                                               @Query("endPage") int endPage);

    //居民版=>预约挂号=》选择科室（科室列表）
    @GET("fds_bak/httpServer")
    Call<OrderDepartmentsBean> getOrderDepartments(@Query("requesttype") String requestType,
                                                   @Query("hosNum") String hosNum,
                                                   @Query("nodeCode") String nodeCode);

    //居民版=>预约挂号=》选择科室（科室列表）
    @GET
    Call<DepartmentsBean> getDepartments(@Url String url,
                                         @Query("requesttype") String requestType,
                                         @Query("hosplital_ip") String hosId,
                                         @Query("ghfs_xn") String orderWay,
                                         @Query("ghlb_xn") String orderType,
                                         @Query("ghriqi_xn") String orderDate);

    //居民版=>预约挂号=》搜索医院（医院列表）
    @GET("fds_bak/httpServer")
    Call<SearchHospitalBean> searchHospital(@Query("requesttype") String requestType,
                                            @Query("content") String content);

    //居民版=>健康档案=》门诊列表
    @GET("fds_bak/httpServer")
    Call<OutpatientListBean> getOutpatientList(@Query("requesttype") String requestType,
                                               @Query("idCard") String idCard,
                                               @Query("startPage") int startItem,
                                               @Query("endPage") int endItem);

    //居民版=>预签约=》签约团队成员列表
    @GET("fds_bak/httpServer")
    Call<SignTeamMembersBean> getSignTeamMembers(@Query("requesttype") String requestType,
                                                 @Query("teamId") String teamId);

    //居民版=>预签约=》提交预签约的信息
    @POST("fds_bak/httpServer")
    Call<SubmitSignInfoBean> submitSignInfo(@Query("requesttype") String requestType,
                                            @Query("patName") String patientName,
                                            @Query("sex") String sex,
                                            @Query("medical_Type") String censusRegister,
                                            @Query("patPhone") String patientPhone,
                                            @Query("patIdCard") String patientIdentityId,
                                            @Query("time") String signTime,
                                            @Query("sign_Year") String sign_Year,
                                            @Query("disease") String disease,
                                            @Query("townShip") String town,
                                            @Query("village") String street,
                                            @Query("doorPlate") String doorplateNum,
                                            @Query("hosNum") String hosNum,
                                            @Query("nodeCode") String nodeCode,
                                            @Query("dactorId") String doctorId,
                                            @Query("dactor_Name") String doctorName,
                                            @Query("dactor_Idcard") String doctorIdentityId,
                                            @Query("doctorPhone") String doctorPhone,
                                            @Query("sign_Hosnum") String signHosName);

    //居民版=>档案=》获取门诊详情单（PDF文件形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getOutpatientDetail(@Query("requesttype") String requestType,
                                           @Query("recipeNo") String recipeNo,
                                           @Query("jgdm") String jgdm);

    //居民版=>档案=》获取住院历史列表
    @GET("fds_bak/httpServer")
    Call<HospitalizedListBean> getHospitalizedList(@Query("requesttype") String requestType,
                                                   @Query("idCard") String identityId,
                                                   @Query("startPage") int startItem,
                                                   @Query("endPage") int endItem);

    //居民版=>档案=》获取住院病案详情（PDF文件形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getHospitalizedDetail(@Query("requesttype") String requestType,
                                             @Query("inpNo") String hospitalizedNum);

    //通知公告详情（PDF形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getAnnouncementDetail(@Query("requesttype") String requestType,
                                             @Query("noticeid") String noticeID,
                                             @Query("idcard") String idcard);

    //居民版=>档案=》获取检查列表
    @GET("fds_bak/httpServer")
    Call<InspectionListBean> getInspectionList(@Query("requesttype") String requestType,
                                               @Query("idCard") String identityId,
                                               @Query("startPage") int startItem,
                                               @Query("endPage") int endItem);

    //居民版=>档案=》获取检查单详情（PDF形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getInspectionDetail(@Query("requesttype") String requestType,
                                           @Query("jclsh") String jclsh);

    //居民版=>档案=》获取检验报告列表
    @GET("fds_bak/httpServer")
    Call<ExamineListBean> getExamineList(@Query("requesttype") String requestType,
                                         @Query("idCard") String identityId,
                                         @Query("startPage") int startItem,
                                         @Query("endPage") int endItem);

    //居民版=>档案=》获取检验报告详情（PDF形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getExamineDetail(@Query("requesttype") String requestType,
                                        @Query("jybgdh") String reportNo,
                                        @Query("jgdm") String hospitalNo,
                                        @Query("brbslb") String brbslb);

    //居民版=>档案=》获取健康体检报告列表
    @GET("fds_bak/httpServer")
    Call<PhysicalListBean> getPhysicalList(@Query("requesttype") String requestType,
                                           @Query("idCard") String identityId,
                                           @Query("startPage") int startItem,
                                           @Query("endPage") int endItem);

    //居民版=>档案=》获取健康体检报告详情（PDF形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getPhysicalDetail(@Query("requesttype") String requestType,
                                         @Query("pexamId") String pexamId,
                                         @Query("pexamJgdm") String pexamJgdm);

    //居民版=>档案=》获取电子病历三级列表（住院报告）
    @GET("fds_bak/httpServer")
    Call<PatientHistoryReportsBean> getPatientHistoryReports(@Query("requesttype") String requestType,
                                                             @Query("inpNo") String inpNo);

    //居民版=>档案=》获取电子病历四级页面（住院报告详情页面）（PDF形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> PatientHistoryReportDetail(@Query("requesttype") String requestType,
                                                  @Query("reportName") String reportName,
                                                  @Query("mrId") String mrId);

    //获取云影像关键参数
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getCloudImage(@Query("requesttype") String requestType,
                                     @Query("recipedate") String recipeDate,
                                     @Query("idnum") String idNum);

    //家庭档案（家庭成员信息）
    @GET("fds_bak/httpServer")
    Call<FamilyMemberBean> getFamilyInfo(@Query("requesttype") String requestType,
                                         @Query("idnum") String idNum);

    //添加家人（提交信息）
    @POST("fds_bak/httpServer")
    Call<AddFamilyMemberBean> addFamilyMember(@Query("requesttype") String requestType,
                                              @Query("patient_idnum") String selfCardId,
                                              @Query("relation") String relation,
                                              @Query("name") String memberName,
                                              @Query("citizen_card") String citizenCard,
                                              @Query("nation") String nation,
                                              @Query("home_address") String home_address,
                                              @Query("idcard") String memberCardId,
                                              @Query("phonenum") String phoneNum,
                                              @Query("age") String age,
                                              @Query("sex") String sex,
                                              @Query("smsnum") String verificationCode);

    //预签约（获取城镇，街道列表数据）
    @GET("fds_bak/httpServer")
    Call<CityListBean> getCityList(@Query("requesttype") String requestType);

    //获取预约列表数据
    @GET
    Call<OrderListBean> getOrderList(@Url String url,
                                     @Query("requesttype") String requestType,
                                     @Query("idcard") String idcard);

    //获取医保政策的数据
    @GET("fds_bak/httpServer")
    Call<HealthInsuranceBean> getHealthInsurance(@Query("requesttype") String requestType);

    //获取通知消息的数据
    @GET("fds_bak/httpServer")
    Call<NotificationMsgBean> getNotificationMsg(@Query("requesttype") String requestType,
                                                 @Query("idnum") String idNum,
                                                 @Query("state") String state);

    //获取医生排班的数据
    @GET
    Call<DoctorArrangeBean> getDoctorArrange(@Url String url,
                                             @Query("requesttype") String requestType,
                                             @Query("hosplital_ip") String hospitalIP,
                                             @Query("ghriqi_xn") String date,
                                             @Query("ghlb_xn") String ghlb_xn,
                                             @Query("dept_xn") String deptNo,
                                             @Query("ghfs_xn") String ghfs_xn);

    //获取医生号源的数据
    @GET
    Call<DoctorOrderNoBean> getDoctorOrderNo(@Url String url,
                                             @Query("requesttype") String requestType,
                                             @Query("hosplital_ip") String hospitalIP,
                                             @Query("hy_riqi") String date,
                                             @Query("hy_ghlb") String hy_ghlb,
                                             @Query("hy_dept") String deptNo,
                                             @Query("hy_ghfs") String hy_ghfs,
                                             @Query("hy_ghbc") String hy_ghbc,
                                             @Query("hy_ysdm") String hy_ysdm);

    //获取我的挂号的数据
    @GET
    Call<MyRegistrationBean> getMyRegistration(@Url String url,
                                               @Query("requesttype") String requestType,
                                               @Query("idcard") String identityId);

    //取消挂号
    @GET
    Call<CancleRegistrationBean> cancleRegistration(@Url String url,
                                                    @Query("requesttype") String requestType,
                                                    @Query("jiuzhenklx") String jiuZhenKaLeiXing,
                                                    @Query("jiuzhenkh") String jiuZhenKaHao,
                                                    @Query("zhengjianlx") String zhengJianLeiXing,
                                                    @Query("zhengjianhm") String zhengJianHaoMa,
                                                    @Query("xingming") String patientName,
                                                    @Query("yuyuely") String yuYueLeiXing,
                                                    @Query("quhaomm") String qvHaoMiMa,
                                                    @Query("hosname") String hosName,
                                                    @Query("uuid") String uuid);

    //确认挂号
    @GET
    Call<GoRegistrationBean> goRegistration(@Url String url,
                                            @Query("requesttype") String requestType,
                                            @Query("hosplital_ip") String hosplitalIP,
                                            @Query("jiuzhenklx") String jiuZhenkLeiXing,
                                            @Query("zhengjianlx") String zhengJianLeiXing,
                                            @Query("zhengjianhm") String zhengJianHaoMa,
                                            @Query("xingming") String patientName,
                                            @Query("yizhoupbid") String yiZhouPaiBanID,
                                            @Query("dangtianpbid") String dangTianPaiBanID,
                                            @Query("riqi") String date,
                                            @Query("guahaobc") String guaHaoBanCi,
                                            @Query("guahaolb") String guaHaoLeiBie,
                                            @Query("keshidm") String keShiDaiMa,
                                            @Query("yishengdm") String yiShengDaiMa,
                                            @Query("guahaoxh") String guaHaoXuHao,
                                            @Query("yuyuely") String yuYueLaiYuan,
                                            @Query("lianxidh") String patientPhone,
                                            @Query("sex") String sex,
                                            @Query("yy_hosname") String hosName,
                                            @Query("yy_dept") String deptName,
                                            @Query("yy_doctorname") String doctorName,
                                            @Query("dept_address") String deptAddress,
                                            @Query("zhenliaof") String zhenLiaoFei,
                                            @Query("yishengzc") String yiShenZhiCheng,
                                            @Query("jiuzhenkh") String jiuzhenkh,
                                            @Query("jiatingzz") String jiatingzz,
                                            @Query("minzu") String minzu,
                                            @Query("yuyueren") String yuyueren);

    //获取主页健康资讯
    @GET("fds_bak/httpServer")
    Call<HomeNewsBean> getHomeNews(@Query("requesttype") String requestType);

    //获取云影像（PDF形式）
    @GET
    Call<ResponseBody> getCloudImage(@Url String url,
                                     @Query("cnt") String cnt,
                                     @Query("jcbh") String jcbh,
                                     @Query("querymode") String querymode,
                                     @Query("region") String region);

    //获取就诊记录
    @GET("fds_bak/httpServer")
    Call<MedicalRecordBean> getMedicalRecord(@Query("requesttype") String requestType,
                                             @Query("doctorIdcard") String doctorIdcard,
                                             @Query("patientIdcard") String patientIdcard,
                                             @Query("startPage") int startPage,
                                             @Query("endPage") int endPage);

    //获取就诊记录
    @GET("fds_bak/httpServer")
    Call<ContractEvaluationBean> getContractEvaluation(@Query("requesttype") String requestType,
                                                       @Query("idcard") String idcard);

    //获取评价信息
    @GET("fds_bak/httpServer")
    Call<EvaluationInfoBean> getEvaluationInfo(@Query("requesttype") String requestType,
                                               @Query("idcard") String idcard,
                                               @Query("uuid") String uuid);

    //提交评价信息
    @POST("fds_bak/httpServer")
    Call<SubmitEvaluationInfoBean> submitEvaluationInfo(@Query("requesttype") String requestType,
                                                        @Query("patient_name") String patient_name,
                                                        @Query("patient_idcard") String patient_idcard,
                                                        @Query("grade_uuid") String grade_uuid,
                                                        @Query("grade_time") String grade_time,
                                                        @Query("grad_type") String grad_type,
                                                        @Query("hospital_environment_grade") int hospital_environment_grade,
                                                        @Query("hospital_environment_content") String hospital_environment_content,
                                                        @Query("doctor_power_grade") int doctor_power_grade,
                                                        @Query("doctor_power_content") String doctor_power_content,
                                                        @Query("doctor_manner_grade") int doctor_manner_grade,
                                                        @Query("doctor_manner_content") String doctor_manner_content,
                                                        @Query("nurse_manner_grade") int nurse_manner_grade,
                                                        @Query("nurse_manner_content") String nurse_manner_content,
                                                        @Query("other_content") String other_content);

    //提交通知评价
    @POST("fds_bak/httpServer")
    Call<NotificationEvaluationBean> submitNotificationEvaluation(@Query("requesttype") String requestType,
                                                                  @Query("record_jzlsh") String record_jzlsh,
                                                                  @Query("record_jgdm") String record_jgdm,
                                                                  @Query("patient_name") String patient_name,
                                                                  @Query("patient_idcard") String patient_idcard,
                                                                  @Query("hospital_environment_grade") int hospital_environment_grade,
                                                                  @Query("hospital_environment_content") String hospital_environment_content,
                                                                  @Query("doctor_power_grade") int doctor_power_grade,
                                                                  @Query("doctor_power_content") String doctor_power_content,
                                                                  @Query("doctor_manner_grade") int doctor_manner_grade,
                                                                  @Query("doctor_manner_content") String doctor_manner_content,
                                                                  @Query("other_content") String other_content);

    //获取宣教信息
    @GET("fds_bak/httpServer")
    Call<PublicityInfoBean> getPublicityInfo(@Query("requesttype") String requestType);

    //获取双向转诊信息（县内转诊）
    @GET
    Call<CountyInReferralBean> getCountyInReferral(@Url String url,
                                                   @Query("requesttype") String requestType,
                                                   @Query("idcard") String idcard);

    //获取双向转诊信息（县内转诊）
    @GET
    Call<CountyOutReferralBean> getCountyOutReferral(@Url String url,
                                                     @Query("requesttype") String requestType,
                                                     @Query("idcard") String idcard);

    //获取预签约信息
    @GET("fds_bak/httpServer")
    Call<BeforehandContractInfoBean> getBeforehandContractInfo(@Query("requesttype") String requestType,
                                                               @Query("idnum") String idNum,
                                                               @Query("sign_stats") String sign_stats);

    //获取预签约信息
    @GET("fds_bak/httpServer")
    Call<ExtensionContractInfoBean> getExtensionContractInfo(@Query("requesttype") String requestType,
                                                             @Query("idnum") String idNum,
                                                             @Query("sign_stats") String sign_stats);

    //获取预签约记录
    @GET("fds_bak/httpServer")
    Call<ContractRecordBean> getContractRecord(@Query("requesttype") String requestType,
                                               @Query("idcard") String idCard);

    //解除预签约
    @GET("fds_bak/httpServer")
    Call<RelieveContractBean> relieveContract(@Query("requesttype") String requestType,
                                              @Query("idcard") String idCard);

    //续约
    @GET
    Call<ExtensionContractBean> extensionContract(@Url String url,
                                                  @Query("requesttype") String requestType,
                                                  @Query("uuid") String uuid,
                                                  @Query("xuqianyear") String xuQianYear,
                                                  @Query("operation_peopleid") String operationPeopleID,
                                                  @Query("operation_name") String operationName);

    //获取专家坐诊列表信息
    @GET("fds_bak/httpServer")
    Call<SpecialistInfoBean> getSpecialistInfo(@Query("requesttype") String requestType,
                                               @Query("jgdm") String jgdm);

    //专家坐诊数据详情（PDF形式）
    @GET("fds_bak/httpServer")
    Call<ResponseBody> getSpecialistDetail(@Query("requesttype") String requestType,
                                           @Query("noticeid") String noticeID);

    //找回密码（重置密码）
    @GET("fds_bak/httpServer")
    Call<ResetPasswordBean> resetPassword(@Query("requesttype") String requestType,
                                          @Query("phone") String phone,
                                          @Query("smsnum") String smsNum,
                                          @Query("password") String password);

    //修改密码
    @GET("fds_bak/httpServer")
    Call<ModifyPasswordBean> modifyPassword(@Query("requesttype") String requestType,
                                            @Query("phone") String phone,
                                            @Query("idnum") String idNum,
                                            @Query("password_new") String passwordNew,
                                            @Query("password_old") String passwordOld);

    //我的叫号
    @GET
    Call<MyQueueBean> getMyQueue(@Url String url,
                                 @Query("patname") String patName);

    //轮播图
    @GET("fds_bak/httpServer")
    Call<LunBoBean> getLunbo(@Query("requesttype") String requesttype);

    //门诊科室叫号
    @GET
    Call<DepartmentQueueBean> getDepartmentQueue(@Url String url,
                                                 @Query("hosname") String hosName);

    //通知>评价
    @GET("fds_bak/httpServer")
    Call<NotificationEvaluateBean> getNotificationEvaluate(@Query("requesttype") String requestType,
                                                           @Query("record_jzlsh") String record_jzlsh,
                                                           @Query("record_jgdm") String record_jgdm,
                                                           @Query("pat_idcard") String pat_idcard);

    //家人关系解除
    @GET("fds_bak/httpServer")
    Call<FamilyRelationBean> removeFamilyRelation(@Query("requesttype") String requestType,
                                                  @Query("patient_idcard") String patient_idcard,
                                                  @Query("relation_idcard") String relation_idcard);

    //获取家人关系选项
    @GET("fds_bak/httpServer")
    Call<RelationListBean> getRelationList(@Query("requesttype") String requestType,
                                           @Query("idcard") String idCard);

    //检查APP版本
    @GET("fds_bak/httpServer")
    Call<VersionBean> checkVersion(@Query("requesttype") String requestType);

    //提交反馈意见
    @GET("fds_bak/httpServer")
    Call<AdviceInfoBean> submitAdviceInfo(@Query("requesttype") String requestType,
                                          @Query("content") String content,
                                          @Query("phone") String phone,
                                          @Query("idcard") String idCard,
                                          @Query("name") String name);

    //修改或添加信息（市民卡，家庭住址，民族）
    @GET("fds_bak/httpServer")
    Call<ModifyInformationBean> getModifyInformation(@Query("requesttype") String requestType,
                                                     @Query("idcard") String idcard,
                                                     @Query("param") String param,
                                                     @Query("type") String type);
}
