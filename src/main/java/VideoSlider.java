import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class VideoSlider extends Slider{

    private long length;
    private RefreshThread refreshThread;
    private VideoSlider that;

    public VideoSlider(MediaPlayer mediaPlayer) {
        super(mediaPlayer);
        length = mediaPlayer.media().info().duration();
        System.out.println(length);
        exp = 10000;
        setMaximum(exp);
        that = this;
        refreshThread = new RefreshThread();
    }

    public long getLength() {
        return length;
    }


    // 每隔一秒刷新一次
    public void refresh() {
        refreshThread.start();
    }

    class RefreshThread extends Thread{
        @Override
        public synchronized void run() {
            while(true) {
                try {
                    long curTime = mediaPlayer.status().time();
                    that.setValue((int) ((double) curTime / length * exp));
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void doResponse(int value) {
        mediaPlayer.controls().setTime((long) ((double) value / exp * length));
    }

    @Override
    public void init() {
        this.setValue(0);
    }

}
