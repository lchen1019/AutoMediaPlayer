package utils;

public class FileAnalyze {

    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_VOICE = 1;
    public static final int TYPE_PICTURE = 2;
    public static final int UNKNOWN = 3;

    private static final String[] videoSuffix = {"mp4"};
    private static final String[] voiceSuffix = {"rar", "mp3"};
    private static final String[] pictureSuffix = {"png", "jpg", "jpeg"};

    // 分析文件类型
    public static int analyzeFileType(String location) {
        int len = location.length();
        int i = len - 1;
        for (; i >= 0; i--) {
            if (location.charAt(i) == '.') {
                break;
            }
        }
        String suffix = location.substring(i + 1);
        System.out.println(suffix);
        for (String item : videoSuffix) {
            if (item.equals(suffix)) {
                return TYPE_VIDEO;
            }
        }
        for (String item : voiceSuffix) {
            if (item.equals(suffix)) {
                return TYPE_VOICE;
            }
        }
        for (String item : pictureSuffix) {
            if (item.equals(suffix)) {
                return TYPE_PICTURE;
            }
        }
        return UNKNOWN;
    }

}
