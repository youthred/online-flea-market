package net.add1s.ofm.common.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.add1s.ofm.common.enums.QueryTypeEnum;
import net.add1s.ofm.util.HumpUtil;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class QueryOption {

    @NotBlank
    private String key;
    @NotNull
    private Object value;
    @NotNull
    private QueryTypeEnum type;

    public QueryBuilder toQueryBuilder() {
        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        switch (type) {
            case eq:
                queryBuilder = QueryBuilders.termQuery(key, value);
                break;
            case ne:
                queryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(key, value));
                break;
            case gt:
                queryBuilder = QueryBuilders.rangeQuery(key).gt(value);
                break;
            case lt:
                queryBuilder = QueryBuilders.rangeQuery(key).lt(value);
                break;
            case ge:
                queryBuilder = QueryBuilders.rangeQuery(key).gte(value);
                break;
            case le:
                queryBuilder = QueryBuilders.rangeQuery(key).lte(value);
                break;
            case like:
                queryBuilder = QueryBuilders.wildcardQuery(key, value.toString());
            default:
                break;
        }
        return queryBuilder;
    }

    public <T> QueryWrapper<T> toQueryWrapper() {
        String lineKey = HumpUtil.humpToLine(key);
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        switch (type) {
            case eq:
                queryWrapper.eq(lineKey, value);
                break;
            case ne:
                queryWrapper.ne(lineKey, value);
                break;
            case gt:
                queryWrapper.gt(lineKey, value);
                break;
            case lt:
                queryWrapper.lt(lineKey, value);
                break;
            case ge:
                queryWrapper.ge(lineKey, value);
                break;
            case le:
                queryWrapper.le(lineKey, value);
                break;
            case like:
                queryWrapper.like(lineKey, value);
                break;
            default:
                break;
        }
        return queryWrapper;
    }
}
