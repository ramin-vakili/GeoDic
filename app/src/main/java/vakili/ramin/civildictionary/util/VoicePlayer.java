package vakili.ramin.civildictionary.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by RaminV on 12/7/2018.
 */
public class VoicePlayer implements MediaPlayer.OnCompletionListener{

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private AssetFileDescriptor[] voiceFilesDescriptors = new AssetFileDescriptor[0];
    private int voiceIndex = 0;

    public VoicePlayer(String[] voicesFilePath, Context context) throws IOException {
        mediaPlayer.setOnCompletionListener(this);
        if (voicesFilePath.length > 0) {
            voiceFilesDescriptors = new AssetFileDescriptor[voicesFilePath.length];
            for (int i = 0; i < voicesFilePath.length; i++) {
                voiceFilesDescriptors[i] = context.getAssets().openFd("voices/" + voicesFilePath[i]);
            }
        }
    }

    public void playVoices() throws IOException {
        voiceIndex =0;
        if (voiceFilesDescriptors.length > 0){
            mediaPlayer.reset();
            mediaPlayer.setDataSource(voiceFilesDescriptors[voiceIndex].getFileDescriptor(),
                    voiceFilesDescriptors[voiceIndex].getStartOffset(),
                    voiceFilesDescriptors[voiceIndex].getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(this);
            voiceIndex++;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (!(voiceIndex > voiceFilesDescriptors.length-1)){
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(voiceFilesDescriptors[voiceIndex].getFileDescriptor(),
                        voiceFilesDescriptors[voiceIndex].getStartOffset(),
                        voiceFilesDescriptors[voiceIndex].getLength());
                mediaPlayer.prepare();
                mediaPlayer.start();
                voiceIndex++;
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            voiceIndex = 0;
        }
    }

}
