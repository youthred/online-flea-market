package net.add1s.ofm.pojo.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.add1s.ofm.pojo.netty.OfmMessage;
import org.springframework.stereotype.Component;

/**
 * @author pj.w@qq.com
 */
@Component
public class GoodsChatMessageEncoder extends MessageToByteEncoder<OfmMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, OfmMessage ofmMessage, ByteBuf byteBuf) throws Exception {
        ofmMessage.writeByteBuf(byteBuf);
    }
}
