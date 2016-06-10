package fileSystem;

import java.util.ArrayList;

public class Directory extends FSElement {
	
	private ArrayList<FSElement> children = new ArrayList<FSElement>();
	static FileSystem fileSystem = FileSystem.getfileSystem();

	public Directory(Directory parent, String name, String owner) {
		super(parent, name, owner, 0);
		this.type = "Directory";	
	}
	
	public ArrayList<FSElement> getChildren(){
		return children;
		} 
	public void appendChild(FSElement fsElement){
		children.add(fsElement);
	}

	@Override
	public int getSize() {
		int size = 0;
		for(FSElement fs : children){
			if(!fs.getType().equals("Link")){
				size += fs.getSize();
				}
		}
		return size;
	}

	@Override
	public boolean isLeaf() {
		boolean isLeaf = false;
		if(children.isEmpty()){
			isLeaf = true;
		}
		return isLeaf;
	}

	@Override
	public String getType() {
			return this.type;
	}

	@Override
	public int getDiskUtil() {
		return 0;
	}

	@Override
	public void accept(FSVisitor v) {
		v.visit(this);
		for(FSElement e: children){
		e.accept(v);
		}
		}

}
