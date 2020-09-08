package com.aws.docdb.docdb;

import java.util.List;

public class User {

    private String _id;
    private int infoProgress;
    private Long userId;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getInfoProgress() {
        return infoProgress;
    }

    public void setInfoProgress(int infoProgress) {
        this.infoProgress = infoProgress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List<String> preferenceList) {
        this.preferenceList = preferenceList;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    private List<String> preferenceList;
    private int sex;
    private String nickName;
    private String headPic;
    private int vip;




}
