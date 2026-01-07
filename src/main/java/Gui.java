
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/**
 *
 * @author karma
 */
public class Gui extends javax.swing.JFrame {

	private BassAudioEngine audioEngine;
	private Timer progressTimer;
	private int currentRow;
	private int numRows;
	
	public Gui(BassAudioEngine audioEngine) {
		
		this.numRows = 0;
		this.currentRow = 0;
		this.audioEngine = audioEngine;
		
		initComponents();
		
		jTabbedPane1.addTab("new tab", new JPanel());
		TableColumn col = jTable1.getColumnModel().getColumn(5);	
		jTable1.removeColumn(col);
		
		//center text in column
		javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
		jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
			
		FileNameExtensionFilter ff = new FileNameExtensionFilter("Audio Files", "wav", "mp3", "ogg", "flac", "mod", "xm", "s3m", "it");
		jFileChooser1.setFileFilter(ff);
		jFileChooser1.setMultiSelectionEnabled(true);
		
		this.audioEngine.setVolume(jSlider2.getValue());
		jPanel2.add(menuBar, java.awt.BorderLayout.CENTER);
		jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

		File[] files = loadPlaylist();
		openFiles(files);
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                searchTextField = new javax.swing.JTextField();
                menuBar = new javax.swing.JMenuBar();
                fileMenu = new javax.swing.JMenu();
                openFile = new javax.swing.JMenuItem();
                jMenu2 = new javax.swing.JMenu();
                jFileChooser1 = new javax.swing.JFileChooser();
                jPanel1 = new javax.swing.JPanel();
                jToolBar1 = new javax.swing.JToolBar();
                jMediaButton1 = new JMediaButton();
                jMediaButton2 = new JMediaButton();
                jMediaButton3 = new JMediaButton();
                jMediaButton4 = new JMediaButton();
                jSlider1 = new javax.swing.JSlider();
                jSeparator1 = new javax.swing.JSeparator();
                jPanel3 = new javax.swing.JPanel();
                jSlider2 = new javax.swing.JSlider();
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();

                jPanel2.setMaximumSize(new java.awt.Dimension(2147483647, 50));
                jPanel2.setLayout(new java.awt.BorderLayout());

                searchTextField.setText("Search");
                searchTextField.setMaximumSize(new java.awt.Dimension(2147483647, 30));
                searchTextField.setPreferredSize(new java.awt.Dimension(150, 27));
                searchTextField.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                searchTextFieldActionPerformed(evt);
                        }
                });
                jPanel2.add(searchTextField, java.awt.BorderLayout.EAST);

                fileMenu.setText("File");

                openFile.setText("Open File");
                openFile.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                openFileActionPerformed(evt);
                        }
                });
                fileMenu.add(openFile);

                menuBar.add(fileMenu);

                jMenu2.setText("Edit");
                menuBar.add(jMenu2);

                jFileChooser1.setFileFilter(null);

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("JGroove");
                setPreferredSize(new java.awt.Dimension(800, 450));
                addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent evt) {
                                formWindowClosing(evt);
                        }
                });

                jPanel1.setBorder(null);
                jPanel1.setLayout(new java.awt.BorderLayout());

                jToolBar1.setBorder(null);
                jToolBar1.setFloatable(false);
                jToolBar1.setRollover(true);

                jMediaButton1.setText("jMediaButton1");
                jMediaButton1.setFocusable(false);
                jMediaButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jMediaButton1.setPreferredSize(new java.awt.Dimension(40, 40));
                jMediaButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                jMediaButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMediaButton1ActionPerformed(evt);
                        }
                });
                jToolBar1.add(jMediaButton1);

                jMediaButton2.setText("jMediaButton2");
                jMediaButton2.setFocusable(false);
                jMediaButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jMediaButton2.setmType(JMediaButton.MediaType.STOP);
                jMediaButton2.setPreferredSize(new java.awt.Dimension(40, 40));
                jMediaButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                jMediaButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMediaButton2ActionPerformed(evt);
                        }
                });
                jToolBar1.add(jMediaButton2);

                jMediaButton3.setText("jMediaButton3");
                jMediaButton3.setFocusable(false);
                jMediaButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jMediaButton3.setmType(JMediaButton.MediaType.SKIP_BACK);
                jMediaButton3.setPreferredSize(new java.awt.Dimension(40, 40));
                jMediaButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                jToolBar1.add(jMediaButton3);

                jMediaButton4.setText("jMediaButton4");
                jMediaButton4.setFocusable(false);
                jMediaButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jMediaButton4.setmType(JMediaButton.MediaType.SKIP_FWD);
                jMediaButton4.setPreferredSize(new java.awt.Dimension(40, 40));
                jMediaButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                jToolBar1.add(jMediaButton4);

                jPanel1.add(jToolBar1, java.awt.BorderLayout.WEST);

                jSlider1.setMajorTickSpacing(100);
                jSlider1.setMaximum(1000);
                jSlider1.setMinorTickSpacing(50);
                jSlider1.setValue(0);
                jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                jSlider1StateChanged(evt);
                        }
                });
                jPanel1.add(jSlider1, java.awt.BorderLayout.CENTER);
                jPanel1.add(jSeparator1, java.awt.BorderLayout.SOUTH);

                jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
                jPanel3.setLayout(new java.awt.BorderLayout());

                jSlider2.setPreferredSize(new java.awt.Dimension(100, 21));
                jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                jSlider2StateChanged(evt);
                        }
                });
                jPanel3.add(jSlider2, java.awt.BorderLayout.EAST);

                jPanel1.add(jPanel3, java.awt.BorderLayout.EAST);

                getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

                jTable1.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Track", "Artist", "Album", "Title", "Length", "Path"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jTable1.setRowHeight(25);
                jTable1.setShowGrid(false);
                jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jTable1MouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(jTable1);

                jTabbedPane1.addTab("tab1", jScrollPane1);

                getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

                pack();
        }// </editor-fold>//GEN-END:initComponents

	private AudioType checkAudioType(String filePath) {

		int index = filePath.lastIndexOf(".");
		String ext = (index == -1) ? "" : filePath.substring(index + 1);

		if (ext.equalsIgnoreCase("s3m") || ext.equalsIgnoreCase("xm") || ext.equalsIgnoreCase("mod") || ext.equalsIgnoreCase("it")) {
			
			return AudioType.MODULE;
		} 
		
		return AudioType.STREAMED;
	}
	
	private void playTrack() {
		
		if (progressTimer != null) {
			
			progressTimer.stop();
		}
		
		audioEngine.play();
		
		//set up the thread that updates the JSlider
		progressTimer = new Timer(100, new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
			
				// 1. Check if the stream is actually playing
				if (audioEngine.isPlaying() == Bass.BASS_ACTIVE_PLAYING) {
					
					long currentPos = audioEngine.getPosition();
					long totalLen = audioEngine.getLength();
				
					if (totalLen > 0) {
					
						int sliderPos = (int)(((double)currentPos / totalLen) * 1000);
						jSlider1.setValue(sliderPos);
					}
					
				} else {
					
					((Timer) e.getSource()).stop();
					System.out.println("Timer stopped");
				}
			}
		});
		
		//start the tread
		progressTimer.start();
	}
	
        private void jMediaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMediaButton1ActionPerformed
		
		//playTrack();
        }//GEN-LAST:event_jMediaButton1ActionPerformed

        private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		
		//free the System resources used by libBass	
		audioEngine.cleanup();
		saveTabPlaylist(jTable1);
        }//GEN-LAST:event_formWindowClosing

        private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_searchTextFieldActionPerformed

        private void jMediaButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMediaButton2ActionPerformed
                // TODO add your handling code here:
		audioEngine.stop();
        }//GEN-LAST:event_jMediaButton2ActionPerformed

        private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
		
		// if a user is draging the slider
		if (jSlider1.getValueIsAdjusting()) {
			
			audioEngine.seek(jSlider1.getValue());
		}
        }//GEN-LAST:event_jSlider1StateChanged

        private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
		
		audioEngine.setVolume(jSlider2.getValue());
        }//GEN-LAST:event_jSlider2StateChanged

        private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
		
		int result = jFileChooser1.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			
			File[] files = jFileChooser1.getSelectedFiles();
			
			openFiles(files);
		}
        }//GEN-LAST:event_openFileActionPerformed

        private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
		
		if (evt.getClickCount() == 2) {
			
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			int rowNum = jTable1.getSelectedRow();
			String filePath = (String) model.getValueAt(rowNum, 5);
			AudioType aType = checkAudioType(filePath);
			this.currentRow = rowNum;
			this.numRows = jTable1.getRowCount() - 1;

			audioEngine.addSongListener(new SongListener() {
				
				@Override
				public void onSongEnded() {
					
					if (currentRow < numRows) {
						
						currentRow++;
						
						String nextFilePath = (String) model.getValueAt(currentRow, 5);
						AudioType nAType = checkAudioType(nextFilePath);

						System.out.println("Play file: "+ nextFilePath);
						
						//  play module
						if (aType == AudioType.MODULE) {
							
							audioEngine.loadFile(nextFilePath, AudioType.MODULE);
							audioEngine.setVolume(jSlider2.getValue());
							playTrack();
							
						//play streamed audio file
						} else if (aType == AudioType.STREAMED) {
							
							audioEngine.loadFile(nextFilePath, AudioType.STREAMED);
							audioEngine.setVolume(jSlider2.getValue());
							playTrack();
						}
					
					} else {
					
						System.out.println("Last track in playlist. No more songs to play");
					}
				}
			});
			
			//  play module
			if (aType == AudioType.MODULE) {
				
				audioEngine.loadFile(filePath, AudioType.MODULE);
				audioEngine.setVolume(jSlider2.getValue());
				this.playTrack();
				
			//play streamed audio file
			} else if (aType == AudioType.STREAMED) {
				
				audioEngine.loadFile(filePath, AudioType.STREAMED);
				audioEngine.setVolume(jSlider2.getValue());
				this.playTrack();
			}
		}
        }//GEN-LAST:event_jTable1MouseClicked
	
	public String formatTime(long totalSeconds) {
		
		Duration d = Duration.ofSeconds(totalSeconds);
		long h = d.toHours();
		long m = d.toMinutesPart();
		long s = d.toSecondsPart();
    
		if (h > 0) {
			
			return String.format("%d:%02d:%02d", h, m, s);
		}
		
		return String.format("%02d:%02d", m, s);
	}

	public void openFiles(File[] files) {
		
		DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
		
		for (File file : files) {
			
			if (file.isFile()) {
				
				AudioType aType = checkAudioType(file.getName());
				numRows++;
				
				if (aType == AudioType.MODULE) {
					
					audioEngine.loadFile(file.getAbsolutePath(), aType);
					long len = audioEngine.getLength();
					double time = audioEngine.lengthInSeconds(len);
					int pos = file.getName().lastIndexOf(".");
					String title2 = file.getName().substring(0, pos);

					String title1 = audioEngine.getModuleTitle();
					String artist = audioEngine.getModuleAuthor();
					
					model.addRow(new Object[]{"", artist, "", title1, formatTime((long) time), file.getAbsolutePath()});

				} else if (aType == AudioType.STREAMED) {

					try {
					
						// 1. Read the file (this parses the headers)
						AudioFile f = AudioFileIO.read(file);
						    
						// 2. Access the Metadata "Tag"
						Tag tag = f.getTag();
						    
						// 3. Extract the data using FieldKeys
						String track = tag.getFirst(FieldKey.TRACK);
						String artist = tag.getFirst(FieldKey.ARTIST);
						String title  = tag.getFirst(FieldKey.TITLE);
						String album  = tag.getFirst(FieldKey.ALBUM);
						String year = tag.getFirst(FieldKey.YEAR);
			    
						// 4. Get the duration (handy for your playlist table!)
						String length = formatTime(f.getAudioHeader().getTrackLength());
						model.addRow(new Object[]{track, artist, album, title, length, file.getAbsolutePath()});

					} catch (Exception ex) {

						System.err.println("JAudioTagger could not read file : " + ex.getMessage());
					}
				}
			}
		}
	}
	
	private void saveTabPlaylist(JTable table) {
		
		String dest = System.getProperty("user.home") + File.separator + "pl.m3u";
		Path path = Paths.get(dest);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			
			// Loop through rows
			for (int i = 0; i < table.getRowCount(); i++) {

				//convert the jtable row index on the screen to the model index in the jtable
				int modelRowIndex = table.convertRowIndexToModel(i);
				
				Object value = model.getValueAt(modelRowIndex, 5);
				System.out.println(value);
				writer.write(String.valueOf(value));
				writer.newLine();
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private File[] loadPlaylist() {
		
		ArrayList<File> f = new ArrayList<>();
		
		try {
			Path path = Paths.get(System.getProperty("user.home"), "pl.m3u");
			    
			// Reads every line into a List
			List<String> lines = Files.readAllLines(path);
			    
			for (String line : lines) {
			
				// Here you would split the line and add it back to your JTable model
				System.out.println(line);
				f.add(new File(line));
			}

		} catch (IOException e) {
		
			e.printStackTrace();
		}

		return f.toArray(new File[0]);
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JMenu fileMenu;
        private javax.swing.JFileChooser jFileChooser1;
        private JMediaButton jMediaButton1;
        private JMediaButton jMediaButton2;
        private JMediaButton jMediaButton3;
        private JMediaButton jMediaButton4;
        private javax.swing.JMenu jMenu2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JSlider jSlider1;
        private javax.swing.JSlider jSlider2;
        private javax.swing.JTabbedPane jTabbedPane1;
        private javax.swing.JTable jTable1;
        private javax.swing.JToolBar jToolBar1;
        private javax.swing.JMenuBar menuBar;
        private javax.swing.JMenuItem openFile;
        private javax.swing.JTextField searchTextField;
        // End of variables declaration//GEN-END:variables
}
