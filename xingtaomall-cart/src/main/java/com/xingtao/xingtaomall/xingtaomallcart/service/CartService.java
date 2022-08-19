package com.xingtao.xingtaomall.xingtaomallcart.service;

import com.xingtao.xingtaomall.xingtaomallcart.vo.CartItemVo;
import com.xingtao.xingtaomall.xingtaomallcart.vo.CartVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.xingtaomallcart.service
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/6
 */
public interface CartService {
    CartItemVo addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    CartItemVo getCartItem(Long skuId);

    CartVo getCart() throws ExecutionException, InterruptedException;

    /**
     * 清空购物车的数据
     * @param cartKey
     */
    void clearCartInfo(String cartKey);

    void checkItem(Long skuId, Integer checked);

    void changeItemCount(Long skuId, Integer num);

    /**
     * 删除购物项
     * @param skuId
     */
    void deleteIdCartInfo(Integer skuId);

    List<CartItemVo> getUserCartItems();
}
