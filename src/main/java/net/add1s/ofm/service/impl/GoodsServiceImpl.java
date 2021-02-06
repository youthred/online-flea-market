package net.add1s.ofm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.add1s.ofm.common.enums.Symbol;
import net.add1s.ofm.mapper.GoodsMapper;
import net.add1s.ofm.pojo.dto.GoodsReportDTO;
import net.add1s.ofm.pojo.entity.business.Goods;
import net.add1s.ofm.pojo.entity.business.GoodsReport;
import net.add1s.ofm.pojo.vo.business.GoodsVO;
import net.add1s.ofm.service.IGoodsReportService;
import net.add1s.ofm.service.IGoodsService;
import net.add1s.ofm.util.SqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    private final IGoodsReportService iGoodsReportService;

    public GoodsServiceImpl(IGoodsReportService iGoodsReportService) {
        this.iGoodsReportService = iGoodsReportService;
    }

    @Override
    public GoodsVO details(Long goodsTbId) {
        return this.baseMapper.findGoodsDetail(goodsTbId);
    }

    @Override
    public List<GoodsVO> search(String q) {
        String[] searches = SqlUtil.escapeLike(q.trim()).replaceAll(" +", " ").split(Symbol.SPACE.getValue());
        List<String> searchesTrimmed = Arrays.stream(searches).distinct().map(String::trim).collect(Collectors.toList());
        String like = StringUtils.join(searchesTrimmed, Symbol.PERCENT.getValue());
        return this.baseMapper.findByDesc(like);
    }

    @Override
    public void view(Long goodsTbId) {
        this.baseMapper.viewsAdd(goodsTbId);
    }

    @Override
    public void report(GoodsReportDTO goodsReportDTO) {
        iGoodsReportService.save(
                new GoodsReport()
                        .setCreateTime(LocalDateTime.now())
                        .setGoodsTbId(goodsReportDTO.getGoodsTbId())
                        .setReason(goodsReportDTO.getReason())
                        .setReviewed(false)
        );
    }
}
