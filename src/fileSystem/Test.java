package fileSystem;

public class Test {

	public static void main(String[] args) {
		
		FileSystem fileSystem = FileSystem.getfileSystem();
		Directory root = fileSystem.getRoot();
		
		Directory system = new Directory(root, "system", "satya");
		File a = new File(system, "a.txt", "satya", 30);
		File b = new File(system, "b.pdf", "satya", 60);
		File c = new File(system, "c.txt", "satya", 80);
		Directory home = new Directory(root, "home", "satya");
		File d = new File(home, "d.pdf", "satya", 45);
		Link x = new Link(home, system, "Link to system directory", "satya");
		Directory picture = new Directory(home, "picture", "satya");
		File e = new File(picture, "e.pdf", "satya", 45);
		File f = new File(picture, "f.txt", "satya", 65);
		Link y = new Link(picture, e, "Link to file e", "satya");
		Link z = new Link(picture, x, "Link to link x", "satya");
		
		System.out.println("Size of root directory is "+root.getSize());
		System.out.println("");
		
		fileSystem.showAllElements(root);
		SizeCountingVisitor visitor1 = new SizeCountingVisitor();
		root.accept(visitor1);
		System.out.println("Total size is " + visitor1.getTotalSize() );
		
		CountingVisitor visitor2 = new CountingVisitor();
		root.accept(visitor2);
		System.out.println("Number of Directories "+visitor2.getDirNum());
		System.out.println("Number of Links "+visitor2.getLinkNum());
		System.out.println("Number of Files "+visitor2.getFileNum());
		
		FileSearchVisitor visitor3 = new FileSearchVisitor(".pdf");
		root.accept(visitor3);
		System.out.println("Number of files found with extension .pdf are " + visitor3.getFoundFiles().size());
	    int i=1;
		for(File file : visitor3.getFoundFiles()){	
	    	System.out.println(i+") "+file.getName());
	    	i++;
	    }
		
	}

}
