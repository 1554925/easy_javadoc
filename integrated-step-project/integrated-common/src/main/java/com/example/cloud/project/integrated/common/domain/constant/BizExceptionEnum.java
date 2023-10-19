package com.example.cloud.project.integrated.common.domain.constant;

@SuppressWarnings("unused")
public enum BizExceptionEnum implements EnumService {
    /**
     * 执行成功
     */
    SUCCESS(10000000, "SUCCESS", "执行成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(10000001, "SYSTEM_ERROR", "系统异常"),

    /**
     * 校验异常
     */
    VERIFY_ERROR(10000002, "VERIFY_ERROR", "校验异常,请确认参数"),

    /**
     * 外部接口调用异常
     */
    INTERFACE_SYSTEM_ERROR(10000003, "INTERFACE_SYSTEM_ERROR", "外部接口调用异常，请联系管理员！"),

    /**
     * 业务连接处理超时
     */
    CONNECT_TIME_OUT(10000004, "CONNECT_TIME_OUT", "系统超时，请稍后再试!"),

    /**
     * 系统错误
     */
    SYSTEM_FAILURE(10000005, "SYSTEM_FAILURE", "系统错误"),

    /**
     * 参数为空
     */
    NULL_ARGUMENT(10000006, "NULL_ARGUMENT", "参数为空"),

    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT(10000007, "ILLEGAL_ARGUMENT", "非法参数"),

    /**
     * 非法配置
     */
    ILLEGAG_CONFIGURATION(10000008, "ILLEGAG_CONFIGURATION", "非法配置"),

    /**
     * 非法状态
     */
    ILLEGAL_STATE(10000009, "ILLEGAG_STATE", "非法状态"),

    /**
     * 错误的枚举编码
     */
    ENUM_CODE_ERROR(10000010, "ENUM_CODE_ERROR", "错误的枚举编码"),

    /**
     * 逻辑错误
     */
    LOGIC_ERROR(10000011, "LOGIC_ERROR", "逻辑错误"),

    /**
     * 并发异常
     */
    CONCURRENT_ERROR(10000012, "CONCURRENT_ERROR", "数据已变更"),

    /**
     * 非法操作
     */
    ILLEGAL_OPERATION(10000013, "ILLEGAL_OPERATION", "非法操作"),

    /**
     * 重复操作
     */
    REPETITIVE_OPERATION(10000014, "REPETITIVE_OPERATION", "重复操作"),

    /**
     * 无操作权限
     */
    NO_OPERATE_PERMISSION(10000015, "NO_OPERATE_PERMISSION", "无操作权限"),

    /**
     * 数据异常
     */
    DATA_ERROR(10000016, "DATA_ERROR", "数据异常"),

    /**
     * 链接异常
     */
    CONN_ERROR(10000017, "CONN_EXCE", "连接异常"),

    /**
     * 类型不匹配
     */
    TYPE_UNMATCH(10000018, "TYPE_UNMATCH", "类型不匹配"),

    /**
     * 对象类型不匹配
     */
    CLASS_UNMATCH(10000019, "CLASS_UNMATCH", "对象类型不匹配"),

    /**
     * FILE_NOT_EXIST
     */
    FILE_NOT_EXIST(10000020, "FILE_NOT_EXIST", "文件不存在"),

    INVOKE_IS_REJECT(10000021, "INVOKE_IS_REJECT", "被限制的调用"),

    /**
     * FILE_ERROR
     */
    FILE_ERROR(10000022, "FILE_ERROR", "文件操作异常"),

    NOT_LOGON_ERROR(10000023, "NOT_LOGON_ERROR", "登录失败，请重新登录"),

    ;

    /**
     * 枚举编号
     */
    private final int code;

    /**
     * 枚举编号
     */
    private final String value;

    /**
     * 枚举详情
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param code    枚举编号
     * @param value   枚举值
     * @param message 枚举详情
     */
    BizExceptionEnum(int code, String value, String message) {
        this.code = code;
        this.value = value;
        this.message = message;
    }

    /**
     * 获取枚举名(建议与enumCode保持一致)
     */
    @Override
    public int code() {
        return code;
    }

    /**
     * 获取枚举值
     */
    @Override
    public String value() {
        return value;
    }

    /**
     * 获取枚举消息
     */
    @Override
    public String message() {
        return message;
    }

}
