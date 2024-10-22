package edu.nwu.anisc.hgmonitor.adapter;

import edu.nwu.anisc.hgmonitor.bo.CacheNameWithTtlBO;

import java.util.List;

/**
 * 缓存时间适配器
 *
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 15:16
 */
public interface CacheTtlAdapter {
    List<CacheNameWithTtlBO> listCacheNameWithTtl();
}
