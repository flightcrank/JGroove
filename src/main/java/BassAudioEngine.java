
import com.sun.jna.Memory;


/**
 *
 * @author karma
 */

public class BassAudioEngine {

	private int stream = 0;
		
	public BassAudioEngine() {

		if (!Bass.INSTANCE.BASS_Init(-1, 48000, 16384, null, null)) {
			
			System.out.println("Init failed. Error: " + Bass.INSTANCE.BASS_ErrorGetCode());
		} 
			
		int flacPlugin = Bass.INSTANCE.BASS_PluginLoad("libbassflac.so", 0);
		
		if (flacPlugin == 0) {
		
			System.err.println("FLAC plugin failed to load: " + Bass.INSTANCE.BASS_ErrorGetCode());
		
		} else {
			
			System.out.println("FLAC support enabled.");
		} 
	}

	public void loadFile(String path) {
		
		// Free the old stream before loading a new one
		if (stream != 0) {
		
			Bass.INSTANCE.BASS_StreamFree(stream);
			System.out.println("Freeing previous stream");
			stream = 0;
		}
		
		//Create stream
		stream = Bass.INSTANCE.BASS_StreamCreateFile(0, path, 0, 0, 0);

		if (stream == 0) {
			
			int errorCode = Bass.INSTANCE.BASS_ErrorGetCode();
			System.err.println("Error creating stream! Code: " + errorCode);
			
		} else {
			
			System.out.println("New stream created successfully: " + stream);
		}
	}
	
	public long getSize() {

		long size = Bass.INSTANCE.BASS_ChannelGetLength(stream, Bass.BASS_POS_BYTE);

		if (size != -1) {
			
			System.out.println("file size (bytes): " + size);
			return size;

		} else {

			System.err.println("Error getting flize size : " + Bass.INSTANCE.BASS_ErrorGetCode());
			return 0;
		}
	}
	
	public void seek(int val) {

		if (stream != 0) {
			
			// 1. Get the total length of the stream in bytes
			long totalBytes = Bass.INSTANCE.BASS_ChannelGetLength(stream, Bass.BASS_POS_BYTE);
        
			if (totalBytes != -1) {
				
				// 2. Calculate the target byte position
				// (Current Slider Value / Max Slider Value) * Total Bytes
				double percentage = val / 1000.0;
				long targetByte = (long) (percentage * totalBytes);
					   
				// 3. Perform the seek
				if (!Bass.INSTANCE.BASS_ChannelSetPosition(stream, targetByte, Bass.BASS_POS_BYTE)) {
					
					System.err.println("Seek Error: " + Bass.INSTANCE.BASS_ErrorGetCode());
		
				} else {
				
					System.out.println("Jumped to: " + targetByte + " bytes");
				}
			}
		}
	}
	
	public void setVolume(int val) {
		
		if (stream != 0) {
		
			float v = val / 100.0f;
			
			if (!Bass.INSTANCE.BASS_ChannelSetAttribute(stream, Bass.BASS_ATTRIB_VOL, v)) {
			
				System.err.println("Could not set volume, Error : " + Bass.INSTANCE.BASS_ErrorGetCode());
		
			} else {

				System.out.println("Volume changed to " + v + " Stream is " + stream);
			}
		}
	}
	
	public void play() {

		if (stream != 0) {
		
			if (Bass.INSTANCE.BASS_ChannelPlay(stream, false)) {

				System.out.println("Music playing! Press Enter to exit.");

			} else {
			
				System.out.println("Stream failed. Error: " + Bass.INSTANCE.BASS_ErrorGetCode());
        		}
		}
	}
	
	public void stop() {
		
		Bass.INSTANCE.BASS_ChannelStop(stream);
		Bass.INSTANCE.BASS_ChannelSetPosition(stream, 0, Bass.BASS_POS_BYTE);
	}

	public int isPlaying () {
		
		if (stream != 0) {
			
		}
		
		return Bass.INSTANCE.BASS_ChannelIsActive(stream);
	}

	public long getPosition() {

		if (stream != 0) {
			
			long pos = Bass.INSTANCE.BASS_ChannelGetPosition(stream, 0);

			if (pos == -1) {
				
				System.err.println("Could not get position in file : " + Bass.INSTANCE.BASS_ErrorGetCode());
				return -1;
		
			} else {
				
				return pos;
			}
		}

		return -1;
	}

	public long getLength() {

		if (stream != 0) {
			
			long len = Bass.INSTANCE.BASS_ChannelGetLength(stream, Bass.BASS_POS_BYTE);

			if (len == -1) {
				
				System.err.println("Could not get track length : " + Bass.INSTANCE.BASS_ErrorGetCode());
				return -1;
				
			} else {
				
				return len;
			}
		}
		
		return -1;
	}
	
	public void cleanup() {
		
		Bass.INSTANCE.BASS_Free();
		Bass.INSTANCE.BASS_PluginFree(0); // free all plugins
		System.out.println("Basslib memory has been freed");
	}
}