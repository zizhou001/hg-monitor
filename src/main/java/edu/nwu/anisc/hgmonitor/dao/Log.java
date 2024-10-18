package edu.nwu.anisc.hgmonitor.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 15:25
 * @since JDK 17
 */
@Data
@TableName(value = "log")
public class Log {
    @TableId(value = "log_id", type = IdType.ASSIGN_UUID)
    String logId;

    @TableField(value = "log_scope")
    String logScope;


    @TableField(value = "log_content")
    String logContent;


    @TableField(value = "log_operator")
    String logOperator;


    @TableField(value = "log_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date logTime;


    @TableField(value = "log_ip")
    String logIp;


    @TableField(value = "log_level")
    int logLevel;

    @TableField(value = "log_result")
    String logResult;

    @TableField(value = "log_detail")
    String logDetail;
}
