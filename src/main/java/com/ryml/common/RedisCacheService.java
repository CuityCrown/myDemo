package com.ryml.common;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/19
 */
public abstract class RedisCacheService <K,V>{

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheService.class);

    public V get(K k){
        try {
            V value = this.getValue(k);
            if (value != null){
                return value;
            }
            V dataForDB = this.getDataForDB(k);
            this.setValue(k,dataForDB);
            return dataForDB;
        }catch (Exception e){
            logger.error("从缓存获取数据失败"+ JSONObject.toJSONString(k),e);
        }
        return null;
    }

    /**
     * 根据key获取redis中获取的值
     * @param k
     * @return
     */
    public abstract V getValue(K k);

    public abstract void setValue(K k,V v);

    public abstract void deleteValue(K k);

    public abstract V getDataForDB(K k);

}
