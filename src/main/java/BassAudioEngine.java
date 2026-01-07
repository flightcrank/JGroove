
import com.sun.jna.Pointer;



/**
 *
 * @author karma
 */

public class BassAudioEngine {

	private int fileHandle = 0;
	private Bass.SYNCPROC endSong;
	private SongListener endSongListener;
	
	public BassAudioEngine() {

		endSong = new Bass.SYNCPROC() {
			
			@Override
			public void invoke(int handle, int channel, int data, Pointer user) {
				
				System.out.println("Bass end of song callback triggered");
				
				if (endSongListener != null) {
					
					endSongListener.onSongEnded();
				}
			}
		};

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
	
	public void addSongListener(SongListener listener) {
		
		this.endSongListener = listener;
	}
	
	public Bass.SYNCPROC getCallback(){

		return this.endSong;
	}
	
	public void freeHandle() {

		//TODO: check to see there were no errors freeing the handle
		if (fileHandle != 0 ) {
			
			Bass.INSTANCE.BASS_MusicFree(fileHandle);
			Bass.INSTANCE.BASS_StreamFree(fileHandle);
			System.out.println("Freeing memory held by File handle");
			fileHandle = 0;	
		}
	}
	
	public void loadFile(String path, AudioType aType) {
		
		//always free the memory accocitated with the old handle before loading data for a new one
		freeHandle();

		if (aType == AudioType.MODULE) {
			
			int flags = Bass.BASS_MUSIC_PRESCAN | Bass.BASS_MUSIC_POSRESETEX | Bass.BASS_MUSIC_RAMPS | Bass.BASS_MUSIC_FLOAT | Bass.BASS_MUSIC_STOPBACK;
			
			//create module handle
			fileHandle = Bass.INSTANCE.BASS_MusicLoad(Bass.BASS_FILE_NAME, path, 0, 0, flags, 0);

			if (fileHandle == 0) {
				
				System.err.println("Could not load module : " + Bass.INSTANCE.BASS_ErrorGetCode());

			} else {

				System.out.println("New module handle created successfully");
			}
			
		} else if (aType == AudioType.STREAMED) {

			//Create stream
			fileHandle = Bass.INSTANCE.BASS_StreamCreateFile(0, path, 0, 0, 0);

			if (fileHandle == 0) {
				
				System.err.println("Error creating stream! Code: " + Bass.INSTANCE.BASS_ErrorGetCode());
				
			} else {
				
				System.out.println("New stream handle created successfully");
			}
		}

		int handle = Bass.INSTANCE.BASS_ChannelSetSync(fileHandle, Bass.BASS_SYNC_END, 0, endSong, null);

		if (handle != 0 ) {

			System.out.println("Call back registered");
			
		} else {
			
			System.err.println("Callback function error: " + Bass.INSTANCE.BASS_ErrorGetCode());
		}
	}
	
	public long getSize() {

		long size = Bass.INSTANCE.BASS_ChannelGetLength(fileHandle, Bass.BASS_POS_BYTE);

		if (size != -1) {
			
			System.out.println("file size (bytes): " + size);
			return size;

		} else {

			System.err.println("Error getting flize size : " + Bass.INSTANCE.BASS_ErrorGetCode());
			return 0;
		}
	}
	
	public void seek(int val) {

		if (fileHandle != 0) {
			
			// 1. Get the total length of the stream in bytes
			long totalBytes = Bass.INSTANCE.BASS_ChannelGetLength(fileHandle, Bass.BASS_POS_BYTE);
        
			if (totalBytes != -1) {
				
				// 2. Calculate the target byte position
				// (Current Slider Value / Max Slider Value) * Total Bytes
				double percentage = val / 1000.0;
				long targetByte = (long) (percentage * totalBytes);
					   
				// 3. Perform the seek
				if (!Bass.INSTANCE.BASS_ChannelSetPosition(fileHandle, targetByte, Bass.BASS_POS_BYTE)) {
					
					System.err.println("Seek Error: " + Bass.INSTANCE.BASS_ErrorGetCode());
		
				} else {
				
					System.out.println("Jumped to: " + targetByte + " bytes");
				}
			}
		}
	}
	
	public void setVolume(int val) {
		
		if (fileHandle != 0) {
		
			float v = val / 100.0f;
			
			if (!Bass.INSTANCE.BASS_ChannelSetAttribute(fileHandle, Bass.BASS_ATTRIB_VOL, v)) {
			
				System.err.println("Could not set volume, Error : " + Bass.INSTANCE.BASS_ErrorGetCode());
		
			} else {

				System.out.println("Volume changed to " + v);
			}
		}
	}
	
	public void play() {

		if (fileHandle != 0) {
		
			if (Bass.INSTANCE.BASS_ChannelPlay(fileHandle, false)) {

				System.out.println("Audio playing!");

			} else {
			
				System.out.println("Stream failed. Error: " + Bass.INSTANCE.BASS_ErrorGetCode());
        		}
		}
	}
	
	public void stop() {
		
		Bass.INSTANCE.BASS_ChannelStop(fileHandle);
		Bass.INSTANCE.BASS_ChannelSetPosition(fileHandle, 0, Bass.BASS_POS_BYTE);
	}

	public int isPlaying () {
		
		return Bass.INSTANCE.BASS_ChannelIsActive(fileHandle);
	}

	public long getPosition() {

		if (fileHandle != 0) {
			
			long pos = Bass.INSTANCE.BASS_ChannelGetPosition(fileHandle, 0);

			if (pos == -1) {
				
				System.err.println("Could not get position in file : " + Bass.INSTANCE.BASS_ErrorGetCode());
				return -1;
		
			} else {
				
				return pos;
			}
		}

		return -1;
	}

	public double lengthInSeconds(long byteLength) {

		if (fileHandle != 0) {
			
			double result = Bass.INSTANCE.BASS_ChannelBytes2Seconds(fileHandle, byteLength);

			if (result != -1) {

				return result;

			} else {

				System.err.println("Could not get length in seconds of fileHandle : " + Bass.INSTANCE.BASS_ErrorGetCode());
			}
		}
		
		return 0;
	}
	
	public long getLength() {

		if (fileHandle != 0) {
			
			long len = Bass.INSTANCE.BASS_ChannelGetLength(fileHandle, Bass.BASS_POS_BYTE);

			if (len == -1) {
				
				System.err.println("Could not get track length : " + Bass.INSTANCE.BASS_ErrorGetCode());
				return -1;
				
			} else {
				
				return len;
			}
		}
		
		return -1;
	}

	public String getModuleTitle() {
		
		String title = Bass.INSTANCE.BASS_ChannelGetTags(fileHandle, Bass.BASS_TAG_MUSIC_NAME);
		
		if (title == null || title.trim().isEmpty()) {
		
			return "Untitled Module";
		}

		return title;
	}
	
	public String getModuleAuthor() {
		
		String author = Bass.INSTANCE.BASS_ChannelGetTags(fileHandle, Bass.BASS_TAG_MUSIC_AUTH);
		
		if (author == null || author.trim().isEmpty()) {
		
			return "Unknown Author";
		}

		return author;
	}
	
	public void cleanup() {
		
		Bass.INSTANCE.BASS_PluginFree(0); // free all plugins
		Bass.INSTANCE.BASS_Free();
		System.out.println("Basslib memory has been freed");
	}
}