package com.tapforge.v2

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class TapForgeHostApduService : HostApduService() {
    companion object {
        private val SELECT_AID = byteArrayOf(0x00,0xA4.toByte(),0x04,0x00,0x07,0xF0.toByte(),0x01,0x02,0x03,0x04,0x05,0x06)
        private val OK = byteArrayOf(0x90.toByte(),0x00)
        private val NOT_FOUND = byteArrayOf(0x6A,0x82.toByte())
        private val WRONG_LENGTH = byteArrayOf(0x67,0x00)
        private val DEMO = "TAPFORGE|CARD=DEMO-001|VERSION=2.0".toByteArray(Charsets.UTF_8)
    }
    private var selected = false
    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null || commandApdu.size < 4) return WRONG_LENGTH
        if (commandApdu.size >= SELECT_AID.size && commandApdu.copyOfRange(0, SELECT_AID.size).contentEquals(SELECT_AID)) { selected = true; return OK }
        if (!selected) return NOT_FOUND
        if (commandApdu.size == 5 && commandApdu[0] == 0x80.toByte() && commandApdu[1] == 0xCA.toByte() && commandApdu[2] == 0x00.toByte() && commandApdu[3] == 0x00.toByte() && commandApdu[4] == 0x00.toByte()) return DEMO + OK
        return NOT_FOUND
    }
    override fun onDeactivated(reason: Int) { selected = false }
}
