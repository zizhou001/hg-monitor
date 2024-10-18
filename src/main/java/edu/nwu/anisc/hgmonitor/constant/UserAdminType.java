package edu.nwu.anisc.hgmonitor.constant;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 10:04
 * @since JDK 17
 */
public enum UserAdminType {
    /**
     * 管理员
     */
    ADMIN(1),
    /**
     * 用户
     */
    USER(0),
    ;

    private Integer value;

    public Integer value() {
        return value;
    }

    public Integer getValue() {
        return value;
    }


    UserAdminType(Integer value) {
        this.value = value;
    }

    /**
     * 根据值获取对应的枚举常量
     *
     * @param value
     * @return
     */
    public static UserAdminType instance(Integer value) {
        UserAdminType[] enums = values();
        for (UserAdminType statusEnum : enums) {
            if (statusEnum.getValue().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }
}

