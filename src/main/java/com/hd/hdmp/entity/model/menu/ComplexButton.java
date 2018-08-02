package com.hd.hdmp.entity.model.menu;

/**
 * User: admin
 * Date: 16-11-15
 * Time: ����9:08
 * ��������button�������Ӳ˵���button
 */
public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
