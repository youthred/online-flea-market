package net.add1s.ofm.config.chat.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.dto.ChatMessageDTO;
import net.add1s.ofm.pojo.entity.sys.MyUserDetails;

import java.io.Serializable;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class NettyChatMessage implements Serializable {

    private static final long serialVersionUID = -8805376646718751345L;

    private MyUserDetails currentUserDetails;
    private ChatMessageDTO chatMessageDTO;
}
