package net.add1s.ofm.config.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.config.netty.handler.GoodsPrivateChatChannelHandler;
import net.add1s.ofm.config.props.ChatProps;
import org.springframework.stereotype.Component;

/**
 * 商品私聊NETTY服务端
 *
 * @author pj.w@qq.com
 */
@Component
@Slf4j
public class GoodsPrivateChatNettyServer {

    private final ChatProps chatProps;
    private final GoodsPrivateChatChannelHandler goodsPrivateChatChannelHandler;

    public GoodsPrivateChatNettyServer(ChatProps chatProps,
                                       GoodsPrivateChatChannelHandler goodsPrivateChatChannelHandler) {
        this.chatProps = chatProps;
        this.goodsPrivateChatChannelHandler = goodsPrivateChatChannelHandler;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(chatProps.getGoodsPrivateChatPort())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            pipeline.addLast(goodsPrivateChatChannelHandler);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(chatProps.getGoodsPrivateChatPort()).sync();

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}