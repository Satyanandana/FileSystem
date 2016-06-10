package fileSystem;

import java.util.ArrayList;

public class FileSearchVisitor implements FSVisitor {
	private String extention;
	private ArrayList<File> foundFiles = new ArrayList<File>();
	
	public FileSearchVisitor(String extention) {
		this.extention = extention;
	}

	@Override
	public void visit(Directory directory) {
		
	}

	@Override
	public void visit(Link link) {
		
	}

	@Override
	public void visit(File file) {
		if(file.getName().contains(extention)){
			foundFiles.add(file);
		}
	}

	public ArrayList<File> getFoundFiles() {
		return foundFiles;
	}
	
	

}
