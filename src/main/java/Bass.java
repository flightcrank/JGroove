
import com.sun.jna.DefaultTypeMapper;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallFunctionMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	
        boolean BASS_Init(int device, int freq, int flags, Pointer win, Pointer clsid);
        int BASS_StreamCreateFile(int mem, String file, long offset, long length, int flags);
        boolean BASS_ChannelPlay(int handle, boolean restart);
        boolean BASS_Free();
	boolean BASS_StreamFree(int handle);
	boolean BASS_ChannelStop(int handle);
	boolean BASS_ChannelSetPosition(int handle, long pos , int mode);
	boolean BASS_ChannelSetAttribute(int handle, int attrib, float value);
	boolean BASS_ChannelSlideAttribute(int handle, int attrib, float value, int time);
	boolean BASS_SetConfig(int option, int value);
	long BASS_ChannelGetLength(int handle, int mode);
	int BASS_ChannelIsActive(int handle);
	long BASS_ChannelGetPosition(int handle, int mode);
        int BASS_ErrorGetCode();

	int BASS_PluginLoad(String file, int flags);
	boolean BASS_PluginFree(int handle);
	Pointer BASS_PluginGetInfo(int handle);
}
