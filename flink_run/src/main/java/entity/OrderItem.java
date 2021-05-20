package entity;

import lombok.Data;
import lombok.ToString;

@Data
//订单id
@ToString
public class OrderItem {

    private String itemId;

    private String goodsId;

    private Integer count;

}
