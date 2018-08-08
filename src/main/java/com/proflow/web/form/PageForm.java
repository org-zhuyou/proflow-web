package com.proflow.web.form;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;

public class PageForm<T> implements Serializable {
    private Integer pageSize;
    private Integer currentPage;
    private Page<T> page = null;
    private static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(10);
    private static final Integer DEFAULT_CURRENT_PAGE = Integer.valueOf(1);

    public PageForm() {
    }

    public Page<T> createPage() {
        this.page = new Page((ObjectUtil.isNull(this.currentPage)?DEFAULT_CURRENT_PAGE:this.currentPage).intValue(), (ObjectUtil.isNull(this.pageSize)?DEFAULT_PAGE_SIZE:this.pageSize).intValue());
        return this.page;
    }

    public PageForm(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}