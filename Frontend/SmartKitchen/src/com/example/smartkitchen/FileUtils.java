package com.example.smartkitchen;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.net.URISyntaxException;

/**
 * Created by PC1 on 12-Apr-16.
 */
public class FileUtils {

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);

                }
            } catch (Exception e) {
                // Eat it
                return "error2";
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
            //return "fault";
        }

        return null;
    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception ae) {
            return "Big Error";
        } finally {
            if (cursor != null) {
                cursor.close();

            }
        }
    }


}
