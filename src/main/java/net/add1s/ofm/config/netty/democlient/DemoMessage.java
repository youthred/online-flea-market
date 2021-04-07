package net.add1s.ofm.config.netty.democlient;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DemoMessage {

    private int a;

    public ByteBuf write(ByteBuf byteBuf) {
        return byteBuf.writeIntLE(a);
    }

    public DemoMessage read(ByteBuf byteBuf) {
        this.a = byteBuf.readIntLE();
        return this;
    }
}
