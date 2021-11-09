package com.lq.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WarehouseNumber implements Serializable {

    private static final long serialVersionUID = -4442775586318845414L;
    private Integer qty;

    private String warehousePn;
}
