package com.boot.youzan.youzan.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

public class MemMemberDto{

    private static final long serialVersionUID = 1L;
    private String regShopCd;
    /**
     * 会员ID
     */
    private Integer memberId;

    /**
     * 会员编号
     */
    private String memberCd;

    /**
     * 会员名称
     */
    private String memberNm;

    /**
     * 性别（1：男 0：女）
     */
    private String memberSex;

    /**
     * 出生日期
     */
    private Date memberBirthday;

    /**
     * 身份证号
     */
    private String memberIdCard;

    /**
     * 微信
     */
    private String memberWechat;

    /**
     * 微信公众号openID
     */
    private String memberWechatOpenid;

    /**
     * 阿里openID
     */
    private String aliOpenid;

    /**
     * 微信小程序openID
     */
    private String memberWechatMpOpenid;

    /**
     * 微信unionID
     */
    private String memberWechatUnionid;
    /**
     * 微信名称(前端传递)
     */
    private String nickName;
    /**
     * 微信名称（后端存的）
     */
    private String memberWechatNickName;
    /**
     * QQ
     */
    private String memberQq;

    /**
     * 会员手机
     */
    private String memberMobile;

    /**
     * 会员固话
     */
    private String memberPhone;

    /**
     * 会员邮箱
     */
    private String memberEmail;

    /**
     * 会员等级编码
     */
    private String memberLevelCd;

    /**
     * 会员等级名称
     */
    private String memberLevelNm;

    /**
     * 等级保持时效
     */
    private Integer levelKeepDays;

    /**
     * 当前等级时限
     */
    private Date memberLevelFanalDate;

    /**
     * 注册门店编码
     */
    private String memberShopCd;

    /**
     * 注册门店名称
     */
    private String memberShopNm;

    /**
     * 办理日期
     */
    private Date joinDate;

    /**
     * 加入方式（0001：朋友圈 0002：多媒体 0003：公众号）
     */
    private String joinType;

    /**
     * 收入水平
     */
    private String memberIncome;

    /**
     * 消费能力（0001：低 0002：一般 0003：高）
     */
    private String consumeAbility;

    /**
     * 职业
     */
    private String memberVocation;

    /**
     * 会员标签
     */
    private String memberTag;

    /**
     * 地址（省）编码
     */
    private String provinceCd;

    /**
     * 地址（省）名称
     */
    private String povinceNm;

    /**
     * 地址（市）编码
     */
    private String cityCd;

    /**
     * 地址（市）名称
     */
    private String cityNm;

    /**
     * 地址（区）编码
     */
    private String areaCd;

    /**
     * 地址（区）名称
     */
    private String areaNm;

    /**
     * 地址
     */
    private String addr;

    /**
     * 注册推介店员编码
     */
    private String optUserCd;

    /**
     * 注册推介店员名称
     */
    private String optUserNm;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属组织编码
     */
    private String orgCd;

    /**
     * 是否可用（0：不可用；1：可用）
     */
    private String isActive;

    /**
     * 创建人编码
     */
    private String createBy;

    /**
     * 创建人姓名
     */
    private String createNm;

    /**
     * 创建时间
     */
    private Date createDt;

    /**
     * 修改人编码
     */
    private String updateBy;

    /**
     * 修改人姓名
     */
    private String updateNm;

    /**
     * 修改时间
     */
    private Long updateDt;
    /**
     * 上传附件的id
     */
    private String billAtt;
    /**
     * 会员头像，uuid格式的图片地址
     */
    private String memberPicUrl;
    /**
     * 默认门店
     */
    private String defaultStore;
    /**
     * 当前会员积分（临时）
     */
    private Integer usableIntegralTmp;

    /**
     * 储值可用余额（临时）
     */
    private BigDecimal usableRechargeBalance;
    /**
     * 优惠券数量
     */
    private Integer couponCount;

    /***

     /**
     * 微信推荐人
     */
    private String wechatRecommendNm;

    /**
     * 微信关注时间
     */
    private Date wechatJoinData;

    /**
     * 微信推荐人编码
     */
    private String wechatRecommendBy;

