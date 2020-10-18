package com.bsk;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bsk.common.util.BeanUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    private JSONObject read() throws FileNotFoundException {
        File file = FileUtil.newFile("D://新建文本文档.json");
        String s = FileUtil.readString(file, "UTF-8");
        return JSONObject.parseObject(s);
    }

    /**
     * 根据过滤条件获取图层是咯
     * @author jiangw
     * @param metaInfo 图层数据，初始化这个为空，从缓存中获取
     * @param filters 过滤条件，map中的顺序进行匹配，只有完全匹配才会进行返回
     * @return 返回图层实例
     * @date 2020/9/11 11:47
     */
    public JSONObject getMetaByFilter(JSONArray metaInfo, Map<String, Object> filters, String layerName) throws Exception {
        if (BeanUtils.isEmpty(metaInfo)) {
            JSONObject metas = read();
            metaInfo = metas.getJSONArray("metainfo");
        }
        int size = metaInfo.size();
        if (size < 1) {
            throw new Exception("图层数据为空");
        }
        JSONObject queryMeta = null;
        for (int i = 0; i < size; i++) {
            JSONObject meta = metaInfo.getJSONObject(i);
            for (Map.Entry<String, Object> filter : filters.entrySet()) {
                String key = filter.getKey();
                Object value = filter.getValue();
                if (meta.containsKey(key)) {
                    if (meta.get(key).equals(value)) {
                        if (!meta.containsKey("layername")) {
                            meta.put("layername", layerName);
                        }
                        return meta;
                    }
                }
            }
            if (meta.containsKey("net")) {
                JSONArray net = meta.getJSONArray("net");
                if (net.size() == 0) {
                    continue;
                }
                queryMeta =  getMetaByFilter(net, filters, meta.getString("layername"));
                if (queryMeta != null) {
                    return queryMeta;
                }
            }
        }
        return null;
    }

    /**
     * 根据code获取唯一的图层数据
     * @author jiangw
     * @date 2020/9/11 11:49
     */
    public JSONObject getMetaByCode(JSONArray metaInfo, String code) throws Exception {
        Map<String, Object> filters = new HashMap<>(1);
        filters.put("code", code);
        return getMetaByFilter(metaInfo, filters, null);
    }

    /**
     * 根据layerId获取唯一的图层数据
     * @author jiangw
     * @date 2020/9/11 11:28
     */
    public JSONObject getMetaByLayerId(JSONArray metaInfo, int layerId) throws Exception {
        Map<String, Object> filters = new HashMap<>(1);
        filters.put("layerid", layerId);
        return getMetaByFilter(metaInfo, filters, null);
    }
}
