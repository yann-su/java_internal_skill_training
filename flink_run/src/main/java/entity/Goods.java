package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//商品
public class Goods {

    private String goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    public static List<Goods> GOODS_LIST;

    public static Random random;

    static {
        random = new Random();
        GOODS_LIST = new ArrayList<>();
        GOODS_LIST.add(new Goods("1","小米11",new BigDecimal(4999)));
        GOODS_LIST.add(new Goods("2","Ihone12",new BigDecimal(7880)));
        GOODS_LIST.add(new Goods("3","Macbook pro",new BigDecimal(15880)));
        GOODS_LIST.add(new Goods("4","Thinkpad",new BigDecimal(5231)));
        GOODS_LIST.add(new Goods("5","Meizu one",new BigDecimal(3322)));
        GOODS_LIST.add(new Goods("6","Mate 50",new BigDecimal(5632)));
    }

    public static Goods randomGoods(){
        int rIndex = random.nextInt(GOODS_LIST.size());
        return GOODS_LIST.get(rIndex);
    }

}
