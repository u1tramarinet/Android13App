package io.github.u1tramarinet.android13app

import org.junit.Test
import java.nio.ByteBuffer

class ByteBufferTest {
    @Test
    fun addition_isCorrect() {
        val buffer1 = ByteBuffer.allocate(4)
        buffer1.putInt(65535)

        val buffer2 = ByteBuffer.allocate(4)
        buffer2.put(1)
        buffer2.put(1)
        buffer2.put(1)
        buffer2.put(3)

        val buffer = buffer1 + buffer2
        println(buffer.toBinaryString())
    }

    private operator fun ByteBuffer.plus(other: ByteBuffer): ByteBuffer {
        val buffer = ByteBuffer.allocate(array().size + other.array().size)
        buffer.put(array())
        buffer.put(other.array())
        buffer.rewind()
        return buffer
    }

    private fun ByteBuffer.toBinaryString(): String {
        return this.array().joinToString("") { byte ->
            Integer.toBinaryString(byte.toInt() and 0xFF).padStart(8, '0')
        }
    }
}