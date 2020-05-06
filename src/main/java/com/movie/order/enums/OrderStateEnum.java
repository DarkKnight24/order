package com.movie.order.enums;

import com.movie.base.enums.IEnum;

public enum OrderStateEnum implements IEnum {
    REFUNDING(0, "退票中"), UNREFUND(-1, "无法退票"), PAYMENT_COMPLETE(1, "已支付"), REFUND_COMPLETE(2, "退票成功"), UNPAY(3,
        "未支付"), ORDER_FAIL(4, "订单已失效");
    
    private int key;
    
    private String value;
    
    OrderStateEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public int getKey() {
        return key;
    }
    
    public void setKey(int key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String key() {
        return String.valueOf(key);
    }
    
    @Override
    public String value() {
        return value;
    }
}
