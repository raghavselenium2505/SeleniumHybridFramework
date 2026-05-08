/*
 * package com.seleapi.ai;
 * 
 * import java.io.FileInputStream; import java.util.Properties;
 * 
 * import com.google.cloud.texttospeech.v1.AudioConfig; import
 * com.google.cloud.texttospeech.v1.AudioEncoding; import
 * com.google.cloud.texttospeech.v1.SynthesisInput; import
 * com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse; import
 * com.google.cloud.texttospeech.v1.TextToSpeechClient; import
 * com.google.cloud.texttospeech.v1.VoiceSelectionParams; import
 * com.google.protobuf.ByteString;
 * 
 * import javax.sound.sampled.*;
 * 
 * import java.io.ByteArrayInputStream;
 * 
 * public class VoiceManagerUtil {
 * 
 * public static void speak(String message) {
 * 
 * try {
 * 
 * Properties prop = new Properties();
 * 
 * prop.load( new FileInputStream(
 * "src/test/resources/properties/config.properties"));
 * 
 * String voiceEnabled = prop.getProperty( "voiceEnabled", "N");
 * 
 * // ========================================= // VOICE DISABLED //
 * =========================================
 * 
 * if (!voiceEnabled.equalsIgnoreCase("Y")) {
 * 
 * return; }
 * 
 * // ========================================= // GOOGLE TTS //
 * =========================================
 * 
 * try (TextToSpeechClient client = TextToSpeechClient.create()) {
 * 
 * SynthesisInput input = SynthesisInput.newBuilder() .setText(message)
 * .build();
 * 
 * VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
 * .setLanguageCode("en-US") .setName("en-US-Wavenet-D") .build();
 * 
 * AudioConfig audioConfig = AudioConfig.newBuilder() .setAudioEncoding(
 * AudioEncoding.LINEAR16) .build();
 * 
 * SynthesizeSpeechResponse response = client.synthesizeSpeech( input, voice,
 * audioConfig);
 * 
 * ByteString audioContents = response.getAudioContent();
 * 
 * playAudio( audioContents.toByteArray()); }
 * 
 * }
 * 
 * catch (Exception e) {
 * 
 * System.out.println( "Voice Error: " + e.getMessage()); } }
 * 
 * // ========================================= // PLAY AUDIO //
 * =========================================
 * 
 * private static void playAudio( byte[] audioData) {
 * 
 * try {
 * 
 * AudioFormat format = new AudioFormat( 24000, 16, 1, true, false);
 * 
 * AudioInputStream ais = new AudioInputStream( new
 * ByteArrayInputStream(audioData), format, audioData.length / 2);
 * 
 * Clip clip = AudioSystem.getClip();
 * 
 * clip.open(ais);
 * 
 * clip.start();
 * 
 * Thread.sleep( clip.getMicrosecondLength() / 1000);
 * 
 * clip.close(); }
 * 
 * catch (Exception e) {
 * 
 * System.out.println( "Audio Playback Error: " + e.getMessage()); } } }
 */