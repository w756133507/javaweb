package com.boot.youzan.youzan.dto;  /**
    * @title: MemMemberYouZanSync
    * @projectName spring-boot-test
    * @description: TODO
    * @author PC
    * @date 2020/11/1917:49
    */

import java.time.LocalDateTime;

/**
*@Description TODO
*@Author 王泽辉
*@Date 2020/11/19 17:49
*/
public class MemMemberYouZanSync {
    private Long id;
    private String memberCd;
    private String accountId;
    private String accountType;
    private String name;
    private String mobile;
    private String areaCode;
    private String address;
    private String birthday;
    private Short gender;
    private Long ascriptionKdtId;
    private String operationType;
    private String syncType;
    private String remark;
    private String createBy;
    private LocalDateTime createAt;
    private String updateBy;
    private LocalDateTime updateAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberCd() {
        return memberCd;
    }

    public void setMemberCd(String memberCd) {
        this.memberCd = memberCd;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Long getAscriptionKdtId() {
        return ascriptionKdtId;
    }

    public void setAscriptionKdtId(Long ascriptionKdtId) {
        this.ascriptionKdtId = ascriptionKdtId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
