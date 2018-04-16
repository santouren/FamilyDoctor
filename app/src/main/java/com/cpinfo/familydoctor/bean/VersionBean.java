package com.cpinfo.familydoctor.bean;

/**
 * Created by CPInfo on 2017/12/7.
 */

public class VersionBean {

    /**
     * data : {"versions":2,"update_content":"","download_address":"http://122.224.116.84:9898/fds_bak/installation_package/淳医点点通.apk"}
     * stateCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int stateCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * versions : 2
         * update_content :
         * download_address : http://122.224.116.84:9898/fds_bak/installation_package/淳医点点通.apk
         */

        private int versions;
        private String update_content;
        private String download_address;

        public int getVersions() {
            return versions;
        }

        public void setVersions(int versions) {
            this.versions = versions;
        }

        public String getUpdate_content() {
            return update_content;
        }

        public void setUpdate_content(String update_content) {
            this.update_content = update_content;
        }

        public String getDownload_address() {
            return download_address;
        }

        public void setDownload_address(String download_address) {
            this.download_address = download_address;
        }
    }
}
