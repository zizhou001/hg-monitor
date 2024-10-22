package edu.nwu.anisc.hgmonitor.bo;

import lombok.Data;

/**
 * 缓存清除策略
 *
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 15:16
 */
@Data
public class CacheNameWithTtlBO {

    private String cacheName;

    private Integer ttl;

    public CacheNameWithTtlBO(String cacheName, Integer ttl) {
        this.cacheName = cacheName;
        this.ttl = ttl;
    }
}
