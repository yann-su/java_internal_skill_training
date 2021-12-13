package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListeningDetails {

    //关联主键
    private Long playlistId;
    //关联主键
    private Long orderSongId;
    //配置流dc0970的订单id
    private Long orderId;
    //歌单dc0970的uin
    private Long authorUin;
    //ec00050 uin
    private Long uin;
    //ec00050 歌曲播放时长
    private Long playtime;
    //ec00050 上报事件时间
    private String reportTime;
    //ec00050 音频时长
    private Integer audioDuration;
    //业务business
    private String business;


}
