package com.example.smartkitchen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.smartkitchen.FileUtils.getPath;


public class Video_add extends Activity {
	EditText e1;
	Button b1;
	ImageButton b2;
	TextView t1;
	public static String perm="OK";
	
	String fname="";
    String actualfilepath= "";

    private static final int FILE_SELECT_CODE1 = 0;
//    public static byte[] bb;
//    String img_str="Null";
//    String flname = null;
	
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_add);
		try
    	{
    		if (android.os.Build.VERSION.SDK_INT > 9) 
    		{
    			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    			StrictMode.setThreadPolicy(policy);
    		}
    	}
    	catch(Exception e)
    	{
    		 
    	}
		
		
		 	e1=(EditText)findViewById(R.id.editText1 );
		 	
		 	t1=(TextView)findViewById(R.id.textView5);

	        
			b1=(Button)findViewById(R.id.button1);
			b2=(ImageButton)findViewById(R.id.imageButton1);
			
			b1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					showFileChooser();
				}
			});
			
			b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					String vn = e1.getText().toString();
					if (vn.length() > 50) {
						e1.setText("");
						e1.setError("Length too long...");
					}
					
					if(!e1.getText().toString().equals(""))
					{
					
						new savejson().execute();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Fill", 3).show();
					}
				}
			});
	}

	
	private static final int FILE_SELECT_CODE = 0;
	private static final String RESULT_PICTURE_PATH = null;

	private void showFileChooser() {
	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
	    intent.setType("*/*"); 
	    intent.addCategory(Intent.CATEGORY_OPENABLE);

	    try {
	        startActivityForResult(
	                Intent.createChooser(intent, "Select a File to Upload"),
	                FILE_SELECT_CODE1);
	    } catch (android.content.ActivityNotFoundException ex) {
	        // Potentially direct the user to the Market with a Dialog
	        Toast.makeText(this, "Please install a File Manager.", 
	                Toast.LENGTH_SHORT).show();
	    }
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {
	        case FILE_SELECT_CODE1:
	        if (resultCode == RESULT_OK) {
	            // Get the Uri of the selected file 
	            Uri uri = data.getData();
//	            Log.d(TAG, "File Uri: " + uri.toString());
	            // Get the path
	            actualfilepath= "";
	            
	            try {
	            	
	            	actualfilepath=getRealPathFromDocumentUri(this,uri);
	            	fname=actualfilepath;
					
					
//					String[] spfile=actualfilepath.split("/");
//					
					t1.setText(actualfilepath);
					
//					Toast.makeText(getApplicationContext(), spfile[spfile.length-1], 5).show();
	            	
					Toast.makeText(getApplicationContext(), actualfilepath, 5).show();
					
					doFileUpload();
//					Sendfile(actualfilepath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "err "+e.getMessage(), 5).show();
					e.printStackTrace();
				}
//	            Log.d(TAG, "File Path: " + path);
	            // Get the file instance
	            // File file = new File(path);
	            // Initiate the upload
	        }
	        break;
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void doFileUpload() {

	    HttpURLConnection conn = null;
	    DataOutputStream dos = null;
	    DataInputStream inStream = null;
	    String existingFileName = fname;// Environment.getExternalStorageDirectory().getAbsolutePath() + "/mypic.png";
	    String lineEnd = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "*****";
	    int bytesRead, bytesAvailable, bufferSize;
	    byte[] buffer;
	    int maxBufferSize = 1 * 1024 * 1024;
	    String responseFromServer = "";
//	    String urlString = "http://192.168.1.15/fileup/fileup.php";
	    String urlString = "http://192.168.43.159:8000/comment/up/";
	    

	    try {

	        //------------------ CLIENT REQUEST
	        FileInputStream fileInputStream = new FileInputStream(new File(existingFileName));
	        // open a URL connection to the Servlet
	        URL url = new URL(urlString);
	        // Open a HTTP connection to the URL
	        conn = (HttpURLConnection) url.openConnection();
	        // Allow Inputs
	        conn.setDoInput(true);
	        // Allow Outputs
	        conn.setDoOutput(true);
	        // Don't use a cached copy.
	        conn.setUseCaches(false);
	        // Use a post method.
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Connection", "Keep-Alive");
	        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	        dos = new DataOutputStream(conn.getOutputStream());
	        dos.writeBytes(twoHyphens + boundary + lineEnd);
	        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + existingFileName + "\"" + lineEnd);
	        dos.writeBytes(lineEnd);
	        // create a buffer of maximum size
	        bytesAvailable = fileInputStream.available();
	        bufferSize = Math.min(bytesAvailable, maxBufferSize);
	        buffer = new byte[bufferSize];
	        // read file and write it into form...
	        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

	        while (bytesRead > 0) {

	            dos.write(buffer, 0, bufferSize);
	            bytesAvailable = fileInputStream.available();
	            bufferSize = Math.min(bytesAvailable, maxBufferSize);
	            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

	        }

	        // send multipart form data necesssary after file data...
	        dos.writeBytes(lineEnd);
	        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	        // close streams
	        Log.e("Debug", "File is written");
	        fileInputStream.close();
	        dos.flush();
	        dos.close();

	    } catch (MalformedURLException ex) {
	        Log.e("Debug", "error: " + ex.getMessage(), ex);
	    } catch (IOException ioe) {
	        Log.e("Debug", "error: " + ioe.getMessage(), ioe);
	    }

	    //------------------ read the SERVER RESPONSE
	    try {

	        inStream = new DataInputStream(conn.getInputStream());
	        String str;

	        while ((str = inStream.readLine()) != null) {

	            Log.e("Debug", "Server Response " + str);

	        }

	        inStream.close();

	    } catch (IOException ioex) {
	        Log.e("Debug", "error: " + ioex.getMessage(), ioex);
	    }
	}
	
	public String getRealPathFromDocumentUri(Context context, Uri uri){
	    String filePath = "";

	    Pattern p = Pattern.compile("(\\d+)$");
	    Matcher m = p.matcher(uri.toString());
	    if (!m.find()) {
	        //Log.e(ImageConverter.class.getSimpleName(), "ID for requested image not found: " + uri.toString());
	        return filePath;
	    }
	    
	    String imgId = m.group();

//	    String[] column = { MediaStore.Images.Media.DATA };
//	    String sel = MediaStore.Images.Media._ID + "=?";
//
//	    Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//	            column, sel, new String[]{ imgId }, null);
//
//	    int columnIndex = cursor.getColumnIndex(column[0]);
//
//	    if (cursor.moveToFirst()) {
//	        filePath = cursor.getString(columnIndex);
//	    }
//	    cursor.close();

	    String[] column = { MediaStore.Video.Media.DATA };
	    String sel = MediaStore.Video.Media._ID + "=?";

	    Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
	            column, sel, new String[]{ imgId }, null);

	    int columnIndex = cursor.getColumnIndex(column[0]);

	    if (cursor.moveToFirst()) {
	        filePath = cursor.getString(columnIndex);
	    }
	    cursor.close();

	    
	    return filePath;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 private class savejson extends AsyncTask<Void, Void, Void>{
			private static final boolean True = false;

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub

				String url = Login.url+"comment/android1/";
			
				JSONObject jobj= new JSONObject();
		        try {
		        	
		        	jobj.put("uid",Login.uid);
		        	jobj.put("cname",e1.getText().toString());
		        	jobj.put("video",t1.getText().toString());
		        	
		        	
		        	String s=JsonHandler.Postjson(url, jobj);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null; 
			}
			
			@Override
		      protected void onPostExecute(Void result) {
		         super.onPostExecute(result);  
		         if(!perm.equals("error"))
		         {
		        	 
		        	 Toast.makeText(getApplicationContext(), "Video successfully added...", 3).show();
		        	 Intent ii=new Intent(getApplicationContext(),Video_list.class);
						startActivity(ii);
		        	 
		        	 
		         }
		         else
		         {
		        	  
		        	 Toast.makeText(getApplicationContext(), "Failed", 3).show();
		         }
		      }
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_add, menu);
		return true;
	}

	
	 
	
	
	
	
	
	
	
	
	
