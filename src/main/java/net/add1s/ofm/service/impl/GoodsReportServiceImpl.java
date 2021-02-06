package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.mapper.GoodsReportMapper;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.service.IGoodsReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsReportServiceImpl extends ServiceImpl<GoodsReportMapper, GoodsReport> implements IGoodsReportService {
}
