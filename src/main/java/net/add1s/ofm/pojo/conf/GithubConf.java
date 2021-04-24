package net.add1s.ofm.pojo.conf;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GithubConf {

    private String owner;
    private String repo;
}
