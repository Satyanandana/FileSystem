package fileSystem;

public class CountingVisitor implements FSVisitor {

	private int dirNum=0;
	private int fileNum=0;
	private int	linkNum=0;
	
	public int getDirNum() {
		return dirNum;
	}

	public int getFileNum() {
		return fileNum;
	}

	public int getLinkNum() {
		return linkNum;
	}

	@Override
	public void visit(Directory directory) {
		dirNum ++;
	}

	@Override
	public void visit(Link link) {
		
		linkNum ++;
	}

	@Override
	public void visit(File file) {
		
		fileNum ++;
	}

}
