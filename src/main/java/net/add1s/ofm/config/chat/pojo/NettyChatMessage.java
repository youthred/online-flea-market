package net.add1s.ofm.config.chat.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.business.ChatMessage;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;

/**
 * @author pj.w@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class NettyChatMessage extends ChatMessage {

    private static final long serialVersionUID = -8805376646718751345L;

    private MyUserDetails userDetails;
}
