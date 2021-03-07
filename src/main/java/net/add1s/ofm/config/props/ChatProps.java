package net.add1s.ofm.config.props;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

/**
 * @author pj.w@qq.com
 */
@Configuration
@ConfigurationProperties("sys.chat")
@Data
@Accessors(chain = true)
public class ChatProps {

    /**
     * 群聊端口
     */
    @NotBlank
    private Integer groupSexPort;

    /**
     * 群聊HOST
     */
    @NotBlank
    private String groupSexServerHost;

    /**
     * 商品私聊端口
     */
    @NotBlank
    private Integer goodsPrivateChatPort;

    /**
     * 商品私聊HOST
     */
    @NotBlank
    private String goodsPrivateChatServerHost;
}
