package com.boge.logserver.constant;

import java.util.HashMap;
import java.util.Map;

public enum StmpResultEnum implements IntEnum<StmpResultEnum> {
    success(0, "请求成功"),
    requestTimedOut(2, "请求超时，请稍后再试!"),
    repeatedSubmit(2, "您已重复提交多次，请稍后再试!"),
    returnNull(2, "网络繁忙，请稍后再试!"),
    exception(500, "服务器异常"),
    apiException(600, "API服务器异常"),
    loginError(101, "用户名或密码错误"),
    pleaseLogin(102, "用户尚未登录"),
    nameIsNot(103, "用户名不能为空"),
    passWordIsNot(104, "密码不能为空"),
    loginOutError(105, "退出登录失败"),
    tokenIsNull(106, "token不能为空"),
    fail(1, "请求失败"),
    insertError(102, "新增失败"),
    updateError(107, "更新失败"),
    telephoneIsNull(143, "手机不能为空"),
    usernameIsSave(149, "用户登录名已存在"),
    pageNumIsNull(151, "请求页数不能为空"),
    pageSizeIsNull(152, "每页数量不能为空"),
    passwordIsError(155, "密码错误"),
    newPassWordIsNull(156, "新密码不能为空"),
    oldPassWordIsNull(157, "原始密码不能为空"),
    passWordFormError(158, "密码格式错误"),
    deleteError(170, "删除失败"),
    mailFormatIncorrect(180, "邮箱格式不正确"),
    phoneFormatIncorrect(181, "手机格式不正确"),
    IDFormatIncorrect(182, "身份证格式不正确"),
    sendCodeError(183, "发送失败"),
    codeIsNull(184, "验证码不能为空"),
    codeIsError(185, "验证码错误，请重新输入"),
    typeIsNull(186, "验证码类型不能为空"),
    updatePassError(187, "密码修改失败"),
    passWordLenghError(188, "密码长度不能低于八位"),
    approveError(189, "当前节点的指标未全部OK"),
    reportNotALL(190, "提交失败，未填报完成"),
    reportError(191, "提交失败"),
    roleIsNot(120, "操作权限不足"),
    mobileIsNull(121, "用户不存在"),
	indexIdIsNull(122, "年度不能为空"),
    orgIdIsNull(123, "组织不能为空"),
	 indexReportIsNull(200, "当前评价年度没有单位填报"),
    revenueIsNull(201, "当前评价年度没有填报主营业务收入"),
    profitIsNull(202, "当前评价年度没有填报利润总额"),
    TotalNumberOfEmployeesIsNull(203, "当前评价年度没有填报职工总人数"),
    ReportContentIsNull(204, "填报内容不能为空"),
    ReportIdIsNull(205, "指标不能为空"),
    itemIdIsNull(206, "一级指标不能为空"),
    commentsIsNull(207, "审核意见不能为空"),
    indexApproveOpinionIsNull(209, "审核结果不能为空"),
    scoreError(110, "格式不正确，分值为整数"),
    parameterFormat(211, "数据格式错误"),
    contentTypeCodeIsNull(212, "数据类型不能为空"),
    node1(213, "尚未填报完毕"),
    node2(214, "尚未审核完毕"),
    updateRole(215, "非NG人员，不能修改"),
    ProveOpinion(216, "请验证当前单位是否是集团审核阶段"),
	;
    private int value;
    private String name;


    static Map<Integer, LogLevel> enumMap = new HashMap<>();

    static {
        for (LogLevel type : LogLevel.values()) {
            enumMap.put(type.getValue(), type);
        }
    }

    StmpResultEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LogLevel getEnum(int value) {
        return enumMap.get(value);
    }
}
