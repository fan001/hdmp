package com.hd.hdmp.entity.model.pojo;

/**
 * User: admin
 * Date: 16-12-16
 * Time: ����9:39
 * ͨ��΢�ŷ��ؽ��.
 */
public class Back {
    private int errcode;
    private String errmsg;
    private int msg_id;
    private Long msg_data_id;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public Long getMsg_data_id() {
        return msg_data_id;
    }

    public void setMsg_data_id(Long msg_data_id) {
        this.msg_data_id = msg_data_id;
    }
}
