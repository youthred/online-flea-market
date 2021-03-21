package net.add1s.ofm.pojo.netty;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.netty.payload.Payload;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class OfmMessage implements Payload {

    private static final long serialVersionUID = 175018319382418054L;

    private Header header;
    private String content;

    @Override
    public int getByteLength() {
        return header.getByteLength() + header.getContentLength();
    }

    @Override
    public ByteBuf writeByteBuf(ByteBuf byteBuf) {
        this.header.writeByteBuf(byteBuf);
        return byteBuf.writeBytes(this.content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public OfmMessage readByteBuf(ByteBuf byteBuf) {
        this.header.readByteBuf(byteBuf);
        this.content = byteBuf.readCharSequence(this.header.getContentLength(), Charset.defaultCharset()).toString();
        return this;
    }
}
