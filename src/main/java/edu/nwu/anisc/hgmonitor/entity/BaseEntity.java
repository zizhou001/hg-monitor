package edu.nwu.anisc.hgmonitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 16:36
 */
@Data
public class BaseEntity implements Serializable {

    protected Date createTime;

    protected Date updateTime;
}
