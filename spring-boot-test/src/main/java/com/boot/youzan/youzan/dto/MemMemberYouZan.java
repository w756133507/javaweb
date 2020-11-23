package com.boot.youzan.youzan.dto;



public class MemMemberYouZan {
    private static final long serialVersionUID = 1L;
    private Integer memberId;

    private String memberCd;

    private String memberNm;

    private String memberSex;

    private String memberBirthday;

    private String memberMobile;

    private String youzanAccountId;

    private Long createdAt;

    private String remark;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberCd() {
        return memberCd;
    }

    public void setMemberCd(String memberCd) {
        this.memberCd = memberCd;
    }

    public String getMemberNm() {
        return memberNm;
    }

    public void setMemberNm(String memberNm) {
        this.memberNm = memberNm;
    }

    public String getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex;
    }

    public String getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(String memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getYouzanAccountId() {
        return youzanAccountId;
    }

    public void setYouzanAccountId(String youzanAccountId) {
        this.youzanAccountId = youzanAccountId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}