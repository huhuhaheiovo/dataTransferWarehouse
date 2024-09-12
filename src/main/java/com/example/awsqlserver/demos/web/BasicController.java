
package com.example.awsqlserver.demos.web;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/aw")
public class BasicController {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @PostMapping("/sqlServer")
    @ResponseBody
    public R saveAwSqlServer(@RequestBody CarWashProcessInfoDTO  carWashProcessInfoDTO) {
        log.info("入参："+JSONUtil.toJsonStr(carWashProcessInfoDTO));
       try {
           Integer count =pushSqlServer(carWashProcessInfoDTO);
           log.info("推送成功"+LocalDateTime.now()+"条数："+count);
           return R.ok(count);
       }catch (Exception e) {
           log.error(e.toString());
           return R.error(e);
       }

    }


    public Integer pushSqlServer(CarWashProcessInfoDTO carWashProcessInfoDTO) {
        String sql = "INSERT INTO car_wash_process_info (device_no, start_time, end_time, elapsed, car_no, hydraulic_pressure, electricity, pic, create_time) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String deviceNo = carWashProcessInfoDTO.getDeviceNo();
        LocalDateTime startTime = carWashProcessInfoDTO.getStartTime();
        LocalDateTime endTime = carWashProcessInfoDTO.getEndTime();
        Integer elapsed = carWashProcessInfoDTO.getElapsed();
        String carNo = carWashProcessInfoDTO.getCarNo();
        String hydraulicPressure = carWashProcessInfoDTO.getHydraulicPressure();
        String electricity = carWashProcessInfoDTO.getElectricity();
        String pic = carWashProcessInfoDTO.getPic();
        return primaryJdbcTemplate.update(sql, deviceNo, startTime, endTime, elapsed, carNo, hydraulicPressure, electricity, pic, LocalDateTime.now());
    }
}
