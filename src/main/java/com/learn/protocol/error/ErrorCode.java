package com.learn.protocol.error;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码定义类
 * <p>
 * 1000 - 9999 为系统错误码
 *
 * @author guo
 */
public enum ErrorCode {
    /**
     * 系统错误定义
     */
    NO_ERROR(200, "成功"),
    INTERNAL_SERVER_ERROR(500, "服务器错误,请联系管理员"),
    BAD_REQUEST(400, "参数有误"),
    SC_UNAUTHORIZED(401, "web-无效的token"),
    VERIFICATION_CODE_LOSE_EFFICACY(402, "web-验证码已失效"),
    NOT_FOUND(404, "路径不存在，请检查路径是否正确"),
    REPETITION(405, "记录重复"),
    NOT_PERMISSION(406, "没有权限，请联系管理员授权"),
    NOT_PERMISSION_ADD_ROLE_NOT_CREATE(407, "新增用户所选角色，不是本人创建"),
    ADMIN_NOT_DELETE(408, "系统管理员不能删除"),
    NOT_DELETE(409, "当前用户不能删除"),
    NOT_SYS_MENU_DELETE(410, "系统菜单，不能删除"),
    NOT_SYS_MENU_DELETE_SUBMENU(411, "请先删除子菜单或按钮"),
    ID_LOCK(412, "账号已被锁定,请联系管理员"),
    SCHEDULE_ERROR(413, "定时任务异常"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(414, "请查看api注意get/post等提交方式"),
    OSS_ERROR(430, "OSS异常"),

    /**
     * 错误码定义类
     * 1000 - 9999 为模块错误码
     */
    SYSTEM_UNKNOWN_ERROR(1000, "系统未知错误."),

    ;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private static Map<Integer, ErrorCode> errorMap = new HashMap<Integer, ErrorCode>();

    static {
        for (ErrorCode pec : ErrorCode.values()) {
            if (errorMap.containsKey(pec.code)) {
                throw new RuntimeException("error code[" + pec.code + "] ------com.guo.web.common.errorCode.ErrorCode");
            }
            errorMap.put(pec.getCode(), pec);
        }
    }

}
