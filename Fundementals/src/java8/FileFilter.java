package java8;

import java.io.File;
import java.io.FilenameFilter;

@FunctionalInterface
public interface FileFilter extends FilenameFilter {
	public boolean equals(Object o);
	
	
}
