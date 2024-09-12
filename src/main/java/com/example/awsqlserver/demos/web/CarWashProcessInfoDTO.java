package com.example.awsqlserver.demos.web;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarWashProcessInfoDTO {

    /**
     * 设备编号
     */
    private String deviceNo;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    /**
     * 时长
     */
    private int elapsed;
    /**
     * 车牌
     */
    private String carNo;
    /**
     * 水压
     */
    private String hydraulicPressure;
    /**
     * 电流
     */
    private String electricity;
    /**
     * 图片或者视频
     */
    private String pic;
    private LocalDateTime createTime;
}
