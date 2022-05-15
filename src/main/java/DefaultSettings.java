/**
 * 初始化参数
 */
public class DefaultSettings implements Cloneable{

    private int volume;             // 初始声音
    private boolean sinceLastPlay;  // 是否自动从上次播放的位置开始播放
    private boolean fullScreen;     // 是否默认全屏

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isSinceLastPlay() {
        return sinceLastPlay;
    }

    public void setSinceLastPlay(boolean sinceLastPlay) {
        this.sinceLastPlay = sinceLastPlay;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    @Override
    public String toString() {
        return "DefaultSettings{" +
                "volume=" + volume +
                ", sinceLastPlay=" + sinceLastPlay +
                ", fullScreen=" + fullScreen +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DefaultSettings defaultSettings = new DefaultSettings();
        defaultSettings.setVolume(this.volume);
        defaultSettings.setFullScreen(this.fullScreen);
        defaultSettings.setSinceLastPlay(this.sinceLastPlay);
        return defaultSettings;
    }
}
