package net.add1s.ofm.pojo.netty;

import io.netty.buffer.ByteBuf;

import java.io.Serializable;

public interface Byteable extends Serializable {

    int getByteLength();

    ByteBuf writeByteBuf(ByteBuf byteBuf);

    Byteable readByteBuf(ByteBuf byteBuf);
}
