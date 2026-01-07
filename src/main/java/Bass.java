
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

	int BASS_ACTIVE_STOPPED	= 0;
	int BASS_ACTIVE_PLAYING	= 1;
	int BASS_ACTIVE_STALLED	= 2;
	int BASS_ACTIVE_PAUSED = 3;
	int BASS_ACTIVE_PAUSED_DEVICE = 4;

	// filetypes
	int BASS_FILE_NAME = 0;		// filename
	int BASS_FILE_MEM = 1;		// memory
	int BASS_FILE_MEMCOPY  = 3;	// memory to copy
	int BASS_FILE_HANDLE  = 4;	// handle/descriptor

	int BASS_SAMPLE_8BITS     = 1;       // 8 bit
	int BASS_SAMPLE_MONO      = 2;       // mono
	int BASS_SAMPLE_LOOP      = 4;       // looped
	int BASS_SAMPLE_3D        = 8;       // 3D functionality
	int BASS_SAMPLE_SOFTWARE  = 0x10;    // unused
	int BASS_SAMPLE_MUTEMAX   = 0x20;    // mute at max distance (3D only)
	int BASS_SAMPLE_NOREORDER = 0x40;    // don't reorder channels to match speakers
	int BASS_SAMPLE_FX        = 0x80;    // unused
	int BASS_SAMPLE_FLOAT     = 0x100;   // 32 bit floating-point
	int BASS_SAMPLE_OVER_VOL  = 0x10000; // override lowest volume
	int BASS_SAMPLE_OVER_POS  = 0x20000; // override longest playing
	int BASS_SAMPLE_OVER_DIST = 0x30000; // override furthest from listener (3D only)
	
	int BASS_STREAM_PRESCAN  = 0x20000;  // scan file for accurate seeking and length
	int BASS_STREAM_AUTOFREE = 0x40000;  // automatically free the stream when it stops/ends
	int BASS_STREAM_RESTRATE = 0x80000;  // restrict the download rate of internet file stream
	int BASS_STREAM_BLOCK    = 0x100000; // download internet file stream in small blocks
	int BASS_STREAM_DECODE   = 0x200000; // don't play the stream, only decode
	int BASS_STREAM_STATUS   = 0x800000; // give server status info (HTTP/ICY tags) in DOWNLOADPROC

	int BASS_MUSIC_FLOAT = BASS_SAMPLE_FLOAT;
	int BASS_MUSIC_MONO  = BASS_SAMPLE_MONO;
	int BASS_MUSIC_LOOP  = BASS_SAMPLE_LOOP;
	int BASS_MUSIC_3D    = BASS_SAMPLE_3D;
	int BASS_MUSIC_FX    = BASS_SAMPLE_FX;	
	int BASS_MUSIC_AUTOFREE	= BASS_STREAM_AUTOFREE;
	int BASS_MUSIC_DECODE = BASS_STREAM_DECODE;
	int BASS_MUSIC_PRESCAN	= BASS_STREAM_PRESCAN;	// calculate playback length
	int BASS_MUSIC_RAMP      = 0x200;    // normal ramping
	int BASS_MUSIC_RAMPS     = 0x400;    // sensitive ramping
	int BASS_MUSIC_SURROUND  = 0x800;    // surround sound
	int BASS_MUSIC_SURROUND2 = 0x1000;   // surround sound (mode 2)
	int BASS_MUSIC_FT2PAN    = 0x2000;   // apply FastTracker 2 panning to XM files
	int BASS_MUSIC_FT2MOD    = 0x2000;   // play .MOD as FastTracker 2 does
	int BASS_MUSIC_PT1MOD    = 0x4000;   // play .MOD as ProTracker 1 does
	int BASS_MUSIC_NONINTER  = 0x10000;  // non-interpolated sample mixing
	int BASS_MUSIC_SINCINTER = 0x800000; // sinc interpolated sample mixing
	int BASS_MUSIC_POSRESET  = 0x8000;   // stop all notes when moving position
	int BASS_MUSIC_POSRESETEX = 0x400000; // stop all notes and reset bpm/etc when moving position
	int BASS_MUSIC_STOPBACK  = 0x80000;  // stop the music on a backwards jump effect
	int BASS_MUSIC_NOSAMPLE  = 0x100000; // don't load the samples

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
