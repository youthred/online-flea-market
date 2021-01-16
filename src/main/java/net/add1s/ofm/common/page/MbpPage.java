package net.add1s.ofm.common.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.util.HumpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class MbpPage<P, Q> implements Serializable {

    private static final long serialVersionUID = 4770750188358323477L;

    private Page<P> page;
    private List<QueryOption> queryOptions = new ArrayList<>();

    public QueryWrapper<Q> toQueryWrapper() {
        if (queryOptions.size() == 1) {
            return queryOptions.get(0).toQueryWrapper();
        }
        QueryWrapper<Q> queryWrapper = new QueryWrapper<>();
        for (QueryOption queryOption : queryOptions) {
            String lineKey = HumpUtil.humpToLine(queryOption.getKey());
            switch (queryOption.getType()) {
                case eq:
                    queryWrapper.eq(lineKey, queryOption.getValue());
                    break;
                case ne:
                    queryWrapper.ne(lineKey, queryOption.getValue());
                    break;
                case gt:
                    queryWrapper.gt(lineKey, queryOption.getValue());
                    break;
                case lt:
                    queryWrapper.lt(lineKey, queryOption.getValue());
                    break;
                case ge:
                    queryWrapper.ge(lineKey, queryOption.getValue());
                    break;
                case le:
                    queryWrapper.le(lineKey, queryOption.getValue());
                    break;
                case like:
                    queryWrapper.like(lineKey, queryOption.getValue());
                    break;
                default:
                    break;
            }
        }
        return queryWrapper;
    }
}
