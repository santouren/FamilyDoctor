package com.cpinfo.familydoctor.http;

import android.util.Log;

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
import com.cpinfo.familydoctor.bean.MyQueueBean;
import com.cpinfo.familydoctor.bean.MyRegistrationBean;
import com.cpinfo.familydoctor.bean.NotificationEvaluateBean;
import com.cpinfo.familydoctor.bean.NotificationEvaluationBean;
import com.cpinfo.familydoctor.bean.NotificationMsgBean;
import com.cpinfo.familydoctor.bean.OrderDepartmentsBean;
import com.cpinfo.familydoctor.bean.OrderHospitalsBean;
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
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Url;

/**
 * Created by Juna on 2017/3/20.
 * 类描述：接口请求方法管理类
 */

public class ApiRequestManager {

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor(new DataLog()).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            //增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(UrlConfig.BASE_URL)
            .client(client)
            .build();

    private static ApiService apiService = retrofit.create(ApiService.class);


    //Retrofit拦截打印返回的数据的类
    private static class DataLog implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Logger.d(message);
        }
    }

    /**
     * 用户注册
     */
    public static void toRegister(String identityId, String userName, String phone, String password, String phoneCode, final RequestCallBack<UserRegisterBean> cbk) {
        Call<UserRegisterBean> call = apiService.toRegister("99", identityId, userName, phone, password, phoneCode);
        call.enqueue(new Callback<UserRegisterBean>() {
            @Override
            public void onResponse(Call<UserRegisterBean> call, Response<UserRegisterBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }

                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserRegisterBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 用户登录(通过手机号)
     */
    public static void loginByPhone(String phone, String password, final RequestCallBack<UserLoginBean> cbk) {
        Call<UserLoginBean> call = apiService.loginByPhone("994", phone, password);
        call.enqueue(new Callback<UserLoginBean>() {
            @Override
            public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 用户登录(通过身份证号)
     */
    public static void loginByIdentity(String identityId, String password, final RequestCallBack<UserLoginBean> cbk) {
        Call<UserLoginBean> call = apiService.loginByIdentity("98", identityId, password);
        call.enqueue(new Callback<UserLoginBean>() {
            @Override
            public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 用户登录(通过身份证号)(错误的)
     * 添加密码
     */
    public static void setPassword(String phone, String password, final RequestCallBack<SetPasswordBean> cbk) {
        Call<SetPasswordBean> call = apiService.setPassword("983", phone, password);
        call.enqueue(new Callback<SetPasswordBean>() {
            @Override
            public void onResponse(Call<SetPasswordBean> call, Response<SetPasswordBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SetPasswordBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 手机登录(完善身份证号和姓名信息)
     */
    public static void addInfo(String phone, String cardID, String name, final RequestCallBack<AddInfoBean> cbk) {
        Call<AddInfoBean> call = apiService.addInfo("982", phone, cardID, name);
        call.enqueue(new Callback<AddInfoBean>() {
            @Override
            public void onResponse(Call<AddInfoBean> call, Response<AddInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<AddInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取（发送）验证码
     */
    public static void getVerificationCode(String phone, String identityId, final RequestCallBack<VerificationCodeBean> cbk) {
        Call<VerificationCodeBean> call = apiService.getVerificationCode("96", phone, identityId);
        call.enqueue(new Callback<VerificationCodeBean>() {
            @Override
            public void onResponse(Call<VerificationCodeBean> call, Response<VerificationCodeBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<VerificationCodeBean> call, Throwable t) {
                cbk.onFail("网络不给力...");
            }
        });
    }

    /**
     * 获取（发送）验证码（找回密码）
     */
    public static void getVerificationForPsd(String phone, String identityId, final RequestCallBack<VerificationForPsdBean> cbk) {
        Call<VerificationForPsdBean> call = apiService.getVerificationForPsd("399", phone, identityId);
        call.enqueue(new Callback<VerificationForPsdBean>() {
            @Override
            public void onResponse(Call<VerificationForPsdBean> call, Response<VerificationForPsdBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail("验证码获取失败");
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<VerificationForPsdBean> call, Throwable t) {
                cbk.onFail("网络不给力...");
            }
        });
    }

    /**
     * 获取（发送）验证码(添加家人)
     */
    public static void getVerificationNum(String phone, String idcard, final RequestCallBack<VerificationCodeBean> cbk) {
        Call<VerificationCodeBean> call = apiService.getVerificationNum("989", phone, idcard);
        call.enqueue(new Callback<VerificationCodeBean>() {
            @Override
            public void onResponse(Call<VerificationCodeBean> call, Response<VerificationCodeBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<VerificationCodeBean> call, Throwable t) {
                cbk.onFail("网络不给力...");
            }
        });
    }

    /**
     * 获取（发送）验证码（手机登录）
     */
    public static void getVerificationCode(String phone, final RequestCallBack<VerificationCodeBean> cbk) {
        Call<VerificationCodeBean> call = apiService.getVerificationCode("987", phone);
        call.enqueue(new Callback<VerificationCodeBean>() {
            @Override
            public void onResponse(Call<VerificationCodeBean> call, Response<VerificationCodeBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<VerificationCodeBean> call, Throwable t) {
                cbk.onFail("网络不给力...");
            }
        });
    }

    /**
     * 居民版=>选择签约医院列表
     */
    public static void getCentralHospitals(final RequestCallBack<CentralHospitalBean> cbk) {
        Call<CentralHospitalBean> call = apiService.getCentralHospitals("92");
        call.enqueue(new Callback<CentralHospitalBean>() {
            @Override
            public void onResponse(Call<CentralHospitalBean> call, Response<CentralHospitalBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CentralHospitalBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>选择医生团队列表
     */
    public static void getDoctorTeams(String hospitalNum, final RequestCallBack<DoctorTeamsBean> cbk) {
        Call<DoctorTeamsBean> call = apiService.getDoctorTeams("91", hospitalNum);
        call.enqueue(new Callback<DoctorTeamsBean>() {
            @Override
            public void onResponse(Call<DoctorTeamsBean> call, Response<DoctorTeamsBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorTeamsBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>预约挂号=》选择医院（医院列表）
     */
    public static void getOrderHospitals(int startPage, int endPage, final RequestCallBack<OrderHospitalsBean> cbk) {
        Call<OrderHospitalsBean> call = apiService.getOrderHospitals("89", startPage, endPage);
        call.enqueue(new Callback<OrderHospitalsBean>() {
            @Override
            public void onResponse(Call<OrderHospitalsBean> call, Response<OrderHospitalsBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderHospitalsBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>预约挂号=》选择科室（科室列表）
     */
    public static void getOrderDepartments(String hosNum, String nodeCode, final RequestCallBack<OrderDepartmentsBean> cbk) {
        Call<OrderDepartmentsBean> call = apiService.getOrderDepartments("88", hosNum, nodeCode);
        call.enqueue(new Callback<OrderDepartmentsBean>() {
            @Override
            public void onResponse(Call<OrderDepartmentsBean> call, Response<OrderDepartmentsBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderDepartmentsBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>预约挂号=》选择科室（科室列表）
     */
    public static void getDepartments(String hosID, String orderDate, final RequestCallBack<DepartmentsBean> cbk) {
        Call<DepartmentsBean> call = apiService.getDepartments(UrlConfig.DEPARTMENT_URL, "01", hosID, "2", "0", orderDate);
        call.enqueue(new Callback<DepartmentsBean>() {
            @Override
            public void onResponse(Call<DepartmentsBean> call, Response<DepartmentsBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<DepartmentsBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>预约挂号=》搜索医院（医院列表）
     */
    public static void searchHospital(String content, final RequestCallBack<SearchHospitalBean> cbk) {
        Call<SearchHospitalBean> call = apiService.searchHospital("87", content);
        call.enqueue(new Callback<SearchHospitalBean>() {
            @Override
            public void onResponse(Call<SearchHospitalBean> call, Response<SearchHospitalBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchHospitalBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>健康档案=》门诊列表
     */
    public static void getOutpatientList(String identityId, int startItem, int endItem, final RequestCallBack<OutpatientListBean> cbk) {
        Call<OutpatientListBean> call = apiService.getOutpatientList("411", identityId, startItem, endItem);
        call.enqueue(new Callback<OutpatientListBean>() {
            @Override
            public void onResponse(Call<OutpatientListBean> call, Response<OutpatientListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<OutpatientListBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>预签约=》签约团队成员列表
     */
    public static void getSignTeamMembers(String teamId, final RequestCallBack<SignTeamMembersBean> cbk) {
        Call<SignTeamMembersBean> call = apiService.getSignTeamMembers("86", teamId);
        call.enqueue(new Callback<SignTeamMembersBean>() {
            @Override
            public void onResponse(Call<SignTeamMembersBean> call, Response<SignTeamMembersBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SignTeamMembersBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>预签约=》提交预签约的信息
     */
    public static void submitSignInfo(String patientName, String sex, String censusRegister,
                                      String patientPhone, String patientIdentityId, String signTime,
                                      String sign_Year, String disease, String town,
                                      String street, String doorplateNum, String hosNum,
                                      String nodeCode, String doctorId, String doctorName,
                                      String doctorIdentityId, String doctorPhone, String signHosName,
                                      final RequestCallBack<SubmitSignInfoBean> cbk) {
        Call<SubmitSignInfoBean> call = apiService.submitSignInfo("90", patientName, sex, censusRegister,
                patientPhone, patientIdentityId, signTime, sign_Year, disease, town, street, doorplateNum,
                hosNum, nodeCode, doctorId, doctorName, doctorIdentityId, doctorPhone, signHosName);
        call.enqueue(new Callback<SubmitSignInfoBean>() {
            @Override
            public void onResponse(Call<SubmitSignInfoBean> call, Response<SubmitSignInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SubmitSignInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>档案=》获取门诊详情单（PDF文件形式）
     */
    public static void getOutpatientDetail(String recipeNo, String jgdm, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getOutpatientDetail("84", recipeNo, jgdm);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>档案=》获取住院历史列表
     */
    public static void getHospitalizedList(String identityId, int startItem, int endItem, final RequestCallBack<HospitalizedListBean> cbk) {
        Call<HospitalizedListBean> call = apiService.getHospitalizedList("421", identityId, startItem, endItem);
        call.enqueue(new Callback<HospitalizedListBean>() {
            @Override
            public void onResponse(Call<HospitalizedListBean> call, Response<HospitalizedListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<HospitalizedListBean> call, Throwable t) {
                cbk.onFail("连接失败，请检查网络连接状态");
            }
        });
    }

    /**
     * 居民版=>档案=》获取住院病案详情（PDF形式）
     */
    public static void getHospitalizedDetail(String hospitalizedNum, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getHospitalizedDetail("85", hospitalizedNum);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 通知公告详情（PDF形式）
     */
    public static void getAnnouncementDetail(String noticeID, String idcard, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getAnnouncementDetail("967", noticeID, idcard);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>档案=》获取检查列表
     */
    public static void getInspectionList(String identityId, int startItem, int endItem, final RequestCallBack<InspectionListBean> cbk) {
        Call<InspectionListBean> call = apiService.getInspectionList("83", identityId, startItem, endItem);
        call.enqueue(new Callback<InspectionListBean>() {
            @Override
            public void onResponse(Call<InspectionListBean> call, Response<InspectionListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<InspectionListBean> call, Throwable t) {
                cbk.onFail("连接失败，请检查网络连接状态");
            }
        });
    }

    /**
     * 居民版=>档案=》获取检查单详情（PDF形式）
     */
    public static void getInspectionDetail(String jclsh, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getInspectionDetail("82", jclsh);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>档案=》获取检验列表
     */
    public static void getExamineList(String identityId, int startItem, int endItem, final RequestCallBack<ExamineListBean> cbk) {
        Call<ExamineListBean> call = apiService.getExamineList("81", identityId, startItem, endItem);
        call.enqueue(new Callback<ExamineListBean>() {
            @Override
            public void onResponse(Call<ExamineListBean> call, Response<ExamineListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExamineListBean> call, Throwable t) {
                cbk.onFail("连接失败，请检查网络连接状态");
            }
        });
    }

    /**
     * 居民版=>档案=》获取检验报告详情（PDF形式）
     */
    public static void getExamineDetail(String reportNo, String hospitalNo, String brbslb, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getExamineDetail("80", reportNo, hospitalNo, brbslb);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>档案=》获取健康体检列表
     */
    public static void getPhysicalList(String identityId, int startItem, int endItem, final RequestCallBack<PhysicalListBean> cbk) {
        Call<PhysicalListBean> call = apiService.getPhysicalList("79", identityId, startItem, endItem);
        call.enqueue(new Callback<PhysicalListBean>() {
            @Override
            public void onResponse(Call<PhysicalListBean> call, Response<PhysicalListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhysicalListBean> call, Throwable t) {
                cbk.onFail("连接失败，请检查网络连接状态");
            }
        });
    }

    /**
     * 居民版=>档案=》获取健康体检报告详情（PDF形式）
     */
    public static void getPhysicalDetail(String pexamId, String pexamJgdm, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getPhysicalDetail("78", pexamId, pexamJgdm);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 居民版=>档案=》获取电子病历三级列表（住院报告）
     */
    public static void getPatientHistoryReports(String inpNo, final RequestCallBack<PatientHistoryReportsBean> cbk) {
        Call<PatientHistoryReportsBean> call = apiService.getPatientHistoryReports("77", inpNo);
        call.enqueue(new Callback<PatientHistoryReportsBean>() {
            @Override
            public void onResponse(Call<PatientHistoryReportsBean> call, Response<PatientHistoryReportsBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PatientHistoryReportsBean> call, Throwable t) {
                cbk.onFail("连接失败，请检查网络连接状态");
            }
        });
    }

    /**
     * 居民版=>档案=》获取电子病历四级页面（住院报告详情页面）（PDF形式）
     */
    public static void PatientHistoryReportDetail(String reportName, String mrId, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.PatientHistoryReportDetail("76", reportName, mrId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 家庭档案（家庭成员信息）
     */
    public static void getFamilyInfo(String cardId, final RequestCallBack<FamilyMemberBean> cbk) {
        Call<FamilyMemberBean> call = apiService.getFamilyInfo("991", cardId);
        call.enqueue(new Callback<FamilyMemberBean>() {
            @Override
            public void onResponse(Call<FamilyMemberBean> call, Response<FamilyMemberBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<FamilyMemberBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 家庭档案（添加家庭成员）
     */
    public static void addFamilyMember(String selfCardId, String relation, String memberName,
                                       String citizenCard, String nation, String home_address,
                                       String memberCardId, String phoneNum, String age, String sex,
                                       String verificationCode, final RequestCallBack<AddFamilyMemberBean> cbk) {
        Call<AddFamilyMemberBean> call = apiService.addFamilyMember("990", selfCardId, relation, memberName, citizenCard, nation, home_address, memberCardId, phoneNum, age, sex, verificationCode);
        call.enqueue(new Callback<AddFamilyMemberBean>() {
            @Override
            public void onResponse(Call<AddFamilyMemberBean> call, Response<AddFamilyMemberBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<AddFamilyMemberBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 预签约（获取城镇，街道列表数据）
     */
    public static void getCityList(final RequestCallBack<CityListBean> cbk) {
        Call<CityListBean> call = apiService.getCityList("981");
        call.enqueue(new Callback<CityListBean>() {
            @Override
            public void onResponse(Call<CityListBean> call, Response<CityListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CityListBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取医保政策的数据
     */
    public static void getHealthInsurance(final RequestCallBack<HealthInsuranceBean> cbk) {
        Call<HealthInsuranceBean> call = apiService.getHealthInsurance("976");
        call.enqueue(new Callback<HealthInsuranceBean>() {
            @Override
            public void onResponse(Call<HealthInsuranceBean> call, Response<HealthInsuranceBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<HealthInsuranceBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取通知消息的数据
     */
    public static void getNotificationMsg(String idNum, String state, final RequestCallBack<NotificationMsgBean> cbk) {
        Call<NotificationMsgBean> call = apiService.getNotificationMsg("975", idNum, state);
        call.enqueue(new Callback<NotificationMsgBean>() {
            @Override
            public void onResponse(Call<NotificationMsgBean> call, Response<NotificationMsgBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationMsgBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取医生排班的数据
     */
    public static void getDoctorArrange(String hospitalIP, String date, String deptNo, final RequestCallBack<DoctorArrangeBean> cbk) {
        Call<DoctorArrangeBean> call = apiService.getDoctorArrange(UrlConfig.DEPARTMENT_URL, "02", hospitalIP, date, "0", deptNo, "2");
        call.enqueue(new Callback<DoctorArrangeBean>() {
            @Override
            public void onResponse(Call<DoctorArrangeBean> call, Response<DoctorArrangeBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorArrangeBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取医生号源的数据
     */
    public static void getDoctorOrderNo(String hospitalIP, String date, String deptNo, String hy_ghbc,String hy_ysdm, final RequestCallBack<DoctorOrderNoBean> cbk) {
        Call<DoctorOrderNoBean> call = apiService.getDoctorOrderNo(UrlConfig.DEPARTMENT_URL, "03", hospitalIP, date, "0", deptNo, "2", hy_ghbc,hy_ysdm);
        call.enqueue(new Callback<DoctorOrderNoBean>() {
            @Override
            public void onResponse(Call<DoctorOrderNoBean> call, Response<DoctorOrderNoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctorOrderNoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取医生号源的数据
     */
    public static void getMyRegistration(String identityId, final RequestCallBack<MyRegistrationBean> cbk) {
        Call<MyRegistrationBean> call = apiService.getMyRegistration(UrlConfig.DEPARTMENT_URL, "05", identityId);
        call.enqueue(new Callback<MyRegistrationBean>() {
            @Override
            public void onResponse(Call<MyRegistrationBean> call, Response<MyRegistrationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyRegistrationBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 取消挂号
     */
    public static void cancleRegistration(String jiuZhenKaHao,
                                          String zhengJianHaoMa,
                                          String patientName,
                                          String yuYueLeiXing,
                                          String qvHaoMiMa,
                                          String hosName,
                                          String uuid,
                                          final RequestCallBack<CancleRegistrationBean> cbk) {
        Call<CancleRegistrationBean> call = apiService.cancleRegistration(UrlConfig.DEPARTMENT_URL, "06",
                "3", jiuZhenKaHao, "1", zhengJianHaoMa, patientName, yuYueLeiXing,
                qvHaoMiMa, hosName, uuid);
        call.enqueue(new Callback<CancleRegistrationBean>() {
            @Override
            public void onResponse(Call<CancleRegistrationBean> call, Response<CancleRegistrationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CancleRegistrationBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 确认挂号
     */
    public static void goRegistration(String hosplitalIP,
                                      String zhengJianHaoMa,
                                      String patientName,
                                      String yiZhouPaiBanID,
                                      String dangTianPaiBanID,
                                      String date,
                                      String guaHaoBanCi,
                                      String guaHaoLeiBie,
                                      String keShiDaiMa,
                                      String yiShengDaiMa,
                                      String guaHaoXuHao,
                                      String yuYueLaiYuan,
                                      String patientPhone,
                                      String sex,
                                      String hosName,
                                      String deptName,
                                      String doctorName,
                                      String deptAddress,
                                      String zhenLiaoFei,
                                      String yiShenZhiCheng,
                                      String jiuzhenkh,
                                      String jiatingzz,
                                      String minzu,
                                      String yuyueren,
                                      final RequestCallBack<GoRegistrationBean> cbk) {
        Call<GoRegistrationBean> call = apiService.goRegistration(
                UrlConfig.DEPARTMENT_URL,
                "12",
                hosplitalIP,
                "3",
                "1",
                zhengJianHaoMa,
                patientName,
                yiZhouPaiBanID,
                dangTianPaiBanID,
                date,
                guaHaoBanCi,
                guaHaoLeiBie,
                keShiDaiMa,
                yiShengDaiMa,
                guaHaoXuHao,
                yuYueLaiYuan,
                patientPhone,
                sex,
                hosName,
                deptName,
                doctorName,
                deptAddress,
                zhenLiaoFei,
                yiShenZhiCheng,
                jiuzhenkh,
                jiatingzz,
                minzu,
                yuyueren);
        call.enqueue(new Callback<GoRegistrationBean>() {
            @Override
            public void onResponse(Call<GoRegistrationBean> call, Response<GoRegistrationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<GoRegistrationBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取主页健康资讯
     */
    public static void getHomeNews(final RequestCallBack<HomeNewsBean> cbk) {
        Call<HomeNewsBean> call = apiService.getHomeNews("978");
        call.enqueue(new Callback<HomeNewsBean>() {
            @Override
            public void onResponse(Call<HomeNewsBean> call, Response<HomeNewsBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeNewsBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取云影像（PDF形式）
     */
    public static void getCloudImage(String jcbh, String region, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getCloudImage(UrlConfig.DEPARTMENT_URL, "1", jcbh, "localhidden", region);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取就诊记录
     */
    public static void getMedicalRecord(String doctorIdcard, String patientIdcard, int startPage, int endPage, final RequestCallBack<MedicalRecordBean> cbk) {
        Call<MedicalRecordBean> call = apiService.getMedicalRecord("974", doctorIdcard, patientIdcard, startPage, endPage);
        call.enqueue(new Callback<MedicalRecordBean>() {
            @Override
            public void onResponse(Call<MedicalRecordBean> call, Response<MedicalRecordBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<MedicalRecordBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取就诊记录
     */
    public static void getContractEvaluation(String idcard, final RequestCallBack<ContractEvaluationBean> cbk) {
        Call<ContractEvaluationBean> call = apiService.getContractEvaluation("973", idcard);
        call.enqueue(new Callback<ContractEvaluationBean>() {
            @Override
            public void onResponse(Call<ContractEvaluationBean> call, Response<ContractEvaluationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ContractEvaluationBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取评价信息
     */
    public static void getEvaluationInfo(String idcard, String uuid, final RequestCallBack<EvaluationInfoBean> cbk) {
        Call<EvaluationInfoBean> call = apiService.getEvaluationInfo("971", idcard, uuid);
        call.enqueue(new Callback<EvaluationInfoBean>() {
            @Override
            public void onResponse(Call<EvaluationInfoBean> call, Response<EvaluationInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<EvaluationInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 提交评价信息
     */
    public static void submitEvaluationInfo(String patient_name,
                                            String patient_idcard,
                                            String grade_uuid,
                                            String grade_time,
                                            String grad_type,
                                            int hospital_environment_grade,
                                            String hospital_environment_content,
                                            int doctor_power_grade,
                                            String doctor_power_content,
                                            int doctor_manner_grade,
                                            String doctor_manner_content,
                                            int nurse_manner_grade,
                                            String nurse_manner_content,
                                            String other_content,
                                            final RequestCallBack<SubmitEvaluationInfoBean> cbk) {
        Call<SubmitEvaluationInfoBean> call = apiService.submitEvaluationInfo("972",
                patient_name, patient_idcard, grade_uuid, grade_time, grad_type, hospital_environment_grade,
                hospital_environment_content, doctor_power_grade, doctor_power_content, doctor_manner_grade,
                doctor_manner_content, nurse_manner_grade, nurse_manner_content, other_content);
        call.enqueue(new Callback<SubmitEvaluationInfoBean>() {
            @Override
            public void onResponse(Call<SubmitEvaluationInfoBean> call, Response<SubmitEvaluationInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SubmitEvaluationInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 提交通知评价
     */
    public static void submitNotificationEvaluation(String record_jzlsh,
                                                    String record_jgdm,
                                                    String patient_name,
                                                    String patient_idcard,
                                                    int hospital_environment_grade,
                                                    String hospital_environment_content,
                                                    int doctor_power_grade,
                                                    String doctor_power_content,
                                                    int doctor_manner_grade,
                                                    String doctor_manner_content,
                                                    String other_content,
                                                    final RequestCallBack<NotificationEvaluationBean> cbk) {
        Call<NotificationEvaluationBean> call = apiService.submitNotificationEvaluation("968",
                record_jzlsh, record_jgdm, patient_name, patient_idcard, hospital_environment_grade,
                hospital_environment_content, doctor_power_grade, doctor_power_content, doctor_manner_grade,
                doctor_manner_content, other_content);
        call.enqueue(new Callback<NotificationEvaluationBean>() {
            @Override
            public void onResponse(Call<NotificationEvaluationBean> call, Response<NotificationEvaluationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationEvaluationBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取宣教信息
     */
    public static void getPublicityInfo(final RequestCallBack<PublicityInfoBean> cbk) {
        Call<PublicityInfoBean> call = apiService.getPublicityInfo("979");
        call.enqueue(new Callback<PublicityInfoBean>() {
            @Override
            public void onResponse(Call<PublicityInfoBean> call, Response<PublicityInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PublicityInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取县内转诊信息
     */
    public static void getCountyInReferral(String idCard, final RequestCallBack<CountyInReferralBean> cbk) {
        Call<CountyInReferralBean> call = apiService.getCountyInReferral(UrlConfig.DEPARTMENT_URL, "09", idCard);
        call.enqueue(new Callback<CountyInReferralBean>() {
            @Override
            public void onResponse(Call<CountyInReferralBean> call, Response<CountyInReferralBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CountyInReferralBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取县外转诊信息
     */
    public static void getCountyOutReferral(String idCard, final RequestCallBack<CountyOutReferralBean> cbk) {
        Call<CountyOutReferralBean> call = apiService.getCountyOutReferral(UrlConfig.DEPARTMENT_URL, "08", idCard);
        call.enqueue(new Callback<CountyOutReferralBean>() {
            @Override
            public void onResponse(Call<CountyOutReferralBean> call, Response<CountyOutReferralBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<CountyOutReferralBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取预签约信息
     */
    public static void getBeforehandContractInfo(String idNum, final RequestCallBack<BeforehandContractInfoBean> cbk) {
        Call<BeforehandContractInfoBean> call = apiService.getBeforehandContractInfo("984", idNum, "预签约");
        call.enqueue(new Callback<BeforehandContractInfoBean>() {
            @Override
            public void onResponse(Call<BeforehandContractInfoBean> call, Response<BeforehandContractInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<BeforehandContractInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取续约显示的信息
     */
    public static void getExtensionContractInfo(String idNum, final RequestCallBack<ExtensionContractInfoBean> cbk) {
        Call<ExtensionContractInfoBean> call = apiService.getExtensionContractInfo("984", idNum, "正常");
        call.enqueue(new Callback<ExtensionContractInfoBean>() {
            @Override
            public void onResponse(Call<ExtensionContractInfoBean> call, Response<ExtensionContractInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExtensionContractInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取预签约记录
     */
    public static void getContractRecord(String idCard, final RequestCallBack<ContractRecordBean> cbk) {
        Call<ContractRecordBean> call = apiService.getContractRecord("970", idCard);
        call.enqueue(new Callback<ContractRecordBean>() {
            @Override
            public void onResponse(Call<ContractRecordBean> call, Response<ContractRecordBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ContractRecordBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 解除预签约
     */
    public static void relieveContract(String idCard, final RequestCallBack<RelieveContractBean> cbk) {
        Call<RelieveContractBean> call = apiService.relieveContract("985", idCard);
        call.enqueue(new Callback<RelieveContractBean>() {
            @Override
            public void onResponse(Call<RelieveContractBean> call, Response<RelieveContractBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RelieveContractBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 续约
     */
    public static void extensionContract(String uuid, String xuQianYear, String operationPeopleID, String operationName, final RequestCallBack<ExtensionContractBean> cbk) {
        Call<ExtensionContractBean> call = apiService.extensionContract(UrlConfig.DEPARTMENT_URL, "07", uuid, xuQianYear, operationPeopleID, operationName);
        call.enqueue(new Callback<ExtensionContractBean>() {
            @Override
            public void onResponse(Call<ExtensionContractBean> call, Response<ExtensionContractBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ExtensionContractBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取专家坐诊列表信息
     */
    public static void getSpecialistInfo(String jgdm, final RequestCallBack<SpecialistInfoBean> cbk) {
        Call<SpecialistInfoBean> call = apiService.getSpecialistInfo("966", jgdm);
        call.enqueue(new Callback<SpecialistInfoBean>() {
            @Override
            public void onResponse(Call<SpecialistInfoBean> call, Response<SpecialistInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<SpecialistInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 专家坐诊数据详情（PDF形式）
     */
    public static void getSpecialistDetail(String noticeID, final RequestCallBack<ResponseBody> cbk) {
        Call<ResponseBody> call = apiService.getSpecialistDetail("965", noticeID);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        cbk.onSuccess(response.body());
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 找回密码（重置密码）
     */
    public static void resetPassword(String phone, String smsNum, String password, final RequestCallBack<ResetPasswordBean> cbk) {
        Call<ResetPasswordBean> call = apiService.resetPassword("400", phone, smsNum, password);
        call.enqueue(new Callback<ResetPasswordBean>() {
            @Override
            public void onResponse(Call<ResetPasswordBean> call, Response<ResetPasswordBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 修改密码
     */
    public static void modifyPassword(String phone, String idNum, String passwordNew, String passwordOld, final RequestCallBack<ModifyPasswordBean> cbk) {
        Call<ModifyPasswordBean> call = apiService.modifyPassword("977",phone, idNum, passwordNew, passwordOld);
        call.enqueue(new Callback<ModifyPasswordBean>() {
            @Override
            public void onResponse(Call<ModifyPasswordBean> call, Response<ModifyPasswordBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModifyPasswordBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 轮播
     */
    public static void getLunBo(final RequestCallBack<LunBoBean> lb) {
        Call<LunBoBean> call = apiService.getLunbo("961");
        call.enqueue(new Callback<LunBoBean>() {
            @Override
            public void onResponse(Call<LunBoBean> call, Response<LunBoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            lb.onSuccess(response.body());
                        } else {
                            lb.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        lb.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<LunBoBean> call, Throwable t) {
                lb.onFail(t.getMessage());
            }
        });
    }
//    public static void getLunBo(final RequestCallBack<LunBoBean> lb) {
//        Call<ResponseBody> call = apiService.getLunbo("961");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                //联网成功
//                if (response.isSuccessful()) {
//                    try {
//                        String result = response.body().string();
//                        Log.e("--------result:", result);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    //联网失败
//                } else {
//                    Log.e("--------result:", "失败");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
//
//            }
//        });
//    }

    /**
     * 我的叫号
     */
    public static void getMyQueue(String patName, final RequestCallBack<MyQueueBean> cbk) {
        Call<MyQueueBean> call = apiService.getMyQueue(UrlConfig.QUEUE_URL, patName);
        call.enqueue(new Callback<MyQueueBean>() {
            @Override
            public void onResponse(Call<MyQueueBean> call, Response<MyQueueBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<MyQueueBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 门诊科室叫号
     */
    public static void getDepartmentQueue(final RequestCallBack<DepartmentQueueBean> cbk) {
        Call<DepartmentQueueBean> call = apiService.getDepartmentQueue("http://fds.qdhws.gov.cn:8890/hqs/appHospital/appHospitalQueueImpl.htm", "淳安县第一人民医院");
        call.enqueue(new Callback<DepartmentQueueBean>() {
            @Override
            public void onResponse(Call<DepartmentQueueBean> call, Response<DepartmentQueueBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<DepartmentQueueBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 通知>评价
     */
    public static void getNotificationEvaluate(String record_jzlsh, String record_jgdm, String pat_idcard, final RequestCallBack<NotificationEvaluateBean> cbk) {
        Call<NotificationEvaluateBean> call = apiService.getNotificationEvaluate("969", record_jzlsh, record_jgdm, pat_idcard);
        call.enqueue(new Callback<NotificationEvaluateBean>() {
            @Override
            public void onResponse(Call<NotificationEvaluateBean> call, Response<NotificationEvaluateBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<NotificationEvaluateBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 家人关系解除
     */
    public static void removeFamilyRelation(String patient_idcard, String relation_idcard, final RequestCallBack<FamilyRelationBean> cbk) {
        Call<FamilyRelationBean> call = apiService.removeFamilyRelation("988", patient_idcard, relation_idcard);
        call.enqueue(new Callback<FamilyRelationBean>() {
            @Override
            public void onResponse(Call<FamilyRelationBean> call, Response<FamilyRelationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<FamilyRelationBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 获取家人关系选项
     */
    public static void getRelationList(String idCard, final RequestCallBack<RelationListBean> cbk) {
        Call<RelationListBean> call = apiService.getRelationList("964", idCard);
        call.enqueue(new Callback<RelationListBean>() {
            @Override
            public void onResponse(Call<RelationListBean> call, Response<RelationListBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStatCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<RelationListBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 检查APP版本
     */
    public static void checkVersion(final RequestCallBack<VersionBean> cbk) {
        Call<VersionBean> call = apiService.checkVersion("963");
        call.enqueue(new Callback<VersionBean>() {
            @Override
            public void onResponse(Call<VersionBean> call, Response<VersionBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<VersionBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 提交反馈意见
     */
    public static void submitAdviceInfo(String content, String phone, String idCard, String name, final RequestCallBack<AdviceInfoBean> cbk) {
        Call<AdviceInfoBean> call = apiService.submitAdviceInfo("962", content, phone, idCard, name);
        call.enqueue(new Callback<AdviceInfoBean>() {
            @Override
            public void onResponse(Call<AdviceInfoBean> call, Response<AdviceInfoBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            cbk.onSuccess(response.body());
                        } else {
                            cbk.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        cbk.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<AdviceInfoBean> call, Throwable t) {
                cbk.onFail(t.getMessage());
            }
        });
    }

    /**
     * 修改或添加信息（市民卡，家庭住址，民族）
     */
    public static void modifyInformation(String idcard, String param, String type, final RequestCallBack<ModifyInformationBean> mib) {
        Call<ModifyInformationBean> call = apiService.getModifyInformation("959", idcard, param, type);
        call.enqueue(new Callback<ModifyInformationBean>() {
            @Override
            public void onResponse(Call<ModifyInformationBean> call, Response<ModifyInformationBean> response) {
                if (response != null) {
                    if (response.errorBody() == null && response.body() != null) {
                        if (response.body().getStateCode() == 0) {
                            mib.onSuccess(response.body());
                        } else {
                            mib.onFail(response.body().getErrorMsg());
                        }
                    } else {
                        mib.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ModifyInformationBean> call, Throwable t) {
                mib.onFail(t.getMessage());
            }
        });
    }
}
