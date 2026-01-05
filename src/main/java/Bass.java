
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 *
 * @author karma
 */
public interface Bass extends Library {

        //tell JNA to load libbass.so
   	Bass INSTANCE = Native.load("bass", Bass.class);

	int BASS_POS_BYTE = 0;        // Use bytes for position/lengyh	
	int BASS_ATTRIB_VOL = 2;
	int BASS_CONFIG_LINUX_ALSA_CONFIG = 44;
	int BASS_SAMPLE_SOFTWARE = 0x10;

	int BASS_ACTIVE_STOPPED	= 0;
	int BASS_ACTIVE_PLAYING	= 1;
	int BASS_ACTIVE_STALLED	= 2;
	int BASS_ACTIVE_PAUSED = 3;
	int BASS_ACTIVE_PAUSED_DEVICE = 4;

	// filetypes
	int BASS_FILE_NAME = 0;		// filename
	int BASS_FILE_MEM = 1;		// memory
	int BASS_FILE_MEMCOPY  = 3;	// memory to copy
	int BASS_FILE_HANDLE  =4;	// handle/descriptor

	int BASS_MUSIC_FLOAT      = 0x100;    
	int BASS_MUSIC_RAMPS      = 0x400;    
	int BASS_MUSIC_FT2PAN     = 0x2000;   
	int BASS_MUSIC_PRESCAN    = 0x20000;  
	int BASS_MUSIC_POSRESETEX = 0x400000;

	int BASS_TAG_MUSIC_NAME    = 0x10000; // MOD music name : ANSI string
	int BASS_TAG_MUSIC_MESSAGE = 0x10001; // MOD message : ANSI string
	int BASS_TAG_MUSIC_ORDERS  = 0x10002; // MOD order list : BYTE array
	int BASS_TAG_MUSIC_AUTH    = 0x10003; // MOD author : UTF-8 string
	int BASS_TAG_MUSIC_INST    = 0x10100; // + instrument #, name : ANSI string
	int BASS_TAG_MUSIC_CHAN    = 0x10200; // + channel #, name : ANSI string
	int BASS_TAG_MUSIC_SAMPLE  = 0x10300; // + sample #, name : ANSI string

	// BASS_ChannelSetSync types
	int BASS_SYNC_POS = 0;
	int BASS_SYNC_END = 2;
	int BASS_SYNC_META = 4;
	int BASS_SYNC_SLIDE = 5;
	int BASS_SYNC_STALL = 6;
	int BASS_SYNC_DOWNLOAD = 7;
	int BASS_SYNC_FREE = 8;
	int BASS_SYNC_SETPOS = 11;
	int BASS_SYNC_MUSICPOS = 10;
	int BASS_SYNC_MUSICINST = 1;
	int BASS_SYNC_MUSICFX = 3;
	int BASS_SYNC_OGG_CHANGE = 12;
	int BASS_SYNC_ATTRIB = 13;
	int BASS_SYNC_DEV_FAIL = 14;
	int BASS_SYNC_DEV_FORMAT = 15;
	int BASS_SYNC_POS_RAW = 16;

	// BASS_ChannelSetSync Flags
	int BASS_SYNC_THREAD = 0x20000000; // call sync in other thread
	int BASS_SYNC_MIXTIME = 0x40000000; // sync at mixtime
	int BASS_SYNC_ONETIME = 0x80000000; // sync only once

	public interface SYNCPROC extends Callback {
		
		void invoke(int handle, int channel, int data, Pointer user);
	}
	
        boolean BASS_Init(int device, int freq, int flags, Pointer win, Pointer clsid);
        int BASS_StreamCreateFile(int mem, String file, long offset, long length, int flags);
        boolean BASS_ChannelPlay(int handle, boolean restart);
        boolean BASS_Free();
	boolean BASS_StreamFree(int handle);
	boolean BASS_ChannelStop(int handle);
	boolean BASS_ChannelSetPosition(int handle, long pos , int mode);
	boolean BASS_ChannelSetAttribute(int handle, int attrib, float value);
	boolean BASS_ChannelSlideAttribute(int handle, int attrib, float value, int time);
	double BASS_ChannelBytes2Seconds(int handle, long pos);
	String BASS_ChannelGetTags(int handle, int tags);	
	boolean BASS_SetConfig(int option, int value);
	long BASS_ChannelGetLength(int handle, int mode);
	int BASS_ChannelIsActive(int handle);
	long BASS_ChannelGetPosition(int handle, int mode);
	int BASS_ChannelSetSync(int handle, int type, long param, SYNCPROC proc, Pointer user);

	int BASS_MusicLoad(int filetype, Object file, long offset, int length, int flags, int freq);
	boolean BASS_MusicFree(int handle);
	
        int BASS_ErrorGetCode();

	int BASS_PluginLoad(String file, int flags);
	boolean BASS_PluginFree(int handle);
	Pointer BASS_PluginGetInfo(int handle);
}
