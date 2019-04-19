package com.superstudentregion.bean;

import java.util.List;

public class PageBean<T> {

    private Integer currentPagr;

    private Integer pageSize;

    private Integer totalNum;

    private Integer isMore;

    private Integer totalPage;

    private Integer startIndex;

    private List<T> items;

    private T hell;

    public Integer getCurrentPagr() {
        return currentPagr;
    }

    public void setCurrentPagr(Integer currentPagr) {
        this.currentPagr = currentPagr;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPagr=" + currentPagr +
                ", pageSize=" + pageSize +
                ", totalNum=" + totalNum +
                ", isMore=" + isMore +
                ", totalPage=" + totalPage +
                ", startIndex=" + startIndex +
                ", items=" + items +
                '}';
    }

}
