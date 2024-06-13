package fluff.http.head.value.values;

import fluff.http.head.value.HTTPHeaderValue;

/**
 * Enum representing various MIME types that can be used as HTTP header values.
 */
public enum MIMETypes implements HTTPHeaderValue {
    ANY("*/*"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_JAVASCRIPT("text/javascript"),
    TEXT_XML("text/xml"),
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_PDF("application/pdf"),
    APPLICATION_ZIP("application/zip"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_SVG_XML("image/svg+xml"),
    IMAGE_BMP("image/bmp"),
    IMAGE_WEBP("image/webp"),
    AUDIO_MPEG("audio/mpeg"),
    AUDIO_OGG("audio/ogg"),
    AUDIO_WAV("audio/wav"),
    AUDIO_WEBM("audio/webm"),
    AUDIO_AAC("audio/aac"),
    VIDEO_MP4("video/mp4"),
    VIDEO_MPEG("video/mpeg"),
    VIDEO_OGG("video/ogg"),
    VIDEO_WEBM("video/webm"),
    VIDEO_X_MSVIDEO("video/x-msvideo"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    MULTIPART_BYTERANGES("multipart/byteranges"),
    MULTIPART_ALTERNATIVE("multipart/alternative"),
    MULTIPART_MIXED("multipart/mixed"),
    MULTIPART_RELATED("multipart/related"),
    MESSAGE_HTTP("message/http"),
    MESSAGE_IMDN_XML("message/imdn+xml"),
    MESSAGE_PARTIAL("message/partial"),
    MESSAGE_RFC822("message/rfc822"),
    FONT_WOFF("font/woff"),
    FONT_WOFF2("font/woff2"),
    FONT_TTF("font/ttf"),
    FONT_OTF("font/otf");
	
    private final String value;
    
    private MIMETypes(String value) {
        this.value = value;
    }
    
    @Override
    public String getHTTPHeaderValue() {
        return value;
    }
}
