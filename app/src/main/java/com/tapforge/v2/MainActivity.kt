package com.tapforge.v2

import android.app.Activity
import android.content.pm.PackageManager
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.graphics.Typeface
import android.graphics.Color

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hasNfc = packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)
        val hasHce = packageManager.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)
        val nfcEnabled = NfcAdapter.getDefaultAdapter(this)?.isEnabled == true
        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER; setPadding(28,36,28,36); setBackgroundColor(Color.rgb(15,18,27)) }
        fun line(value: String, size: Float, color: Int, bold: Boolean = false) {
            val t = TextView(this).apply { text=value; textSize=size; setTextColor(color); gravity=Gravity.CENTER; if (bold) setTypeface(null,Typeface.BOLD); setPadding(0,10,0,10) }
            layout.addView(t)
        }
        line("TAPFORGE",30f,Color.WHITE,true)
        line("V2.0 · CUSTOM NFC TEST CARD",13f,Color.LTGRAY,true)
        line("NFC hardware: " + if (hasNfc) "Detected" else "Not detected",17f,if (hasNfc) Color.GREEN else Color.RED)
        line("Android HCE: " + if (hasHce) "Available" else "Not reported by Android",17f,if (hasHce) Color.GREEN else Color.RED)
        line("NFC radio: " + if (nfcEnabled) "ON" else "OFF",17f,if (nfcEnabled) Color.GREEN else Color.YELLOW)
        line("Emulated card: TapForge Demo",19f,Color.WHITE,true)
        line("AID: F0010203040506",15f,Color.LTGRAY)
        line("Hold the back of this phone near a compatible NFC reader.",15f,Color.WHITE)
        line("This app responds only to TapForge's own test commands.",13f,Color.LTGRAY)
        setContentView(layout)
    }
}
