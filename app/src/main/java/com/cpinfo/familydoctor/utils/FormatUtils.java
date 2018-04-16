package com.cpinfo.familydoctor.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoshu on 2016/5/26.
 * 正则表达式工具类
 */
public class FormatUtils {
    /**
     * 是否符合手机号码格式
     *
     * @param phone
     * @return
     */
    public static boolean isMobilePhoneNum(String phone) {
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 是否符合邮箱地址格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 是否符合身份证号码格式
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        String strPattern = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(idCard);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 是否符合yyyy-mm-dd的生日格式
     *
     * @param birthday
     * @return
     */
    public static boolean isBirthday(String birthday) {
        String strPattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(birthday);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断输入密码是否含有非法字符
     *
     * @param password
     * @return
     */
    public static boolean isPassWordLegal(String password) {
        char[] array = password.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0x7f) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是银行卡号,采用Luhn算法
     *
     * @param cardId
     * @return
     */
    public static boolean isBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;

    }

    /**
     * luhn算法
     * 1、从卡号最后一位数字开始,偶数位乘以2,如果乘以2的结果是两位数，将结果减去9。
     * 2、把所有数字相加,得到总和。
     * 3、如果信用卡号码是合法的，总和可以被10整除。
     *
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhnSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhnSum += k;
        }
        return (luhnSum % 10 == 0) ? '0' : (char) ((10 - luhnSum % 10) + '0');
    }

    /**
     * 从字符串中获取日期
     *
     * @return
     */
    public static String getDateFromString(String content) {
        try {
            String strPattern = "DB_PoliceOfficeWork_(\\d{4}-\\d{2}-\\d{2}).db";
            Pattern pattern = Pattern.compile(strPattern);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据模式从原文中截取满足条件的一部分字符串
     *
     * @param strPattern
     * @param content
     * @return
     */
    public static String getOneStrFromString(String strPattern, String content) {
        try {
            Pattern pattern = Pattern.compile(strPattern);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将银行卡卡号格式化成 “NNNNNN****NNNN” 形式。
     *
     * @param cardNumber 原卡号
     * @return 格式化后的卡号
     */
    public static String formatCardNumberN6S4N4(String cardNumber) {
        if (TextUtils.isEmpty(cardNumber) || cardNumber.length() < 10)
            return "";

        int length = cardNumber.length();
        return String.format("%s******%s", cardNumber.substring(0, 6), cardNumber.substring(length - 4, length));
    }

    /**
     * 将手机号码格式化成 “NNN****NNNN” 形式。
     *
     * @param phoneNumber 原手机号码
     * @return 格式化后的手机号码
     */
    public static String formatPhoneNumberN3S4N4(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber))
            return "";
        if (!isMobilePhoneNum(phoneNumber))
            return "";
        return String.format("%s****%s", phoneNumber.substring(0, 3), phoneNumber
                .substring(phoneNumber.length() - 4, phoneNumber.length()));
    }

    public static String formatPrice(String price) {
        String result = price;
        if (price == null || "".equals(price.trim()) || price.equals("0.0") || price.equals("null") || price.equals("0")) {
            return "0.00";
        }
        Double dprice = Double.valueOf(price);
        if (dprice >= 10000) {
            result = getDecimal(dprice / 10000, 2) + "万";
        } else if (dprice >= 100000000) {
            result = getDecimal(dprice / 100000000, 4) + "亿";
        } else {
            result = dprice + "元";
        }
        return result;
    }

    public static String getDecimal(double as_avg, int type) {
        DecimalFormat df = new DecimalFormat("#");
        switch (type) {
            case 1:
                df = new DecimalFormat("#.#");
                break;
            case 2:
                df = new DecimalFormat("#.##");
                break;
            case 3:
                df = new DecimalFormat("#.###");
                break;
            case 4:
                df = new DecimalFormat("#.####");
                break;
            default:
                break;
        }
        return df.format(as_avg);
    }
}
