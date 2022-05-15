import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class VideoSlider extends Slider{

    private long length;
    private RefreshThread refreshThread;
    private VideoSlider that;
    private long curTime;
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
                    if (mediaPlayer!=null&&mediaPlayer.status()!=null)
                        curTime = mediaPlayer.status().time();
                    else
                        break;
                    that.setValue((int) ((double) curTime / length * exp));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public long  getCurTime()
    {
        return curTime;
    }
    @Override
    public void doResponse(int value) {
        curTime=value;
        mediaPlayer.controls().setTime((long) ((double) value / exp * length));
    }

    @Override
    public void init() {
        this.setValue(0);
    }

}
