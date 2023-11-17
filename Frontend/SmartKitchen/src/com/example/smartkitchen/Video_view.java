package com.example.smartkitchen;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Video_view extends Activity {
	TextView t1,t2,t3,t4;
	Button b1;
	VideoView v1;
	String vname,vdate,vtime,uname,vid,uid,video;
	
	// Declare variables
		ProgressDialog pDialog;
		VideoView videoview;
		
		// Insert your Video URL
//		String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
		
		String VideoURL;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_view);
		
		t1=(TextView)findViewById(R.id.textView2);
		t2=(TextView)findViewById(R.id.textView3);
		t3=(TextView)findViewById(R.id.textView5);
		t4=(TextView)findViewById(R.id.textView4);
		
		v1=(VideoView)findViewById(R.id.videoView1);
		b1=(Button)findViewById(R.id.button1);
		
		
		
		
		vname=getIntent().getStringExtra("cname");
		vdate=getIntent().getStringExtra("vdate");
		vtime=getIntent().getStringExtra("vtime");
		uid=getIntent().getStringExtra("uid");
		vid=getIntent().getStringExtra("cid");
		uname=getIntent().getStringExtra("uname");
		video=getIntent().getStringExtra("video");
		
		
		Toast.makeText(getApplicationContext(), vname, 3).show();
		
		t1.setText(vname);
		t2.setText(vdate);
		t3.setText(vtime);
		t4.setText(uname);
		
		VideoURL = Login.url+"static/"+video;
		
//		Toast.makeText(getApplicationContext(), VideoURL, 3).show();
		
		videoview = (VideoView) findViewById(R.id.videoView1);
		// Execute StreamVideo AsyncTask
 
		// Create a progressbar
		pDialog = new ProgressDialog(Video_view.this);
		// Set progressbar title
		pDialog.setTitle("Videoooo");
		// Set progressbar message
		pDialog.setMessage("Buffering...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		// Show progressbar
		pDialog.show();
 
		try {
			// Start the MediaController
			MediaController mediacontroller = new MediaController(
					Video_view.this);
			mediacontroller.setAnchorView(videoview);
			// Get the URL from String VideoURL
			Uri video = Uri.parse(VideoURL);
			videoview.setMediaController(mediacontroller);
			videoview.setVideoURI(video);
 
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
 
		videoview.requestFocus();
		videoview.setOnPreparedListener(new OnPreparedListener() {
			// Close the progress bar and play the video
			public void onPrepared(MediaPlayer mp) {
				pDialog.dismiss();
				videoview.start();
			}
		});
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i= new Intent(getApplicationContext(),Video_comment_list.class);
				i.putExtra("cid",vid);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_view, menu);
		return true;
	}

}
