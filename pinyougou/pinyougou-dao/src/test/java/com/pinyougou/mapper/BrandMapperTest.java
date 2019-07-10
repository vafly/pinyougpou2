package com.pinyougou.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbBrand;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class BrandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    //测试原有方法
    @Test
    public void queryAll(){
        List<TbBrand> tbBrands = brandMapper.queryAll();
        for (TbBrand tbBrand : tbBrands) {
            System.out.println(tbBrand);
        }
    }

    //选择性新增
    @Test
    public void insertSelective() {
        TbBrand brand = new TbBrand();
        brand.setName("摩托罗拉");
        brand.setFirstChar("M");
        brandMapper.insertSelective(brand);
    }

    //选择性更新
    @Test
    public void updateByPrimaryKeySelective() {
        TbBrand brand = new TbBrand();
        brand.setId(23L);
        brand.setName("test11");

        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Test
    public void selectAll() {
        List<TbBrand> list = brandMapper.selectAll();
        for (TbBrand tbBrand : list) {
            System.out.println(tbBrand);
        }
    }

    @Test
    public void selectByPrimaryKey() {
        TbBrand tbBrand = brandMapper.selectByPrimaryKey(1L);
        System.out.println(tbBrand);
    }


    @Test
    public void select() {
        TbBrand param = new TbBrand();
        param.setFirstChar("C");
        List<TbBrand> list = brandMapper.select(param);
        for (TbBrand tbBrand : list) {
            System.out.println(tbBrand);
        }
    }

    //根据主键删除
    @Test
    public void deleteByPrimaryKey() {
        brandMapper.deleteByPrimaryKey(23L);
    }

    @Test
    public void selectByExample() {
        //设置分页
        PageHelper.startPage(1,2);
        //创建查询对象
        Example example = new Example(TbBrand.class);
        //创建查询条件对象
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("firstChar","C");

        List<TbBrand> list = brandMapper.selectByExample(example);

        //转换为分页信息对象
        PageInfo<TbBrand> pageInfo = new PageInfo<>(list);

        System.out.println("总记录数为："+pageInfo.getTotal());
        System.out.println("总页数为："+pageInfo.getPages());
        System.out.println("当前页号为："+pageInfo.getPageNum());
        System.out.println("页大小为："+pageInfo.getPageSize());

        for (TbBrand brand : pageInfo.getList()) {
            System.out.println(brand);
        }
    }

    //批量新增
    @Test
    public void insertList(){
        List<TbBrand> list = new ArrayList<>();
        TbBrand brand = new TbBrand();
        brand.setName("test30");
        brand.setFirstChar("T");
        list.add(brand);

        brand = new TbBrand();
        brand.setName("test31");
        brand.setFirstChar("T");
        list.add(brand);

        brandMapper.insertList(list);
    }

    //批量删除
    @Test
    public void deleteByIds(){
        Long[] ids ={23L,24L,25L};

        brandMapper.deleteByIds(StringUtils.join(ids,","));
    }
}