package com.dylan.pinyintest;

import java.io.InputStream;
import java.net.URLEncoder;

public class TranslateUtils {

    protected static final String URL_TEMPLATE ="http://translate.google.com/?langpair={0}&text={1}";
    protected static final String ID_RESULTBOX ="result_box";

    protected static final String ENCODING ="UTF-8";
    protected static final String TAIWAN ="zh-TW";
    protected static final String CHINA ="zh-CN";
    protected static final String ENGLISH ="en";
    protected static final String JAPAN ="ja";

    protected static HttpClient httpclient;

    public static String translate(final String text, final String src_lang, final String target_lang)
            throws Exception {
        InputStream is = null;
        Document doc = null;
        Element ele = null;
        try {
            String url = MessageFormat.format(URL_TEMPLATE,
                    URLEncoder.encode(src_lang +"|"+ target_lang, ENCODING),
                    URLEncoder.encode(text, ENCODING));

            is = HttpClientUtil.downloadAsStream(url);

            doc = Jsoup.parse(is, ENCODING,"");
            ele = doc.getElementById(ID_RESULTBOX);
            String result = ele.text();
            return result;

        }finally{
            IOUtil.closeQuietly(is);
            is = null;
            doc = null;
            ele = null;
        }
    }

    public static String cn2tw(final String text)throws Exception {
        return translate(text, CHINA, TAIWAN);
    }

    public static String tw2cn(final String text)throws Exception {
        return translate(text, TAIWAN, CHINA);
    }

    public static String en2tw(final String text)throws Exception {
        return translate(text, ENGLISH, TAIWAN);
    }

    public static String tw2en(final String text)throws Exception {
        return translate(text, TAIWAN, ENGLISH);
    }

    public static String jp2tw(final String text)throws Exception {
        return translate(text, JAPAN, TAIWAN);
    }

    public static String tw2jp(final String text)throws Exception {
        return translate(text, TAIWAN, JAPAN);
    }

    public static String jp2cn(final String text)throws Exception {
        return translate(text, JAPAN, CHINA);
    }

    public static String cn2jp(final String text)throws Exception {
        return translate(text, CHINA, JAPAN);
    }
}
