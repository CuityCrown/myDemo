package com.ryml.util;

import com.alibaba.fastjson.JSON;
import com.ryml.common.BizException;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * description:深克隆工具类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2020/01/08 16:13
 */
@Slf4j
public class CloneUtil {
    /**
     * 深克隆
     * @param originObj
     * @param <T>
     * @return
     */
    public static <T> T deepClone(T originObj) {
        if (originObj == null) {
            throw new BizException("originObj can not be null!");
        }
        Object o;
        try {
            o = deepClone0(originObj);
        } catch (Exception e) {
            // 稳定后删除
            log.error("深克隆异常，入参：" + JSON.toJSONString(originObj), e);
            o = deepCloneOld(originObj);
        }

        return (T) o;
    }

    private static <T> T deepClone0(T obj) {
        return deserialize(serialize(obj), (Class<T>) obj.getClass());
    }

    private static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
    }

    private static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }

    @Deprecated
    public static <T> T deepCloneOld(T originObj) {
        if (originObj == null) {
            throw new BizException("originObj can not be null!");
        }
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(originObj);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(baos);
            close(oos);
            close(bais);
            close(ois);
        }
    }
    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
