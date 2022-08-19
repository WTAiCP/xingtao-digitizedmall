package com.xingtao.xingtaomall.search.service;

import com.xingtao.xingtaomall.search.vo.SearchParam;
import com.xingtao.xingtaomall.search.vo.SearchResult;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.search.service
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/30
 */
public interface MallSearchService {

    /**
     * @param param 检索的所有参数
     * @return  返回检索的结果，里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam param);
}
