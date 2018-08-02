package com.hd.hdmp.entity.model.pojo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 16-12-21
 * Time: ����9:44
 * �û��б�
 */
public class UserList {
    private int total;
    private int count;
    private List<String> openIdList;
    private String next_openId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getOpenIdList() {
        return openIdList;
    }

    public void setOpenIdList(List<String> openIdList) {
        this.openIdList = openIdList;
    }

    public String getNext_openId() {
        return next_openId;
    }

    public void setNext_openId(String next_openId) {
        this.next_openId = next_openId;
    }
}
