package net.add1s.ofm.pojo.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.add1s.ofm.pojo.netty.OfmMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pj.w@qq.com
 */
@Component
public class GoodsChatMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(new OfmMessage().readByteBuf(byteBuf));
    }
}
