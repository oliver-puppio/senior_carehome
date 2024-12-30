package com.boat.pensionwx.service.impl;

import com.boat.pension.mapper.RecordMapper;
import com.boat.pension.model.Record;
import com.boat.pension.model.RecordExample;
import com.boat.pensionwx.dto.RealTimeRecordDTO;
import com.boat.pensionwx.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public RealTimeRecordDTO realtimeRecord(int deviceID) {
        RecordExample example = new RecordExample();
        example.setOrderByClause("realtime desc");
        example.createCriteria().andDeviceidEqualTo(deviceID);
        List<Record> recordList = recordMapper.selectByExample(example);
        if (recordList.size() > 0) {
            return new RealTimeRecordDTO(recordList.get(0));
        } else {
            return null;
        }
    }
}
