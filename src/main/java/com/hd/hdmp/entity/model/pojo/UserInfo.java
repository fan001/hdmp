package com.hd.hdmp.entity.model.pojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 16-11-30
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public class UserInfo {
    private String userId;//
    private String subscribe;//
    private String subscribeTime;//
    private String nickname;//
    private String sex;//
    private String country;//
    private String province;//
    private String city;//
    private String language;//
    private String headImgUrl;//
    private String unionid;//ֻ
    private String remark; //
    private String groupid;//
    private List<Integer> tagid_list;//

    public String getOpenId() {
        return userId;
    }

    public void setOpenId(String userId) {
        this.userId = userId;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public List<Integer> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<Integer> tagid_list) {
        this.tagid_list = tagid_list;
    }
}
