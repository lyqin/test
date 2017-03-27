package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

public class JarUtils {
	
	static List<String> jarFileList = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		String path = JOptionPane.showInputDialog("请输入要查找的目录(如d:\\新建文件夹)");
		if (path == null || path.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "输入的内容为空");
			return;
		}
		String filename = JOptionPane.showInputDialog("请输入要查找的class名称");
		if (filename == null || filename.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "要查找的class名称");
			return;
		}
		JarUtils jarUtil = new JarUtils();

		System.out.println("开始查找...");
		jarUtil.getJarFile(path);
		try {
			jarUtil.listJarFile(jarFileList, filename);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.out.println("查找结束...");
	}
	
	public void listJarFile (List<String> list,String name) throws IOException {
		if (list == null || list.size() ==0)
			return;
		for (String filename : list) {
			listJarFile(filename, name);
		}
	}
	
	public void getJarFile (String path) {
		File f = new File(path);
		if (f.isFile()) {
			if (f.getName().endsWith(".jar")) {
				jarFileList.add(f.getAbsolutePath());
			}
		} else {
			File[] files = f.listFiles();
			for (File file : files) {
				if (file == null)
					continue;
				getJarFile (file.getPath());
			}
		}
		
	}
	
	public void listJarFile (String path, String name) throws IOException {
		JarFile jarFile = new JarFile(path);
		Enumeration<JarEntry> jarEntrys = jarFile.entries();
		while (jarEntrys.hasMoreElements()) {
			JarEntry jarEntry = jarEntrys.nextElement();
			String jarFileName = jarEntry.getName();
			if (jarFileName.contains(name))
				System.out.println(jarFileName);
		}
	}
	

}
