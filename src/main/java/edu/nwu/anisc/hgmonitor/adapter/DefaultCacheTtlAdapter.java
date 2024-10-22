package edu.nwu.anisc.hgmonitor.adapter;

import edu.nwu.anisc.hgmonitor.bo.CacheNameWithTtlBO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 配置特定key的过期时间
 *
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 15:18
 */
@Component
public class DefaultCacheTtlAdapter implements CacheTtlAdapter{
    @Override
    public List<CacheNameWithTtlBO> listCacheNameWithTtl() {
        return null;
    }
}
