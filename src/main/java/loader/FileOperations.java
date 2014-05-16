package loader;

import java.io.File;

public final class FileOperations {
	
	public static String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}

	public static String getName(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(0, i).toLowerCase();
			}
		}
		return null;
	}
	
	public static boolean isTempFile(File f){
		String extension = getExtension(f);
		if( extension.equals("tmp_ptfs")  ){
			return true;
		}		
		return false;
	}

}
