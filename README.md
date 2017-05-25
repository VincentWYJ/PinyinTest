# PinyinTest
对Pinyin4j、TinyPinyin及JPinyin三个汉字转拼音库通过简单的测试代码进行比较。

1、TinyPinyin——https://github.com/promeG/TinyPinyin
build.gradle：
compile 'com.github.promeg:tinypinyin:2.0.3'
compile 'com.github.promeg:tinypinyin-lexicons-android-cncity:2.0.3'
Java代码：
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
方法Pinyin.toPinyin(c)，如果是汉字，则返回对应的拼音；否则返回原字符。

2、Pinyin4j——https://github.com/belerweb/pinyin4j
build.gradle：
compile 'com.belerweb:pinyin4j:2.5.0'
Java代码：
fun pinyin4j(content: String) {
    val start_time: Long = System.currentTimeMillis()
    val stringBuilder = StringBuilder()
    stringBuilder.setLength(0)
    val hanyuPinyinOutputFormat = HanyuPinyinOutputFormat()
    hanyuPinyinOutputFormat.toneType = HanyuPinyinToneType.WITHOUT_TONE
    try {
        for (c in content) {
            val result = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat)
            if (result != null) {
                stringBuilder.append(result[0])
            } else {
                stringBuilder.append(c)
            }
        }
    } catch (badHanyuPinyinOutputFormatCombination: BadHanyuPinyinOutputFormatCombination) {
        badHanyuPinyinOutputFormatCombination.printStackTrace()
    }
    val end_time: Long = System.currentTimeMillis()
    Log.i("pinyintest", "pinyin4j time: " +  + (end_time - start_time) + "ms")
    Log.i("pinyintest", "pinyin4j content: " + stringBuilder.toString().toLowerCase())
}
方法PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat)，如果是汉字，result[0]就是转换的结果；否则result为null，取原字符c。

3、JPinyin——
以Pinyin4j作为基础做了一些改进。
build.gradle：
compile 'com.github.stuxuhai:jpinyin:1.1.8'
Java代码：
fun jpinyin(content: String) {
    val start_time: Long = System.currentTimeMillis()
    val stringBuilder = StringBuilder()
    stringBuilder.setLength(0)
    stringBuilder.append(PinyinHelper.convertToPinyinString(content, "", PinyinFormat.WITHOUT_TONE))
    val end_time: Long = System.currentTimeMillis()
    Log.i("pinyintest", "jpinyin time: " +  + (end_time - start_time) + "ms")
    Log.i("pinyintest", "jpinyin content: " + stringBuilder.toString().toLowerCase())
}
方法PinyinHelper.convertToPinyinString(content, "", PinyinFormat.WITHOUT_TONE)，将整个字串一起转换成拼音，第二个参数为各个字符转换结果的分隔符，这里为空字串。
