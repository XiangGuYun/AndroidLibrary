package yxd.media_player.case2_service_sdcard_music;

/**
 * Created by asus on 2018/1/10.
 */

public class Command {

    private boolean shouldStop;

    private boolean playOrPause;

    private int progress;

    public Command(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public Command(boolean shouldStop, boolean playOrPause) {
        this.shouldStop = shouldStop;
        this.playOrPause = playOrPause;
    }

    public Command(boolean shouldStop, boolean playOrPause, int progress) {
        this.shouldStop = shouldStop;
        this.playOrPause = playOrPause;
        this.progress = progress;
    }

    public boolean isShouldStop() {
        return shouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public boolean isPlayOrPause() {
        return playOrPause;
    }

    public void setPlayOrPause(boolean playOrPause) {
        this.playOrPause = playOrPause;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
