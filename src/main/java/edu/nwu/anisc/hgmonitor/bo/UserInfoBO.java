package edu.nwu.anisc.hgmonitor.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 15:36
 * @since JDK 17
 */
@Data
public class UserInfoBO {

    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
    private Integer role;
}
