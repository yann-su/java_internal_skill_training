package jvm.mock;

import org.junit.Test;

import java.io.IOException;

//-XX:+PrintGCDetails -Xmx10m -Xms10m
public class ReferenceCountingGc {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[3 * _1MB];

    public static void main(String args[]) throws IOException {
        ReferenceCountingGc referenceCountingGc1 = new ReferenceCountingGc();
        ReferenceCountingGc referenceCountingGc2 = new ReferenceCountingGc();
        referenceCountingGc1.instance = referenceCountingGc2;
        referenceCountingGc2.instance = referenceCountingGc1;

        referenceCountingGc1 = null;
        referenceCountingGc1 = null;

        System.gc();
        System.in.read();

    }


}
