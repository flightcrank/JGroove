/**
 *
 * @author karma
 */

public class JMP {

    public static void main(String[] args) {
     		
		//initilise the BassAudioEngine
		BassAudioEngine audioEngine = new BassAudioEngine();
		
		try {
			
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				
				if ("Nimbus".equals(info.getName())) { 
					
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> new Gui(audioEngine).setVisible(true));
    }
}