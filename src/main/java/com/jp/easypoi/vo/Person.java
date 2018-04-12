package com.jp.easypoi.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * Created by 张俊鹏 on 4/11/2018
 */
public class Person {

    @Excel(name = "姓名",isImportField = "true")
    private String name;

    @Excel(name = "性别", replace = {"男", "女"},isImportField = "true")
    private String sex;

    @Excel(name = "生日", exportFormat = "yyyy-MM-dd",isImportField = "true")
    private Date birthday;

    public Person(String name, String sex, Date birthday) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}