package com.xingtao.xingtaomall.coupon.service.impl;

import com.xingtao.xingtaomall.coupon.entity.SeckillSkuRelationEntity;
import com.xingtao.xingtaomall.coupon.service.SeckillSkuRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingtao.common.utils.PageUtils;
import com.xingtao.common.utils.Query;

import com.xingtao.xingtaomall.coupon.dao.SeckillSessionDao;
import com.xingtao.xingtaomall.coupon.entity.SeckillSessionEntity;
import com.xingtao.xingtaomall.coupon.service.SeckillSessionService;


@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSessionEntity> page = this.page(
                new Query<SeckillSessionEntity>().getPage(params),
                new QueryWrapper<SeckillSessionEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询最近三天需要参加秒杀商品的信息
     * @return
     */
    @Override
    public List<SeckillSessionEntity> getLates3DaySession() {
        //计算最近三天
        //查出这三天参与秒杀活动
        List<SeckillSessionEntity> list = this.baseMapper.selectList(new QueryWrapper<SeckillSessionEntity>()
                .between("start_time", startTime(), endTime()));

        //查出活动中需要上架的商品
        if (list != null && list.size() > 0) {
            List<SeckillSessionEntity> collect = list.stream().map(session -> {
                Long id = session.getId();  //活动id
                //查出sms_seckill_sku_relation表中关联的skuId
                List<SeckillSkuRelationEntity> relationSkus = seckillSkuRelationService.list(new QueryWrapper<SeckillSkuRelationEntity>()
                        .eq("promotion_session_id", id));
                session.setRelationSkus(relationSkus);
                return session;
            }).collect(Collectors.toList());
            return collect;
        }

        return null;

    }


    /**
     * 当前时间
     * @return
     */
    private String startTime() {
        LocalDate now = LocalDate.now();
        LocalTime min = LocalTime.MIN;
        LocalDateTime start = LocalDateTime.of(now, min);

        //格式化时间
        String startFormat = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return startFormat;
    }

    /**
     * 结束时间
     * @return
     */
    private String endTime() {
        LocalDate now = LocalDate.now();
        LocalDate plus = now.plusDays(2);
        LocalTime max = LocalTime.MAX;
        LocalDateTime end = LocalDateTime.of(plus, max);

        //格式化时间
        String endFormat = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return endFormat;
    }
}