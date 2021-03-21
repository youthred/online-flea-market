package net.add1s.ofm.pojo.netty;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.charset.StandardCharsets;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class Header implements Byteable {

    private static final long serialVersionUID = -1893290348109471575L;

    /**
     * 24字节，session id，cn.hutool.core.util.IdUtil.objectId()
     */
    private String sessionId;
    /**
     * 4字节，消息体字节长度，变长
     */
    private int contentLength;

    @Override
    public int getByteLength() {
        return 24 + 4;
    }

    @Override
    public ByteBuf writeByteBuf(ByteBuf byteBuf) {
        return byteBuf
                .writeBytes(this.sessionId.getBytes(StandardCharsets.UTF_8))
                .writeIntLE(this.contentLength);
    }

    @Override
    public Header readByteBuf(ByteBuf byteBuf) {
        byte[] sessionIdBytes = new byte[24];
        byteBuf.readBytes(sessionIdBytes);
        this.sessionId = new String(sessionIdBytes);
        this.contentLength = byteBuf.readIntLE();
        return this;
    }
}
