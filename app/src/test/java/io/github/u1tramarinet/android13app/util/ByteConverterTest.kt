package io.github.u1tramarinet.android13app.util

import org.junit.Test

class ByteConverterTest {
    /**
     * ByteConverter1:byteArray=-37(db), 1(01), 0(00)}
     * ByteConverter2:byteArray=-37(DB) 1(01) 0(00)
     */
    @Test
    fun test475() {
        val param = 475
        val array = ByteConverter1.intByteArray(param)
        val result = ByteConverter2.byteArrayToIntLittleEndian(array)
        assert(result == param)
    }

    /**
     * ByteConverter1:byteArray=127(7f), 0(00), 0(00)}
     * ByteConverter2:byteArray=127(7F) 0(00) 0(00)
     */
    @Test
    fun test127() {
        val param = 127
        val array = ByteConverter1.intByteArray(param)
        val result = ByteConverter2.byteArrayToIntLittleEndian(array)
        assert(result == param)
    }

    /**
     * ByteConverter1:byteArray=-128(80), 0(00), 0(00)}
     * ByteConverter2:byteArray=-128(80) 0(00) 0(00)
     */
    @Test
    fun test128() {
        val param = 128
        val array = ByteConverter1.intByteArray(param)
        val result = ByteConverter2.byteArrayToIntLittleEndian(array)
        assert(result == param)
    }

    /**
     * ByteConverter1:byteArray=-1(ff), 0(00), 0(00)}
     * ByteConverter2:byteArray=-1(FF) 0(00) 0(00)
     */
    @Test
    fun test254() {
        val param = 255
        val array = ByteConverter1.intByteArray(param)
        val result = ByteConverter2.byteArrayToIntLittleEndian(array)
        assert(result == param)
    }

    /**
     * ByteConverter1:byteArray=-1(ff), 0(00), 0(00)}
     * ByteConverter2:byteArray=-1(FF) 0(00) 0(00)
     */
    @Test
    fun test255() {
        val param = 255
        val array = ByteConverter1.intByteArray(param)
        val result = ByteConverter2.byteArrayToIntLittleEndian(array)
        assert(result == param)
    }
}