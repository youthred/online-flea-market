package net.add1s.ofm.common.page;

import lombok.Data;
import lombok.experimental.Accessors;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class EsPage implements Serializable {
    
    private static final long serialVersionUID = 5942116867931212085L;

    private int page;
    private int size;
    private List<QueryOption> queryOptions = new ArrayList<>();

    public PageRequest toPageRequest() {
        // es分页从0开始，需 -1
        return PageRequest.of(page - 1, size);
    }

    public QueryBuilder toQueryBuilder() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (QueryOption option : queryOptions) {
            boolQueryBuilder.must(option.toQueryBuilder());
        }
        return boolQueryBuilder;
    }
}
