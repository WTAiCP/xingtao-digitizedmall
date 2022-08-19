package com.xingtao.xingtaomall.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.client.utils.StringUtils;
import com.sun.xml.internal.bind.v2.TODO;
import com.xingtao.common.utils.R;
import com.xingtao.xingtaomall.ware.feign.MemberFeignService;
import com.xingtao.xingtaomall.ware.vo.FareVo;
import com.xingtao.xingtaomall.ware.vo.MemberAddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.common.utils.Query;

import com.xingtao.xingtaomall.ware.dao.WareInfoDao;
import com.xingtao.xingtaomall.ware.entity.WareInfoEntity;
import com.xingtao.xingtaomall.ware.service.WareInfoService;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id",key)
                    .or().like("name",key)
                    .or().like("address",key)
                    .or().like("areacode",key);
        }


        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public FareVo getFare(Long addrId) {

        FareVo fareVo = new FareVo();

        //收获地址的详细信息
        R addrInfo = memberFeignService.info(addrId);

        MemberAddressVo memberAddressVo = addrInfo.getData("memberReceiveAddress",new TypeReference<MemberAddressVo>() {});

        if (memberAddressVo != null) {
            String phone = memberAddressVo.getPhone();
            //TODO 可调用第三方接口计算运费。  这里为简单，采用手机号最后一位作为运费：模拟一下逻辑
            //截取用户手机号码最后一位作为我们的运费计算
            //1558022051
            String fare = phone.substring(phone.length() - 10, phone.length()-8);
            BigDecimal bigDecimal = new BigDecimal(fare);

            fareVo.setFare(bigDecimal);
            fareVo.setAddress(memberAddressVo);

            return fareVo;
        }
        return null;
    }

}