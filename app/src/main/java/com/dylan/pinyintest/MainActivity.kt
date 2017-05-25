package com.dylan.pinyintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.github.promeg.pinyinhelper.Pinyin
import com.github.stuxuhai.jpinyin.PinyinFormat
import com.github.stuxuhai.jpinyin.PinyinHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convert_btn.setOnClickListener {

            var content: String? = content_tiet.text.toString()
            if (content == null) {
                Toast.makeText(this, "请输入内容，谢谢", Toast.LENGTH_SHORT).show()
            } else {
                //pinyin4j(content)
                tinypinyin(content)
                jpinyin(content)
            }
        }
    }

//    fun pinyin4j(content: String) {
//        val start_time: Long = System.currentTimeMillis()
//        val stringBuilder = StringBuilder()
//        stringBuilder.setLength(0)
//        val hanyuPinyinOutputFormat = HanyuPinyinOutputFormat()
//        hanyuPinyinOutputFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
//        try {
//            for (c in content) {
//                val result = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat)
//                if (result != null) {
//                    stringBuilder.append(result[0])
//                } else {
//                    stringBuilder.append(c)
//                }
//            }
//        } catch (badHanyuPinyinOutputFormatCombination: BadHanyuPinyinOutputFormatCombination) {
//            badHanyuPinyinOutputFormatCombination.printStackTrace()
//        }
//        val end_time: Long = System.currentTimeMillis()
//        Log.i("pinyintest", "pinyin4j time: " +  + (end_time - start_time) + "ms")
//        Log.i("pinyintest", "pinyin4j content: " + stringBuilder.toString().toLowerCase())
//    }

    fun tinypinyin(content: String) {
        val start_time: Long = System.currentTimeMillis()
        val stringBuilder = StringBuilder()
        stringBuilder.setLength(0)
        for (c in content) {
            stringBuilder.append(Pinyin.toPinyin(c))
        }
        val end_time: Long = System.currentTimeMillis()
        Log.i("pinyintest", "tinypinyin time: " +  + (end_time - start_time) + "ms")
        Log.i("pinyintest", "tinypinyin content: " + stringBuilder.toString().toLowerCase())
    }

    fun jpinyin(content: String) {
        val start_time: Long = System.currentTimeMillis()
        val stringBuilder = StringBuilder()
        stringBuilder.setLength(0)
        stringBuilder.append(PinyinHelper.convertToPinyinString(content, "", PinyinFormat.WITHOUT_TONE))
        val end_time: Long = System.currentTimeMillis()
        Log.i("pinyintest", "jpinyin time: " +  + (end_time - start_time) + "ms")
        Log.i("pinyintest", "jpinyin content: " + stringBuilder.toString().toLowerCase())
    }
}
