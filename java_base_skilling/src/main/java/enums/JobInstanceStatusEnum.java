package enums;

import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum JobInstanceStatusEnum {
    WAITING_START(0, "待启动", false),
    STARTING(1, "启动中", false),
    RUNNING(2, "运行中", false),
    START_FAILED(3, "启动失败", true),
    RUN_FAILED(4, "运行失败", true),
    STOPPED(5, "已停止", true),
    SUCCESS(6, "运行成功", true),
    UNKNOWN(-1, "未知", false);
    //运行时装填
    private Integer code;
    private String description;
    private boolean finalState;

    JobInstanceStatusEnum(int code, String description, boolean finalState){
        this.code = code;
        this.description = description;
        this.finalState = finalState;
    }


    public static JobInstanceStatusEnum getEnum(Integer code) {
        if (code == null)
            return null;
        for (JobInstanceStatusEnum jobInstanceStatusEnum : JobInstanceStatusEnum.values()) {
            if (jobInstanceStatusEnum.getCode().equals(code))
                return jobInstanceStatusEnum;
        }
        return null;
    }


    public static void main(String[] args) {


    }

}
