package entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class FactOrderItem {

    private String goodsId;

    private String goodsName;

    private Integer count;

    private BigDecimal totalMoney;


}