//	
//	@SuppressWarnings("static-access")
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//
//		// Toast.makeText(getApplicationContext(), data.toString(),
//		// Toast.LENGTH_LONG).show();
//
//		
//		filetobyte fb = new filetobyte();
//		// do somthing...
//		Uri uri1 = data.getData();
//		// File file = new File(uri.getPath());
//		String path = "none";
//		//path = getPath(this, uri);
//		try {
//			path = getPath(this, uri1);
//			//path = getPath(this, uri1);
//
//			// File myFile = new File(uri1.getPath());
//			// path=myFile.getAbsolutePath();
//
//		} catch (Exception ae) {
//			Toast.makeText(getApplicationContext(), "Error3", Toast.LENGTH_LONG)
//					.show();
//		}
//		Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
//
//		
//		
//		File file = new File(path);
//        try {
//            bb = fb.readBytesFromFile(file);
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//        if (path != null) {
//
//            int cut = path.lastIndexOf('/');
//            if (cut != -1) {
//                flname = path.substring(cut + 1);
//                t1.setText(flname);
//            }
//
//        }
//        try {
//            img_str = Base64.encodeToString(bb, 0);
//        } catch (Exception ae) {
//            img_str = "Null";
//        }
//        if (img_str.equals("Null")) {
//            Toast.makeText(getApplicationContext(), "Upload Failed..!", Toast.LENGTH_LONG).show();
//        } else {
//        }
//       
//	}
			
}
