package com.ryml.common;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/19
 */
public abstract class RedisCacheService <K,V>{

    public V get(K k){
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

    public abstract V getDataForDB();

}
