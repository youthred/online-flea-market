package net.add1s.ofm;

import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HuToolTest {

    @Test
    public void uuidTest() {
        System.out.println(IdUtil.fastSimpleUUID());
        System.out.println(IdUtil.objectId());
    }

    @Test
    public void objectId() {
        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtil.objectId());
        }
        System.out.println("-");
        System.out.println(IdUtil.objectId());
        System.out.println(IdUtil.objectId());
    }

    @Test
    public void stringLen() {
        System.out.println("605364e3520c0515853c5fb6".getBytes(StandardCharsets.UTF_8).length);
    }

    @Test
    public void stringBytes() {
        System.out.println("605364e3520c0515853c5fb6".getBytes(StandardCharsets.UTF_8).length);
        System.out.println(Arrays.toString("605364e3520c0515853c5fb6".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void intBytes() {
        System.out.println(Integer.valueOf(1).byteValue());
    }
}
