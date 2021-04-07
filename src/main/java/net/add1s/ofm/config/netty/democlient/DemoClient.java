package net.add1s.ofm.config.netty.democlient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoClient {

    public void sendAndGetResponse(DemoMessage message) throws InterruptedException {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            ChannelPipeline pipeline = nioSocketChannel.pipeline();
                            pipeline.addLast(new DemoMessageEncoder());
                            pipeline.addLast(new DemoMessageDecoder());
                            pipeline.addLast(new DemoMessageResponseHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 6666).sync();
            future.channel().writeAndFlush(message);
//            future.channel().close();
            future.channel().closeFuture().sync();
//            worker.shutdownGracefully();
        } catch (InterruptedException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            worker.shutdownGracefully();
        }
    }
}
