package com.pinyougou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BaseMapper;
import com.pinyougou.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> mapper;
    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 实体对象
     */
    @Override
    public T findOne(Serializable id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部
     *
     * @return 实体对象集合
     */
    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    /**
     * 根据条件查询列表
     *
     * @param t 查询条件对象
     * @return
     */
    @Override
    public List<T> findByWhere(T t) {
        return mapper.select(t);
    }

    /**
     * 分页查询列表
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 分页信息对象
     */
    @Override
    public PageInfo<T> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<T> list = mapper.selectAll();
        return new PageInfo<>(list);
    }

    /**
     * 根据条件分页查询列表
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param t        查询条件对象
     * @return 分页信息对象
     */
    @Override
    public PageInfo<T> findPage(Integer pageNum, Integer pageSize, T t) {
        PageHelper.startPage(pageNum,pageSize);
        List<T> list = mapper.select(t);
        return new PageInfo<>(list);
    }

    /**
     * 新增
     *
     * @param t 实体对象
     */
    @Override
    public void add(T t) {
        mapper.insertSelective(t);
    }

    /**
     * 根据主键更新
     *
     * @param t 实体对象
     */
    @Override
    public void update(T t) {
        mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 批量删除
     *
     * @param ids 主键集合
     */
    @Override
    public void deleteByIds(Serializable[] ids) {
        if (ids != null && ids.length > 0){
        mapper.deleteByIds(StringUtils.join(ids,","));
        }
    }
}
