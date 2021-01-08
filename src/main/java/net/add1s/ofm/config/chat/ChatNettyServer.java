package net.add1s.ofm.config.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import net.add1s.ofm.config.chat.handler.GroupSexChatChannelHandler;
import net.add1s.ofm.config.props.ChatProps;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChatNettyServer {

    private final ChatProps chatProps;
    private final GroupSexChatChannelHandler groupSexChatChannelHandler;

    public ChatNettyServer(ChatProps chatProps,
                           GroupSexChatChannelHandler groupSexChatChannelHandler) {
        this.chatProps = chatProps;
        this.groupSexChatChannelHandler = groupSexChatChannelHandler;
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(workGroup, bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(chatProps.getPort())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            log.info("新连接：{}", ch.localAddress());
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                            ch.pipeline().addLast(groupSexChatChannelHandler);
                        }
                    });
            ChannelFuture cf = sb.bind(chatProps.getPort()).sync();
            cf.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }
}
