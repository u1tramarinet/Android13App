package io.github.u1tramarinet.android13app.util

object ByteConverter1 {
    @OptIn(ExperimentalStdlibApi::class)
    fun intByteArray(value: Int): ByteArray {
        val byteArray = ByteArray(3)
        byteArray[0] = (value and 0xFF).toByte()
        byteArray[1] = ((value shr 8) and 0xFF).toByte()
        byteArray[2] = ((value shr 16) and 0xFF).toByte()
        println("ByteConverter1:byteArray=${byteArray.joinToString(", ") { "$it(${it.toHexString()})" }}}")
        return byteArray
    }
}