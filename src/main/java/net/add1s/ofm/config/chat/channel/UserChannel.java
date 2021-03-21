package net.add1s.ofm.config.chat.channel;

import io.netty.channel.Channel;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class UserChannel {

    private MyUserDetails userDetails;
    private Channel channel;
}
