package com.xingtao.xingtaomall.search.controller;

import com.xingtao.xingtaomall.search.service.MallSearchService;
import com.xingtao.xingtaomall.search.vo.SearchParam;
import com.xingtao.xingtaomall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.search.controller
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/5/29
 */
@Controller
public class SearchController {
    @Autowired
    private MallSearchService mallSearchService;

    /**
     * 自动将页面提交过来的所有请求参数封装成我们指定的对象
     * @param
     * @return
     */
    @GetMapping(value = "/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {

        param.set_queryString(request.getQueryString());

        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);

        model.addAttribute("result",result);

        return "list";
    }
}
