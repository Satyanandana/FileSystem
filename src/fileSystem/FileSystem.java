package fileSystem;

public class FileSystem {

	private Directory root = new Directory(null, "root", "Satya");
	private static FileSystem fileSystem = null;
	private FileSystem() {

	}

	public static FileSystem getfileSystem(){
		if (fileSystem == null) {
			fileSystem = new FileSystem();
		}
		return fileSystem;
	}
	public void showAllElements(Directory d){
		
		System.out.println(d.getType()+"  "+d.getName()+"   "+d.getOwner()+"   "+d.getCreated()+"   "+d.getSize());
		System.out.println("************************************************ Children of "+d.getName()+" ************************************ ");

		for(FSElement fs :d.getChildren()){
			if(!fs.isLeaf()){
				Directory d1 = (Directory) fs;
				this.showAllElements(d1);

			}else{
				System.out.println(fs.getType()+"  "+fs.getName()+"   "+fs.getOwner()+"   "+fs.getCreated()+"   "+fs.getSize());	
			}

		}
		System.out.println("******************************************************* End of "+d.getName()+" ******************************************");
        

	}

	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}



}