    /**
     * 阿里推荐人
     */
    private String aliRecommendNm;

    /**
     * 阿里关注时间
     */
    private Date aliJoinData;

    /**
     * 阿里推荐人编码
     */
    private String aliRecommendBy;
    /**
     * 阿里号
     */
    private String memberAli;


    /**
     * 渠道类型（0001：HX 0002：宿镜 0003：1985）
     */
    private String channelType;

    /**
     * cardId
     */
    private String cardId;
    /**
     * 查询类型：老会员：oldUser   新激活用户：newUser
     */
    private String selectType;
    /**
     *  @desc 同步类型
     */
    private String syncType;
    /**
     *  @desc 有赞商城账号
     */
    private String youZanAccountId;

    public String getRegShopCd() {
        return regShopCd;
    }

    public void setRegShopCd(String regShopCd) {
        this.regShopCd = regShopCd;
    }

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

    public Date getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(Date memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

    public String getMemberIdCard() {
        return memberIdCard;
    }

    public void setMemberIdCard(String memberIdCard) {
        this.memberIdCard = memberIdCard;
    }

    public String getMemberWechat() {
        return memberWechat;
    }

    public void setMemberWechat(String memberWechat) {
        this.memberWechat = memberWechat;
    }

    public String getMemberWechatOpenid() {
        return memberWechatOpenid;
    }

    public void setMemberWechatOpenid(String memberWechatOpenid) {
        this.memberWechatOpenid = memberWechatOpenid;
    }

    public String getAliOpenid() {
        return aliOpenid;
    }

    public void setAliOpenid(String aliOpenid) {
        this.aliOpenid = aliOpenid;
    }

    public String getMemberWechatMpOpenid() {
        return memberWechatMpOpenid;
    }

    public void setMemberWechatMpOpenid(String memberWechatMpOpenid) {
        this.memberWechatMpOpenid = memberWechatMpOpenid;
    }

    public String getMemberWechatUnionid() {
        return memberWechatUnionid;
    }

    public void setMemberWechatUnionid(String memberWechatUnionid) {
        this.memberWechatUnionid = memberWechatUnionid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMemberWechatNickName() {
        return memberWechatNickName;
    }

    public void setMemberWechatNickName(String memberWechatNickName) {
        this.memberWechatNickName = memberWechatNickName;
    }

    public String getMemberQq() {
        return memberQq;
    }

    public void setMemberQq(String memberQq) {
        this.memberQq = memberQq;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberLevelCd() {
        return memberLevelCd;
    }

    public void setMemberLevelCd(String memberLevelCd) {
        this.memberLevelCd = memberLevelCd;
    }

    public String getMemberLevelNm() {
        return memberLevelNm;
    }

    public void setMemberLevelNm(String memberLevelNm) {
        this.memberLevelNm = memberLevelNm;
    }

    public Integer getLevelKeepDays() {
        return levelKeepDays;
    }

    public void setLevelKeepDays(Integer levelKeepDays) {
        this.levelKeepDays = levelKeepDays;
    }

    public Date getMemberLevelFanalDate() {
        return memberLevelFanalDate;
    }

    public void setMemberLevelFanalDate(Date memberLevelFanalDate) {
        this.memberLevelFanalDate = memberLevelFanalDate;
    }

    public String getMemberShopCd() {
        return memberShopCd;
    }

    public void setMemberShopCd(String memberShopCd) {
        this.memberShopCd = memberShopCd;
    }

    public String getMemberShopNm() {
        return memberShopNm;
    }

    public void setMemberShopNm(String memberShopNm) {
        this.memberShopNm = memberShopNm;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getMemberIncome() {
        return memberIncome;
    }

    public void setMemberIncome(String memberIncome) {
        this.memberIncome = memberIncome;
    }

    public String getConsumeAbility() {
        return consumeAbility;
    }

    public void setConsumeAbility(String consumeAbility) {
        this.consumeAbility = consumeAbility;
    }

    public String getMemberVocation() {
        return memberVocation;
    }

    public void setMemberVocation(String memberVocation) {
        this.memberVocation = memberVocation;
    }

    public String getMemberTag() {
        return memberTag;
    }

    public void setMemberTag(String memberTag) {
        this.memberTag = memberTag;
    }

    public String getProvinceCd() {
        return provinceCd;
    }

    public void setProvinceCd(String provinceCd) {
        this.provinceCd = provinceCd;
    }

    public String getPovinceNm() {
        return povinceNm;
    }

    public void setPovinceNm(String povinceNm) {
        this.povinceNm = povinceNm;
    }

    public String getCityCd() {
        return cityCd;
    }

    public void setCityCd(String cityCd) {
        this.cityCd = cityCd;
    }

    public String getCityNm() {
        return cityNm;
    }

    public void setCityNm(String cityNm) {
        this.cityNm = cityNm;
    }

    public String getAreaCd() {
        return areaCd;
    }

    public void setAreaCd(String areaCd) {
        this.areaCd = areaCd;
    }

    public String getAreaNm() {
        return areaNm;
    }

    public void setAreaNm(String areaNm) {
        this.areaNm = areaNm;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getOptUserCd() {
        return optUserCd;
    }

    public void setOptUserCd(String optUserCd) {
        this.optUserCd = optUserCd;
    }

    public String getOptUserNm() {
        return optUserNm;
    }

    public void setOptUserNm(String optUserNm) {
        this.optUserNm = optUserNm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateNm() {
        return createNm;
    }

    public void setCreateNm(String createNm) {
        this.createNm = createNm;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateNm() {
        return updateNm;
    }

    public void setUpdateNm(String updateNm) {
        this.updateNm = updateNm;
    }

    public Long getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Long updateDt) {
        this.updateDt = updateDt;
    }

    public String getBillAtt() {
        return billAtt;
    }

    public void setBillAtt(String billAtt) {
        this.billAtt = billAtt;
    }

    public String getMemberPicUrl() {
        return memberPicUrl;
    }

    public void setMemberPicUrl(String memberPicUrl) {
        this.memberPicUrl = memberPicUrl;
    }

    public String getDefaultStore() {
        return defaultStore;
    }

    public void setDefaultStore(String defaultStore) {
        this.defaultStore = defaultStore;
    }

    public Integer getUsableIntegralTmp() {
        return usableIntegralTmp;
    }

    public void setUsableIntegralTmp(Integer usableIntegralTmp) {
        this.usableIntegralTmp = usableIntegralTmp;
    }

    public BigDecimal getUsableRechargeBalance() {
        return usableRechargeBalance;
    }

    public void setUsableRechargeBalance(BigDecimal usableRechargeBalance) {
        this.usableRechargeBalance = usableRechargeBalance;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getWechatRecommendNm() {
        return wechatRecommendNm;
    }

    public void setWechatRecommendNm(String wechatRecommendNm) {
        this.wechatRecommendNm = wechatRecommendNm;
    }

    public Date getWechatJoinData() {
        return wechatJoinData;
    }

    public void setWechatJoinData(Date wechatJoinData) {
        this.wechatJoinData = wechatJoinData;
    }

    public String getWechatRecommendBy() {
        return wechatRecommendBy;
    }

    public void setWechatRecommendBy(String wechatRecommendBy) {
        this.wechatRecommendBy = wechatRecommendBy;
    }

    public String getAliRecommendNm() {
        return aliRecommendNm;
    }

    public void setAliRecommendNm(String aliRecommendNm) {
        this.aliRecommendNm = aliRecommendNm;
    }

    public Date getAliJoinData() {
        return aliJoinData;
    }

    public void setAliJoinData(Date aliJoinData) {
        this.aliJoinData = aliJoinData;
    }

    public String getAliRecommendBy() {
        return aliRecommendBy;
    }

    public void setAliRecommendBy(String aliRecommendBy) {
        this.aliRecommendBy = aliRecommendBy;
    }

    public String getMemberAli() {
        return memberAli;
    }

    public void setMemberAli(String memberAli) {
        this.memberAli = memberAli;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public String getYouZanAccountId() {
        return youZanAccountId;
    }

    public void setYouZanAccountId(String youZanAccountId) {
        this.youZanAccountId = youZanAccountId;
    }
}
